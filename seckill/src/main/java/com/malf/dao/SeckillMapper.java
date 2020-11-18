package com.malf.dao;

import com.malf.entity.Seckill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author malf
 * @description TODO
 * @project seckill
 * @since 2020/11/17
 */
@Mapper
public interface SeckillMapper {
	/**
	 * 根据 seckillId 减少商品的库存。
	 *
	 * @param seckillId 秒杀商品ID
	 * @param killTime  秒杀的精确时间
	 * @return 如果秒杀成功就返回1, 否则就返回0
	 */
	int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

	/**
	 * 根据 seckillId 查询秒杀商品的详情。
	 *
	 * @param seckillId 秒杀商品ID
	 * @return 对应商品ID的的数据
	 */
	Seckill queryById(@Param("seckillId") long seckillId);

	/**
	 * 根据一个偏移量去查询秒杀的商品列表.
	 *
	 * @param offset 偏移量
	 * @param limit  限制查询的数据个数
	 * @return 符合偏移量查出来的数据个数
	 */
	List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
