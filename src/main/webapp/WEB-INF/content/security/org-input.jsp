<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>部门管理</title>
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
		var iframewbox;
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				rules : {
					name : "required"
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
		<form id="inputForm" action="org!save.action" method="post">
			<input type="hidden" name="id" id="id" value="${id }"/>
			<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td class="td_table_1">
						<span>部门名称：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<input type="text" class="input_240" id="name" name="name"
							value="${name }" />
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>上级部门：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<input type="hidden" id="parentOrgId" name="parentOrgId" value="${parentOrg.id }">
						<input type="text" id="parentOrgName" readonly="readonly" name="parentOrgName" class="input_240" value="${parentOrg.name }">
						<a href="${ctx}/security/org.action" id="selectOrg" onclick="selectOrg()"><input type='button' class='button_70px' value='上级部门'/></a>
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>部门描述：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<input type="text" class="input_520" id="description" name="description"
							value="${description }">
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
		</form>
	</body>
</html>
