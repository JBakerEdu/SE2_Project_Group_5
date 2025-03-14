package edu.westga.cs3211.hyre_defyer_project.test.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.hyre_defyer_project.model.Categories;
import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;
import edu.westga.cs3211.hyre_defyer_project.util.FreelancerFilter;

class TestFilteringFreelancersByCategories {
	    
	    @Test
	    public void testFilterByCategoryWithNoFreelancers() {
	        List<Freelancer> freelancers = new ArrayList<>();

	        List<Categories> catList = new ArrayList<>();
	        
	        List<Freelancer> result = FreelancerFilter.filterByCategories(freelancers, catList);
	        assertTrue(result.isEmpty());
	    }
	    
	    @Test
	    public void testFilterByCategoryWithNoCategories() {
	        List<Freelancer> freelancers = new ArrayList<>();
	        freelancers.add(new Freelancer("Alice", "Bio A", Categories.MARKETING_AND_SALES,
		            new String[]{"SEO", "Content", "Branding", "Social Media", "Advertising"}));
		    freelancers.add(new Freelancer("Bob", "Bio B", Categories.MARKETING_AND_SALES,
		            new String[]{"Sales", "Negotiation", "Networking", "Public Speaking", "Strategy"}));
		    freelancers.add(new Freelancer("Charlie", "Bio C", Categories.DEVELOPMENT_AND_IT,
		            new String[]{"Java", "C++", "Python", "JavaScript", "SQL"}));

	        List<Categories> catList = new ArrayList<>();
	        
	        List<Freelancer> result = FreelancerFilter.filterByCategories(freelancers, catList);
	        assertTrue(result.isEmpty());
	    }
	    
	    @Test
	    public void testFilterByCategoryWithCategoryNotInList() {
	        List<Freelancer> freelancers = new ArrayList<>();
	        freelancers.add(new Freelancer("Alice", "Bio A", Categories.MARKETING_AND_SALES,
		            new String[]{"SEO", "Content", "Branding", "Social Media", "Advertising"}));
		    freelancers.add(new Freelancer("Bob", "Bio B", Categories.MARKETING_AND_SALES,
		            new String[]{"Sales", "Negotiation", "Networking", "Public Speaking", "Strategy"}));
		    freelancers.add(new Freelancer("Charlie", "Bio C", Categories.DEVELOPMENT_AND_IT,
		            new String[]{"Java", "C++", "Python", "JavaScript", "SQL"}));

	        List<Categories> catList = new ArrayList<>();
	        catList.add(Categories.DESIGN_AND_CREATIVE);
	        
	        List<Freelancer> result = FreelancerFilter.filterByCategories(freelancers, catList);
	        assertTrue(result.isEmpty());
	    }
	    
	    @Test
	    public void testFilterByCategoryWithCategoryInList() {
	        List<Freelancer> freelancers = new ArrayList<>();
	        Freelancer charlie = new Freelancer("Charlie", "Bio C", Categories.DEVELOPMENT_AND_IT,
		            new String[]{"Java", "C++", "Python", "JavaScript", "SQL"});
	        freelancers.add(new Freelancer("Alice", "Bio A", Categories.MARKETING_AND_SALES,
		            new String[]{"SEO", "Content", "Branding", "Social Media", "Advertising"}));
		    freelancers.add(new Freelancer("Bob", "Bio B", Categories.MARKETING_AND_SALES,
		            new String[]{"Sales", "Negotiation", "Networking", "Public Speaking", "Strategy"}));
		    freelancers.add(charlie);

	        List<Categories> catList = new ArrayList<>();
	        catList.add(Categories.DEVELOPMENT_AND_IT);
	        
	        List<Freelancer> result = FreelancerFilter.filterByCategories(freelancers, catList);
	        assertTrue(result.contains(charlie));
	    }
	    
	    @Test
	    public void testFilterByCategoryWithCategoryInListTwoResults() {
	        List<Freelancer> freelancers = new ArrayList<>();
	        Freelancer alice = new Freelancer("Alice", "Bio A", Categories.MARKETING_AND_SALES,
		            new String[]{"SEO", "Content", "Branding", "Social Media", "Advertising"});
	        Freelancer bob = new Freelancer("Bob", "Bio B", Categories.MARKETING_AND_SALES,
		            new String[]{"Sales", "Negotiation", "Networking", "Public Speaking", "Strategy"});
	        Freelancer charlie = new Freelancer("Charlie", "Bio C", Categories.DEVELOPMENT_AND_IT,
		            new String[]{"Java", "C++", "Python", "JavaScript", "SQL"});
	        freelancers.add(alice);
	        freelancers.add(bob);
		    freelancers.add(charlie);

	        List<Categories> catList = new ArrayList<>();
	        catList.add(Categories.MARKETING_AND_SALES);
	        
	        List<Freelancer> result = FreelancerFilter.filterByCategories(freelancers, catList);
	        assertTrue(result.contains(alice));
	        assertTrue(result.contains(bob));
	    }
	    
	    @Test
	    public void testFilterByTwoCategories() {
	        List<Freelancer> freelancers = new ArrayList<>();
	        Freelancer alice = new Freelancer("Alice", "Bio A", Categories.MARKETING_AND_SALES,
		            new String[]{"SEO", "Content", "Branding", "Social Media", "Advertising"});
	        Freelancer bob = new Freelancer("Bob", "Bio B", Categories.MARKETING_AND_SALES,
		            new String[]{"Sales", "Negotiation", "Networking", "Public Speaking", "Strategy"});
	        Freelancer charlie = new Freelancer("Charlie", "Bio C", Categories.DEVELOPMENT_AND_IT,
		            new String[]{"Java", "C++", "Python", "JavaScript", "SQL"});
	        freelancers.add(alice);
	        freelancers.add(bob);
		    freelancers.add(charlie);

	        List<Categories> catList = new ArrayList<>();
	        catList.add(Categories.MARKETING_AND_SALES);
	        catList.add(Categories.DEVELOPMENT_AND_IT);
	        
	        List<Freelancer> result = FreelancerFilter.filterByCategories(freelancers, catList);
	        assertTrue(result.contains(alice));
	        assertTrue(result.contains(bob));
	        assertTrue(result.contains(charlie));
	    }

	    @Test
	    public void testFilterByCategoryWithEmptyList() {
	        List<Freelancer> emptyList = new ArrayList<>();
	        List<Categories> emptyCatList = new ArrayList<>();
	        List<Freelancer> result;
	        result = FreelancerFilter.filterByCategories(emptyList, emptyCatList);
	        assertTrue(result.isEmpty());
	    }

	    @Test
	    public void testFilterByCategoryWithNullFreelancerList() {
	        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	            FreelancerFilter.filterByCategories(null, null);
	        });
	        assertEquals("Freelancers list cannot be null.", exception.getMessage());
	    }
	    
	    @Test
	    public void testFilterByCategoryWithNullSelectedCategorieList() {
	    	new FreelancerFilter();
	    	List<Freelancer> emptyList = new ArrayList<>();
	        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	            FreelancerFilter.filterByCategories(emptyList, null);
	        });
	        assertEquals("Selected Categories list cannot be null.", exception.getMessage());
	    }

}
