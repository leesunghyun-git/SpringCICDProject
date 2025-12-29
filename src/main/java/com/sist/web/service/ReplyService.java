package com.sist.web.service;

import java.util.List;
import java.util.Map;

import com.sist.web.vo.ReplyVO;

public interface ReplyService {
	public List<ReplyVO> replyListData(Map map);
	public void replyInsert(ReplyVO vo);
}
