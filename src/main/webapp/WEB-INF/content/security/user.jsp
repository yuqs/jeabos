<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
	<head>
		<title>账号管理</title>
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
	<form id="mainForm" action="user.action" method="get">
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td class="td_table_1">
					<span>账号：</span>
				</td>
				<td class="td_table_2">
					<input type="text" class="input_240" name="filter_LIKES_username" value="${param['filter_LIKES_username']}"/>
				</td>
				<td class="td_table_1">
					<span>姓名：</span>
				</td>
				<td class="td_table_2">
					<input type="text" class="input_240" name="filter_LIKES_fullname" value="${param['filter_LIKES_fullname']}"/>
				</td>
			</tr>
		</table>
		<table align="center" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="left">
					<input type='button' onclick="addNew('user!input.action')" class='button_70px' value='新建'/>
					<input type='submit' class='button_70px' value='查询'/>
				</td>
			</tr>
		</table>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align=center width=20% class="td_list_1" nowrap>
					账号
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					姓名
				</td>
				<td align=center width=30% class="td_list_1" nowrap>
					邮箱地址
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					部门
				</td>
				<td align=center width=10% class="td_list_1" nowrap>
					操作
				</td>				
			</tr>
			<s:iterator value="page.result">
				<tr>
					<td class="td_list_2" align=left nowrap>
						${username}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${fullname}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${email}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${org.name}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						<a href="user!delete.action?id=${id }" class="btnDel" title="删除" onclick="return confirmDel();">删除</a>
						<a href="user!input.action?id=${id }" class="btnEdit" title="编辑">编辑</a>
						<a href="user!input.action?id=${id }" class="btnView" title="查看">查看</a>
					</td>
				</tr>
			</s:iterator>
			<frame:page exportUrl="user!export.action" curPage="${page.pageNo}" totalPages="${page.totalPages }" totalRecords="${page.totalCount }" lookup="${lookup }"/>
		</table>
	</form>
	</body>
</html>