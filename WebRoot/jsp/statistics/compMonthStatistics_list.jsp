<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<%@ include file="../common/taglib.jsp"%>
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css" />
<div class="action  divaction">
	<div class="t">月度统计列表</div>
	<div class="pages">
	     <s:form action="searchBizClaimVoucherStatistics.action" method="post" name="queryForm">
	     		<label for="time">年份</label>
	     		<s:select name="condition.year" list="#{2013:'2013',2014:'2014',2015:'2015',2016:'2016',2017:'2017'}" 
	  			listKey="key" listValue="value" theme="simple"></s:select>
	       		<label for="time">开始月份</label>
	       		<s:select name="condition.startMonth" list="#{1:'1月份',2:'2月份',3:'3月份',4:'4月份',5:'5月份',6:'6月份',7:'7月份',8:'8月份',9:'9月份',10:'10月份',11:'11月份',12:'12月份' }" 
	  			listKey="key" listValue="value" headerKey="0" headerValue="无" theme="simple"></s:select>
	       		<label for="end-time">结束月份</label>
	       		<s:select name="condition.endMonth" list="#{1:'1月份',2:'2月份',3:'3月份',4:'4月份',5:'5月份',6:'6月份',7:'7月份',8:'8月份',9:'9月份',10:'10月份',11:'11月份',12:'12月份' }" 
	  			listKey="key" listValue="value" headerKey="0" headerValue="无" theme="simple"></s:select>
		 	    <!-- <input type="hidden" name="year" value="2013"/> -->
		 	    <input type="hidden" name="condition.pageIndex" value="1"/>
		 	    <input type="hidden" name="condition.pageSize" value="5"/>
		 	    <input type="hidden" name="group" value="true"/>
		        <s:submit cssClass="submit_01" value="查询"/>
	     </s:form>
	    
		<table width="90%" border="0" cellspacing="0" cellpadding="0" class="list items">
	      <tr class="even">
	        <td>编号</td>
	        <td>总计</td>
	        <td>年份</td>
	        <td>月份</td>
	        <!-- <td>部门</td> -->
	        <td>操作</td>
	      </tr>
	      <s:iterator value="#session.compMonth.list" id="claimVoucherStatistics" begin="0" status="s">
	      <tr>
	        <td><s:property value="#s.index+1"/></td>
	        <td>￥<s:property value="#claimVoucherStatistics.totalCount"/></td>
	        <td><s:property value="#claimVoucherStatistics.year"/>年</td>
	        <td><s:property value="#claimVoucherStatistics.month"/>月</td>
	        <%-- <td><s:property value="#claimVoucherStatistics.department.name"/></td> --%>
	        <td>
	        <%-- <a href="claimVoucher_getClaimVoucherById.action?claimVoucher.id=<s:property value="#claimVoucher.id"/>">
	        	<img src="${ images}/search.gif" width="16" height="15" />
	        </a> --%>
	       <a href="searchBizClaimVoucherStatistics.action?condition.year=<s:property value="#claimVoucherStatistics.year"/>&condition.month=<s:property value="#claimVoucherStatistics.month"/>">
	        <!-- <a href="claimVoucherStatistics_getDeptVoucherDetailByMonth.action?year=2013&selectMonth=7&departmentId=2"> -->
	        <img src="${images}/search.gif" width="16" height="15" />
	        </a>
	        </td>
	      </tr>
	      </s:iterator>
	      <tr>
	        <td colspan="6" algin="center">
		      	<c:import url="../common/rollPage.jsp" charEncoding="UTF-8">
				<c:param name="formName" value="document.forms[0]"/>
				<c:param name="totalCount" value="${compMonth.totalCount}"/>
				<c:param name="pageTotalCount" value="${compMonth.pageTotalCount}"/>
				<c:param name="pageIndex" value="${compMonth.pageIndex}"/>
  			</c:import> 
  			</td>
	      </tr>
	    </table>        
       </div>
      </div>