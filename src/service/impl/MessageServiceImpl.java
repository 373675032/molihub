package service.impl;

import domain.Message;
import domain.MessageToShow;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.MessageService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @User MOTI
 * @Time 2019/9/8 13:26
 */
@Transactional
@Service("messageServiceImpl")
public class MessageServiceImpl extends BaseServiceImpl implements MessageService {

    /**
     * 添加点赞的动态消息
     */
    @Override
    public void addLikeMessage(int sent_id, int accept_id, int article_id) {
        md.addLikeMessage(sent_id,accept_id,article_id);
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
        md.addCommentMessage(send_id,accept_id,article_id,content);
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
        md.addOtherMessage(send_id, accept_id, article_id, content);
    }

    /**
     * 全部设置为已读
     * @param accept_id
     */
    @Override
    public void setLooked(int accept_id) {
        md.setLooked(accept_id);
    }

    /**
     * 清空已读消息
     * @param accept_id
     */
    @Override
    public void deleteRead(int accept_id) {
        md.deleteRead(accept_id);
    }

    /**
     * 删除点赞消息
     * @param send_id
     * @param accept_id
     * @param article_id
     */
    @Override
    public void deleteLikeMessage(int send_id, int accept_id, int article_id) {
        md.deleteLikeMessage(send_id, accept_id, article_id);
    }

    /**
     * 获得所有的消息
     * @param user_id
     * @return
     */
    @Override
    public List<MessageToShow> getAllMessages(int user_id) {
        List<Message> list = md.getAllMessages(user_id);
        List<MessageToShow> result = new ArrayList<>();
        if(list == null || list.size() == 0) return result;
        for (int i = 0; i < list.size(); i++) {
            MessageToShow temp = transformMessage(list.get(i));
            result.add(temp);
        }
        return result;
    }

    /**
     * 删除关于文章的全部消息
     * @param article_id
     */
    @Override
    public void deleteArticleMessage(int article_id) {
        md.deleteArticleMessage(article_id);
    }

    /**
     * 转换Message实体类
     * @param message
     * @return
     */
    private MessageToShow transformMessage(Message message){
        MessageToShow messageToShow = new MessageToShow();
        messageToShow.setArticle_id(message.getArticle_id());
        messageToShow.setArticle_name(ad.getArticleById(message.getArticle_id()).getArticle_title());
        messageToShow.setComment_content(message.getComment_content());
        messageToShow.setIs_looked(message.getIs_looked());
        messageToShow.setMessage_type(message.getMessage_type());
        messageToShow.setUser_name(ud.getUserById(message.getSend_id()).getUser_name());
        messageToShow.setMessage_time(formatTime(message.getMessage_time().getTime()));
        return messageToShow;
    }

    /**
     * 格式化Long型日期
     * @param time
     * @return
     */
    public String formatTime(long time){
        Date date = new Date(time);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String result = sd.format(date);
        return result;
    }
}
