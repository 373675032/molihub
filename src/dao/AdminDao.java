package dao;

/**
 * @User MOTI
 * @Time 2019/8/20 0:58
 */
public interface AdminDao {

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
