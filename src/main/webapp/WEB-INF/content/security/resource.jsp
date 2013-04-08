<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
	<head>
		<title>资源管理</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet"
			href="${ctx}/styles/css/style.css" type="text/css"
			media="all" />
		<link href="${ctx}/styles/js/validate/jquery.validate.css" type="text/css"
			rel="stylesheet" />
		<script src="${ctx}/styles/js/jquery-1.8.3.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/validate/jquery.validate.min.js"
			type="text/javascript"></script>
		<script src="${ctx}/styles/js/table.js" type="text/javascript"></script>
	</head>

	<body style="PADDING-TOP: 5px">
	<form id="mainForm" action="resource.action?lookup=${lookup }" method="get">
		<input type="hidden" name="lookup" value="${lookup}" />
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td class="td_table_1">
					<span>资源名称：</span>
				</td>
				<td class="td_table_2">
					<input type="text" class="input_240" name="filter_LIKES_name" value="${param['filter_LIKES_name']}"/>
				</td>
			</tr>
		</table>
		<table align="center" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="left">
				<c:choose>
					<c:when test="${empty lookup}">
					<input type='button' onclick="addNew('resource!input.action')" class='button_70px' value='新建'/>
					</c:when>
					<c:otherwise>
					<input type='button' onclick="javascript:bringback('','')" class='button_70px' value='重置'/>
					</c:otherwise>
				</c:choose>
					<input type='submit' class='button_70px' value='查询'/>
				</td>
			</tr>
		</table>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align=center width=40% class="td_list_1" nowrap>
					资源名称
				</td>
				<td align=center width=25% class="td_list_1" nowrap>
					资源
				</td>
				<td align=center width=25% class="td_list_1" nowrap>
					资源类型
				</td>
				<td align=center width=10% class="td_list_1" nowrap>
					操作
				</td>				
			</tr>
			<s:iterator value="page.result">
				<tr>
					<td class="td_list_2" align=left nowrap>
						${name}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${source}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${type}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
				    <c:choose>
				    <c:when test="${empty lookup}">
						<a href="resource!delete.action?id=${id }" class="btnDel" title="删除" onclick="return confirmDel();">删除</a>
						<a href="resource!input.action?id=${id }" class="btnEdit" title="编辑">编辑</a>
						<a href="resource!input.action?id=${id }" class="btnView" title="查看">查看</a>
					</c:when>
					<c:otherwise>
						<a href="javascript:void(0)" class="btnSelect" title="选择" onclick="bringback('${id}','${name }')">选择</a>
					</c:otherwise>
					</c:choose>
					</td>
				</tr>
			</s:iterator>
			<frame:page exportUrl="resource!export.action" curPage="${page.pageNo}" totalPages="${page.totalPages }" totalRecords="${page.totalCount }" lookup="${lookup }"/>
		</table>
	</form>
	</body>
</html>
