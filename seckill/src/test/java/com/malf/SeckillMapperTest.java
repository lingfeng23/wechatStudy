package com.malf;

import com.malf.dao.SeckillMapper;
import com.malf.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author malf
 * @description TODO
 * @project seckill
 * @since 2020/11/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-dao.xml"})
public class SeckillMapperTest {
	@Resource
	private SeckillMapper seckillMapper;

	@Test
	public void reduceNumber() throws Exception {
		long seckillId = 1000;
		Date date = new Date();
		System.out.println(seckillMapper);
		System.out.println(date);
		int i = seckillMapper.reduceNumber(seckillId, date);
		System.out.println(i);
	}

	@Test
	public void queryById() throws Exception {
		long seckillId = 1000;
		Seckill seckill = seckillMapper.queryById(seckillId);
		System.out.println(seckill.toString());
	}

	@Test
	public void queryAll() throws Exception {
		List<Seckill> seckills = seckillMapper.queryAll(0, 100);
		for (Seckill seckill : seckills) {
			System.out.println(seckill.toString());
		}
	}

}
