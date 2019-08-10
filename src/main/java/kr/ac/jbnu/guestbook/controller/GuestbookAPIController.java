package kr.ac.jbnu.guestbook.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jbnu.guestbook.dto.Guestbook;
import kr.ac.jbnu.guestbook.service.GuestbookService;

@RestController
@RequestMapping(path="/guestbook")
public class GuestbookAPIController {
	@Autowired
	GuestbookService guestbookService;
	
	@GetMapping
	public Map<String,Object> list(@RequestParam(name="start",required=false,defaultValue="0")int start){
		List<Guestbook> list= guestbookService.getGuestbooks(start);
		
		int count = guestbookService.getCount();
		int pageCount = count/guestbookService.LIMIT;
		if(count%GuestbookService.LIMIT>0)
			pageCount++;
		
		List<Integer> pageStartList = new ArrayList<Integer>();
		
		for(int i=0;i<pageCount;i++) {
			pageStartList.add(i*guestbookService.LIMIT);
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("list", list);
		map.put("count", count);
		map.put("pageStartList", pageStartList);
		return map;
	}
	
	@PostMapping
	public Guestbook write(@RequestBody Guestbook guestbook,HttpServletRequest request) {
		String ClientIP = request.getRemoteAddr();
		
		Guestbook resultGuestbook = guestbookService.addGuestbook(guestbook, ClientIP);
		return resultGuestbook;
	}
	
	@DeleteMapping(path="/{id}")
	public Map<String, String> delete(@PathVariable(name="id")long id, HttpServletRequest request){
		String ClientIP = request.getRemoteAddr();
		
		int deleteCount = guestbookService.deleteGuestbook(id, ClientIP);
		return Collections.singletonMap("success",deleteCount>0?"true":"false");
	}
}


