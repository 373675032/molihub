package service.impl;

import dao.LabelDao;
import domain.Article;
import domain.ArticleToShow;
import domain.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.LabelService;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章标签业务层实现类
 * @User MOTI
 * @Time 2019/8/6 20:27
 */
@Transactional
@Service("labelServiceImpl")
public class LabelServiceImpl extends BaseServiceImpl implements LabelService {

    /**
     * 根据用户ID获得该用户的所有个性标签
     * @param userId
     * @return
     */
    @Override
    public List<String> getAllLabelsByUserId(int userId) {
        List<String> result = new ArrayList<>();
        List<Label> list = ld.getAllLabelsByUserId(userId);
        for (Label temp: list ) {
            result.add(temp.getLabel_name());
        }
        return result;
    }

    /**
     * 为用户添加标签
     * @param label
     * @param user_id
     */
    @Override
    public void addLabel(String label, int user_id) {
        ld.addLabel(label,user_id);
    }

    /**
     * 为用户删除标签
     * @param label
     * @param user_id
     */
    @Override
    public void deleteLabel(String label, int user_id) {
        List<Article> list = ad.getMyArticlesByTag(label,user_id);
        if(list != null){
            for (Article a:list
            ) {
                int id =  a.getArticle_id();
                String oldTag = ld.getArticleLabel(id);
                String newTag = oldTag.replaceAll("/"+label,"");
                ad.updateArticleTag(newTag,id);
            }
        }
        ld.deleteLabel(label,user_id);
    }

    /**
     * 为用户重命名标签
     * @param oldLabel
     * @param newLabel
     * @param user_id
     */
    @Override
    public void updateLabel(String oldLabel, String newLabel, int user_id) {
        List<Article> list = ad.getMyArticlesByTag(oldLabel,user_id);
        if(list != null){
            for (Article a:list
            ) {
                int id =  a.getArticle_id();
                String oldTag = ld.getArticleLabel(id);
                String newTag = oldTag.replaceAll(oldLabel,newLabel);
                ad.updateArticleTag(newTag,id);
            }
        }
        ld.updateLabel(oldLabel,newLabel,user_id);
    }

    /**
     * 查找标签
     * @param label
     * @param user_id
     */
    @Override
    public Label getLabel(String label, int user_id) {
        return ld.getLabel(label,user_id);
    }

    /**
     * 获得标签对应的文章数
     * @param tag
     * @param user_id
     * @return
     */
    @Override
    public int getTagArticleCount(String tag, int user_id) {
        List<Article> list = ad.getMyArticlesByTag(tag,user_id);
        if(list == null){
            return 0;
        }
        return list.size();
    }

    /**
     * 获得标签对应的文章数
     * @param tag
     * @param user_id
     * @return
     */
    @Override
    public int getOtherTagArticleCount(String tag, int user_id) {
        List<Article> list = ad.getOtherArticlesByTag(tag,user_id);
        if(list == null){
            return 0;
        }
        return list.size();
    }

    /**
     * 获得文章的标签
     * @param article_id
     * @return
     */
    @Override
    public String getArticleLabel(int article_id) {
        return ld.getArticleLabel(article_id);
    }
}
