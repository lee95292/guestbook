package kr.ac.jbnu.guestbook.dao;

public class GuestbookDaoSqls {
	public static final String SELECT_PAGING = "SELECT id,name,content,regdate FROM guestbook ORDER BY id DESC LIMIT :start, :limit";
	public static final String DELETE_BY_ID = "DELETE FROM guestbook WHERE id = :id";
	public static final String SELECT_COUNT = "SELECT count(*) from guestbook";

}
