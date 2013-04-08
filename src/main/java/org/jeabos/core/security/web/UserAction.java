package org.jeabos.core.security.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import org.jeabos.core.security.entity.Org;
import org.jeabos.core.security.entity.Role;
import org.jeabos.core.security.entity.User;
import org.jeabos.core.security.service.SecurityManager;
import org.jeabos.core.security.springsecurity.SpringSecurityUtils;
import org.jeabos.core.service.ServiceException;
import org.jeabos.core.utils.ConfigHelper;
import org.jeabos.core.web.struts.BaseAction;

@Namespace("/security")
@Results( { @Result(name = BaseAction.RELOAD, location = "user.action", type = "redirect"),
			@Result(name = UserAction.RESULT_USERSETTING, location = "/WEB-INF/content/security/userSetting.jsp")})
public class UserAction extends BaseAction<User> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7831900786247842095L;
	public static final String RESULT_USERSETTING = "setting";
	private SecurityManager securityManager;
	//-- 页面属性 --//
	private Long id;
	private User entity;
	private Page<User> page = new Page<User>(ConfigHelper.getPageSize());
	private List<Role> roleList = new ArrayList<Role>();
	private String orderIndexs;
	private Long parentOrgId;
	
	@Override
	public User getModel() {
		return entity;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		page = securityManager.searchUser(page, filters);
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		roleList = securityManager.getAllRole();
		
		Set<Role> roless = entity.getRoles();
		for(Role role : roleList) {
			for(Role selRole : roless) {
				if(role.getId().longValue() == selRole.getId().longValue())
				{
					role.setSelected(1);
				}
				if(role.getSelected() == null)
				{
					role.setSelected(0);
				}
			}
		}
		return INPUT;
	}
	
	public String setting() throws Exception {
		User user = SpringSecurityUtils.getUser();
		if(user != null) {
			entity = user;
		}
		return RESULT_USERSETTING;
	}

	@Override
	public String save() throws Exception {
		entity.getRoles().clear();
		if (orderIndexs != null && orderIndexs.length() > 0 )
		{
			orderIndexs = orderIndexs.trim();
			String[] ids = orderIndexs.split(",");
			String id = "";
			for(int i = 0; i < ids.length; i++)
			{
				id = ids[i].trim();
				if (id.length() > 0)
				{
					Role role = new Role();
					role.setId(Long.valueOf(id));
					entity.getRoles().add(role);
				}
			}
		}
		if(parentOrgId != null && parentOrgId.longValue() > 0) {
			Org org = new Org();
			org.setId(parentOrgId);
			entity.setOrg(org);
		}
		securityManager.saveUser(entity);
		addActionMessage("保存用户成功");
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		try {
			securityManager.deleteUser(id);
			addActionMessage("删除用户成功");
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage("删除用户失败");
		}
		return RELOAD;
	}
	
	//-- 其他Action函数 --//
	/**
	 * 支持使用Jquery.validate Ajax检验用户名是否重复.
	 */
	public String checkLoginName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String newUserName = request.getParameter("username");
		String oldUserName = request.getParameter("oldUserName");

		if (securityManager.isUserNameUnique(newUserName, oldUserName)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		//因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = securityManager.getUser(id);
		} else {
			entity = new User();
		}
	}

	@Autowired
	public void setSecurityManager(SecurityManager securityManager) {
		this.securityManager = securityManager;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Page<User> getPage() {
		return page;
	}

	public Long getId() {
		return id;
	}

	public String getOrderIndexs() {
		return orderIndexs;
	}

	public void setOrderIndexs(String orderIndexs) {
		this.orderIndexs = orderIndexs;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}
}
