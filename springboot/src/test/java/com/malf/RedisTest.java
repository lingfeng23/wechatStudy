package com.malf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author malf
 * @description Redis 测试
 * @project springboot
 * @since 2020/11/17
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {
	@Resource
	private RedisTemplate redisTemplate;

	/**
	 * 测试 Redis 序列化乱码问题，RedisConfig 配置解决
	 */
	@Test
	public void messyCode() {
		redisTemplate.opsForValue().set("test:1", "1233");
		Object object = redisTemplate.opsForValue().get("test:1");
		System.out.println(object);
		System.out.println("success");
	}

}
