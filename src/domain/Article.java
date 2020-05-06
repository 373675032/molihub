package domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章实体类
 * @User MOTI
 * @Time 2019/8/6 21:48
 */
@Component
public class Article implements Serializable {
    // 文章id
    private int article_id;
    // 用户id
    private int user_id;
    // 标题
    private String article_title;
    // 内容
    private String article_content;
    // 时间
    private Date article_time;
    // 类型
    private String article_kind;
    //  标签
    private String article_tag;
    //  是否私密
    private String is_private;
    //  阅读次数
    private int read_count;

    public Article() {
    }

    @Override
    public String toString() {
        return "Article{" +
                "article_id=" + article_id +
                ", user_id=" + user_id +
                ", article_title='" + article_title + '\'' +
                ", article_content='" + article_content + '\'' +
                ", article_time=" + article_time +
                ", article_kind='" + article_kind + '\'' +
                ", article_tag='" + article_tag + '\'' +
                ", is_private='" + is_private + '\'' +
                ", read_count=" + read_count +
                '}';
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getIs_private() {
        return is_private;
    }

    public void setIs_private(String is_private) {
        this.is_private = is_private;
    }

    public int getRead_count() {
        return read_count;
    }

    public void setRead_count(int read_count) {
        this.read_count = read_count;
    }
}
