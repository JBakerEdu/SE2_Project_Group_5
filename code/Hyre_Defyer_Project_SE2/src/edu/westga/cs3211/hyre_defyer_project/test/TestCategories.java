package edu.westga.cs3211.hyre_defyer_project.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.hyre_defyer_project.model.Categories;

class TestCategories {

	@Test
    public void testToString() {
        assertEquals("DEVELOPMENT AND IT", Categories.DEVELOPMENT_AND_IT.toString());
        assertEquals("DESIGN AND CREATIVE", Categories.DESIGN_AND_CREATIVE.toString());
        assertEquals("WRITING AND TRANSLATION", Categories.WRITING_AND_TRANSLATION.toString());
        assertEquals("MARKETING AND SALES", Categories.MARKETING_AND_SALES.toString());
        assertEquals("BUSINESS AND FINANCE", Categories.BUSINESS_AND_FINANCE.toString());
        assertEquals("ENGINEERING AND SCIENCE", Categories.ENGINEERING_AND_SCIENCE.toString());
        assertEquals("MUSIC AND AUDIO", Categories.MUSIC_AND_AUDIO.toString());
        assertEquals("TRADES AND SKILLED LABOR", Categories.TRADES_AND_SKILLED_LABOR.toString());
        assertEquals("UNDETERMINED", Categories.UNDETERMINED.toString());
    }

}
