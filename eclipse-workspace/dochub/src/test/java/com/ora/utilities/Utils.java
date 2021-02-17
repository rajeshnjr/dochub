package com.ora.utilities;

public class Utils {

	public static void sleep(int sleepfor) {
		try {
			Thread.sleep(sleepfor);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
