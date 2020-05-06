package service.impl;

import domain.Comment;
import domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.UserService;
import utils.ActiveCodeUtils;
import utils.MailUtils;

import javax.mail.MessagingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户业务层接口实现类
 * @User MOTI
 * @Time 2019/8/3 17:23
 */
@Transactional
@Service("userServiceImpl")
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    /**
     * 添加用户
     * @param user
     */
    @Override
    public void addUser(User user) {
        ud.addUser(user);
    }

    /**
     * 根据ID获得用户
     * @param userId
     * @return
     */
    @Override
    public User getUserById(int userId) {
        return ud.getUserById(userId);
    }

    /**
     * 根据用户名获得用户
     * @param userName
     * @return
     */
    @Override
    public User getUserByUserName(String userName) {
        return ud.getUserByUserName(userName);
    }

    /**
     * 根据邮箱地址获得用户
     * @param email
     * @return
     */
    @Override
    public User getUserByEmail(String email) {
        return ud.getUserByEmail(email);
    }

    /**
     * 根据手机号获得用户
     * @param phone
     * @return
     */
    @Override
    public User getUserByPhone(String phone) {
        return ud.getUserByPhone(phone);
    }

    /**
     * 根据用户名和密码获得用户
     * @param userName
     * @param password
     * @return
     */
    @Override
    public User getUserByUserNameAndPassword(String userName, String password) {
        return ud.getUserByUserNameAndPassword(userName,password);
    }

    /**
     * 根据邮箱和密码获取对象
     * @param email
     * @param password
     * @return
     */
    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        return ud.getUserByEmailAndPassword(email,password);
    }

    /**
     * 根据手机号和密码获取对象
     * @param phone
     * @param password
     * @return
     */
    @Override
    public User getUserByPhoneAndPassword(String phone, String password) {
        return ud.getUserByPhoneAndPassword(phone,password);
    }

    /**
     * 更新用户
     * @param user
     */
    @Override
    public void updateUser(User user) {
        ud.updateUser(user);
    }

    /**
     * 获得一篇文章的所有评论者
     * @param article_id
     * @return
     */
    @Override
    public Set<Integer> getAllCommentUsersId(int article_id) {
        List<Comment> list = ud.getAllCommentUsers(article_id);
        Set<Integer> set = new HashSet<>();
        if(list == null) return set;
        for (int i = 0; i < list.size(); i++) {
            String user_name = list.get(i).getUser_name();
            User user = getUserByUserName(user_name);
            set.add(user.getUser_id());
        }
        return set;
    }

    @Override
    public void updateUserStatus(String code, String userName) {
        ud.updateUserStatus(code,userName);
    }

    @Override
    public void unValidateEmail(String userName) {
        ud.unValidateEmail(userName);
    }
}
