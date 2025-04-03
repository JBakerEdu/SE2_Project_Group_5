package edu.westga.cs3211.hyre_defyer_project.test.view_model;

import edu.westga.cs3211.hyre_defyer_project.model.Categories;
import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;
import edu.westga.cs3211.hyre_defyer_project.view_model.CategoryViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the CategoryViewModel class.
 */
public class TestCategoryViewModel {
    
    private CategoryViewModel categoryViewModel;
    private Freelancer freelancer1;
    private Freelancer freelancer2;
    private Freelancer freelancer3;
    private Freelancer freelancer4;

    @BeforeEach
    void setUp() {
        categoryViewModel = new CategoryViewModel();
        freelancer1 = new Freelancer("JohnDoe", "Bio", Categories.DEVELOPMENT_AND_IT);
        freelancer2 = new Freelancer("JaneSmith", "Bio", Categories.DESIGN_AND_CREATIVE);
        freelancer3 = new Freelancer("MikeJones", "Bio", Categories.DEVELOPMENT_AND_IT);
        freelancer4 = new Freelancer("NewFreelancer", "New Role", Categories.DEVELOPMENT_AND_IT, new String[]{"Java", "Spring", "SQL", "Go", "Rust"});
        
        CategoryViewModel.freelancerRoster.addFreelancer(freelancer1);
        CategoryViewModel.freelancerRoster.addFreelancer(freelancer2);
        CategoryViewModel.freelancerRoster.addFreelancer(freelancer3);
        CategoryViewModel.freelancerRoster.addFreelancer(freelancer4);
    }

    @Test
    void testSetSelectedCategory() {
        categoryViewModel.setSelectedCategory(Categories.DEVELOPMENT_AND_IT);
        assertEquals(Categories.DEVELOPMENT_AND_IT, categoryViewModel.getSelectedCategory(), "Selected category should be set correctly.");
    }

    @Test
    void testAddFreelancerToCorrectCategory() {
        categoryViewModel.setSelectedCategory(Categories.DEVELOPMENT_AND_IT);
        categoryViewModel.addPersonToCategory(freelancer4);
        boolean containsFreelancer = false;
        for (Freelancer freelancer : CategoryViewModel.freelancerRoster.getFreelancersByCategory(Categories.DEVELOPMENT_AND_IT)) {
            if (freelancer.getUserName().equals(freelancer4.getUserName())) {
                containsFreelancer = true;
                break;
            }
        }
        assertTrue(containsFreelancer, "Freelancer should be added to the correct category.");
    }

    @Test
    void testAddFreelancerToWrongCategoryThrowsException() {
        categoryViewModel.setSelectedCategory(Categories.DEVELOPMENT_AND_IT);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryViewModel.addPersonToCategory(freelancer2);
        });
        assertEquals("JaneSmith does not belong to this category.", exception.getMessage(), "Should throw exception for incorrect category.");
    }

    @Test
    void testAddMultipleFreelancersToSameCategory() {
        categoryViewModel.setSelectedCategory(Categories.DEVELOPMENT_AND_IT);
        categoryViewModel.addPersonToCategory(freelancer1);
        categoryViewModel.addPersonToCategory(freelancer3);
        boolean containsFreelancer1 = false;
        boolean containsFreelancer3 = false;

        for (Freelancer freelancer : CategoryViewModel.freelancerRoster.getFreelancersByCategory(Categories.DEVELOPMENT_AND_IT)) {
            if (freelancer.getUserName().equals(freelancer1.getUserName())) {
                containsFreelancer1 = true;
            }
            if (freelancer.getUserName().equals(freelancer3.getUserName())) {
                containsFreelancer3 = true;
            }
        }
        assertTrue(containsFreelancer1, "First freelancer should be added.");
        assertTrue(containsFreelancer3, "Second freelancer should be added.");
    }

}