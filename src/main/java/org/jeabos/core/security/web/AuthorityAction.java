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
import org.jeabos.core.security.entity.Authority;
import org.jeabos.core.security.entity.Resource;
import org.jeabos.core.security.entity.Role;
import org.springside.modules.utils.web.struts2.Struts2Utils;
import org.jeabos.core.web.struts.BaseAction;
import org.jeabos.core.security.service.SecurityManager;
import org.jeabos.core.service.ServiceException;
import org.jeabos.core.utils.ConfigHelper;

@Namespace("/security")
@Results( { @Result(name = BaseAction.RELOAD, location = "authority.action", type = "redirect") })
public class AuthorityAction extends BaseAction<Authority> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7831900786247842095L;
	private SecurityManager securityManager;
	private Long id;
	private Authority entity;
	private Page<Authority> page = new Page<Authority>(ConfigHelper.getPageSize());
	private List<Resource> resList = new ArrayList<Resource>();
	private String orderIndexs;
	
	@Override
	public Authority getModel() {
		return entity;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		page = securityManager.searchAuthority(page, filters);
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		resList = securityManager.getAllResource();
		
		Set<Resource> resss = entity.getResources();
		for(Resource res : resList) {
			for(Resource selRes : resss) {
				if(res.getId().longValue() == selRes.getId().longValue())
				{
					res.setSelected(1);
				}
				if(res.getSelected() == null)
				{
					res.setSelected(0);
				}
			}
		}
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		entity.getResources().clear();
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
					Resource res = new Resource();
					res.setId(Long.valueOf(id));
					entity.getResources().add(res);
				}
			}
		}
		securityManager.saveAuthority(entity);
		addActionMessage("淇濆瓨鏉冮檺鎴愬姛");
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		try {
			if(id != null && id.longValue() > 0) {
				List<Role> roles = securityManager.getAllRole();
				for(Role role : roles) {
					for(Authority auth : role.getAuthorities()) {
						if(auth.getId().longValue() == id.longValue()) {
							role.getAuthorities().remove(auth);
							securityManager.saveRole(role);
							break;
						}
					}
				}
				securityManager.deleteAuthority(id);
			}
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
		}
		return RELOAD;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = securityManager.getAuthority(id);
		} else {
			entity = new Authority();
		}
	}

	@Autowired
	public void setSecurityManager(SecurityManager securityManager) {
		this.securityManager = securityManager;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Page<Authority> getPage() {
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

	public List<Resource> getResList() {
		return resList;
	}
}
