package utils;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtil {
	public static List<Integer> getPageArray(int pageNum) {
		List<Integer> pageArray = new ArrayList<Integer>();
		for (int i = 1; i <= pageNum; i++) {
			pageArray.add(i);
		}
		return pageArray;
	} 
}
