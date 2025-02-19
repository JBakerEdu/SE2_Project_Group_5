package edu.westga.cs3211.hyre_defyer_project.model;

public enum Categories {
	
	DEVELOPMENT_AND_IT,
	DESIGN_AND_CREATIVE,
	WRITING_AND_TRANSLATION,
	MARKETING_AND_SALES,
	BUSINESS_AND_FINANCE,
	ENGINEERING_AND_SCIENCE,
	MUSIC_AND_AUDIO,
	TRADES_AND_SKILLED_LABOR;

	
	@Override
    public String toString() {
        return this.name().replace("_", " ");
    }
}
