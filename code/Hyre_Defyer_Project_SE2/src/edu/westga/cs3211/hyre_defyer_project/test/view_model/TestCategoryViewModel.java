package edu.westga.cs3211.hyre_defyer_project.test.view_model;

import edu.westga.cs3211.hyre_defyer_project.view_model.CategoryPageViewModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class TestCategoryViewModel {
	
	CategoryPageViewModel viewModel = new CategoryPageViewModel();

    @BeforeEach
    void setUp() {
        CategoryPageViewModel.setSelectedName(null);
        CategoryPageViewModel.setSelectedName(null);
        CategoryPageViewModel.setSelectedSkills(null);
    }

    @AfterEach
    void tearDown() {
        CategoryPageViewModel.setSelectedName(null);
        CategoryPageViewModel.setSelectedName(null);
        CategoryPageViewModel.setSelectedSkills(null);
    }

    @Test
    void testSelectedNameSetterAndGetter() {
    	
    	assertNotNull(viewModel, "Should not be null once created");
        assertNull(CategoryPageViewModel.getSelectedName(), "Initially, selectedName should be null");

        CategoryPageViewModel.setSelectedName("Alice");
        assertEquals("Alice", CategoryPageViewModel.getSelectedName(),
                "After setting, getSelectedName should return the same value");

        CategoryPageViewModel.setSelectedName("Bob");
        assertEquals("Bob", CategoryPageViewModel.getSelectedName(),
                "Setter should overwrite previous selectedName");
    }

    @Test
    void testSelectedSkillsSetterAndGetter() {
        assertNull(CategoryPageViewModel.getSelectedSkills(), "Initially, selectedSkills should be null");

        ArrayList<String> skills = new ArrayList<>();
        skills.add("Java");
        skills.add("Spring");

        CategoryPageViewModel.setSelectedSkills(skills);
        ArrayList<String> retrieved = CategoryPageViewModel.getSelectedSkills();

        assertNotNull(retrieved, "After setting, selectedSkills should not be null");
        assertEquals(2, retrieved.size(), "List size should match");
        assertTrue(retrieved.contains("Java") && retrieved.contains("Spring"),
                "Retrieved list should contain exactly the elements set");

        retrieved.remove("Spring");
        assertEquals(1, CategoryPageViewModel.getSelectedSkills().size(),
                "Modifications to the returned list should affect the stored list");
    }
    
    @Test
    void testClearSelectionsResetsAllStaticFields() {
        CategoryPageViewModel.setSelectedName("TestName");
        ArrayList<String> skills = new ArrayList<>();
        skills.add("SkillA");
        CategoryPageViewModel.setSelectedSkills(skills);
        CategoryPageViewModel.setSelectedCategory("Some Category");

        assertNotNull(CategoryPageViewModel.getSelectedName(), "Precondition: selectedName should be non-null");
        assertNotNull(CategoryPageViewModel.getSelectedSkills(), "Precondition: selectedSkills should be non-null");
        assertNotNull(CategoryPageViewModel.getSelectedCategory(), "Precondition: selectedCategory should be non-null");

        CategoryPageViewModel.clearSelections();

        assertNull(CategoryPageViewModel.getSelectedName(), "selectedName should be null after clearSelections()");
        assertNull(CategoryPageViewModel.getSelectedSkills(), "selectedSkills should be null after clearSelections()");
        assertNull(CategoryPageViewModel.getSelectedCategory(), "selectedCategory should be null after clearSelections()");
    }


    @Test
    void testSelectedCategoryStaticBehavior() {
        assertNull(CategoryPageViewModel.getSelectedCategory(), "Initially, selectedCategory should be null");

        CategoryPageViewModel.setSelectedCategory("  Development and IT  ");
        assertEquals("DEVELOPMENT_AND_IT", CategoryPageViewModel.getSelectedCategory(),
                "Category should be normalized to uppercase with underscores");

        assertEquals("DEVELOPMENT_AND_IT", CategoryPageViewModel.getSelectedCategory(),
                "Static selectedCategory should be shared across instances");
    }
}
