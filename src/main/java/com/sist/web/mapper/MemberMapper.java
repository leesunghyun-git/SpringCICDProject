package com.sist.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.vo.*;
@Mapper
@Repository
public interface MemberMapper {
	//ID체크
	@Select("SELECT COUNT(*) FROM member_2 WHERE id=#{id}")
	public int memberIdCheck(String id);
	// PWD
	@Select("SELECT pwd,name,id,sex,address FROM member_2 WHERE id=#{id}")
	public MemberVO memberInfoData(String id);
}
