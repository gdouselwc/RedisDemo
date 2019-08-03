package DAO;

import POJO.UserModel;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by liangwenchang on 2019/8/3.
 */
public class RedisDao {

    private final JedisPool jedisPool;

    public RedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    private RuntimeSchema<UserModel> schema = RuntimeSchema.createFrom(UserModel.class);

    public String SetUser2Redis(UserModel userModel) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String key = userModel.getUserId();
            //序列化
            byte[] bytes = ProtobufIOUtil.toByteArray(userModel, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            //超时缓存
            int timeOut = 60 * 60;

            String result = jedis.setex(key.getBytes(), timeOut, bytes);
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public UserModel GetUser(String userId) {
        UserModel userModel = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();

            byte[] bytes = jedis.get(userId.getBytes());

            if (bytes != null) {
                userModel = schema.newMessage();
                //反序列化
                ProtobufIOUtil.mergeFrom(bytes, userModel, schema);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }

        return userModel;
    }

}
