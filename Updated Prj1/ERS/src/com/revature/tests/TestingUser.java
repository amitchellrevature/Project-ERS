package tests;

import requests.*;
import static org.junit.Assert.*;
import java.sql.SQLException;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestingUser {
	
	static TicketsImpl dao = null;
	
	@BeforeClass
	public static void createObject() {
		if(dao == null) {
			dao = new TicketsImpl();
		}
	}
	
	@Test
	public void checkIfUserIsInDatabase() throws SQLException {
		createObject();
		int actualValue1 = dao.login("jmitchell", "password");
		int actualValue2 = dao.login("amitchell", "password");
		assertEquals(1, actualValue1);
		assertEquals(2, actualValue2);
	}
	
	@Test
	public void checkIfUserIsManager() throws SQLException {
		createObject();
		boolean trueVal = dao.manage(1);
		boolean falseVal = dao.manage(2);
		assertTrue(trueVal);
		assertFalse(falseVal);
	}
}