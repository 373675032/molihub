package domain;

/**
 * 点赞实体类
 * @User MOTI
 * @Time 2019/8/19 23:42
 */
public class Like {
    private int like_id;
    private int user_id;
    private int article_id;

    @Override
    public String toString() {
        return "Like{" +
                "like_id=" + like_id +
                ", user_id=" + user_id +
                ", article_id=" + article_id +
                '}';
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public int getLike_id() {
        return like_id;
    }

    public void setLike_id(int like_id) {
        this.like_id = like_id;
    }

    public Like() {
    }
}
