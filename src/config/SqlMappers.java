package config;

import java.util.HashMap;
import java.util.Map;

/**
 * @User MOTI
 * @Time 2019/8/3 17:35
 */
public class SqlMappers {
    //用户的sql语句集合
    public static Map<String,String> UserSqlMapper = new HashMap<>();
    public static Map<String,String> LabelSqlMapper = new HashMap<>();
    public static Map<String,String> ArticleSqlMapper = new HashMap<>();
    public static Map<String,String> AdminSqlMapper = new HashMap<>();
    public static Map<String,String> MessageSqlMapper = new HashMap<>();
    static {

        /*
            用户相关
         */

        //根据用户ID找到用户
        UserSqlMapper.put("getUserById","select * from user where user_id = ?");
        //更新用户
        UserSqlMapper.put("updateUser","update user set user_name = ?,password = ?,sex = ?,age = ?,email = ?,phone = ? where user_id = ?");
        //添加用户
        UserSqlMapper.put("addUser","insert into user(user_name,password,sex,age,email,phone,activecode) values (?,?,?,?,?,?,?)");
        //根据用户名获得用户
        UserSqlMapper.put("getUserByUserName","select * from user where user_name = ?");
        //根据手机号获得用户
        UserSqlMapper.put("getUserByPhone","select * from user where phone = ?");
        //根据邮箱地址获得用户
        UserSqlMapper.put("getUserByEmail","select * from user where email = ?");
        //根据用户名和密码获得用户
        UserSqlMapper.put("getUserByUserNameAndPassword","select * from user where user_name = ? and password = ?");
        //根据邮箱和密码获得用户
        UserSqlMapper.put("getUserByEmailAndPassword","select * from user where email = ? and password = ?");
        //根据手机号和密码获得用户
        UserSqlMapper.put("getUserByPhoneAndPassword","select * from user where phone = ? and password = ?");
        UserSqlMapper.put("getAllCommentUsers","select * from comment where article_id = ?");
        //通过邮箱验证
        UserSqlMapper.put("updateUserStatus","update user set status = 1 where activecode = ? and user_name = ?");
        //未验证的邮箱
        UserSqlMapper.put("unValidateEmail","update user set status = 0 where user_name = ?");
        /*
        标签相关
         */

        //根据用户ID获得所有标签
        LabelSqlMapper.put("getAllLabelsByUserId","select * from label where user_id = ?");
        //添加文章标签
        LabelSqlMapper.put("addLabel","insert into label(user_id,label_name) values (?,?)");
        //删除文章标签
        LabelSqlMapper.put("deleteLabel","delete from label where user_id = ? and label_name = ?");
        //获得用户的所有标签
        LabelSqlMapper.put("getLabel","select * from label where user_id = ? and label_name = ?");
        //获得某一篇文章的标签
        LabelSqlMapper.put("getArticleLabel","select * from article where article_id = ?");
        //更新标签
        LabelSqlMapper.put("updateLabel","UPDATE molihub.label set label_name = ? where label_name = ? and user_id = ?");

        /*
        文章相关
         */

        //添加文章
        ArticleSqlMapper.put("addArticle","insert into article(user_id,article_kind,article_title,article_content,article_time,article_tag,is_private,read_count) values (?,?,?,?,?,?,?,?)");
        //重新编辑文章
        ArticleSqlMapper.put("updateArticle","UPDATE molihub.article set article_title = ? , article_content = ? , article_kind = ? , article_tag = ? , is_private = ? where article_id = ?");
        //分页获得所有文章
        ArticleSqlMapper.put("getAllArticleByLimit","SELECT * FROM molihub.article where is_private = 'no' ORDER BY article_time DESC  LIMIT ? , ?;");
        //分页获得用户的所有文章(我的主页)
        ArticleSqlMapper.put("getMyArticleByLimit","SELECT * FROM molihub.article where user_id = ? ORDER BY article_time DESC  LIMIT ? , ?;");
        //分页获得他人的所有文章
        ArticleSqlMapper.put("getOtherArticleByLimit","SELECT * FROM molihub.article where user_id = ? and  is_private = 'no' ORDER BY article_time DESC  LIMIT ? , ?;");
        //根据文章ID获得文章
        ArticleSqlMapper.put("getArticleById","SELECT * FROM molihub.article where article_id = ?");
        //获得所有公开文章
        ArticleSqlMapper.put("getAllPublishArticles","SELECT * FROM article where is_private = 'no'");
        //获得我的所有文章
        ArticleSqlMapper.put("getAllMyArticles","SELECT * FROM article where user_id = ?");
        //获得他人的公开文章
        ArticleSqlMapper.put("getAllOtherPublicArticles","SELECT * FROM article where user_id = ? and is_private = 'no'");
        //根据类别获得我的所有文章
        ArticleSqlMapper.put("getMyArticlesByKind","SELECT * FROM article where user_id = ? AND article_kind = ? ");
        //根据类别获得他人的所有公开文章
        ArticleSqlMapper.put("getOtherArticlesByKind","SELECT * FROM article where user_id = ? AND article_kind = ? and is_private = 'no'");
        //添加评论
        ArticleSqlMapper.put("addComment","insert into comment(article_id,user_name,comment_content,comment_time) values (?,?,?,?)");
        //根据文章ID获得所有评论
        ArticleSqlMapper.put("getAllCommentById","SELECT * FROM molihub.comment where article_id = ?");
        //增加文章的阅读数
        ArticleSqlMapper.put("addArticleReadCount","UPDATE molihub.article SET read_count = (read_count+1) WHERE article_id = ?");
        //添加喜欢的文章
        ArticleSqlMapper.put("addLike","INSERT INTO molihub.like (user_id, article_id) VALUES(?, ?)");
        //删除喜欢的文章
        ArticleSqlMapper.put("deleteLike","DELETE FROM molihub.like WHERE article_id = ? AND user_id = ?");
        //判断是不是已经点赞
        ArticleSqlMapper.put("isLiked","select * from molihub.like where article_id = ? and user_id = ?");
        //根据文章id获得点赞数
        ArticleSqlMapper.put("getLikeCount","select * from molihub.like where article_id = ?");
        //获得指定用户id的所有文章
        ArticleSqlMapper.put("getAllArticleByUserId","SELECT * FROM molihub.article WHERE is_private = 'no' AND user_id = ? ORDER BY article_time DESC");
        //根据类别查找文章
        ArticleSqlMapper.put("getAllArticleByKind","SELECT * FROM molihub.article WHERE is_private = 'no' AND article_kind = ? ORDER BY article_time DESC");
        //根据标题进行模糊查找
        ArticleSqlMapper.put("getAllArticleByTitle","SELECT * FROM molihub.article WHERE is_private = 'no' AND article_title LIKE ? ORDER BY article_time DESC");
        //根据标题进行模糊查找我的文章
        ArticleSqlMapper.put("getMyArticleByTitle","SELECT * FROM molihub.article WHERE user_id = ? AND article_title LIKE ? ORDER BY article_time DESC");
        //根据标题进行模糊查找他人的公开文章
        ArticleSqlMapper.put("getOtherArticleByTitle","SELECT * FROM molihub.article WHERE user_id = ? AND article_title LIKE ? and  is_private = 'no' ORDER BY article_time DESC");
        //获得阅读排行榜(10)文章
        ArticleSqlMapper.put("getTopByRead","SELECT * FROM molihub.article WHERE is_private = 'no' ORDER BY read_count DESC LIMIT 0 , 10");
        //获得阅读排行榜(10)文章
        ArticleSqlMapper.put("getMyTopByRead","SELECT * FROM molihub.article WHERE user_id = ? and  is_private = 'no' ORDER BY read_count DESC LIMIT 0 , 5");
        //获得人气排行榜(10)文章ID
        ArticleSqlMapper.put("getTopArticlesIdByLike","SELECT * FROM molihub.like GROUP BY article_id ORDER BY COUNT(article_id) DESC LIMIT 0,10;");
        //根据标签获得我的文章
        ArticleSqlMapper.put("getMyArticlesByTag","SELECT * FROM molihub.article where user_id = ? and article_tag like ?");
        //根据标签获得他人的公开文章
        ArticleSqlMapper.put("getOtherArticlesByTag","SELECT * FROM molihub.article where user_id = ? and article_tag like ?  and  is_private = 'no'");
        //更新文章的标签
        ArticleSqlMapper.put("updateArticleTag","UPDATE molihub.article set article_tag = ? where article_id = ?");
        //删除文章
        ArticleSqlMapper.put("deleteArticle","delete from article where article_id = ?");
        //删除评论
        ArticleSqlMapper.put("deleteComments","delete from comment where article_id = ?");
        //删除点赞
        ArticleSqlMapper.put("deleteLikes","delete from molihub.like where article_id = ?");
        //更新评论表的用户名
        ArticleSqlMapper.put("updateComment","UPDATE molihub.comment set user_name = ? where user_name = ?");
        //根据文章id获得作者id
        ArticleSqlMapper.put("getUserIdByArticleId","SELECT * FROM molihub.article where article_id = ?");
        //获得我的文章总数
        ArticleSqlMapper.put("getMyArticleCount","SELECT * FROM molihub.article where user_id = ?");
        //获得他人的文章总数
        ArticleSqlMapper.put("getOtherArticleCount","SELECT * FROM molihub.article where user_id = ? and is_private = 'no'");

        /*
        管理员相关
         */

        //添加建议
        AdminSqlMapper.put("addSuggestion","insert into suggest(content,user_name,time) values (?,?,?)");
        //获得注册用户的总数
        AdminSqlMapper.put("getUserCount","select * from user");
        //获得文章的总数
        AdminSqlMapper.put("getArticleCount","select * from article");

        /*
        消息相关
         */

        //添加点赞消息
        MessageSqlMapper.put("addLikeMessage","insert into message(send_id,accept_id,article_id,message_time,message_type) values (?,?,?,?,?)");
        //添加评论消息
        MessageSqlMapper.put("addCommentMessage","insert into message(send_id,accept_id,article_id,comment_content,message_time,message_type) values (?,?,?,?,?,?)");
        //全部设置为已读
        MessageSqlMapper.put("setLooked","UPDATE molihub.message set is_looked = 'yes' where accept_id = ?");
        //根据类别删除
        MessageSqlMapper.put("deleteMessage","delete from molihub.message where accept_id = ? and is_looked = 'yes'");
        //删除点赞消息
        MessageSqlMapper.put("deleteLikeMessage","delete from molihub.message where send_id = ? and accept_id = ? and article_id = ? and message_type = ?");
        //删除点赞消息
        MessageSqlMapper.put("getAllMessages","select * from message where accept_id = ?");
        //删除关于文章的全部消息
        MessageSqlMapper.put("deleteArticleMessage","delete from molihub.message where article_id = ?");

    }
}
