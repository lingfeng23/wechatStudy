package com.malf.service.impl;

import com.malf.annotation.Master;
import com.malf.entity.Member;
import com.malf.entity.MemberExample;
import com.malf.mapper.MemberMapper;
import com.malf.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author malf
 * @description TODO
 * @project springboot
 * @since 2020/11/17
 */
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberMapper memberMapper;

	@Transactional
	@Override
	public int insert(Member member) {
		return memberMapper.insert(member);
	}

	@Master
	@Override
	public int save(Member member) {
		return memberMapper.insert(member);
	}

	@Override
	public List<Member> selectAll() {
		return memberMapper.selectAll();
	}

	@Master
	@Override
	public String getToken(String appId) {
		//  有些读操作必须读主数据库
		//  比如，获取微信 access_token，因为高峰时期主从同步可能延迟
		//  这种情况下就必须强制从主数据读
		return null;
	}
}
