package kr.ac.jbnu.guestbook.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.ac.jbnu.guestbook.dto.Guestbook;

public interface GuestbookService {
	public static final Integer LIMIT = 5;
	public List<Guestbook> getGuestbooks(Integer start);
	public int deleteGuestbook(long id, String ip);
	public Guestbook addGuestbook(Guestbook guestbook,String ip);
	public int getCount();
}
