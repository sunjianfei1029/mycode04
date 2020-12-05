package com.powernode;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 *  jedis事务的操作
 */
public class TestJedis2 {

    public static void main(String[] args) {
        JedisPool jedisPool = RedisUtils.open("192.168.174.128", 6379);
        Jedis jedis = jedisPool.getResource();
        jedis.flushDB();
        // 开启事务
        Transaction multi = jedis.multi();
        multi.set("str1","aaa");
        multi.set("str2","bbb");

        // 提交事务
        List<Object> list = multi.exec();

        for (Object o : list) {
            System.out.println(o);
        }


        // 关闭连接池
        jedisPool.close();
    }

}
