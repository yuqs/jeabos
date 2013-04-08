<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>帐号管理</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css"
			type="text/css" media="all" />
		<link href="${ctx}/styles/js/validate/jquery.validate.css"
			type="text/css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/wbox/wbox/wbox.css" />
		<script src="${ctx}/styles/js/jquery-1.8.3.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/validate/jquery.validate.min.js"
			type="text/javascript"></script>
		<script src="${ctx}/styles/js/validate/messages_cn.js"
			type="text/javascript"></script>
		<script type="text/javascript" src="${ctx}/styles/wbox/wbox.js"></script> 
		<script>
	$(document)
			.ready(
					function() {
						$("#username").focus();
						$("#inputForm")
								.validate(
										{
											rules : {
												username : {
													required : true,
													remote : "user!checkLoginName.action?oldUserName="
															+ encodeURIComponent('${username}')
												},
												name : "required",
												password : {
													required : true,
													minlength : 3
												},
												passwordConfirm : {
													equalTo : "#password"
												},
												email : "email",
												checkedRoleIds : "required"
											},
											messages : {
												username : {
													remote : "用户账号已存在"
												},
												passwordConfirm : {
													equalTo : "输入与上面相同的密码"
												}
											}
										});
					});
	
	function selectOrg() {
		iframewbox=$("#selectOrg").wBox({
			   	requestType: "iframe",
			   	iframeWH:{width:800,height:400},
			   	title:"选择上级菜单",
				target:"${ctx}/security/org.action?lookup=1"
			   });
	}
	
	function callbackProcess(id, name) {
		if(iframewbox) {
			document.getElementById("parentOrgId").value=id;
			document.getElementById("parentOrgName").value=name;
			iframewbox.close();
		}
	}
</script>
	</head>

	<body>
		<form id="inputForm" action="user!save.action" method="post">
			<input type="hidden" name="id" id="id" value="${id }"/>
			<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td class="td_table_1">
						<span>账号：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="username" name="username"
							value="${username }" />
					</td>
					<td class="td_table_1">
						<span>姓名：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="fullname" name="fullname"
							value="${fullname }" />
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>密码：</span>
					</td>
					<td class="td_table_2">
						<input type="password" class="input_240" id="password" name="password"
							value="${password }" />
					</td>
					<td class="td_table_1">
						<span>确认密码：</span>
					</td>
					<td class="td_table_2">
						<input type="password" class="input_240" id="passwordConfirm"
							name="passwordConfirm" value="${password }" />
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>邮箱：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<input type="text" class="input_240" id="email" name="email"
							value="${email }" />
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>部门：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<input type="hidden" id="parentOrgId" name="parentOrgId" value="${org.id }">
						<input type="text" id="parentOrgName" readonly="readonly" name="parentOrgName" class="input_240" value="${org.name }">
						<a href="${ctx}/security/org.action" id="selectOrg" onclick="selectOrg()"><input type='button' class='button_70px' value='选择部门'/></a>
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
						<a href="javascript:sort('name','asc')">角色名称</a>
					</td>
					<td align=center width=45% class="td_list_1" nowrap>
						<a href="javascript:sort('fullname','asc')">显示名称</a>
					</td>
				</tr>

				<s:iterator value="roleList">
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
							${fullname}&nbsp;
						</td>
					</tr>
				</s:iterator>
			</table>
		</form>
	</body>
</html>
