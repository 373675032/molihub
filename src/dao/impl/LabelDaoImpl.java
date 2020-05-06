package dao.impl;

import config.SqlMappers;
import dao.LabelDao;
import domain.Article;
import domain.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文章标签持久层接口实现类
 * @User MOTI
 * @Time 2019/8/6 20:20
 */
@Repository("labelDaoImpl")
public class LabelDaoImpl extends BaseDaoImpl implements LabelDao {

    /**
     * 根据用户ID获得该用户的所有个性标签
     * @param userId
     * @return
     */
    @Override
    public List<Label> getAllLabelsByUserId(int userId) {
        List<Label> list = JdbcTemplate.query(SqlMappers.LabelSqlMapper.get("getAllLabelsByUserId"),new BeanPropertyRowMapper<Label>(Label.class),userId);
        return list;
    }

    /**
     * 为用户添加标签
     * @param label
     * @param user_id
     */
    @Override
    public void addLabel(String label, int user_id) {
        JdbcTemplate.update(SqlMappers.LabelSqlMapper.get("addLabel"),user_id,label);
    }

    /**
     * 为用户删除标签
     * @param label
     * @param user_id
     */
    @Override
    public void deleteLabel(String label, int user_id) {
        JdbcTemplate.update(SqlMappers.LabelSqlMapper.get("deleteLabel"),user_id,label);
    }

    /**
     * 更新标签名
     * @param oldLabel
     * @param newLabel
     * @param user_id
     */
    @Override
    public void updateLabel(String oldLabel, String newLabel, int user_id) {
        JdbcTemplate.update(SqlMappers.LabelSqlMapper.get("updateLabel"),newLabel,oldLabel,user_id);
    }

    /**
     * 查找标签
     * @param label
     * @param user_id
     */
    @Override
    public Label getLabel(String label, int user_id) {
       List<Label> list = JdbcTemplate.query(SqlMappers.LabelSqlMapper.get("getLabel"),new BeanPropertyRowMapper<Label>(Label.class),user_id,label);
        if(list.size() == 0){
            return null;
        }
        return list.get(0);
    }

    /**
     * 获得文章的标签
     * @param article_id
     * @return
     */
    @Override
    public String getArticleLabel(int article_id) {
        List<Article> list = JdbcTemplate.query(SqlMappers.LabelSqlMapper.get("getArticleLabel"),new BeanPropertyRowMapper<Article>(Article.class),article_id);
        if(list.size() == 0){
            return null;
        }
        return list.get(0).getArticle_tag();
    }

}
