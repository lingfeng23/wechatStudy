package com.malf;

import com.malf.entity.Member;
import com.malf.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author malf
 * @description TODO
 * @project springboot
 * @since 2020/11/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourcesTest {
	@Autowired
	MemberService memberService;

	@Test
	public void testWrite() {
		Member member = new Member();
		member.setName("zhangsan");
		int num = memberService.insert(member);
		assert num == 1;
	}

	@Test
	public void testRead() {
		for (int i = 0; i < 4; i++) {
			List<Member> memberList = memberService.selectAll();
			System.out.println(memberList);
		}
	}

	@Test
	public void testSave() {
		Member member = new Member();
		member.setName("wangwu");
		memberService.save(member);
	}

}
