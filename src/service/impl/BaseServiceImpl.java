package service.impl;

import dao.*;
import domain.Article;
import domain.ArticleToShow;
import domain.Label;
import domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * @User MOTI
 * @Time 2019/8/8 12:46
 */
public class BaseServiceImpl {
    protected static UserDao ud;
    protected static ArticleDao ad;
    protected static LabelDao ld;
    protected static AdminDao adminDao;
    protected static MessageDao md;


    protected static Article article;
    protected static ArticleToShow articleToShow;
    protected static Label label;
    protected static User user;


    static {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ud = (UserDao) context.getBean("userDaoImpl");
        ad = (ArticleDao) context.getBean("articleDaoImpl");
        ld = (LabelDao) context.getBean("labelDaoImpl");
        adminDao = (AdminDao) context.getBean("adminDaoImpl");
        md = (MessageDao) context.getBean("messageDaoImpl");
        article = (Article) context.getBean("article");
        articleToShow = (ArticleToShow) context.getBean("articleToShow");
        label = (Label) context.getBean("label");
        user = (User) context.getBean("user");
    }
}
