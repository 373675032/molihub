package domain;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 评论实体类
 * @User MOTI
 * @Time 2019/8/17 22:33
 */
@Component
public class Comment {
    private int comment_id;
    private int article_id;
    private String user_name;
    private String comment_content;
    private Date comment_time;
    private String time_to_show;
    public Comment() {
    }

    @Override
    public String toString() {
        return "Comment{" +
                "comment_id=" + comment_id +
                ", article_id=" + article_id +
                ", user_name='" + user_name + '\'' +
                ", comment_content='" + comment_content + '\'' +
                ", comment_time=" + comment_time +
                ", time_to_show='" + time_to_show + '\'' +
                '}';
    }

    public String getTime_to_show() {
        return time_to_show;
    }

    public void setTime_to_show(String time_to_show) {
        this.time_to_show = time_to_show;
    }

    public Date getComment_time() {
        return comment_time;
    }

    public void setComment_time(Date comment_time) {
        this.comment_time = comment_time;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
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

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }
}
