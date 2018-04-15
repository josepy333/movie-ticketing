package edu.csumb.xtreme.movieticketing.dao;

import edu.csumb.xtreme.movieticketing.entities.ViewerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewerDao extends JpaRepository<ViewerEntity, Integer> {

public class User {
	private static String USER_NAME;
	private static String PASSWORD;
	public static String getUSER_NAME() {
		return USER_NAME;
	}
	public static void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}
	public static String getPASSWORD() {
		return PASSWORD;
	}
	public static void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	

}

}
