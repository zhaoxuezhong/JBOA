<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script>
	$(function(){
			 //日期选择控件
		 	$("#startDate").click(function(){
				WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:true, readOnly:true });
			});
			$("#endDate").click(function(){ 
				WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}',isShowClear:true, readOnly:true });
			});
		});
   	function delVoucher(id){
   		if(!confirm('确定删除报单吗')) return;
   		document.claimVoucherForm.action = "claimvoucher_deleteBizClaimVoucher.action?claimVoucher.id="+id;
   		document.claimVoucherForm.submit();
   	}
   	
</script>
<div class="action  divaction">
	<div class="t">报销单列表</div>
	<div class="pages">
		<div class="forms">
			 <s:form action="searchBizClaimVoucher.action" name="queryForm">
	       		<label>报销单状态</label>
	  			<s:select name="condition.status" list="#{'新创建':'新创建','已提交':'已提交','待审批':'待审批','已审批':'已审批','已打回':'已打回','已付款':'已付款','已终止':'已终止'}" 
	  			headerKey="" headerValue="全部" theme="simple"></s:select>
		       <label for="time">开始时间</label>
		       <%-- <s:textfield name="condition.startTime" value="''" id="startDate" cssClass="nwinput"></s:textfield> --%>
		       <input type="text" name="condition.startTime" value="<s:date name="condition.startTime" format="yyyy-MM-dd"/>" id="startDate" class="nwinput">
		       <label for="end-time">结束时间</label>
		       <%-- <s:textfield name="condition.endTime" value="''" id="endDate" cssClass="nwinput"></s:textfield> --%>
		       <input type="text" name="condition.endTime" value="<s:date name="condition.endTime" format="yyyy-MM-dd"/>" id="endDate" class="nwinput">
		       <input type="hidden" name="condition.pageIndex" value="1"/>
		 	   <input type="hidden" name="condition.pageSize" value="5"/>
		       <s:submit cssClass="submit_01" value="查询"/>
	       </s:form>
	     </div>
	<!--增加报销单 区域 开始-->
	<s:form action="searchBizClaimVoucher.action" name="claimVoucherForm">
		<table width="90%" border="0" cellspacing="0" cellpadding="0" class="list items">
	      <tr class="even">
	        <td>编号</td>
	        <td>填报日期</td>
	        <td>填报人</td>
	        <td>总金额</td>
	        <td>状态</td>
	        <td>待处理人</td>
	        <td>操作</td>
	      </tr>
	      <s:if test="#session.pageInfo.list!=null">
	      <s:iterator value="#session.pageInfo.list" id="claimVoucher" begin="0" status="s">
	      <tr>
	        <td><a href="searchBizClaimVoucherById.action?type=show&claimVoucher.id=<s:property value="#claimVoucher.id"/>"><s:property value="#claimVoucher.id"/></a></td>
	        <td><s:date name="#claimVoucher.createTime"/></td>
	        <td><s:property value="#claimVoucher.sysEmployeeByCreateSn.name"/></td>
	        <td><s:property value="#claimVoucher.totalAccount"/></td>
	        <td><s:property value="#claimVoucher.status"/></td>
	        <td><s:property value="#claimVoucher.sysEmployeeByNextDealSn.name"/></td>
	        <td>
	        	<s:if test="#claimVoucher.status == '新创建' || #claimVoucher.status == '已打回'">
	        		<a href="searchBizClaimVoucherById.action?type=update&claimVoucher.id=<s:property value="#claimVoucher.id"/>">
			        	<img src="${images}/edit.gif" width="16" height="16" /> 
			        </a>
			        <a onClick="delVoucher(<s:property value="#claimVoucher.id"/>)" href="#">
			        	<img src="${images}/delete.gif" width="16" height="16" />
			        </a>
		        </s:if>
		        <%-- <img src="${images}/save.gif" width="16" height="16" />  --%>
		        <a href="searchBizClaimVoucherById.action?type=show&claimVoucher.id=<s:property value="#claimVoucher.id"/>">
		        	<img src="${images}/search.gif" width="16" height="15" />
		        </a>
		        <s:if test="#claimVoucher.sysEmployeeByNextDealSn.name == #session.employee.name">
		        	<s:if test="#claimVoucher.status == '已提交' || #claimVoucher.status == '待审批' || #claimVoucher.status == '已审批'">
				        <a href="searchBizClaimVoucherById.action?type=check&claimVoucher.id=<s:property value="#claimVoucher.id"/>">
				         <img src="${images}/sub.gif" width="16" height="16" />
				        </a>
			        </s:if>
		        </s:if>
	        </td>
	      </tr>
	      </s:iterator>
	      </s:if>
	      <s:else>
	      		<tr>
	        <td colspan="7" align="center">
		      	查无数据
  		  	</td>
  		  </tr>
	      </s:else>
	      <tr>
	        <td colspan="7" align="center">
		      	<c:import url="../common/rollPage.jsp" charEncoding="UTF-8">
				<c:param name="formName" value="document.forms[0]"/>
				<c:param name="totalCount" value="${pageInfo.totalCount}"/>
				<c:param name="pageTotalCount" value="${pageInfo.pageTotalCount}"/>
				<c:param name="pageIndex" value="${pageInfo.pageIndex}"/>
  			</c:import>
  		  	</td>
  		  </tr>  		  
	    </table>      
	   </s:form>  
	          <!--增加报销单 区域 结束-->
       </div>
      </div>