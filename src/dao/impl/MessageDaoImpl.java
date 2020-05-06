package dao.impl;

import config.SqlMappers;
import dao.MessageDao;
import domain.Article;
import domain.Message;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 消息持久层接口实现类
 * @User MOTI
 * @Time 2019/9/8 13:18
 */
@Repository("messageDaoImpl")
public class MessageDaoImpl extends BaseDaoImpl implements MessageDao {

    /**
     * 添加点赞的动态消息
     */
    @Override
    public void addLikeMessage(int sent_id,int accept_id,int article_id) {
        JdbcTemplate.update(SqlMappers.MessageSqlMapper.get("addLikeMessage"),sent_id,accept_id,article_id,new Date(),1);
    }

    /**
     *  添加评论的消息
     * @param send_id
     * @param accept_id
     * @param article_id
     * @param content
     */
    @Override
    public void addCommentMessage(int send_id, int accept_id, int article_id, String content) {
        JdbcTemplate.update(SqlMappers.MessageSqlMapper.get("addCommentMessage"),send_id,accept_id,article_id,content,new Date(),2);
    }

    /**
     *  添加参与的消息
     * @param send_id
     * @param accept_id
     * @param article_id
     * @param content
     */
    @Override
    public void addOtherMessage(int send_id, int accept_id, int article_id, String content) {
        JdbcTemplate.update(SqlMappers.MessageSqlMapper.get("addCommentMessage"),send_id,accept_id,article_id,content,new Date(),3);
    }

    /**
     * 全部设置为已读
     * @param accept_id
     */
    @Override
    public void setLooked(int accept_id) {
        JdbcTemplate.update(SqlMappers.MessageSqlMapper.get("setLooked"),accept_id);
    }

    /**
     * 清空已读消息
     * @param accept_id
     */
    @Override
    public void deleteRead(int accept_id) {
        JdbcTemplate.update(SqlMappers.MessageSqlMapper.get("deleteMessage"),accept_id);
    }

    /**
     * 删除点赞消息
     * @param send_id
     * @param accept_id
     * @param article_id
     */
    @Override
    public void deleteLikeMessage(int send_id, int accept_id, int article_id) {
        JdbcTemplate.update(SqlMappers.MessageSqlMapper.get("deleteLikeMessage"),send_id,accept_id,article_id,1);
    }

    /**
     * 获得所有的消息
     * @param user_id
     * @return
     */
    @Override
    public List<Message> getAllMessages(int user_id) {
        List<Message> list = JdbcTemplate.query(SqlMappers.MessageSqlMapper.get("getAllMessages"),new BeanPropertyRowMapper<Message>(Message.class),user_id);
        if(list.size() == 0){
            return null;
        }
        return list;
    }

    /**
     * 删除关于文章的全部消息
     * @param article_id
     */
    @Override
    public void deleteArticleMessage(int article_id) {
        JdbcTemplate.update(SqlMappers.MessageSqlMapper.get("deleteArticleMessage"),article_id);
    }
}
