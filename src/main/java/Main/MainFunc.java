package command;

import DAO.RedisDao;
import POJO.UserModel;

/**
 * Created by liangwenchang on 2019/8/1.
 */
public class MainFunc {

    public static void main(String[] args){

        UserModel userModel = new UserModel();
        userModel.setUserId("80246303");
        userModel.setUserName("hello");
        userModel.setPhone("18138447785");

        RedisDao redisDao = new RedisDao("192.168.0.108",6379);
        //redisDao.SetUser2Redis(userModel);

        UserModel result = redisDao.GetUser("80246303");
        System.out.println(result.toString());
    }
}
