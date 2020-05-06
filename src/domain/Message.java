package domain;

import java.util.Date;

/**
 * 消息实体类
 * @User MOTI
 * @Time 2019/9/8 15:23
 */
public class Message {

    private int message_id;
    private int send_id;
    private int accept_id;
    private int article_id;
    private int message_type;
    private String comment_content;
    private Date message_time;
    private String is_looked;

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "message_id=" + message_id +
                ", send_id=" + send_id +
                ", accept_id=" + accept_id +
                ", article_id=" + article_id +
                ", message_type=" + message_type +
                ", comment_content='" + comment_content + '\'' +
                ", message_time=" + message_time +
                ", is_looked='" + is_looked + '\'' +
                '}';
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public int getSend_id() {
        return send_id;
    }

    public void setSend_id(int send_id) {
        this.send_id = send_id;
    }

    public int getAccept_id() {
        return accept_id;
    }

    public void setAccept_id(int accept_id) {
        this.accept_id = accept_id;
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

    public Date getMessage_time() {
        return message_time;
    }

    public void setMessage_time(Date message_time) {
        this.message_time = message_time;
    }

    public String getIs_looked() {
        return is_looked;
    }

    public void setIs_looked(String is_looked) {
        this.is_looked = is_looked;
    }
}
