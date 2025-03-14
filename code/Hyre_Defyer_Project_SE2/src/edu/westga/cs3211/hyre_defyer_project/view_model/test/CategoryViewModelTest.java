package edu.westga.cs3211.hyre_defyer_project.view_model.test;

import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;
import edu.westga.cs3211.hyre_defyer_project.model.Categories;
import edu.westga.cs3211.hyre_defyer_project.view_model.CategoryViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test the Category View Model class that handles some logic that will be used in the categoryPageView
 * 
 * @author Jacob baker
 * @version Spring 2025
 */
public class CategoryViewModelTest {

    private CategoryViewModel categoryViewModel;
    private Freelancer freelancer1;
    private Freelancer freelancer2;

    @BeforeEach
    public void setUp() {
        categoryViewModel = new CategoryViewModel();
        freelancer1 = new Freelancer("Freelancer1", "Business and Finance Guy.", Categories.BUSINESS_AND_FINANCE);
        freelancer2 = new Freelancer("Freelancer2", "Creative Music and Audio Designer", Categories.MUSIC_AND_AUDIO);
        categoryViewModel.setSelectedCategory(Categories.BUSINESS_AND_FINANCE);
    }

    @Test
    public void testSetSelectedCategory() {
        categoryViewModel.setSelectedCategory(Categories.MUSIC_AND_AUDIO);
        assertEquals(Categories.MUSIC_AND_AUDIO, categoryViewModel.getSelectedCategory());
    }

    @Test
    public void testAddPersonToCategory_ValidCategory() {        
        categoryViewModel.addPersonToCategory(freelancer1);
        
        assertEquals(freelancer1.getUserName(), categoryViewModel.getFreelancers().get(0).getUserName());
    }

    @Test
    public void testAddPersonToCategory_InvalidCategory() {
        categoryViewModel.setSelectedCategory(Categories.BUSINESS_AND_FINANCE);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryViewModel.addPersonToCategory(freelancer2);
        });
        assertEquals("Freelancer2 does not belong to this category.", exception.getMessage());
    }

    @Test
    public void testGetFreelancers() {
        categoryViewModel.setSelectedCategory(Categories.MUSIC_AND_AUDIO);
        categoryViewModel.addPersonToCategory(freelancer2);
        assertEquals(1, categoryViewModel.getFreelancers().size());
        assertEquals(freelancer2.getUserName(), categoryViewModel.getFreelancers().get(0).getUserName());
        assertFalse(categoryViewModel.getFreelancers().stream()
                     .anyMatch(freelancer -> freelancer.getUserName().equals(freelancer1.getUserName())));
    }

    @Test
    public void testAddPersonToCategoryError() {
        categoryViewModel.setSelectedCategory(Categories.DEVELOPMENT_AND_IT);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryViewModel.addPersonToCategory(freelancer2);
        });
        assertEquals(freelancer2.getUserName() + " does not belong to this category.", exception.getMessage());
    }
}
