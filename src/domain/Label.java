package domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 文章标签实体类
 * @User MOTI
 * @Time 2019/8/6 20:17
 */
@Component
public class Label  implements Serializable {
    //标签ID
    private int label_id;
    //标签作者
    private int user_id;
    //标签名
    private String label_name;

    public Label() {
    }

    @Override
    public String toString() {
        return "Label{" +
                "label_id=" + label_id +
                ", user_id=" + user_id +
                ", label_name='" + label_name + '\'' +
                '}';
    }

    public int getLabel_id() {
        return label_id;
    }

    public void setLabel_id(int label_id) {
        this.label_id = label_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getLabel_name() {
        return label_name;
    }

    public void setLabel_name(String label_name) {
        this.label_name = label_name;
    }
}
