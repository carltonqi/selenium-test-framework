package org.bestqa.selenium.test;

/**
 * Case priority definition
 */
public class CasePriority {
	/**
	 * The highest level, the priority cases will be triggered after each code check in
	 */
	public static final String CheckIn = "CheckIn";
	
	/**
	 * Build Verification Test, Level consistent with the CheckIn, the difference is not triggered after each check in
	 */
	public static final String BVT = "BVT";
	
	/**
	 *  Acceptance test
	 */
	public static final String ACCEPTANCE = "ACCEPTANCE";
	
	/**
	 * Comprehensive test
	 */
	public static final String COMPREHENSIVE = "COMPREHENSIVE";
	
	/**
	 * Expired test case which will not be executed.
	 */
	public static final String DEPRECATED = "DEPRECATED";
	
	/**
	 * The current debug test case
	 */
	public static final String CURRENT_DEBUG = "CURRENT_DEBUG";
}
