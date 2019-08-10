package kr.ac.jbnu.guestbook.service.impl;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.ac.jbnu.guestbook.config.ApplicationConfig;
import kr.ac.jbnu.guestbook.dto.Guestbook;
import kr.ac.jbnu.guestbook.service.GuestbookService;

public class GuestbookServiceTest {

	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		GuestbookService guestbookService = ac.getBean(GuestbookService.class);
		
		Guestbook guestbook = new Guestbook();
		
		guestbook.setName("mklee");
		guestbook.setContent("hihi");
		guestbook.setRegDate(new Date());
		System.out.println(guestbookService.addGuestbook(guestbook, "127,0,0,1"));
	}

}
