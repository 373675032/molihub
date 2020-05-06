package dao;

import domain.Article;
import domain.Comment;
import domain.Like;

import java.util.List;

/**
 * 文章持久层接口
 * @User MOTI
 * @Time 2019/8/6 22:01
 */
public interface ArticleDao {

    /**
     * 添加文章
     * @param article
     */
    void addArticle(Article article);

    /**
     * 分页获得所有的文章
     * @param pageSize
     * @return
     */
    List<Article> getAllArticleByLimit(int pageSize,int pageNow);

    /**
     * 分页获得我的文章
     * @param user_id
     * @param pageSize
     * @param pageNow
     * @return
     */
    List<Article> getMyArticleByLimit(int user_id,int pageSize,int pageNow);

    /**
     * 分页获得他人的文章
     * @param user_id
     * @param pageSize
     * @param pageNow
     * @return
     */
    List<Article> getOtherArticleByLimit(int user_id,int pageSize,int pageNow);

    /**
     * 根据文章ID获得文章
     * @param articleId
     * @return
     */
    Article getArticleById(int articleId);

    /**
     * 根据用户ID分页获得文章
     * @param user_id
     * @return
     */
    List<Article> getAllArticleByUserId(int user_id);

    /**
     * 根据文章类别分页获得文章
     * @param kind
     * @return
     */
    List<Article> getAllArticleByKind(String kind);

    /**
     * 根据文章标题分页获得文章
     * @param title
     * @return
     */
    List<Article> getAllArticleByTitle(String title);

    /**
     * 根据文章标题获得我的文章
     * @param title
     * @return
     */
    List<Article> getMyArticleByTitle(int user_id,String title);

    /**
     * 根据文章标题获得他人的公开文章
     * @param title
     * @return
     */
    List<Article> getOtherArticleByTitle(int user_id,String title);

    /**
     * 获得首页总页数
     * @return
     */
    int getPageNum();

    /**
     * 获得我的文章总页数
     * @return
     */
    int getMyPageNum(int user_id);

    /**
     * 获得他人的公开文章页数
     * @param user_id
     * @return
     */
    int getOtherPageNum(int user_id);

    /**
     * 添加评论
     * @param comment
     */
    void addComment(Comment comment);

    /**
     * 获得所有的评论
     * @return
     */
    List<Comment> getAllComments(int article_id);

    /**
     * 阅读文章阅读数加1
     * @param article_id
     */
    void addArticleReadCount(int article_id);

    /**
     * 添加喜欢文章
     * @param user_id
     * @param article_id
     */
    void addLike(int user_id,int article_id);

    /**
     * 删除喜欢文章
     * @param user_id
     * @param article_id
     */
    void deleteLike(int user_id,int article_id);

    /**
     * 是不是已经是喜欢的文章
     * @param user_id
     * @param article_id
     */
    boolean isLiked(int user_id,int article_id);

    /**
     * 获得文章的点赞数
     * @param article_id
     * @return
     */
    int getLikeCount(int article_id);

    /**
     * 获得阅读排行榜文章(10个)
     * @return
     */
    List<Article> getTopByRead();

    /**
     * 获得我的文章阅读排行榜文章(5个)
     * @return
     */
    List<Article> getMyTopByRead(int user_id);

    /**
     * 获得人气排行榜文章(10个)
     * @return
     */
    List<Like> getTopByLike();

    /**
     * 根据标签获得我的文章
     * @param tag
     * @param user_id
     * @return
     */
    List<Article> getMyArticlesByTag(String tag,int user_id);

    /**
     * 根据标签获得他人的公开文章
     * @param tag
     * @param user_id
     * @return
     */
    List<Article> getOtherArticlesByTag(String tag,int user_id);

    /**
     * 根据类别获得我的文章
     * @param kind
     * @param user_id
     * @return
     */
    List<Article> getMyArticlesByKind(String kind,int user_id);

    /**
     * 根据类别获得他人公开的文章
     * @param kind
     * @param user_id
     * @return
     */
    List<Article> getOtherArticlesByKind(String kind,int user_id);

    /**
     * 更新文章的标签
     * @param tag
     * @param article_id
     */
    void updateArticleTag(String tag,int article_id);

    /**
     * 删除文章
     * @param article_id
     */
    void deleteArticle(int article_id);

    /**
     * 删除文章的评论
     * @param article_id
     */
    void deleteComments(int article_id);

    /**
     * 删除文章的点赞
     * @param article_id
     */
    void deleteLikes(int article_id);

    /**
     * 重新编辑文章
     * @param article
     */
    void updateArticle(Article article);

    /**
     * 修改用户名后要修改评论表用户名
     * @param newName
     * @param oldName
     */
    void updateComment(String newName,String oldName);

    /**
     * 根据文章id获得作者id
     * @param article_id
     * @return
     */
    int getUserIdByArticleId(int article_id);

    /**
     * 获得我的文章的总数
     * @param user_id
     * @return
     */
    int getMyArticleCount(int user_id);

    /**
     * 获得他人的文章的总数
     * @param user_id
     * @return
     */
    int getOtherArticleCount(int user_id);
}
