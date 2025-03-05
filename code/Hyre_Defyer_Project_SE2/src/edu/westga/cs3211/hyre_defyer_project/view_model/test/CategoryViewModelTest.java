package edu.westga.cs3211.hyre_defyer_project.view_model.test;

import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;
import edu.westga.cs3211.hyre_defyer_project.model.Categories;
import edu.westga.cs3211.hyre_defyer_project.view_model.CategoryViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryViewModelTest {

    private CategoryViewModel categoryViewModel;
    private Freelancer freelancer1;
    private Freelancer freelancer2;

    @BeforeEach
    public void setUp() {
        categoryViewModel = new CategoryViewModel();
        freelancer1 = new Freelancer("Freelancer1", "password123", "Business and Finance Guy.", Categories.BUSINESS_AND_FINANCE);
        freelancer2 = new Freelancer("Freelancer2", "password456", "Creative Music and Audio Designer", Categories.MUSIC_AND_AUDIO);
        categoryViewModel.setSelectedCategory("BUSINESS AND FINANCE");
    }

    @Test
    public void testSetSelectedCategory() {
        categoryViewModel.setSelectedCategory("MUSIC AND AUDIO");
        assertEquals("MUSIC AND AUDIO", categoryViewModel.getSelectedCategory());
    }

    @Test
    public void testAddPersonToCategory_ValidCategory() {
        categoryViewModel.addPersonToCategory(freelancer1);
        assertTrue(categoryViewModel.getFreelancers().contains(freelancer1));
    }

    @Test
    public void testAddPersonToCategory_InvalidCategory() {
        categoryViewModel.setSelectedCategory("MUSIC AND AUDIO");
        categoryViewModel.addPersonToCategory(freelancer2);
        assertTrue(categoryViewModel.getFreelancers().contains(freelancer2));
    }

    @Test
    public void testGetFreelancers() {
        categoryViewModel.addPersonToCategory(freelancer1);
        categoryViewModel.setSelectedCategory("MUSIC AND AUDIO");
        categoryViewModel.addPersonToCategory(freelancer2);
        assertEquals(2, categoryViewModel.getFreelancers().size());
        assertTrue(categoryViewModel.getFreelancers().contains(freelancer1));
        assertTrue(categoryViewModel.getFreelancers().contains(freelancer2));
    }
    
    @Test
    public void testAddPersonToCategoryError() {
        categoryViewModel.setSelectedCategory("DEVELOPMENT_AND_IT");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryViewModel.addPersonToCategory(freelancer2);
        });
        assertEquals("Freelancer2 does not belong to this category.", exception.getMessage());
    }
}
