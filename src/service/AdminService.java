package service;

import java.util.List;

/**
 * 管理员业务层接口
 * @User MOTI
 * @Time 2019/8/6 20:25
 */
public interface AdminService {

    /**
     * 添加建议
     * @param content
     * @param user_name
     */
    void addSuggestion(String content,String user_name);

    /**
     * 获得用户总数
     * @return
     */
    int getUserCount();

    /**
     * 获得文章总数
     * @return
     */
    int getArticleCount();

}
