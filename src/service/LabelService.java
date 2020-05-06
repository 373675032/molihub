package service;

import domain.Label;

import java.util.List;

/**
 * 文章标签业务层接口
 * @User MOTI
 * @Time 2019/8/6 20:25
 */
public interface LabelService {

    /**
     * 根据用户ID获得该用户的所有个性标签
     * @param userId
     * @return
     */
    List<String> getAllLabelsByUserId(int userId);

    /**
     * 为用户添加标签
     * @param label
     * @param user_id
     */
    void addLabel(String label,int user_id);

    /**
     * 为用户删除标签
     * @param label
     * @param user_id
     */
    void deleteLabel(String label,int user_id);

    /**
     * 为用户重命名标签
     * @param oldLabel
     * @param newLabel
     * @param user_id
     */
    void updateLabel(String oldLabel,String newLabel,int user_id);

    /**
     * 查找标签
     * @param label
     * @param user_id
     */
    Label getLabel(String label,int user_id);

    /**
     * 获得标签对应的文章数
     * @param tag
     * @param user_id
     * @return
     */
    int getTagArticleCount(String tag,int user_id);

    /**
     * 获得标签对应的文章数
     * @param tag
     * @param user_id
     * @return
     */
    int getOtherTagArticleCount(String tag,int user_id);

    /**
     * 获得文章的标签
     * @param article_id
     * @return
     */
    String getArticleLabel(int article_id);
}
