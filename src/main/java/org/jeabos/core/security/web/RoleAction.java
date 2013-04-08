package org.jeabos.core.security.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import org.jeabos.core.security.entity.Authority;
import org.jeabos.core.security.entity.Role;
import org.jeabos.core.security.service.SecurityManager;
import org.jeabos.core.service.ServiceException;
import org.jeabos.core.utils.ConfigHelper;
import org.jeabos.core.web.struts.BaseAction;

@Namespace("/security")
@Results( { @Result(name = BaseAction.RELOAD, location = "role.action", type = "redirect") })
public class RoleAction extends BaseAction<Role> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7831900786247842095L;
	private SecurityManager securityManager;
	//-- 页面属性 --//
	private Long id;
	private Role entity;
	private Page<Role> page = new Page<Role>(ConfigHelper.getPageSize());
	private List<Authority> authList = new ArrayList<Authority>();
	private String orderIndexs;
	
	@Override
	public Role getModel() {
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
		page = securityManager.searchRole(page, filters);
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		authList = securityManager.getAllAuthority();
		
		Set<Authority> authss = entity.getAuthorities();
		for(Authority auth : authList) {
			for(Authority selAuth : authss) {
				if(auth.getId().longValue() == selAuth.getId().longValue())
				{
					auth.setSelected(1);
				}
				if(auth.getSelected() == null)
				{
					auth.setSelected(0);
				}
			}
		}
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		entity.getAuthorities().clear();
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
					Authority auth = new Authority();
					auth.setId(Long.valueOf(id));
					entity.getAuthorities().add(auth);
				}
			}
		}
		securityManager.saveRole(entity);
		addActionMessage("保存角色成功");
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		try {
			if(id != null && id.longValue() > 0) {
				Role role = securityManager.getRole(id);
				System.out.println(role.getUsers());
				securityManager.deleteRole(id);
				addActionMessage("删除角色成功");
			}
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage("删除角色失败");
		}
		return RELOAD;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = securityManager.getRole(id);
		} else {
			entity = new Role();
		}
	}

	@Autowired
	public void setSecurityManager(SecurityManager securityManager) {
		this.securityManager = securityManager;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Page<Role> getPage() {
		return page;
	}

	public Long getId() {
		return id;
	}

	public List<Authority> getAuthList() {
		return authList;
	}

	public String getOrderIndexs() {
		return orderIndexs;
	}

	public void setOrderIndexs(String orderIndexs) {
		this.orderIndexs = orderIndexs;
	}

}
