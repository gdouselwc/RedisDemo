package POJO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by liangwenchang on 2019/8/2.
 */
@Getter
@Setter
@ToString
public class UserModel implements Serializable{

    private static final long serialVersionUID = -2823533000307445849L;
    public String UserId;
    public String UserName;
    public String Phone;
}
