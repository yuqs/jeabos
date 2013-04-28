package org.jeabos.core.security.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jeabos.core.security.entity.User;
import org.jeabos.core.security.service.SecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/users")
public class SecurityRestService {
	private SecurityManager securityManager;
	
	@Autowired
	public void setSecurityManager(SecurityManager securityManager) {
		this.securityManager = securityManager;
	}

	@GET
	@Path("list")
	@Produces({MediaType.APPLICATION_JSON})
	public String getAllUsers() {
		System.out.println("list");
		StringBuffer sb = new StringBuffer();
		List<User> users = securityManager.getAllUser();
		for(User user : users) {
			sb.append(user.getUsername());
		}
		return sb.toString();
//		return securityManager.getAllUser();
	}
}
