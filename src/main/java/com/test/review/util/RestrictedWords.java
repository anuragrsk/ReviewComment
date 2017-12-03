package com.test.review.util;

import java.util.HashMap;
/**
 * TODO: This list should come from Database.
 * @author anuraag
 *
 */
public class RestrictedWords {
	public static HashMap<String,String> wordMap = new HashMap<String,String>();
	static {
		wordMap.put("DOG", "DOG");
		wordMap.put("MONKEY", "MONKEY");
		wordMap.put("STUPID", "STUPID");
		wordMap.put("JOKER", "JOKER");
		wordMap.put("JOKE", "JOKE");


	}

}
