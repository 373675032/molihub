package dao.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 持久层接口基类
 * @User MOTI
 * @Time 2019/8/8 12:38
 */
public class BaseDaoImpl {

    protected static org.springframework.jdbc.core.JdbcTemplate JdbcTemplate;

    static {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcTemplate = (org.springframework.jdbc.core.JdbcTemplate) context.getBean("JdbcTemplate");
    }
}
