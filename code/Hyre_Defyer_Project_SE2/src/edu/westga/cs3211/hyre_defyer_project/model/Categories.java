package edu.westga.cs3211.hyre_defyer_project.model;

import java.util.List;

import edu.westga.cs3211.hyre_defyer_project.server.ServerInterface;

/**
 * The Category enums that we can use for category
 * 
 * @author Kate Anglin and Jacob Baker
 * @version Spring 2025
 */
public class Categories {
	public static final String BUSINESS_AND_FINANCE = "BUSINESS AND FINANCE";
	public static final String DESIGN_AND_CREATIVE = "DESIGN AND CREATIVE";
	public static final String DEVELOPMENT_AND_IT = "DEVELOPMENT AND IT";
	public static final String ENGINEERING_AND_SCIENCE = "ENGINEERING AND SCIENCE";
	public static final String MARKETING_AND_SALES = "MARKETING AND SALES";
	public static final String MUSIC_AND_AUDIO = "MUSIC AND AUDIO";
	public static final String TRADES_AND_SKILLED_LABOR = "TRADES AND SKILLED LABOR";
	public static final String WRITING_AND_TRANSLATION = "WRITING AND TRANSLATION";
	public static final String UNDETERMINED = "UNDETERMINED";
	
	/**
	 * gets the servers categories
	 * 
	 * @return the categories
	 */
	public static List<String> values() {
		return ServerInterface.getCategories();
	}

}
