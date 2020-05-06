package service.impl;

import domain.Label;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.AdminService;
import service.LabelService;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理员业务层实现类
 * @User MOTI
 * @Time 2019/8/6 20:27
 */
@Transactional
@Service("adminServiceImpl")
public class AdminServiceImpl extends BaseServiceImpl implements AdminService {

    /**
     * 添加建议
     * @param content
     * @param user_name
     */
    @Override
    public void addSuggestion(String content, String user_name) {
        adminDao.addSuggestion(content,user_name);
    }

    /**
     * 获得用户总数
     * @return
     */
    @Override
    public int getUserCount() {
        return adminDao.getUserCount();
    }

    /**
     * 获得文章总数
     * @return
     */
    @Override
    public int getArticleCount() {
        return adminDao.getArticleCount();
    }
}
