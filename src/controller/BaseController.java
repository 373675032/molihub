package controller;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.ModelAttribute;
import service.*;
import utils.LogUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Controller的基类
 * @User MOTI
 * @Time 2019/8/3 23:37
 */
public class BaseController {

    public static UserService us;
    public static LabelService ls;
    public static ArticleService as;
    public static AdminService adminService;
    public static MessageService ms;
    public static Logger logger = LogUtils.getInstance(BaseController.class);
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    /**
     * 静态代码块,随着类的加载而执行，而且只执行一次
     */
    static {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        us = (UserService) context.getBean("userServiceImpl");
        ls = (LabelService) context.getBean("labelServiceImpl");
        as = (ArticleService) context.getBean("articleServiceImpl");
        adminService = (AdminService) context.getBean("adminServiceImpl");
        ms = (MessageService) context.getBean("messageServiceImpl");
    }

    /**
     * 在每个子类方法调用之前先调用
     * 设置request,response,session这三个对象
     * @param request
     * @param response
     */
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession(true);
    }
}
