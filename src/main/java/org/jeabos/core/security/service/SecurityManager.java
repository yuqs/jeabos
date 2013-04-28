package org.jeabos.core.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import org.jeabos.core.security.dao.AuthorityDAO;
import org.jeabos.core.security.dao.OrgDAO;
import org.jeabos.core.security.dao.ResourceDAO;
import org.jeabos.core.security.dao.RoleDAO;
import org.jeabos.core.security.dao.UserDAO;
import org.jeabos.core.security.entity.Authority;
import org.jeabos.core.security.entity.Org;
import org.jeabos.core.security.entity.Resource;
import org.jeabos.core.security.entity.Role;
import org.jeabos.core.security.entity.User;

@Component
@Transactional
public class SecurityManager {
	private UserDAO userDAO;
	private ResourceDAO resourceDAO;
	private OrgDAO orgDAO;
	private RoleDAO roleDAO;
	private AuthorityDAO authorityDAO;
	
	/**User Manager**/
	public void saveUser(User user) {
		userDAO.save(user);
	}
	
	public void deleteUser(Long id) {
		userDAO.delete(id);
	}
	
	@Transactional(readOnly = true)
	public User getUser(Long id) {
		return userDAO.get(id);
	}
	
	@Transactional(readOnly = true)
	public User findUserByName(String username) {
		return userDAO.findUniqueBy("username", username);
	}
	
	@Transactional(readOnly = true)
	public List<User> getAllUser() {
		return userDAO.getAll();
	}
	
	@Transactional(readOnly = true)
	public boolean isUserNameUnique(String newUserName, String oldUserName) {
		return userDAO.isPropertyUnique("username", newUserName, oldUserName);
	}
	
	@Transactional(readOnly = true)
	public Page<User> searchUser(final Page<User> page, final List<PropertyFilter> filters) {
		return userDAO.findPage(page, filters);
	}
	
	@Transactional(readOnly = true)
	public Page<User> searchUser(final Page<User> page, Long orgId) {
		Org org = new Org();
		org.setId(orgId);
		
		String hql = "from User user where user.org=?";
		return userDAO.findPage(page, hql, org);
	}
	
	/**Org Manager**/
	public void saveOrg(Org org) {
		orgDAO.save(org);
	}
	
	public void deleteOrg(Long id) {
		orgDAO.delete(id);
	}
	
	@Transactional(readOnly = true)
	public Org getOrg(Long id) {
		return orgDAO.get(id);
	}
	
	@Transactional(readOnly = true)
	public Page<Org> searchOrg(final Page<Org> page, final List<PropertyFilter> filters) {
		return orgDAO.findPage(page, filters);
	}
	
	@Transactional(readOnly = true)
	public List<Org> getAllOrg() {
		return orgDAO.getAll();
	}
	
	/**Role Manager**/
	public void saveRole(Role entity) {
		roleDAO.save(entity);
	}

	public void deleteRole(Long id) {
		roleDAO.delete(id);
	}
	
	@Transactional(readOnly = true)
	public Role getRole(Long id) {
		return roleDAO.get(id);
	}
	
	@Transactional(readOnly = true)
	public Page<Role> searchRole(final Page<Role> page, final List<PropertyFilter> filters) {
		return roleDAO.findPage(page, filters);
	}

	@Transactional(readOnly = true)
	public List<Role> getAllRole() {
		return roleDAO.getAll();
	}
	
	/**Authority Manager**/
	public void saveAuthority(Authority authority) {
		authorityDAO.save(authority);
	}
	
	public void deleteAuthority(Long id) {
		authorityDAO.delete(id);
	}
	
	@Transactional(readOnly = true)
	public Authority getAuthority(Long id) {
		return authorityDAO.get(id);
	}
	
	@Transactional(readOnly = true)
	public Page<Authority> searchAuthority(final Page<Authority> page, final List<PropertyFilter> filters) {
		return authorityDAO.findPage(page, filters);
	}
	
	@Transactional(readOnly = true)
	public List<Authority> getAllAuthority() {
		return authorityDAO.getAll();
	}

	/**Resource Manager**/
	public void saveResource(Resource resource) {
		resourceDAO.save(resource);
	}
	
	public void deleteResource(Long id) {
		resourceDAO.delete(id);
	}
	
	@Transactional(readOnly = true)
	public Resource getResource(Long id) {
		return resourceDAO.get(id);
	}
	
	@Transactional(readOnly = true)
	public List<Resource> getAllResource() {
		return resourceDAO.getAll();
	}
	
	@Transactional(readOnly = true)
	public Page<Resource> searchResource(final Page<Resource> page, final List<PropertyFilter> filters) {
		return resourceDAO.findPage(page, filters);
	}
	
	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Autowired
	public void setResourceDAO(ResourceDAO resourceDAO) {
		this.resourceDAO = resourceDAO;
	}

	@Autowired
	public void setOrgDAO(OrgDAO orgDAO) {
		this.orgDAO = orgDAO;
	}

	@Autowired
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	@Autowired
	public void setAuthorityDAO(AuthorityDAO authorityDAO) {
		this.authorityDAO = authorityDAO;
	}
}
