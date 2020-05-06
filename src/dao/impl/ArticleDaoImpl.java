package dao.impl;

import config.SqlMappers;
import dao.ArticleDao;
import domain.Article;
import domain.Comment;
import domain.Like;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @User MOTI
 * @Time 2019/8/6 23:05
 */
@Repository("articleDaoImpl")
public class ArticleDaoImpl  extends BaseDaoImpl  implements ArticleDao {

    /**
     * 添加文章
     * userId,article_kind,article_title,article_content,article_time,article_tag,is_private,read_count
     * @param a
     */
    @Override
    public void addArticle(Article a) {
        JdbcTemplate.update(SqlMappers.ArticleSqlMapper.get("addArticle"),
                a.getUser_id(),a.getArticle_kind(),a.getArticle_title(),a.getArticle_content(),
                a.getArticle_time(),a.getArticle_tag(),a.getIs_private(),a.getRead_count());
    }

    /**
     * 分页获得所有的文章
     *
     * @param pageSize
     * @return
     */
    @Override
    public List<Article> getAllArticleByLimit(int pageSize,int pageNow) {
        List<Article> list = JdbcTemplate.query(SqlMappers.ArticleSqlMapper.get("getAllArticleByLimit"),new BeanPropertyRowMapper<Article>(Article.class),pageSize*(pageNow-1),pageSize);
        if(list.size() == 0){
            return null;
        }
        return list;
    }

    /**
     * 分页获得我的文章
     * @param user_id
     * @param pageSize
     * @param pageNow
     * @return
     */
    @Override
    public List<Article> getMyArticleByLimit(int user_id, int pageSize, int pageNow) {
        List<Article> list = JdbcTemplate.query(SqlMappers.ArticleSqlMapper.get("getMyArticleByLimit"),new BeanPropertyRowMapper<Article>(Article.class),user_id,pageSize*(pageNow-1),pageSize);
        if(list.size() == 0){
            return null;
        }
        return list;
    }

    /**
     * 分页获得他人的文章
     * @param user_id
     * @param pageSize
     * @param pageNow
     * @return
     */
    @Override
    public List<Article> getOtherArticleByLimit(int user_id, int pageSize, int pageNow) {
        List<Article> list = JdbcTemplate.query(SqlMappers.ArticleSqlMapper.get("getOtherArticleByLimit"),new BeanPropertyRowMapper<Article>(Article.class),user_id,pageSize*(pageNow-1),pageSize);
        if(list.size() == 0){
            return null;
        }
        return list;
    }

    /**
     * 根据文章ID获得文章
     * @param articleId
     * @return
     */
    @Override
    public Article getArticleById(int articleId) {
        List<Article> list = JdbcTemplate.query(SqlMappers.ArticleSqlMapper.get("getArticleById"),new BeanPropertyRowMapper<Article>(Article.class),articleId);
        if(list.size() == 0){
            return null;
        }
        return list.get(0);
    }

    /**
     * 根据用户ID分页获得文章
     * @param user_id
     * @return
     */
    @Override
    public List<Article> getAllArticleByUserId(int user_id) {
        List<Article> list = JdbcTemplate.query(SqlMappers.ArticleSqlMapper.get("getAllArticleByUserId"),new BeanPropertyRowMapper<Article>(Article.class),user_id);
        if(list.size() == 0){
            return null;
        }
        return list;
    }

    /**
     * 根据文章类别分页获得文章
     * @param kind
     * @return
     */
    @Override
    public List<Article> getAllArticleByKind(String kind) {
        List<Article> list = JdbcTemplate.query(SqlMappers.ArticleSqlMapper.get("getAllArticleByKind"),new BeanPropertyRowMapper<Article>(Article.class),kind);
        if(list.size() == 0){
            return null;
        }
        return list;
    }

    /**
     * 根据文章标题分页获得文章
     * @param title
     * @return
     */
    @Override
    public List<Article> getAllArticleByTitle(String title) {
        List<Article> list = JdbcTemplate.query(SqlMappers.ArticleSqlMapper.get("getAllArticleByTitle"),new BeanPropertyRowMapper<Article>(Article.class),"%"+title+"%");
        if(list.size() == 0){
            return null;
        }
        return list;
    }

    /**
     * 根据文章标题获得我的文章
     * @param title
     * @return
     */
    @Override
    public List<Article> getMyArticleByTitle(int user_id, String title) {
        List<Article> list = JdbcTemplate.query(SqlMappers.ArticleSqlMapper.get("getMyArticleByTitle"),new BeanPropertyRowMapper<Article>(Article.class),user_id,"%"+title+"%");
        if(list.size() == 0){
            return null;
        }
        return list;
    }

    /**
     * 根据文章标题获得他人的公开文章
     * @param title
     * @return
     */
    @Override
    public List<Article> getOtherArticleByTitle(int user_id, String title) {
        List<Article> list = JdbcTemplate.query(SqlMappers.ArticleSqlMapper.get("getOtherArticleByTitle"),new BeanPropertyRowMapper<Article>(Article.class),user_id,"%"+title+"%");
        if(list.size() == 0){
            return null;
        }
        return list;
    }

    /**
     * 获得首页总页数
     * @return
     */
    @Override
    public int getPageNum() {
        List result =JdbcTemplate.query(SqlMappers.ArticleSqlMapper.get("getAllPublishArticles"),new BeanPropertyRowMapper<Article>(Article.class));
        return result.size();
    }

    /**
     * 获得我的文章总页数
     * @return
     */
    @Override
    public int getMyPageNum(int user_id) {
        List result =JdbcTemplate.query(SqlMappers.ArticleSqlMapper.get("getAllMyArticles"),new BeanPropertyRowMapper<Article>(Article.class),user_id);
        return result.size();
    }

    /**
     * 获得他人的公开文章页数
     * @param user_id
     * @return
     */
    @Override
    public int getOtherPageNum(int user_id) {
        List result =JdbcTemplate.query(SqlMappers.ArticleSqlMapper.get("getAllOtherPublicArticles"),new BeanPropertyRowMapper<Article>(Article.class),user_id);
        return result.size();
    }

    /**
     * 添加评论
     * @param comment
     */
    @Override
    public void addComment(Comment comment) {
        JdbcTemplate.update(SqlMappers.ArticleSqlMapper.get("addComment"),comment.getArticle_id(),
                comment.getUser_name(),comment.getComment_content(),comment.getComment_time());
    }

    /**
     * 获得所有的评论
     * @return
     */
    @Override
    public List<Comment> getAllComments(int article_id) {
        List<Comment> list = JdbcTemplate.query(SqlMappers.ArticleSqlMapper.get("getAllCommentById"),new BeanPropertyRowMapper<Comment>(Comment.class),article_id);
        if(list.size() == 0){
            return null;
        }
        return list;
    }

    /**
     * 阅读文章阅读数加1
     * @param article_id
     */
    @Override
    public void addArticleReadCount(int article_id) {
        JdbcTemplate.update(SqlMappers.ArticleSqlMapper.get("addArticleReadCount"),article_id);
    }

    /**
     * 添加喜欢文章
     * @param user_id
     * @param article_id
     */
    @Override
    public void addLike(int user_id, int article_id) {
        JdbcTemplate.update(SqlMappers.ArticleSqlMapper.get("addLike"),user_id,article_id);
    }

    /**
     * 删除喜欢文章
     * @param user_id
     * @param article_id
     */
    @Override
    public void deleteLike(int user_id, int article_id) {
        JdbcTemplate.update(SqlMappers.ArticleSqlMapper.get("deleteLike"),article_id,user_id);
    }

    /**
     * 是不是已经是喜欢的文章
     * @param user_id
     * @param article_id
     */
    @Override
    public boolean isLiked(int user_id, int article_id) {
        List<Like> list = JdbcTemplate.query(SqlMappers.ArticleSqlMapper.get("isLiked"),new BeanPropertyRowMapper<Like>(Like.class),article_id,user_id);
        if(list == null || list.size() == 0){
            return false;
        }else {
            return true;
        }
    }

    /**
     * 获得文章的点赞数
     * @param article_id
     * @return
     */
    @Override
    public int getLikeCount(int article_id) {
        List<Like> list = JdbcTemplate.query(SqlMappers.ArticleSqlMapper.get("getLikeCount"),new BeanPropertyRowMapper<Like>(Like.class),article_id);
        if(list == null || list.size() == 0){
            return 0;
        }else{
            return list.size();
        }
    }

    /**
     * 获得阅读排行榜文章(10个)
     * @return
     */
    @Override
    public List<Article> getTopByRead() {
        List<Article> list = JdbcTemplate.query(SqlMappers.ArticleSqlMapper.get("getTopByRead"),new BeanPropertyRowMapper<Article>(Article.class));
        if(list.size() == 0){
            return null;
        }
        return list;
    }

    /**
     * 获得我的文章阅读排行榜文章(5个)
     * @return
     */
    @Override
    public List<Article> getMyTopByRead(int user_id) {
        List<Article> list = JdbcTemplate.query(SqlMappers.ArticleSqlMapper.get("getMyTopByRead"),new BeanPropertyRowMapper<Article>(Article.class),user_id);
        if(list.size() == 0){
            return null;
        }
        return list;
    }

    /**
     * 获得人气排行榜文章(10个)
     * @return
     */
    @Override
    public List<Like> getTopByLike() {
        List<Like> list = JdbcTemplate.query(SqlMappers.ArticleSqlMapper.get("getTopArticlesIdByLike"),new BeanPropertyRowMapper<Like>(Like.class));
        if(list.size() == 0){
            return null;
        }
        return list;
    }

    /**
     * 根据标签获得我的文章
     * @param tag
     * @param user_id
     * @return
     */
    @Override
    public List<Article> getMyArticlesByTag(String tag, int user_id) {
        List<Article> list = JdbcTemplate.query(SqlMappers.ArticleSqlMapper.get("getMyArticlesByTag"),new BeanPropertyRowMapper<Article>(Article.class),user_id,"%/"+tag+"%");
        if(list.size() == 0){
            return null;
        }
        return list;
    }

    /**
     * 根据标签获得他人的公开文章
     * @param tag
     * @param user_id
     * @return
     */
    @Override
    public List<Article> getOtherArticlesByTag(String tag, int user_id) {
        List<Article> list = JdbcTemplate.query(SqlMappers.ArticleSqlMapper.get("getOtherArticlesByTag"),new BeanPropertyRowMapper<Article>(Article.class),user_id,"%/"+tag+"%");
        if(list.size() == 0){
            return null;
        }
        return list;
    }

    /**
     * 根据类别获得我的文章
     * @param kind
     * @param user_id
     * @return
     */
    @Override
    public List<Article> getMyArticlesByKind(String kind, int user_id) {
        List<Article> list = JdbcTemplate.query(SqlMappers.ArticleSqlMapper.get("getMyArticlesByKind"),new BeanPropertyRowMapper<Article>(Article.class),user_id,kind);
        if(list.size() == 0){
            return null;
        }
        return list;
    }

    /**
     * 根据类别获得他人公开的文章
     * @param kind
     * @param user_id
     * @return
     */
    @Override
    public List<Article> getOtherArticlesByKind(String kind, int user_id) {
        List<Article> list = JdbcTemplate.query(SqlMappers.ArticleSqlMapper.get("getOtherArticlesByKind"),new BeanPropertyRowMapper<Article>(Article.class),user_id,kind);
        if(list.size() == 0){
            return null;
        }
        return list;
    }

    /**
     * 更新文章的标签
     * @param tag
     * @param article_id
     */
    @Override
    public void updateArticleTag(String tag, int article_id) {
        JdbcTemplate.update(SqlMappers.ArticleSqlMapper.get("updateArticleTag"),tag,article_id);
    }

    /**
     * 删除文章
     * @param article_id
     */
    @Override
    public void deleteArticle(int article_id) {
        JdbcTemplate.update(SqlMappers.ArticleSqlMapper.get("deleteArticle"),article_id);
    }

    /**
     * 删除文章的评论
     * @param article_id
     */
    @Override
    public void deleteComments(int article_id) {
        JdbcTemplate.update(SqlMappers.ArticleSqlMapper.get("deleteComments"),article_id);
    }

    /**
     * 删除文章的点赞
     * @param article_id
     */
    @Override
    public void deleteLikes(int article_id) {
        JdbcTemplate.update(SqlMappers.ArticleSqlMapper.get("deleteLikes"),article_id);
    }

    /**
     * 重新编辑文章
     * @param article
     */
    @Override
    public void updateArticle(Article article) {
        JdbcTemplate.update(SqlMappers.ArticleSqlMapper.get("updateArticle"),article.getArticle_title(),article.getArticle_content(),article.getArticle_kind(),
                article.getArticle_tag(),article.getIs_private(),article.getArticle_id());
    }

    /**
     * 修改用户名后要修改评论表用户名
     * @param newName
     * @param oldName
     */
    @Override
    public void updateComment(String newName, String oldName) {
        JdbcTemplate.update(SqlMappers.ArticleSqlMapper.get("updateComment"),newName,oldName);
    }

    /**
     * 根据文章id获得作者id
     * @param article_id
     * @return
     */
    @Override
    public int getUserIdByArticleId(int article_id) {
        List<Article> list = JdbcTemplate.query(SqlMappers.ArticleSqlMapper.get("getUserIdByArticleId"),new BeanPropertyRowMapper<Article>(Article.class),article_id);
        if(list.size() == 0){
            return -1;
        }
        return list.get(0).getUser_id();
    }

    @Override
    public int getMyArticleCount(int user_id) {
        List<Article> list = JdbcTemplate.query(SqlMappers.ArticleSqlMapper.get("getMyArticleCount"),new BeanPropertyRowMapper<Article>(Article.class),user_id);
        if(list == null){
            return 0;
        }
        return list.size();
    }

    @Override
    public int getOtherArticleCount(int user_id) {
        List<Article> list = JdbcTemplate.query(SqlMappers.ArticleSqlMapper.get("getOtherArticleCount"),new BeanPropertyRowMapper<Article>(Article.class),user_id);
        if(list == null){
            return 0;
        }
        return list.size();
    }

}
