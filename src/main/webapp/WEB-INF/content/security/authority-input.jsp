<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>权限管理</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css"
			type="text/css" media="all" />
		<link href="${ctx}/styles/js/validate/jquery.validate.css"
			type="text/css" rel="stylesheet" />
		<script src="${ctx}/styles/js/jquery-1.8.3.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/validate/jquery.validate.min.js"
			type="text/javascript"></script>
		<script src="${ctx}/styles/js/validate/messages_cn.js"
			type="text/javascript"></script>
		<script>
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				rules : {
					name : "required"
				}
			});
		});
</script>
	</head>

	<body>
		<form id="inputForm" action="authority!save.action" method="post">
			<input type="hidden" name="id" id="id" value="${id }"/>
			<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td class="td_table_1">
						<span>权限名称：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="name" name="name"
							value="${name }" />
					</td>
					<td class="td_table_1">
						<span>显示名称：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="display" name="display"
							value="${display }" />
					</td>
				</tr>
			</table>
			<table align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr align="left">
					<td colspan="1">
						<input type="submit" class="button_70px" name="submit" value="提交">
						&nbsp;&nbsp;
						<input type="button" class="button_70px" name="reback" value="返回"
							onclick="history.back()">
					</td>
				</tr>
			</table>
			<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td align=center width=10% class="td_list_1" nowrap>
						<input type="checkbox" title="全选">
					</td>
					<td align=center width=45% class="td_list_1" nowrap>
						<a href="javascript:sort('name','asc')">资源名称</a>
					</td>
					<td align=center width=45% class="td_list_1" nowrap>
						<a href="javascript:sort('source','asc')">资源</a>
					</td>
					<td align=center width=45% class="td_list_1" nowrap>
						<a href="javascript:sort('type','asc')">资源类型</a>
					</td>
				</tr>

				<s:iterator value="resList">
					<tr>
						<td class="td_list_2" align=center nowrap>
							<label class="checkbox">
								<input type="checkbox" name="orderIndexs" value="${id}"
									${selected== 1 ? 'checked=true' : '' }>
							</label>
						</td>
						<td class="td_list_2" align=left nowrap>
							${name}&nbsp;
						</td>
						<td class="td_list_2" align=left nowrap>
							${source}&nbsp;
						</td>
						<td class="td_list_2" align=left nowrap>
							${type}&nbsp;
						</td>
					</tr>
				</s:iterator>
			</table>
		</form>
	</body>
</html>
