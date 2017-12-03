package com.test.review.util;

/**
 * 
 * @author anuraag
 *
 */
public class UtilHelper {
	/**
	 * check if the String is null
	 * @param str
	 * @return
	 */
	public static boolean isNotNullOrBlank(String str) {
		return ((str!=null&&str.length()>0)?true:false);
	}
/**
 * check if the email valid
 * @param reviewer_email
 * @return
 */
	public static boolean isValidEmail(String reviewer_email) {
		return (isNotNullOrBlank(reviewer_email) && reviewer_email.contains("@"))?true:false;
	}
	/**
	 * This method validate if comments contain any restricted words
	 * @param review_comments
	 * @return
	 */
public static boolean validateCommnets(String review_comments) {
	String breakUp[] = review_comments.toUpperCase().split(" ");
	
	for(String word:breakUp) {
		if(RestrictedWords.wordMap.containsKey(word))
			return false;
	}
	return true;
}
}
