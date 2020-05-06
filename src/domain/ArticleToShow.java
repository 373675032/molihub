package domain;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

/**
 * 用来展示的文章实体类
 * @User MOTI
 * @Time 2019/8/8 9:43
 */
@Component
public class ArticleToShow {
    // 文章id
    private int article_id;
    // 作者
    private String user_name;
    // 标题
    private String article_title;
    // 内容
    private String article_content;
    // 时间
    private Date article_time;
    // 格式化之后的时间
    private String time_to_show;
    // 类型
    private String article_kind;
    //  标签
    private String article_tag;
    //  阅读次数
    private int read_count;
    //  点赞数
    private int like_count;
    //  评论数
    private int comment_count;
    //  是否私密
    private String is_private;

    public ArticleToShow() {
    }

    public String getIs_private() {
        return is_private;
    }

    public void setIs_private(String is_private) {
        this.is_private = is_private;
    }

    @Override
    public String toString() {
        return "ArticleToShow{" +
                "article_id=" + article_id +
                ", user_name='" + user_name + '\'' +
                ", article_title='" + article_title + '\'' +
                ", article_content='" + article_content + '\'' +
                ", article_time=" + article_time +
                ", time_to_show='" + time_to_show + '\'' +
                ", article_kind='" + article_kind + '\'' +
                ", article_tag='" + article_tag + '\'' +
                ", read_count=" + read_count +
                ", like_count=" + like_count +
                ", comment_count=" + comment_count +
                ", is_private='" + is_private + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleToShow that = (ArticleToShow) o;
        return article_id == that.article_id &&
                read_count == that.read_count &&
                like_count == that.like_count &&
                comment_count == that.comment_count &&
                Objects.equals(user_name, that.user_name) &&
                Objects.equals(article_title, that.article_title) &&
                Objects.equals(article_content, that.article_content) &&
                Objects.equals(article_time, that.article_time) &&
                Objects.equals(time_to_show, that.time_to_show) &&
                Objects.equals(article_kind, that.article_kind) &&
                Objects.equals(article_tag, that.article_tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(article_id, user_name, article_title, article_content, article_time, time_to_show, article_kind, article_tag, read_count, like_count, comment_count);
    }

    public String getTime_to_show() {
        return time_to_show;
    }

    public void setTime_to_show(String time_to_show) {
        this.time_to_show = time_to_show;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_content() {
        return article_content;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }

    public Date getArticle_time() {
        return article_time;
    }

    public void setArticle_time(Date article_time) {
        this.article_time = article_time;
    }

    public String getArticle_kind() {
        return article_kind;
    }

    public void setArticle_kind(String article_kind) {
        this.article_kind = article_kind;
    }

    public String getArticle_tag() {
        return article_tag;
    }

    public void setArticle_tag(String article_tag) {
        this.article_tag = article_tag;
    }

    public int getRead_count() {
        return read_count;
    }

    public void setRead_count(int read_count) {
        this.read_count = read_count;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }
}
