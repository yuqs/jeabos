<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<body>
<a href="${ctx }/security/user.action" target="_self">用户管理</a>
<a href="${ctx }/security/org.action" target="_self">部门管理</a>
<a href="${ctx }/security/role.action" target="_self">角色管理</a>
<a href="${ctx }/security/authority.action" target="_self">权限管理</a>
<a href="${ctx }/security/resource.action" target="_self">资源管理</a>
</body>
</html>
