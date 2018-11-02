<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<%
String path = request.getContextPath();
%>
<%@ include file="../common/taglib.jsp"%>
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
	<%-- $(function(){
		$('#chart_img').attr('src','claimVoucherStatistics_createDetailChart.action?year=2013&month=7');
	});
	
	year = <%=request.getAttribute("year")%>;
	selectMonth = <%=request.getAttribute("selectMonth")%>;
	action = "claimVoucherStatistics_createDetailChart.action?year="+year+"&selectMonth="+selectMonth;
	document.getElementById("chart_img").setAttribute("src", action); --%>
	
	function exportExcel(year,month,dept){
		var info="condition.year="+year+"&condition.month="+month+"&condition.dept="+dept;
		$.post("exportExcel.action",info,function(result){
			alert(result);
		});
	}
	
</script>

<div class="action  divaction">
	<div class="t">月度统计详情</div>
	<div class="pages">
		<s:form action="return false;"
			name="queryForm">
			<label for="time">年份:</label>
			<s:property value="condition.year" />
			<label for="end-time">月份:</label>
			<s:property value="condition.month" />
			<%-- <s:hidden name="condition.year" value="%{condition.year}" />
			<s:hidden name="condition.month" value="%{condition.month}" />
			<s:hidden name="condition.dept" value="%{condition.dept}" /> --%>
			<%-- <s:submit cssClass="submit_01" onclick="exportExcel(<s:property value="condition.year" />,<s:property value="condition.month" />,<s:property value="condition.dept" />)" value="导出报表" /> --%>
			<input type="button" class="submit_01" onclick="exportExcel(<s:property value="condition.year" />,<s:property value="condition.month" />,<s:property value="condition.dept" />)" value="导出报表" />
		</s:form>

		<table width="90%" border="0" cellspacing="0" cellpadding="0"
			class="list items">
			<tr class="even">
				<td>编号</td>
				<td>报销人</td>
				<td>报销总额</td>
				<td>年份</td>
				<td>月份</td>
				<td>部门</td>
			</tr>
			<s:set name="detailCount" value="0"/>
			<s:iterator value="#session.claimVoucherList.list" id="claimVoucher"
				begin="0" status="s">
				<tr>
					<td><s:property value="#claimVoucher.sysEmployeeByCreateSn.sn" /></td>
					<td><s:property
							value="#claimVoucher.sysEmployeeByCreateSn.name" /></td>
					<td>￥<s:property value="#claimVoucher.totalAccount" /></td>
					<td><s:property value="condition.year" />年</td>
					<td><s:property value="condition.month" />月</td>
					<td><s:property
							value="#claimVoucher.sysEmployeeByCreateSn.sysDepartment.name" /></td>
					<s:set name="detailCount" value="#detailCount+#claimVoucher.totalAccount"/>
				</tr>
			</s:iterator>
			<tr>
				<td></td>
				<td bgcolor="yellow">总计</td>
				<td bgcolor="yellow">￥<s:property value="#detailCount" /></td>
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
       	  <center><img src="<%=path%>/jsp/statistics/claimVoucherStatistics_createDetailChart.action?year=<%=request.getAttribute("year")%>&amp;selectMonth=<%=request.getAttribute("selectMonth")%>&amp;departmentId=<%=request.getAttribute("departmentId")%>"></center>
       </div> --%>
      