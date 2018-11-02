<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
%>
<%@ include file="../common/taglib.jsp"%>
<link href="<%=request.getContextPath()%>/css/style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
	function exportExcel(year,month,dept){
		var info="condition.year="+year+"&condition.month="+month;
		$.post("exportExcel.action",info,function(result){
			alert(result);
		});
	}
	
</script>
<div class="action  divaction">
	<div class="t">月度统计详情</div>
	<div class="pages">
		<s:form action="compMonStatistics_getDetailExcel.action"
			name="queryForm">
			<label for="time">年份:</label>
			<s:property value="condition.year" />
			<label for="end-time">月份:</label>
			<s:property value="condition.month" />
			<%-- <s:hidden name="year" value="%{year}" />
			<s:hidden name="currMonth" value="%{currMonth}" /> --%>
			<input type="button" class="submit_01" onclick="exportExcel(<s:property value="condition.year" />,<s:property value="condition.month" />)" value="导出报表" />
			<%-- <s:submit cssClass="submit_01" value="导出报表" /> --%>
		</s:form>
		<table width="90%" border="0" cellspacing="0" cellpadding="0"
			class="list items">
			<tr class="even">
				<td>部门编号</td>
				<td>部门</td>
				<td>报销总额</td>
				<td>年份</td>
				<td>月份</td>
			</tr>
			<s:set name="totalCount" value="0"/>
			<s:iterator value="#session.compMonth.list" id="deptClaimVoucher" begin="0"
				status="s">
				<tr>
					<td><s:property value="#deptClaimVoucher.sysDepartment.id" /></td>
					<td><s:property value="#deptClaimVoucher.sysDepartment.name" /></td>
					<td>￥<s:property value="#deptClaimVoucher.totalCount" /></td>
					<td><s:property value="#deptClaimVoucher.year" />年</td>
					<td><s:property value="#deptClaimVoucher.month" />月</td>
					<s:set name="totalCount" value="#totalCount+#deptClaimVoucher.totalCount"></s:set>
				</tr>
			</s:iterator>
			<tr>
				<td></td>
				<td bgcolor="yellow">总计</td>
				<td bgcolor="yellow">￥<s:property value="#totalCount" /></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>


		</table>
	</div>
	<span style="display:none;"><iframe id="downloadIframe" src=""
			style="width:0px;height:0px;"></iframe></span>
	<!--增加报销单 区域 结束-->
</div>
<%-- <div style="">
	<center>
		<img src="<%=path%>/jsp/statistics/compMonStatistics_getDetailChart.action?year=<%=request.getAttribute("year")%>&currMonth=<%=request.getAttribute("currMonth")%>"/>
	</center>

</div> --%>
