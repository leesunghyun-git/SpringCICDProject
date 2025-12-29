package com.sist.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.ReplyVO;

@Mapper
@Repository
public interface ReplyMapper {
	/*
	 * @Select("SELECT no,cno,type,id,name,msg,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday FROM comment_2 WHERE cno=#{cno} AND type=#{type} ORDER BY no DESC"
	 * )
	 */
	public List<ReplyVO> replyListData(Map map);
	
	@Insert("INSERT INTO comment_2 VALUES((SELECT NVL(MAX(no)+1,1) FROM comment_2),#{cno},#{type},#{id},#{name},#{msg},SYSDATE)")
	public void replyInsert(ReplyVO vo);
}
