package com.sist.web.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.ReplyService;
import com.sist.web.vo.ReplyVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReplyRestController {
	private final ReplyService rService;
	
	@GetMapping("/reply/list_vue/")
	public ResponseEntity<Map> reply_list_vue(@RequestParam("cno")int cno,@RequestParam("type")int type,HttpSession session)
	{
		Map map = new HashMap<>();
		try {
			map.put("cno", cno);
			map.put("type", type);
			List<ReplyVO> list= rService.replyListData(map);
			map = new HashMap();
			map.put("rList", list);
			map.put("cno", cno);
			map.put("sessionId", (String)session.getAttribute("id"));
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@PostMapping("/reply/lnsert_vue/")
	public ResponseEntity<Map> reply_insert_vue(@RequestBody ReplyVO vo,HttpSession session)
	{
		Map map = new HashMap();
		try {
			String id = (String)session.getAttribute("id");
			String name = (String)session.getAttribute("name");
			vo.setId(id);
			vo.setName(name);
			rService.replyInsert(vo);
			map.put("cno", vo.getCno());
			map.put("type",vo.getType());
			List<ReplyVO> list=rService.replyListData(map);
			map = new HashMap();
			map.put("rList", list);
			map.put("cno",vo.getCno());
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
