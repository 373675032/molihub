package dao;

import domain.Message;

import java.util.List;

/**
 * @User MOTI
 * @Time 2019/9/8 13:18
 */
public interface MessageDao {

    /**
     * 添加点赞的动态消息
     */
    void addLikeMessage(int sent_id,int accept_id,int article_id);

    /**
     *  添加评论的消息
     * @param send_id
     * @param accept_id
     * @param article_id
     * @param content
     */
    void addCommentMessage(int send_id,int accept_id,int article_id,String content);

    /**
     *  添加参与的消息
     * @param send_id
     * @param accept_id
     * @param article_id
     * @param content
     */
    void addOtherMessage(int send_id,int accept_id,int article_id,String content);

    /**
     * 全部设置为已读
     * @param accept_id
     */
    void setLooked(int accept_id);

    /**
     * 清空已读消息
     * @param accept_id
     */
    void deleteRead(int accept_id);

    /**
     * 删除点赞消息
     * @param send_id
     * @param accept_id
     * @param article_id
     */
    void deleteLikeMessage(int send_id,int accept_id,int article_id);

    /**
     * 获得所有的消息
     * @param user_id
     * @return
     */
    List<Message> getAllMessages(int user_id);

    /**
     * 删除关于文章的全部消息
     * @param article_id
     */
    void deleteArticleMessage(int article_id);
}
