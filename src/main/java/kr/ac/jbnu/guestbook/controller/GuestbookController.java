package kr.ac.jbnu.guestbook.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.jbnu.guestbook.dto.Guestbook;
import kr.ac.jbnu.guestbook.service.GuestbookService;

@Controller
public class GuestbookController {
	
	@Autowired
	GuestbookService guestbookService;
	
	@GetMapping(path="/list")
	public String list(@RequestParam(name="start",required=false,defaultValue="0")int start,ModelMap model) {
		List<Guestbook> list = guestbookService.getGuestbooks(start);
		
		int count = guestbookService.getCount();
		int pageCount = count/GuestbookService.LIMIT;
		
		if(count%guestbookService.LIMIT>0)
			pageCount++;
		
		List<Integer> pageStartList = new ArrayList<Integer>();
		for(int i=0;i<pageCount;i++) {
			pageStartList.add(i*guestbookService.LIMIT);
		}
		
		model.addAttribute("list",list);
		model.addAttribute("count",count);
		model.addAttribute("pageStartList",pageStartList);
		
		return "list";
		
	}
	
	@PostMapping(path="write")
	public String write(@ModelAttribute Guestbook guestbook,
						HttpServletRequest request) {
		
		String content = guestbook.getContent().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		guestbook.setContent(content);
		String clientIp = request.getRemoteAddr();
		System.out.println("clientIp : " + clientIp);
		guestbookService.addGuestbook(guestbook, clientIp);
		return "redirect:list";
	}
	
}
