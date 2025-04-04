package edu.westga.cs3211.hyre_defyer_project.model;

/**
 * The Category enums that we can use for category
 * 
 * @author Kate Anglin and Jacob Baker
 * @version Spring 2025
 */
public enum Categories {
	
	BUSINESS_AND_FINANCE,
	DESIGN_AND_CREATIVE,
	DEVELOPMENT_AND_IT,
	ENGINEERING_AND_SCIENCE,
	MARKETING_AND_SALES,
	MUSIC_AND_AUDIO,
	TRADES_AND_SKILLED_LABOR,
	WRITING_AND_TRANSLATION,
	UNDETERMINED;

	@Override
    public String toString() {
        return this.name().replace("_", " ");
    }
}
