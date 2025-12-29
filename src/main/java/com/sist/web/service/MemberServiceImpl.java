package com.sist.web.service;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.MemberMapper;
import com.sist.web.vo.MemberVO;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	private final MemberMapper mMapper;
	@Override
	public MemberVO isLogin(String id, String pwd) {
		MemberVO vo=new MemberVO();
		String res="";
		int count=mMapper.memberIdCheck(id);
		if(count==0)
		{
			vo.setMsg("NOID");
		}
		else {
			MemberVO dbVO=mMapper.memberInfoData(id);
			if(pwd.equals(dbVO.getPwd()))
			{
				vo.setMsg("OK");
				vo.setId(dbVO.getId());
				vo.setSex(dbVO.getSex());
				vo.setAddress(dbVO.getAddress());
				vo.setName(dbVO.getName());
			}
			else {
				vo.setMsg("NOPWD");
			}
		}
		return vo;
	}

}
