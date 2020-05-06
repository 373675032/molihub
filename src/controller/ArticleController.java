package controller;

import com.alibaba.fastjson.JSON;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.*;

/**
 * 文章的表现层控制器
 * @User MOTI
 * @Time 2019/8/6 23:18
 */
@Controller
public class ArticleController extends BaseController {

    /**
     * 发表文章
     * @param article
     * @param blog_labels
     * @param is_private
     * @param kind
     * @return
     */
    @RequestMapping("/publish")
    public String publish(Article article,String[] blog_labels,boolean is_private,String kind){
        User user = (User) session.getAttribute("onlineUser");
        String tag = "";
        //处理标签
        if(blog_labels.length != 0){
            for (int i = 0; i < blog_labels.length; i++) {
                if(!"".equals(blog_labels[i])){
                    tag += "/"+blog_labels[i];
                }
            }
        }
        //处理是否私密
        if(is_private){
            article.setIs_private("yes");
        }else{
            article.setIs_private("no");
        }
        //处理其他的信息
        article.setUser_id(user.getUser_id());
        article.setArticle_kind(getKind(kind));
        article.setArticle_tag(tag);
        article.setArticle_time(new Date());
        article.setRead_count(0);
        as.addArticle(article);
        return "loading";
    }

    /**
     * 重新编辑文章
     * @param article
     * @param blog_labels
     * @param is_private
     * @param kind
     * @return
     */
    @RequestMapping("/edit")
    public String edit(int article_id,Article article,String[] blog_labels,boolean is_private,String kind){
        Article article1 = as.getMyArticleById(article_id);
        article1.setArticle_title(article.getArticle_title());
        article1.setArticle_content(article.getArticle_content());
        if(is_private){
            article1.setIs_private("yes");
        }else{
            article1.setIs_private("no");
        }
        String tag = "";
        if(blog_labels.length != 0){
            for (int i = 0; i < blog_labels.length; i++) {
                if(!"".equals(blog_labels[i])){
                    tag += "/"+blog_labels[i];
                }
            }
        }
        article1.setArticle_kind(getKind(kind));
        article1.setArticle_tag(tag);
        as.updateArticle(article1);
        return "loading1";
    }

    /**
     * 分页获得文章
     * @param pageNow
     * @throws IOException
     */
    @RequestMapping("/getAllArticlesByPage")
    public void getAllArticlesByPage(int pageNow) throws IOException {
        List<ArticleToShow> list = as.getAllArticleByLimit(pageNow);
        // 解决response返回的数据中文乱码问题
        response.setContentType("text/html;charset=UTF-8");
        // 使用工具类JSON将集合转为json字符串,方便前端调取
        String result = JSON.toJSONString(list);
        // 将转换好的json字符串返回给前端进行渲染
        response.getWriter().write(result);
    }

    /**
     * 分页获得我的文章
     * @param pageNow
     * @throws IOException
     */
    @RequestMapping("/getMyArticlesByPage")
    public void getMyArticlesByPage(int pageNow) throws IOException {
        User user = (User) session.getAttribute("onlineUser");
        List<ArticleToShow> list = as.getMyArticleByLimit(user.getUser_id(),pageNow);
        // 解决response返回的数据中文乱码问题
        response.setContentType("text/html;charset=UTF-8");
        // 使用工具类JSON将集合转为json字符串,方便前端调取
        String result = JSON.toJSONString(list);
        // 将转换好的json字符串返回给前端进行渲染
        response.getWriter().write(result);
    }

    /**
     * 分页获得他人的文章
     * @param pageNow
     * @throws IOException
     */
    @RequestMapping("/getOtherArticlesByPage")
    public void getOtherArticlesByPage(int pageNow) throws IOException {
        User user = (User) session.getAttribute("otherUser");
        List<ArticleToShow> list = as.getOtherArticleByLimit(user.getUser_id(),pageNow);
        // 解决response返回的数据中文乱码问题
        response.setContentType("text/html;charset=UTF-8");
        // 使用工具类JSON将集合转为json字符串,方便前端调取
        String result = JSON.toJSONString(list);
        // 将转换好的json字符串返回给前端进行渲染
        response.getWriter().write(result);
    }

    /**
     * 获得全部文章总页数
     * @throws IOException
     */
    @RequestMapping("/getPageNum")
    public void getPageNum() throws IOException {
        int num = as.getPageNum();
        // 解决response返回的数据中文乱码问题
        response.setContentType("text/html;charset=UTF-8");
        // 将转换好的json字符串返回给前端进行渲染
        response.getWriter().write(num+"");
    }

    /**
     * 获得我的文章总页数
     * @throws IOException
     */
    @RequestMapping("/getMyPageNum")
    public void getMyPageNum() throws IOException {
        User user = (User) session.getAttribute("onlineUser");
        int num = as.getMyPageNum(user.getUser_id());
        // 解决response返回的数据中文乱码问题
        response.setContentType("text/html;charset=UTF-8");
        // 将转换好的json字符串返回给前端进行渲染
        response.getWriter().write(num+"");
    }

    /**
     * 获得他人的文章总页数
     * @throws IOException
     */
    @RequestMapping("/getOtherPageNum")
    public void getOtherPageNum() throws IOException {
        User user = (User) session.getAttribute("otherUser");
        int num = as.getOtherPageNum(user.getUser_id());

        // 解决response返回的数据中文乱码问题
        response.setContentType("text/html;charset=UTF-8");
        // 将转换好的json字符串返回给前端进行渲染
        response.getWriter().write(num+"");
    }

    /**
     * 根据文章的ID获得文章
     * @param article_id
     * @throws IOException
     */
    @RequestMapping("/readArticleById")
    public String readArticleById(int article_id) throws IOException {
        User user = (User) session.getAttribute("onlineUser");
        List<Integer> readArticle = (List<Integer>) session.getAttribute("read");
        if(readArticle == null){
            readArticle = new ArrayList<>();
            session.setAttribute("read",readArticle);
        }
        if(readArticle.indexOf(article_id) == -1){
            as.addArticleReadCount(article_id);
            readArticle.add(article_id);
        }
        ArticleToShow articleToShow = as.getArticleById(article_id);
        if(user == null){
            request.setAttribute("article",articleToShow);
            return "read";
        }
        //只有用户自己才可以阅读自己的私密文章
        if (!articleToShow.getUser_name() .equals(user.getUser_name()) && ("yes").equals(articleToShow.getIs_private())){
            return "main";
        }
        request.setAttribute("article",articleToShow);
        return "read";
    }

    /**
     * 处理文章类别
     * @param num
     * @return
     */
    public String getKind(String num){
        if(num.equals("1")){
            return "学习笔记";
        }else if(num.equals("2")){
            return "技术博客";
        }else if(num.equals("3")){
            return "算法";
        }else if(num.equals("4")){
            return "Bug分析";
        }else if(num.equals("5")){
            return "面试相关";
        }else if(num.equals("6")){
            return "项目实战";
        }else{
            return "其他";
        }
    }

    /**
     * 添加评论
     * @param article_id
     * @param content
     */
    @RequestMapping("/addComment")
    public void addComment(int article_id,String content){
        Comment comment = new Comment();
        comment.setArticle_id(article_id);
        comment.setComment_content(dealStr(content));
        comment.setComment_time(new Date());
        User user = (User) session.getAttribute("onlineUser");
        comment.setUser_name(user.getUser_name());
        as.addComment(comment);
        int accept_id = as.getUserIdByArticleId(article_id);
        if(user.getUser_id() != accept_id){
            ms.addCommentMessage(user.getUser_id(),accept_id,article_id,content);
        }
        Set<Integer> set = us.getAllCommentUsersId(article_id);
        if(set.size() != 0){
            set.remove(user.getUser_id());
            set.remove(accept_id);
            for (Integer tempInt: set
            ) {
                ms.addOtherMessage(user.getUser_id(),tempInt,article_id,content);
            }
        }
    }

    /**
     * 获得指定文章的所有评论
     * @param article_id
     * @throws IOException
     */
    @RequestMapping("/getAllComments")
    public void getAllComments(int article_id) throws IOException {
        List<Comment> list  = as.getAllComments(article_id);
        if(list == null){
            list = new ArrayList<>();
        }
        response.setContentType("text/html;charset=UTF-8");
        // 使用工具类JSON将集合转为json字符串,方便前端调取
        String result = JSON.toJSONString(list);
        // 将转换好的json字符串返回给前端进行渲染
        response.getWriter().write(result);
    }

    /**
     * 点赞
     * @param article_id
     */
    @RequestMapping("/like")
    public void like(int article_id){
        User user = (User) session.getAttribute("onlineUser");
        as.addLike(user.getUser_id(),article_id);
        int accept_id = as.getUserIdByArticleId(article_id);
        if(user.getUser_id() != accept_id){
            ms.addLikeMessage(user.getUser_id(),accept_id,article_id);
        }
    }

    /**
     * 取消点赞
     * @param article_id
     */
    @RequestMapping("/disLike")
    public void disLike(int article_id){
        User user = (User) session.getAttribute("onlineUser");
        as.deleteLike(user.getUser_id(),article_id);
        int accept_id = as.getUserIdByArticleId(article_id);
        ms.deleteLikeMessage(user.getUser_id(),accept_id,article_id);
    }

    /**
     * 是不是已经点过赞
     * @param article_id
     * @throws IOException
     */
    @RequestMapping("/isLiked")
    public void isLiked(int article_id) throws IOException {
        User user = (User) session.getAttribute("onlineUser");
        response.setContentType("text/html;charset=UTF-8");
        if(as.isLiked(user.getUser_id(),article_id)){
            // 如果已经点赞,返回yes结果
            response.getWriter().write("yes");
        }else{
            // 如果没有点赞,返回no结果
            response.getWriter().write("no");
        }
    }

    /**
     * 搜索文章
     * @param content
     * @throws IOException
     */
    @RequestMapping("/searchArticle")
    public void searchArticle(String content) throws IOException {
        List<ArticleToShow> list1 = new ArrayList<>();
        List<ArticleToShow> list2 = new ArrayList<>();
        List<ArticleToShow> list3 = new ArrayList<>();
        User user = us.getUserByUserName(content);
        if (user!= null){
            list1 = as.getAllArticleByUserId(user.getUser_id());
        }
        list2 = as.getAllArticleByKind(content);
        list3 = as.getAllArticleByTitle(content);
        // 将3个list中的元素合体到一个list
        list1.addAll(list2);
        list1.addAll(list3);
        // 利用set集合对list进行去重
        Set set = new HashSet(list1);
        // 使用工具类JSON将集合转为json字符串,方便前端调取
        String result = JSON.toJSONString(set);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(result);
    }

    /**
     * 获得排行榜数据
     * @throws IOException
     */
    @RequestMapping("/getTop")
    public void getTop() throws IOException {
        List<ArticleToShow> listRead = as.getTopByRead();
        List<ArticleToShow> listLike = as.getTopLike();
        Map<String,List<ArticleToShow>> map = new HashMap<>();
        map.put("listRead",listRead);
        map.put("listLike",listLike);
        String result = JSON.toJSONString(map);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(result);
    }

    /**
     * 搜索我的文章
     * @param content
     * @throws IOException
     */
    @RequestMapping("/searchMyArticles")
    public void searchMyArticles(String content) throws IOException {
        User user = (User) session.getAttribute("onlineUser");
        List<ArticleToShow> list = as.getMyArticleByTitle(user.getUser_id(),content);
        String result = JSON.toJSONString(list);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(result);
    }

    /**
     * 搜索他人的文章
     * @param content
     * @throws IOException
     */
    @RequestMapping("/searchOtherArticles")
    public void searchOtherArticles(String content) throws IOException {
        User user = (User) session.getAttribute("otherUser");
        List<ArticleToShow> list = as.getOtherArticleByTitle(user.getUser_id(),content);
        String result = JSON.toJSONString(list);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(result);
    }

    /**
     * 获得个人主页初始化信息
     * @throws IOException
     */
    @RequestMapping("/getMyPageInfo")
    public void getMyPageInfo() throws IOException {
        User user = (User) session.getAttribute("onlineUser");
        //获得阅读排行榜
        List<ArticleToShow> listRead = as.getMyTopByRead(user.getUser_id());
        int sum = as.getMyArticleCount(user.getUser_id());
        //获得所有的个性标签
        List<String> list = ls.getAllLabelsByUserId(user.getUser_id());
        List<String> labels = new ArrayList<>();
        for (String tag:list
        ) {
            int num = ls.getTagArticleCount(tag,user.getUser_id());
            labels.add(tag+"("+num+")");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("listRead",listRead);
        map.put("labels",labels);
        map.put("sum",sum);
        String result = JSON.toJSONString(map);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(result);
    }

    /**
     * 获得他人主页初始化信息
     * @throws IOException
     */
    @RequestMapping("/getOtherPageInfo")
    public void getOtherPageInfo() throws IOException {
        User user = (User) session.getAttribute("otherUser");
        int sum = as.getOtherArticleCount(user.getUser_id());
        //获得阅读排行榜
        List<ArticleToShow> listRead = as.getMyTopByRead(user.getUser_id());
        //获得所有的个性标签
        List<String> list = ls.getAllLabelsByUserId(user.getUser_id());
        List<String> labels = new ArrayList<>();
        for (String tag:list
        ) {
            int num = ls.getOtherTagArticleCount(tag,user.getUser_id());
            labels.add(tag+"("+num+")");
        }

        Map<String,Object> map = new HashMap<>();
        map.put("listRead",listRead);
        map.put("labels",labels);
        map.put("sum",sum);
        String result = JSON.toJSONString(map);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(result);
    }

    /**
     * 根据标签获得自己的文章
     * @param tagName
     * @throws IOException
     */
    @RequestMapping("/getMyArticlesByTag")
    public void getArticlesByTag(String tagName) throws IOException {
        User user = (User) session.getAttribute("onlineUser");
        List<ArticleToShow> list = as.getMyArticlesByTag(tagName,user.getUser_id());
        String result = JSON.toJSONString(list);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(result);
    }

    /**
     * 根据标签获得他人的文章
     * @param tagName
     * @throws IOException
     */
    @RequestMapping("/getOtherArticlesByTag")
    public void getOtherArticlesByTag(String tagName) throws IOException {
        User user = (User) session.getAttribute("otherUser");
        List<ArticleToShow> list = as.getOtherArticlesByTag(tagName,user.getUser_id());
        String result = JSON.toJSONString(list);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(result);
    }

    /**
     * 根据类别获得自己的文章
     * @param kind
     * @throws IOException
     */
    @RequestMapping("/getMyArticlesByKind")
    public void getArticlesByKind(String kind) throws IOException {
        User user = (User) session.getAttribute("onlineUser");
        List<ArticleToShow> list = as.getMyArticlesByKind(kind,user.getUser_id());
        String result = JSON.toJSONString(list);
        response.setContentType("text/html;charset=UTF-8");
        if(list == null){
            response.getWriter().write("no");
        }else{
            response.getWriter().write(result);
        }

    }

    /**
     * 根据类别获得他人的文章
     * @param kind
     * @throws IOException
     */
    @RequestMapping("/getOtherArticlesByKind")
    public void getOtherArticlesByKind(String kind) throws IOException {
        User user = (User) session.getAttribute("otherUser");
        List<ArticleToShow> list = as.getOtherArticlesByKind(kind,user.getUser_id());
        String result = JSON.toJSONString(list);
        response.setContentType("text/html;charset=UTF-8");
        if(list == null){
            response.getWriter().write("no");
        }else{
            response.getWriter().write(result);
        }
    }

    /**
     * 删除文章
     * @param article_id
     * @throws IOException
     */
    @RequestMapping("/deleteArticle")
    public void deleteArticle(int article_id) throws IOException {
        //删除文章
        as.deleteArticle(article_id);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("yes");
    }

    /**
     * 处理字符串,将<>换成HTML实体字符
     * @param content
     * @return
     */
    private String dealStr(String content){
        String temp1 = content.replaceAll("<","&lt;");
        String temp2 = temp1.replaceAll(">","&gt;");
        return temp2;
    }
}
