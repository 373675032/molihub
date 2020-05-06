package dao;

import domain.Comment;
import domain.User;

import java.util.List;

/**
 * 用户持久层接口
 * @User MOTI
 * @Time 2019/8/3 17:22
 */
public interface UserDao {

    /**
     * 向用户表添加用户
     */
    void addUser(User user);

    /**
     * 根据用户Id获得用户
     * @param userId
     * @return
     */
    User getUserById(int userId);

    /**
     * 根据用户名获得用户
     * @param userName
     * @return
     */
    User getUserByUserName(String userName);

    /**
     * 根据邮箱地址获得用户
     * @param email
     * @return
     */
    User getUserByEmail(String email);

    /**
     * 根据手机号获得用户
     * @param phone
     * @return
     */
    User getUserByPhone(String phone);

    /**
     * 根据用户名和密码获得用户
     * @param userName
     * @param password
     * @return
     */
    User getUserByUserNameAndPassword(String userName,String password);

    /**
     * 根据邮箱和密码获取对象
     * @param email
     * @param password
     * @return
     */
    User getUserByEmailAndPassword(String email,String password);

    /**
     * 根据手机号和密码获取对象
     * @param phone
     * @param password
     * @return
     */
    User getUserByPhoneAndPassword(String phone,String password);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 获得一篇文章的所有评论者
     * @param article_id
     * @return
     */
    List<Comment> getAllCommentUsers(int article_id);

    /**
     * 邮箱验证
     * @param code
     * @param userName
     */
    void updateUserStatus(String code ,String userName);

    /**
     * 未验证的邮箱
     * @param userName
     */
    void unValidateEmail(String userName);
}
