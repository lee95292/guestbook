package kr.ac.jbnu.guestbook.dao;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.ac.jbnu.guestbook.config.ApplicationConfig;
import kr.ac.jbnu.guestbook.dto.Guestbook;
import kr.ac.jbnu.guestbook.dto.Log;

public class GuestbookDaoTest {

	public static void main(String args[]) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class); 
		GuestbookDao guestbookDao = ac.getBean(GuestbookDao.class);
		
		Guestbook guestbook = new Guestbook();
		guestbook.setName("�����");
		guestbook.setContent("�ݰ����ϴ�. ������.");
		guestbook.setRegDate(new Date());
		Long id = guestbookDao.insert(guestbook);
		System.out.println("id : " + id);
		
		LogDao logDao = ac.getBean(LogDao.class);
		Log log = new Log();
		log.setIp("127.0.0.1");
		log.setMethod("insert");
		log.setRegDate(new Date());
		logDao.insert(log);
	}
}
