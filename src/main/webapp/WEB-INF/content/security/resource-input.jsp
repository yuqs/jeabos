<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
	<head>
		<title>资源管理</title>
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
					name : "required",
					source : "required"
				}
			});
		});
		
		function showMenuSelect(value) {
			switch (value) {
			case "0":
				document.getElementById("cascadeMenu").style.display = 'block';
				break;
			default:
				document.getElementById("cascadeMenu").style.display = 'none';
				break;
			}
		}
		
		function selectMenu() {
			iframewbox=$("#selectMenu").wBox({
				   	requestType: "iframe",
				   	iframeWH:{width:800,height:400},
				   	title:"选择上级菜单",
					target:"${ctx}/security/resource.action?lookup=1"
				   });
		}
		
		function callbackProcess(id, name) {
			if(iframewbox) {
				document.getElementById("menuId").value=id;
				document.getElementById("menuName").value=name;
				iframewbox.close();
			}
		}
		</script>
	</head>

	<body>
		<form id="inputForm" action="resource!save.action" method="post">
			<input type="hidden" name="id" id="id" value="${id }"/>
			<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td class="td_table_1">
						<span>资源名称：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="name" name="name"
							value="${name }" />
					</td>
					<td class="td_table_1">
						<span>资源：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="source" name="source"
							value="${source }" />
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>资源类型：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<input type="radio" name="type" id="type"
							onclick="showMenuSelect(this.value)" value="1" checked>
						URL资源
						<input type="radio" name="type" id="type"
							onclick="showMenuSelect(this.value)" value="0">
						菜单资源
						<input type="radio" name="type" id="type"
							onclick="showMenuSelect(this.value)" value="2">
						文件目录
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>资源描述：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<input type="text" class="input_520" id="description" name="description"
							value="${description }">
					</td>
				</tr>
			</table>
			<div id="cascadeMenu" style="display:${type == 0 ? 'block' : 'none'}">
				<table class="table_all" align="center" border="0" cellpadding="0"
					cellspacing="0">
					<tr>
						<td class="td_table_1">
							<span>上级菜单：</span>
						</td>
						<td class="td_table_2" colspan="3">
							<input type="hidden" id="menuId" name="menuId" value="${menu.id }">
							<input type="text" readonly="readonly" id="menuName" name="menuName" class="input_240" value="${menu.name }">
							<a href="${ctx}/security/resource.action" id="selectMenu" onclick="selectMenu()"><input type='button' class='button_70px' value='选择菜单'/></a>
						</td>
					</tr>
				</table>
			</div>
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
