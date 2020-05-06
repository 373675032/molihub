package service.impl;

import domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.ArticleService;
import service.UserService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @User MOTI
 * @Time 2019/8/6 23:15
 */
@Transactional
@Service("articleServiceImpl")
public class ArticleServiceImpl extends BaseServiceImpl implements ArticleService {

    //每页显示的文章数
    private static final int PAGE_SIZE = 9;

    /**
     * 添加文章
     *
     * @param article
     */
    @Override
    public void addArticle(Article article) {
        ad.addArticle(article);
    }

    /**
     * 分页获得所有的文章
     *
     * @return
     */
    @Override
    public List<ArticleToShow> getAllArticleByLimit(int pageNow) {
        List<ArticleToShow> list = new ArrayList<>();
        List<Article> articles = ad.getAllArticleByLimit(PAGE_SIZE,pageNow);
        for (int i = 0;i < articles.size();i++) {
            ArticleToShow articleToShow = new ArticleToShow();
            //设置文章ID
            articleToShow.setArticle_id(articles.get(i).getArticle_id());
            //设置文章标题
            articleToShow.setArticle_title(articles.get(i).getArticle_title());
            //设置处理过得文章内容
            articleToShow.setArticle_content(dealArticleContent(articles.get(i).getArticle_content()));
            //设置文章类型
            articleToShow.setArticle_kind(articles.get(i).getArticle_kind());
            //设置文章标签
            articleToShow.setArticle_tag(articles.get(i).getArticle_tag());
            //设置发布时间
            articleToShow.setArticle_time(articles.get(i).getArticle_time());
            //设置格式化之后的时间
            articleToShow.setTime_to_show(formatTime(articleToShow.getArticle_time().getTime()));
            UserService us = new UserServiceImpl();
            int userId = articles.get(i).getUser_id();
            User user = us.getUserById(userId);
            //设置文章作者
            articleToShow.setUser_name(user.getUser_name());
            //设置阅读次数
            articleToShow.setRead_count(articles.get(i).getRead_count());
            //设置点赞数
            articleToShow.setLike_count(getLikeCount(articles.get(i).getArticle_id()));
            //设置评论数
            articleToShow.setComment_count(getCommentsCount(articles.get(i).getArticle_id()));
            //添加到结果返回集
            list.add(articleToShow);
        }
        return list;
    }

    /**
     * 分页获得我的文章
     * @param user_id
     * @param pageNow
     * @return
     */
    @Override
    public List<ArticleToShow> getMyArticleByLimit(int user_id, int pageNow) {
        List<ArticleToShow> list = new ArrayList<>();
        List<Article> articles = ad.getMyArticleByLimit(user_id,PAGE_SIZE,pageNow);
        if(articles == null) return list;
        for (int i = 0;i < articles.size();i++) {
            ArticleToShow temp = new ArticleToShow();
            temp = transformArticle(articles.get(i));
            temp.setArticle_content(dealArticleContent(temp.getArticle_content()));
            //添加到结果返回集
            list.add(temp);
        }
        return list;
    }

    /**
     * 分页获得他人的文章
     * @param user_id
     * @param pageNow
     * @return
     */
    @Override
    public List<ArticleToShow> getOtherArticleByLimit(int user_id, int pageNow) {
        List<ArticleToShow> list = new ArrayList<>();
        List<Article> articles = ad.getOtherArticleByLimit(user_id,PAGE_SIZE,pageNow);
        if(articles == null) return list;
        for (int i = 0;i < articles.size();i++) {
            ArticleToShow temp = new ArticleToShow();
            temp = transformArticle(articles.get(i));
            temp.setArticle_content(dealArticleContent(temp.getArticle_content()));
            //添加到结果返回集
            list.add(temp);
        }
        return list;
    }

    /**
     * 根据文章ID获得文章
     *
     * @param articleId
     * @return
     */
    @Override
    public ArticleToShow getArticleById(int articleId) {
        return transformArticle(ad.getArticleById(articleId));
    }

    /**
     * 获得我的文章
     * @param article_id
     * @return
     */
    @Override
    public Article getMyArticleById(int article_id) {
        return ad.getArticleById(article_id);
    }

    /**
     * 获得总页数,每页10个文章
     *
     * @return
     */
    @Override
    public int getPageNum() {
        int sum = ad.getPageNum();
        if (sum <= 10){
            return 1;
        }else if(sum %10 == 0){
            return sum/10;
        }else{
            return sum/10 +1;
        }
    }

    /**
     * 获得我的文章总页数
     *
     * @return
     */
    @Override
    public int getMyPageNum(int user_id) {
        int sum = ad.getMyPageNum(user_id);
        if (sum <= 10){
            return 1;
        }else if(sum %10 == 0){
            return sum/10;
        }else{
            return sum/10 +1;
        }
    }

    /**
     * 获得他人文章的总页数
     * @param user_id
     * @return
     */
    @Override
    public int getOtherPageNum(int user_id) {
        int sum = ad.getOtherPageNum(user_id);
        if (sum <= 10){
            return 1;
        }else if(sum %10 == 0){
            return sum/10;
        }else{
            return sum/10 +1;
        }
    }

    /**
     * 添加评论
     *
     * @param comment
     */
    @Override
    public void addComment(Comment comment) {
        ad.addComment(comment);
    }

    /**
     * 获得所有的评论
     *
     * @return
     */
    @Override
    public List<Comment> getAllComments(int article_id) {
        List<Comment> list = ad.getAllComments(article_id);
        if(list!= null){
            for (Comment comment:list
                 ) {
                comment.setTime_to_show(formatTime(comment.getComment_time().getTime()));
            }
        }
        return list;
    }

    /**
     * 获得文章的评论数
     *
     * @param article_id
     * @return
     */
    @Override
    public int getCommentsCount(int article_id) {
        List<Comment> list = ad.getAllComments(article_id);
        if(list== null){
            return 0;
        }else{
            return list.size();
        }
    }

    /**
     * 阅读文章阅读数加1
     *
     * @param article_id
     */
    @Override
    public void addArticleReadCount(int article_id) {
        ad.addArticleReadCount(article_id);
    }

    /**
     * 添加喜欢文章
     *
     * @param user_id
     * @param article_id
     */
    @Override
    public void addLike(int user_id, int article_id) {
        ad.addLike(user_id,article_id);
    }

    /**
     * 删除喜欢文章
     *
     * @param user_id
     * @param article_id
     */
    @Override
    public void deleteLike(int user_id, int article_id) {
        ad.deleteLike(user_id,article_id);
    }

    /**
     * 是不是已经是喜欢的文章
     *
     * @param user_id
     * @param article_id
     */
    @Override
    public boolean isLiked(int user_id, int article_id) {
        return ad.isLiked(user_id,article_id);
    }

    /**
     * 获得文章的点赞数
     *
     * @param article_id
     * @return
     */
    @Override
    public int getLikeCount(int article_id) {
        return ad.getLikeCount(article_id);
    }

    /**
     * 根据用户ID分页获得文章
     * @param user_id
     * @return
     */
    @Override
    public List<ArticleToShow> getAllArticleByUserId(int user_id) {
        List<ArticleToShow> list = new ArrayList<>();
        List<Article> articles = ad.getAllArticleByUserId(user_id);
        if(articles == null) return list;
        for (int i = 0;i < articles.size();i++) {
            ArticleToShow temp = new ArticleToShow();
            temp = transformArticle(articles.get(i));
            temp.setArticle_content(dealArticleContent(temp.getArticle_content()));
            //添加到结果返回集
            list.add(temp);
        }
        return list;
    }

    /**
     * 根据文章类别分页获得文章
     * @param kind
     * @return
     */
    @Override
    public List<ArticleToShow> getAllArticleByKind(String kind) {
        List<ArticleToShow> list = new ArrayList<>();
        List<Article> articles = ad.getAllArticleByKind(kind);
        if(articles == null) return list;
        for (int i = 0;i < articles.size();i++) {
            ArticleToShow temp = new ArticleToShow();
            temp = transformArticle(articles.get(i));
            temp.setArticle_content(dealArticleContent(temp.getArticle_content()));
            //添加到结果返回集
            list.add(temp);
        }
        return list;
    }

    /**
     * 根据文章标题分页获得文章
     * @param title
     * @return
     */
    @Override
    public List<ArticleToShow> getAllArticleByTitle(String title) {
        List<ArticleToShow> list = new ArrayList<>();
        List<Article> articles = ad.getAllArticleByTitle(title);
        if(articles == null) return list;
        for (int i = 0;i < articles.size();i++) {
            ArticleToShow temp = new ArticleToShow();
            temp = transformArticle(articles.get(i));
            temp.setArticle_content(dealArticleContent(temp.getArticle_content()));
            //添加到结果返回集
            list.add(temp);
        }
        return list;
    }

    /**
     * 根据文章标题获得我的文章
     * @param title
     * @return
     */
    @Override
    public List<ArticleToShow> getMyArticleByTitle(int user_id, String title) {
        List<ArticleToShow> list = new ArrayList<>();
        List<Article> articles = ad.getMyArticleByTitle(user_id,title);
        if(articles == null) return list;
        for (int i = 0;i < articles.size();i++) {
            ArticleToShow temp = new ArticleToShow();
            temp = transformArticle(articles.get(i));
            temp.setArticle_content(dealArticleContent(temp.getArticle_content()));
            //添加到结果返回集
            list.add(temp);
        }
        return list;
    }

    /**
     * 根据文章标题获得我的文章
     * @param title
     * @return
     */
    @Override
    public List<ArticleToShow> getOtherArticleByTitle(int user_id, String title) {
        List<ArticleToShow> list = new ArrayList<>();
        List<Article> articles = ad.getOtherArticleByTitle(user_id,title);
        if(articles == null) return list;
        for (int i = 0;i < articles.size();i++) {
            ArticleToShow temp = new ArticleToShow();
            temp = transformArticle(articles.get(i));
            temp.setArticle_content(dealArticleContent(temp.getArticle_content()));
            //添加到结果返回集
            list.add(temp);
        }
        return list;
    }

    /**
     * 获得阅读排行榜文章(10个)
     * @return
     */
    @Override
    public List<ArticleToShow> getTopByRead() {
        List<ArticleToShow> list = new ArrayList<>();
        List<Article> articles = ad.getTopByRead();
        if(articles == null) return list;
        for (int i = 0;i < articles.size();i++) {
            ArticleToShow temp = new ArticleToShow();
            temp = transformArticle(articles.get(i));
            temp.setArticle_content(dealArticleContent(temp.getArticle_content()));
            temp.setArticle_title(dealArticleTitle(temp.getArticle_title()));
            //添加到结果返回集
            list.add(temp);
        }
        return list;
    }

    /**
     * 获得我的文章阅读排行榜文章(5个)
     * @return
     */
    @Override
    public List<ArticleToShow> getMyTopByRead(int user_id) {
        List<ArticleToShow> list = new ArrayList<>();
        List<Article> articles = ad.getMyTopByRead(user_id);
        if(articles == null) return list;
        for (int i = 0;i < articles.size();i++) {
            ArticleToShow temp = new ArticleToShow();
            temp = transformArticle(articles.get(i));
            temp.setArticle_content(dealArticleContent(temp.getArticle_content()));
            //添加到结果返回集
            list.add(temp);
        }
        return list;
    }

    /**
     * 获得人气排行榜
     * @return
     */
    @Override
    public List<ArticleToShow> getTopLike() {
        List<ArticleToShow> resultList = new ArrayList<>();
        List<Like> list = ad.getTopByLike();
        for (int i = 0; i < list.size(); i++) {
            Article article = ad.getArticleById(list.get(i).getArticle_id());
            if(article == null){
                continue;
            }
            article.setArticle_title(dealArticleTitle(article.getArticle_title()));
            resultList.add(transformArticle(article));
        }
        return resultList;
    }

    /**
     * 更新文章的标签
     * @param tag
     * @param article_id
     */
    @Override
    public void updateArticleTag(String tag, int article_id) {
        ad.updateArticleTag(tag,article_id);
    }

    /**
     * 根据标签获得全部文章
     * @param tag
     * @param user_id
     * @return
     */
    @Override
    public List<ArticleToShow> getMyArticlesByTag(String tag, int user_id) {
        List<ArticleToShow> resultList = new ArrayList<>();
        List<Article> list = ad.getMyArticlesByTag(tag,user_id);
        if(list == null){
            return resultList;
        }
        for (int i = 0; i < list.size(); i++) {
            Article article = ad.getArticleById(list.get(i).getArticle_id());
            if(article == null){
                continue;
            }
            resultList.add(transformArticle(article));
        }
        return resultList;
    }

    /**
     * 根据标签获得他人的公开文章
     * @param tag
     * @param user_id
     * @return
     */
    @Override
    public List<ArticleToShow> getOtherArticlesByTag(String tag, int user_id) {
        List<ArticleToShow> resultList = new ArrayList<>();
        List<Article> list = ad.getOtherArticlesByTag(tag,user_id);
        if(list == null){
            return resultList;
        }
        for (int i = 0; i < list.size(); i++) {
            Article article = ad.getArticleById(list.get(i).getArticle_id());
            if(article == null){
                continue;
            }
            resultList.add(transformArticle(article));
        }
        return resultList;
    }

    /**
     * 根据标签获得全部文章
     * @param kind
     * @param user_id
     * @return
     */
    @Override
    public List<ArticleToShow> getMyArticlesByKind(String kind, int user_id) {
        List<ArticleToShow> resultList = new ArrayList<>();
        List<Article> list = ad.getMyArticlesByKind(kind,user_id);
        if(list == null){
            return resultList;
        }
        for (int i = 0; i < list.size(); i++) {
            Article article = ad.getArticleById(list.get(i).getArticle_id());
            if(article == null){
                continue;
            }
            resultList.add(transformArticle(article));
        }
        return resultList;
    }

    /**
     * 根据标签获得全部文章
     * @param kind
     * @param user_id
     * @return
     */
    @Override
    public List<ArticleToShow> getOtherArticlesByKind(String kind, int user_id) {
        List<ArticleToShow> resultList = new ArrayList<>();
        List<Article> list = ad.getOtherArticlesByKind(kind,user_id);
        if(list == null){
            return resultList;
        }
        for (int i = 0; i < list.size(); i++) {
            Article article = ad.getArticleById(list.get(i).getArticle_id());
            if(article == null){
                continue;
            }
            resultList.add(transformArticle(article));
        }
        return resultList;
    }

    /**
     * 删除文章
     * @param article_id
     */
    @Override
    public void deleteArticle(int article_id) {
        ad.deleteArticle(article_id);
        ad.deleteComments(article_id);
        ad.deleteLikes(article_id);
        md.deleteArticleMessage(article_id);
    }

    /**
     * 更新文章
     * @param article
     */
    @Override
    public void updateArticle(Article article) {
        ad.updateArticle(article);
    }

    /**
     * 修改用户名后要修改评论表用户名
     * @param newName
     * @param oldName
     */
    @Override
    public void updateComment(String newName, String oldName) {
        ad.updateComment(newName,oldName);
    }

    /**
     * 根据文章id获得作者id
     * @param article_id
     * @return
     */
    @Override
    public int getUserIdByArticleId(int article_id) {
        return ad.getUserIdByArticleId(article_id);
    }

    @Override
    public int getMyArticleCount(int user_id) {
        return ad.getMyArticleCount(user_id);
    }

    @Override
    public int getOtherArticleCount(int user_id) {
        return ad.getOtherArticleCount(user_id);
    }

    /**
     * 切割文章的内容
     * @param articleContent
     * @return
     */
    public String dealArticleContent(String articleContent){
        if (articleContent.length() >= 300) {
            articleContent = articleContent.substring(0, 300);
        }
        return articleContent;
    }

    /**
     * 切割文章的标题
     * @param articleTitle
     * @return
     */
    public String dealArticleTitle(String articleTitle){
        if (articleTitle.length() >= 10) {
            articleTitle = articleTitle.substring(0, 10);
            articleTitle += "...";
        }
        return articleTitle;
    }

    /**
     * 格式化Long型日期
     * @param time
     * @return
     */
    public String formatTime(long time){
        Date date = new Date(time);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String result = sd.format(date);
        return result;
    }

    /**
     * 转换Article
     * @param article
     * @return
     */
    public ArticleToShow transformArticle(Article article){
        ArticleToShow articleToShow = new ArticleToShow();
        //设置文章ID
        articleToShow.setArticle_id(article.getArticle_id());
        //设置文章标题
        articleToShow.setArticle_title(article.getArticle_title());
        //设置文章内容
        articleToShow.setArticle_content(article.getArticle_content());
        //设置文章类型
        articleToShow.setArticle_kind(article.getArticle_kind());
        //设置文章标签
        articleToShow.setArticle_tag(article.getArticle_tag());
        //设置发布时间
        articleToShow.setArticle_time(article.getArticle_time());
        //设置格式化之后的时间
        articleToShow.setTime_to_show(formatTime(articleToShow.getArticle_time().getTime()));
        UserService us = new UserServiceImpl();
        int userId = article.getUser_id();
        User user = us.getUserById(userId);
        //设置文章作者
        articleToShow.setUser_name(user.getUser_name());
        //设置阅读次数
        articleToShow.setRead_count(article.getRead_count());
        //设置点赞数
        articleToShow.setLike_count(getLikeCount(article.getArticle_id()));
        //设置评论数
        articleToShow.setComment_count(getCommentsCount(article.getArticle_id()));
        //设置是否私密
        articleToShow.setIs_private(article.getIs_private());
        //返回结果
        return articleToShow;
    }

}
