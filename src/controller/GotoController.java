package controller;

import domain.Article;
import domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 导航跳转页面的控制器
 *
 * @User MOTI
 * @Time 2019/8/6 12:26
 */
@Controller
public class GotoController extends BaseController {

    /**
     * 跳转到发表文章的页面
     *
     * @return publish.jsp
     */
    @RequestMapping("/toPublish")
    public String toPublish() {
        return "publish";
    }

    /**
     * 跳转到反馈的页面
     *
     * @return suggest.jsp
     */
    @RequestMapping("/toSuggest")
    public String toSuggest() {
        return "suggest";
    }

    /**
     * 跳转到论坛首页
     *
     * @return main.jsp
     */
    @RequestMapping("/toMain")
    public String toMain() {
        return "main";
    }

    /**
     * 跳转到我的消息
     *
     * @return messages.jsp
     */
    @RequestMapping("/toMessages")
    public String toMessages() {
        return "messages";
    }

    /**
     * 跳转到他人主页
     *
     * @return other_home.jsp
     */
    @RequestMapping("/toOtherHome")
    public String toMyCircle(String user_name) {
        User user = us.getUserByUserName(user_name);
        User userOnline = (User) session.getAttribute("onlineUser");
        if(user.getUser_name().equals(userOnline.getUser_name())){
            return "my_home";
        }
        session.setAttribute("otherUser",user);
        return "other_home";
    }

    /**
     * 跳转到我的主页
     *
     * @return my_home.jsp
     */
    @RequestMapping("/toMyHome")
    public String toMyHome() {
        return "my_home";
    }

    /**
     * 跳转到网盘
     *
     * @return file_store.jsp
     */
    @RequestMapping("/toFileStore")
    public String toFileStore() {
        return "file_store";
    }

    /**
     * 跳转到标签管理
     *
     * @return add_tag.jsp
     */
    @RequestMapping("/toTags")
    public String toTags() {
        return "add_tag";
    }

    /**
     * 跳转到文章管理
     *
     * @return my_articles.jsp
     */
    @RequestMapping("/toArticles")
    public String toArticles() {
        return "my_articles";
    }

    /**
     * 挑战到标签重命名
     * @param name
     * @return
     */
    @RequestMapping("/toUpdateTag")
    public String toUpdateTag(String name) {
        request.setAttribute("tagName",name);
        return "update_tag";
    }

    /**
     * 跳转到修改资料
     *
     * @return user/update-info.jsp
     */
    @RequestMapping("/toUpdateInfo")
    public String toUpdateInfo() {
        return "update_info";
    }

    /**
     * 退出登录
     */
    @RequestMapping("/toLogout")
    public String toLogout() {
        //清空整个session域
        request.getSession().invalidate();
        return "redirect:login.jsp";
    }

    /**
     * 跳转到编辑页面
     * @param article_id
     * @return
     */
    @RequestMapping("/toEdit")
    public String toEdit(int article_id){
        Article article = as.getMyArticleById(article_id);
        request.setAttribute("articleToEdit",article);
        return "edit_article";
    }

}
