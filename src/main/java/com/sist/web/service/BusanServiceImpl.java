package com.sist.web.service;
import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.mapper.*;
import com.sist.web.vo.*;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class BusanServiceImpl implements BusanService{
   private final BusanMapper mapper;

   @Override
   public List<BusanVO> busanListData(Map map) {
	// TODO Auto-generated method stub
	return mapper.busanListData(map);
   }

   @Override
   public int busanTotalPage(Map map) {
	// TODO Auto-generated method stub
	return mapper.busanTotalPage(map);
   }
   
}
