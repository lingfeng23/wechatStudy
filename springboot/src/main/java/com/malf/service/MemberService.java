package com.malf.service;

import com.malf.entity.Member;

import java.util.List;

/**
 * @author malf
 * @description TODO
 * @project springboot
 * @since 2020/11/17
 */
public interface MemberService {
	public int insert(Member member);

	public int save(Member member);

	public List<Member> selectAll();

	public String getToken(String appId);

}
