package org.jeabos.core.security.dao;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.jeabos.core.security.entity.User;

@Component
public class UserDAO extends HibernateDao<User, Long> {

}
