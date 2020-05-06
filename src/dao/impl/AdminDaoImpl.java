package dao.impl;

import config.SqlMappers;
import dao.AdminDao;
import domain.Article;
import domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 管理员持久层接口实现类
 * @User MOTI
 * @Time 2019/8/6 20:20
 */
@Repository("adminDaoImpl")
public class AdminDaoImpl extends BaseDaoImpl implements AdminDao {

    /**
     * 添加建议
     * @param content
     * @param user_name
     */
    @Override
    public void addSuggestion(String content, String user_name) {
        JdbcTemplate.update(SqlMappers.AdminSqlMapper.get("addSuggestion"),content,user_name,new Date());
    }

    /**
     * 获得用户总数
     * @return
     */
    @Override
    public int getUserCount() {
        List<User> list = JdbcTemplate.query(SqlMappers.AdminSqlMapper.get("getUserCount"),new BeanPropertyRowMapper<User>(User.class));
        return list.size();
    }

    /**
     * 获得文章总数
     * @return
     */
    @Override
    public int getArticleCount() {
        List<Article> list = JdbcTemplate.query(SqlMappers.AdminSqlMapper.get("getArticleCount"),new BeanPropertyRowMapper<Article>(Article.class));
        return list.size();
    }
}
