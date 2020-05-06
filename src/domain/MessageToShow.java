package domain;

import java.util.Date;

/**
 * 用来显示的消息实体类
 * @User MOTI
 * @Time 2019/9/8 15:26
 */
public class MessageToShow {

    private String user_name;
    private String article_name;
    private int article_id;
    private int message_type;
    private String comment_content;
    private String message_time;
    private String is_looked;

    @Override
    public String toString() {
        return "MessageToShow{" +
                "user_name='" + user_name + '\'' +
                ", article_name='" + article_name + '\'' +
                ", article_id=" + article_id +
                ", message_type=" + message_type +
                ", comment_content='" + comment_content + '\'' +
                ", message_time=" + message_time +
                ", is_looked='" + is_looked + '\'' +
                '}';
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getArticle_name() {
        return article_name;
    }

    public void setArticle_name(String article_name) {
        this.article_name = article_name;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public int getMessage_type() {
        return message_type;
    }

    public void setMessage_type(int message_type) {
        this.message_type = message_type;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getMessage_time() {
        return message_time;
    }

    public void setMessage_time(String message_time) {
        this.message_time = message_time;
    }

    public String getIs_looked() {
        return is_looked;
    }

    public void setIs_looked(String is_looked) {
        this.is_looked = is_looked;
    }

    public MessageToShow() {
    }
}
