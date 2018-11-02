<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<div class="page-bar">
	<c:if test="${param.pageIndex>1}">
		<a href="javascript:page(${param.formName},1);">首页</a>  
		<a href="javascript:page(${param.formName},<c:out value="${param.pageIndex-1}"/>);">上一页</a>
	</c:if>
	<c:if test="${param.pageIndex<=1}">
		首页&nbsp;&nbsp;上一页
	</c:if>
	<c:set var="end" value="${param.pageIndex<3?5:param.pageIndex+3}"></c:set>
			<c:set var="begin" value="${param.pageIndex<3?1:param.pageIndex-2}"></c:set>
			<c:forEach begin="${begin}" end="${end>param.pageTotalCount?param.pageTotalCount:end}" var="i">
				<span><A  style="color:${param.pageIndex==i?'red':''}" id="pageIndex${i}"
				href="javascript:page(${param.formName},${i})">${i}</A></span>
			</c:forEach>
	<c:if test="${param.pageIndex<param.pageTotalCount}">
		<a href="javascript:page(${param.formName},<c:out value="${param.pageIndex+1}" />);">下一页</a>
		<a href="javascript:page(${param.formName},<c:out value="${param.pageTotalCount}"/>);">尾页</a>
	</c:if> 
	<c:if test="${param.pageIndex>=param.pageTotalCount}">
		下一页&nbsp;&nbsp;尾页
	</c:if>&nbsp;&nbsp;
	&nbsp;&nbsp;第 ${param.pageIndex}页/共${param.pageTotalCount}页&nbsp;&nbsp;共${param.totalCount }条记录
</div>