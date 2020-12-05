package com.powernode;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestJedis {
    public static void main(String[] args) {
        // 创建jedis对象
        //Jedis jedis = new Jedis("192.168.174.128", 6379);
        JedisPool jedisPool = RedisUtils.open("192.168.174.128", 6379);
        Jedis jedis = jedisPool.getResource();
        jedis.flushDB();
        jedis.set("str1","aaaa");
        jedis.set("str2","bbbb");
        String str1 = jedis.get("str1");
        System.out.println(str1);
        System.out.println("================");
        Map<String, String> map = new HashMap<String, String>();
        map.put("id","10001");
        map.put("name","陈冠希");
        map.put("age","35");
        jedis.hmset("student",map);

        List<String> hmget = jedis.hmget("student", "id", "name", "age");

        for (String s : hmget) {
            System.out.println(s);
        }

        jedisPool.close();
    }
}
