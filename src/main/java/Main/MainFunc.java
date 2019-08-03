package command;

import DAO.RedisDao;
import POJO.UserModel;

/**
 * Created by liangwenchang on 2019/8/1.
 */
public class MainFunc {

    public static void main(String[] args){

        UserModel userModel = new UserModel();
        userModel.setUserId("123");
        userModel.setUserName("hello");
        userModel.setPhone("123456");

        RedisDao redisDao = new RedisDao("192.168.0.108",6379);
        redisDao.SetUser2Redis(userModel);

        UserModel result = redisDao.GetUser("123");
        System.out.println(result.toString());
    }
}
