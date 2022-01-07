package utils;

public class BirthDateUtils {
	
	public static String parseDate(String date) {
		String day = date.substring(8);
		String month = date.substring(5,7);
		String year = date.substring(0,4);
		return day+ "-" +month + "-"+year;
	}
}
