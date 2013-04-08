package org.jeabos.core.security.dao;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.jeabos.core.security.entity.Resource;

@Component
public class ResourceDAO extends HibernateDao<Resource, Long> {

}
