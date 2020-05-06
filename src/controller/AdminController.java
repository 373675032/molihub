package controller;

import domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * 管理员的表现层控制器
 * @User MOTI
 * @Time 2019/8/6 23:18
 */
@Controller
public class AdminController extends BaseController {

    /**
     * 添加反馈信息
     * @param message
     */
    @RequestMapping("/suggest")
    public void suggest(String message){
        User user = (User) session.getAttribute("onlineUser");
        adminService.addSuggestion(message,user.getUser_name());
    }

    /**
     * 获得论坛首页的初始化信息
     * @param message
     * @throws IOException
     */
    @RequestMapping("/getBasicInfo")
    public void getBasicInfo(String message) throws IOException {
        int user_count = adminService.getUserCount();
        int article_count = adminService.getArticleCount();
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(user_count+"-"+article_count);
    }
}
