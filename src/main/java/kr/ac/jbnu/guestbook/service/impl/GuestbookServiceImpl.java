package kr.ac.jbnu.guestbook.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.jbnu.guestbook.dao.GuestbookDao;
import kr.ac.jbnu.guestbook.dao.LogDao;
import kr.ac.jbnu.guestbook.dto.Guestbook;
import kr.ac.jbnu.guestbook.dto.Log;
import kr.ac.jbnu.guestbook.service.GuestbookService;

@Service
public class GuestbookServiceImpl implements GuestbookService{
	@Autowired
	LogDao logdao;
	
	@Autowired
	GuestbookDao guestbookDao;

	@Transactional
	public List<Guestbook> getGuestbooks(Integer start) {
		List<Guestbook> list = guestbookDao.selectAll(start, GuestbookService.LIMIT);
		return list;
	}

	@Transactional(readOnly=false)
	public int deleteGuestbook(long id, String ip) {
		int deleteCount = guestbookDao.deleteById(id);
		
		Log log = new Log();
		log.setId(id);
		log.setIp(ip);
		log.setMethod("delete");
		log.setRegDate(new Date());
		logdao.insert(log);
		
		return deleteCount;
	}

	@Transactional(readOnly=false)
	public Guestbook addGuestbook(Guestbook guestbook, String ip) {
		guestbook.setRegDate(new Date());
		long id = guestbookDao.insert(guestbook);
		guestbook.setId(id);
		
		Log log = new Log();
		log.setId(id);
		log.setIp(ip);
		log.setMethod("insert");
		log.setRegDate(new Date());
		logdao.insert(log);
		
		return guestbook;
	}
	
	
	public int getCount() {
		int count = guestbookDao.selectCount();
		return count;
	}

	
}
