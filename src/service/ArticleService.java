package service;

import domain.Article;
import domain.ArticleToShow;
import domain.Comment;

import java.util.List;

/**
 * 文章业务层接口
 *
 * @User MOTI
 * @Time 2019/8/6 23:14
 */
public interface ArticleService {

    /**
     * 添加文章
     *
     * @param article
     */
    void addArticle(Article article);

    /**
     * 分页获得所有的文章
     *
     * @return
     */
    List<ArticleToShow> getAllArticleByLimit(int pageNow);

    /**
     * 分页获得我的文章
     * @param user_id
     * @param pageNow
     * @return
     */
    List<ArticleToShow> getMyArticleByLimit(int user_id,int pageNow);

    /**
     * 分页获得他人的文章
     * @param user_id
     * @param pageNow
     * @return
     */
    List<ArticleToShow> getOtherArticleByLimit(int user_id,int pageNow);

    /**
     * 根据文章ID获得文章
     *
     * @param articleId
     * @return
     */
    ArticleToShow getArticleById(int articleId);

    /**
     * 获得我的文章
     * @param article_id
     * @return
     */
    Article getMyArticleById(int article_id);

    /**
     * 获得总页数,每页10个文章
     *
     * @return
     */
    int getPageNum();

    /**
     * 获得我的文章总页数
     *
     * @return
     */
    int getMyPageNum(int user_id);

    /**
     * 获得他人文章的总页数
     * @param user_id
     * @return
     */
    int getOtherPageNum(int user_id);

    /**
     * 添加评论
     *
     * @param comment
     */
    void addComment(Comment comment);

    /**
     * 获得所有的评论
     *
     * @return
     */
    List<Comment> getAllComments(int article_id);

    /**
     * 获得文章的评论数
     *
     * @param article_id
     * @return
     */
    int getCommentsCount(int article_id);

    /**
     * 阅读文章阅读数加1
     *
     * @param article_id
     */
    void addArticleReadCount(int article_id);

    /**
     * 添加喜欢文章
     *
     * @param user_id
     * @param article_id
     */
    void addLike(int user_id, int article_id);

    /**
     * 删除喜欢文章
     *
     * @param user_id
     * @param article_id
     */
    void deleteLike(int user_id, int article_id);

    /**
     * 是不是已经是喜欢的文章
     *
     * @param user_id
     * @param article_id
     */
    boolean isLiked(int user_id, int article_id);

    /**
     * 获得文章的点赞数
     *
     * @param article_id
     * @return
     */
    int getLikeCount(int article_id);

    /**
     * 根据用户ID分页获得文章
     * @param user_id
     * @return
     */
    List<ArticleToShow> getAllArticleByUserId(int user_id);

    /**
     * 根据文章类别分页获得文章
     * @param kind
     * @return
     */
    List<ArticleToShow> getAllArticleByKind(String kind);

    /**
     * 根据文章标题分页获得文章
     * @param title
     * @return
     */
    List<ArticleToShow> getAllArticleByTitle(String title);

    /**
     * 根据文章标题获得我的文章
     * @param title
     * @return
     */
    List<ArticleToShow> getMyArticleByTitle(int user_id,String title);

    /**
     * 根据文章标题获得我的文章
     * @param title
     * @return
     */
    List<ArticleToShow> getOtherArticleByTitle(int user_id,String title);

    /**
     * 获得阅读排行榜文章(10个)
     * @return
     */
    List<ArticleToShow> getTopByRead();

    /**
     * 获得我的文章阅读排行榜文章(5个)
     * @return
     */
    List<ArticleToShow> getMyTopByRead(int user_id);

    /**
     * 获得人气排行榜
     * @return
     */
    List<ArticleToShow> getTopLike();

    /**
     * 更新文章的标签
     * @param tag
     * @param article_id
     */
    void updateArticleTag(String tag,int article_id);

    /**
     * 根据标签获得全部文章
     * @param tag
     * @param user_id
     * @return
     */
    List<ArticleToShow> getMyArticlesByTag(String tag, int user_id);

    /**
     * 根据标签获得他人的公开文章
     * @param tag
     * @param user_id
     * @return
     */
    List<ArticleToShow> getOtherArticlesByTag(String tag, int user_id);

    /**
     * 根据标签获得全部文章
     * @param kind
     * @param user_id
     * @return
     */
    List<ArticleToShow> getMyArticlesByKind(String kind, int user_id);

    /**
     * 根据标签获得全部文章
     * @param kind
     * @param user_id
     * @return
     */
    List<ArticleToShow> getOtherArticlesByKind(String kind, int user_id);

    /**
     * 删除文章
     * @param article_id
     */
    void deleteArticle(int article_id);

    /**
     * 更新文章
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
