package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.ReplyMapper;
import com.sist.web.vo.ReplyVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{
	private final ReplyMapper rMapper;
	
	@Override
	public List<ReplyVO> replyListData(Map map) {
		// TODO Auto-generated method stub
		return rMapper.replyListData(map);
	}
	@Override
	public void replyInsert(ReplyVO vo) {
		// TODO Auto-generated method stub
		rMapper.replyInsert(vo);
	}
	
}
