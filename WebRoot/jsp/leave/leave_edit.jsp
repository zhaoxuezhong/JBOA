<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>北大青鸟办公自动化管理系统</title>
		<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
			$(function(){
				 //日期选择控件
			 	$("#startDate").click(function(){
					WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:true, readOnly:true });
				});
				$("#endDate").click(function(){
					WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}',isShowClear:true, readOnly:true });
				});
				
				$(".time").blur(function(){
			   		var sDate1=$("#startDate").val();
			   		var sDate2=$("#endDate").val();
			   		if(sDate1==""||sDate2==""){
			   			$("#day").val("0");
			   			return false;
			   		}
				   	var days = daysBetween(sDate1,sDate2);
				   	$("#day").val(days);
		   		});	
			});
			
			function submitLeave(){
		   		if(!confirm('确定提交请假申请吗')) return;
		   		document.leaveForm.submit();
		   	}
		   	
		   	function daysBetween(sDate1,sDate2){
				//Date.parse() 解析一个日期时间字符串，并返回1970/1/1 午夜距离该日期时间的毫秒数
				var time1 = Date.parse(new Date(sDate1));
				var time2 = Date.parse(new Date(sDate2));
				var nDays = Math.abs(parseInt((time2 - time1)/1000/3600/24));
				return  nDays;
			};
		   	
		</script>
	</head>
	<body>
	<div class="action divaction">
		<div class="t">请假申请</div>
		<div class="pages">
	<!--请假申请 区域 开始-->
	<s:form action="leave_saveBizLeave.action" name="leaveForm">
     <table class="leave">
       <tr>
         <td class="width1"><label for="name">姓名：</label></td>
         <td class="width2"><s:textfield name="name" value="%{#session.employee.name}" cssClass="nwinput" /></td>
         <s:hidden name="leave.sysEmployeeByEmployeeSn.sn" value="%{#session.employee.sn}"></s:hidden>
         <td class="width1"><label>部门：</label></td>
         <td class="width2">
            <select name="" class="nwselect">
             <option value="<s:property value="#session.employee.sysDepartment.id"/>"><s:property value="#session.employee.sysDepartment.name"/></option>
           </select>
         </td>
       </tr>
        <tr>
         <td class="width1"><label for="time">开始时间：</label></td>
         <td class="width2"><s:textfield name="leave.starttime" id="startDate" cssClass="nwinput time"/></td>
         <td class="width1"><label for="end-time">结束时间：</label></td>
         <td class="width2"><s:textfield name="leave.endtime" id="endDate" cssClass="nwinput time"/></td>
       </tr>
        <tr>
         <td class="width1"><label for="size">请假天数：</label></td>
         <td class="width2"><s:textfield id="day" name="leave.leaveday"  cssClass="nwinput"/>(天)</td>
         <td class="width1"><label>休假类型：</label></td>
         <td class="width2">
            <s:select name="leave.leavetype" list="leaveTypeMap" 
	  			listKey="key" listValue="value" theme="simple" cssClass="nwselect"></s:select>
         </td>
       </tr>
       <tr>
         <td class="width1"><label>请假事由：</label></td>
         <td colspan="3">
          <s:textarea name="leave.reason" cssClass="textarea"></s:textarea>
          </td>
       </tr>
     </table>
      	<div class="forms ">
          <p class="leave">
           <label>下一审批人：</label>
           <select name="leave.sysEmployeeByNextDealSn.sn" class="nwselect" >
             <option value="<s:property value="#session.manager.sn"/>"><s:property value="#session.manager.name"/></option>
           </select>
          </p>
          <p class="marg">
           <input name="button" type="button"  value="提交流程" class="submit_01" onClick="submitLeave()"/>
           <input name="" type="button"  value="取消"  class="submit_01"/>
          </p>
     	</div>
     	</s:form>
     <!--请假申请 区域 结束-->
     </div>
     </div>
	</body>
</html>
