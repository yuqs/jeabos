package org.jeabos.core.security.dao;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.jeabos.core.security.entity.Authority;

@Component
public class AuthorityDAO extends HibernateDao<Authority, Long> {

}
