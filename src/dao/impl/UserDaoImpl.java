package dao.impl;

import config.SqlMappers;
import dao.UserDao;
import domain.Comment;
import domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import utils.ActiveCodeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户持久层接口实现类
 * @User MOTI
 * @Time 2019/8/3 17:23
 */
@Repository("userDaoImpl")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    /**
     * 向用户表添加用户
     */
    @Override
    public void addUser(User user) {
        JdbcTemplate.update(SqlMappers.UserSqlMapper.get("addUser"),
                user.getUser_name(),user.getPassword(),(user.getSex()).toString(),user.getAge(),user.getEmail(),user.getPhone(), user.getActivecode());
    }

    /**
     * 根据用户Id获得用户
     * @param userId
     * @return
     */
    @Override
    public User getUserById(int userId) {
        List<User> list = JdbcTemplate.query(SqlMappers.UserSqlMapper.get("getUserById"),new BeanPropertyRowMapper<User>(User.class),userId);
        if(list.size() == 0){
            return null;
        }else {
            return list.get(0);
        }
    }

    /**
     * 根据用户名获得用户
     * @param userName
     * @return
     */
    @Override
    public User getUserByUserName(String userName) {
        List<User> list = JdbcTemplate.query(SqlMappers.UserSqlMapper.get("getUserByUserName"),new BeanPropertyRowMapper<User>(User.class),userName);
        if(list.size() == 0){
            return null;
        }
        return list.get(0);
    }

    /**
     * 根据邮箱地址获得用户
     * @param email
     * @return
     */
    @Override
    public User getUserByEmail(String email) {
        List<User> list = JdbcTemplate.query(SqlMappers.UserSqlMapper.get("getUserByEmail"),new BeanPropertyRowMapper<User>(User.class),email);
        if(list.size() == 0){
            return null;
        }
        return list.get(0);
    }

    /**
     * 根据手机号获得用户
     * @param phone
     * @return
     */
    @Override
    public User getUserByPhone(String phone) {
        List<User> list = JdbcTemplate.query(SqlMappers.UserSqlMapper.get("getUserByPhone"),new BeanPropertyRowMapper<User>(User.class),phone);
        if(list.size() == 0){
            return null;
        }
        return list.get(0);
    }

    /**
     * 根据用户名和密码获得用户
     * @param userName
     * @param password
     * @return
     */
    @Override
    public User getUserByUserNameAndPassword(String userName, String password) {
        List<User> list = JdbcTemplate.query(SqlMappers.UserSqlMapper.get("getUserByUserNameAndPassword"),new BeanPropertyRowMapper<User>(User.class),userName,password);
        if(list.size() == 0){
            return null;
        }
        return list.get(0);
    }

    /**
     * 根据邮箱和密码获取对象
     * @param email
     * @param password
     * @return
     */
    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        List<User> list = JdbcTemplate.query(SqlMappers.UserSqlMapper.get("getUserByEmailAndPassword"),new BeanPropertyRowMapper<User>(User.class),email,password);
        if(list.size() == 0){
            return null;
        }
        return list.get(0);
    }

    /**
     * 根据手机号和密码获取对象
     * @param phone
     * @param password
     * @return
     */
    @Override
    public User getUserByPhoneAndPassword(String phone, String password) {
        List<User> list = JdbcTemplate.query(SqlMappers.UserSqlMapper.get("getUserByPhoneAndPassword"),new BeanPropertyRowMapper<User>(User.class),phone,password);
        if(list.size() == 0){
            return null;
        }
        return list.get(0);
    }

    /**
     * 更新用户
     * @param user
     */
    @Override
    public void updateUser(User user) {
        JdbcTemplate.update(SqlMappers.UserSqlMapper.get("updateUser"),
            user.getUser_name(),user.getPassword(),(user.getSex()).toString(),user.getAge(),user.getEmail(),user.getPhone(),user.getUser_id());
    }

    /**
     * 获得一篇文章的所有评论者
     * @param article_id
     * @return
     */
    @Override
    public List<Comment> getAllCommentUsers(int article_id) {
        List<Comment> list = JdbcTemplate.query(SqlMappers.UserSqlMapper.get("getAllCommentUsers"),new BeanPropertyRowMapper<Comment>(Comment.class),article_id);
        if(list.size() == 0){
            return null;
        }
        return list;
    }

    @Override
    public void updateUserStatus(String code, String userName) {
        JdbcTemplate.update(SqlMappers.UserSqlMapper.get("updateUserStatus"),code,userName);
    }

    @Override
    public void unValidateEmail(String userName) {
        JdbcTemplate.update(SqlMappers.UserSqlMapper.get("unValidateEmail"),userName);
    }
}
