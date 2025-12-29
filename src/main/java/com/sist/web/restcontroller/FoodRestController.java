package com.sist.web.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.web.service.*;
import com.sist.web.vo.*;

import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor
public class FoodRestController {
	private final FoodService fService;
	
	@GetMapping("/food/find_vue")
	public ResponseEntity<Map> find_vue(@RequestParam("page")int page,@RequestParam("address")String address)
	{
		Map map = new HashMap();
		try {
			List<FoodVO> list=fService.foodFindData((page-1)*12, address);
			int totalPage=fService.foodFindTotalPage(address);
			
			final int BLOCK=10;
			int startPage=((page-1)/BLOCK*BLOCK)+1;
			int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
			if (endPage>totalPage)
				endPage=totalPage;
			map.put("list", list);
			map.put("totalPage", totalPage);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
			map.put("curPage", page);
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@GetMapping("/food/detail_vue/")
	public ResponseEntity<FoodVO> food_detail_vue(@RequestParam("fno")int fno)
	{
		FoodVO vo = null;
		try {
			vo=fService.foodDetailData(fno);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(vo,HttpStatus.OK);
	}
}
