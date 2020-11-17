package com.malf.mapper;

import com.malf.entity.Member;
import com.malf.entity.MemberExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author malf
 * @description TODO
 * @project springboot
 * @since 2020/11/17
 */
@Mapper
public interface MemberMapper {
	int insert(Member member);

	List<Member> selectAll();

}
