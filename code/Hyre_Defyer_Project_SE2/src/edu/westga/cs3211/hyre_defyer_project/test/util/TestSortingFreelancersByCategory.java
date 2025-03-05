package edu.westga.cs3211.hyre_defyer_project.test.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.hyre_defyer_project.model.Categories;
import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;
import edu.westga.cs3211.hyre_defyer_project.util.FreelancerSorter;

class TestSortingFreelancersByCategory {
	 
	    @Test
	    public void testSortByCategoryWithOneItem() {
	    	List<Freelancer> freelancers = new ArrayList<>();
	    	Freelancer alice = new Freelancer("Alice", "pass123", "Bio A", Categories.MARKETING_AND_SALES, 
		            new String[]{"SEO", "Content", "Branding", "Social Media", "Advertising"});
	        freelancers.add(alice);
	        FreelancerSorter.sortByCategory(freelancers);
	        assertEquals(Categories.MARKETING_AND_SALES, freelancers.get(0).getCategory());
	        assertEquals(freelancers.get(0), alice);
	    }
	    
	    @Test
	    public void testSortByCategoryWithTwoItems() {
	    	List<Freelancer> freelancers = new ArrayList<>();
	        freelancers.add(new Freelancer("Alice", "pass123", "Bio A", Categories.MARKETING_AND_SALES,
	            new String[]{"SEO", "Content", "Branding", "Social Media", "Advertising"}));
	        freelancers.add(new Freelancer("Bob", "pass456", "Bio B", Categories.DEVELOPMENT_AND_IT,
	            new String[]{"Java", "C++", "Python", "JavaScript", "SQL"}));
	        FreelancerSorter.sortByCategory(freelancers);
	        assertEquals(Categories.DEVELOPMENT_AND_IT, freelancers.get(0).getCategory());
	        assertEquals(Categories.MARKETING_AND_SALES, freelancers.get(1).getCategory());
	    }
	    
	    @Test
	    public void testSortByCategory_Permutation1() {
	        List<Freelancer> freelancers = new ArrayList<>();
	        freelancers.add(new Freelancer("Alice", "pass123", "Bio A", Categories.MARKETING_AND_SALES, new String[]{"SEO", "Content", "Branding", "Social Media", "Advertising"}));
	        freelancers.add(new Freelancer("Bob", "pass456", "Bio B", Categories.DEVELOPMENT_AND_IT, new String[]{"Java", "C++", "Python", "JavaScript", "SQL"}));
	        freelancers.add(new Freelancer("Charlie", "pass789", "Bio C", Categories.DESIGN_AND_CREATIVE, new String[]{"Photoshop", "Illustrator", "Figma", "Sketch", "UI/UX"}));

	        FreelancerSorter.sortByCategory(freelancers);

	        assertEquals(Categories.DESIGN_AND_CREATIVE, freelancers.get(0).getCategory());
	        assertEquals(Categories.DEVELOPMENT_AND_IT, freelancers.get(1).getCategory());
	        assertEquals(Categories.MARKETING_AND_SALES, freelancers.get(2).getCategory());
	    }

	    @Test
	    public void testSortByCategory_Permutation2() {
	        List<Freelancer> freelancers = new ArrayList<>();
	        freelancers.add(new Freelancer("Alice", "pass123", "Bio A", Categories.MARKETING_AND_SALES, new String[]{"SEO", "Content", "Branding", "Social Media", "Advertising"}));
	        freelancers.add(new Freelancer("Charlie", "pass789", "Bio C", Categories.DESIGN_AND_CREATIVE, new String[]{"Photoshop", "Illustrator", "Figma", "Sketch", "UI/UX"}));
	        freelancers.add(new Freelancer("Bob", "pass456", "Bio B", Categories.DEVELOPMENT_AND_IT, new String[]{"Java", "C++", "Python", "JavaScript", "SQL"}));

	        FreelancerSorter.sortByCategory(freelancers);

	        assertEquals(Categories.DESIGN_AND_CREATIVE, freelancers.get(0).getCategory());
	        assertEquals(Categories.DEVELOPMENT_AND_IT, freelancers.get(1).getCategory());
	        assertEquals(Categories.MARKETING_AND_SALES, freelancers.get(2).getCategory());
	    }

	    @Test
	    public void testSortByCategory_Permutation3() {
	        List<Freelancer> freelancers = new ArrayList<>();
	        freelancers.add(new Freelancer("Bob", "pass456", "Bio B", Categories.DEVELOPMENT_AND_IT, new String[]{"Java", "C++", "Python", "JavaScript", "SQL"}));
	        freelancers.add(new Freelancer("Alice", "pass123", "Bio A", Categories.MARKETING_AND_SALES, new String[]{"SEO", "Content", "Branding", "Social Media", "Advertising"}));
	        freelancers.add(new Freelancer("Charlie", "pass789", "Bio C", Categories.DESIGN_AND_CREATIVE, new String[]{"Photoshop", "Illustrator", "Figma", "Sketch", "UI/UX"}));

	        FreelancerSorter.sortByCategory(freelancers);

	        assertEquals(Categories.DESIGN_AND_CREATIVE, freelancers.get(0).getCategory());
	        assertEquals(Categories.DEVELOPMENT_AND_IT, freelancers.get(1).getCategory());
	        assertEquals(Categories.MARKETING_AND_SALES, freelancers.get(2).getCategory());
	    }

	    @Test
	    public void testSortByCategory_Permutation4() {
	        List<Freelancer> freelancers = new ArrayList<>();
	        freelancers.add(new Freelancer("Bob", "pass456", "Bio B", Categories.DEVELOPMENT_AND_IT, new String[]{"Java", "C++", "Python", "JavaScript", "SQL"}));
	        freelancers.add(new Freelancer("Charlie", "pass789", "Bio C", Categories.DESIGN_AND_CREATIVE, new String[]{"Photoshop", "Illustrator", "Figma", "Sketch", "UI/UX"}));
	        freelancers.add(new Freelancer("Alice", "pass123", "Bio A", Categories.MARKETING_AND_SALES, new String[]{"SEO", "Content", "Branding", "Social Media", "Advertising"}));

	        FreelancerSorter.sortByCategory(freelancers);

	        assertEquals(Categories.DESIGN_AND_CREATIVE, freelancers.get(0).getCategory());
	        assertEquals(Categories.DEVELOPMENT_AND_IT, freelancers.get(1).getCategory());
	        assertEquals(Categories.MARKETING_AND_SALES, freelancers.get(2).getCategory());
	    }

	    @Test
	    public void testSortByCategory_Permutation5() {
	        List<Freelancer> freelancers = new ArrayList<>();
	        freelancers.add(new Freelancer("Charlie", "pass789", "Bio C", Categories.DESIGN_AND_CREATIVE, new String[]{"Photoshop", "Illustrator", "Figma", "Sketch", "UI/UX"}));
	        freelancers.add(new Freelancer("Alice", "pass123", "Bio A", Categories.MARKETING_AND_SALES, new String[]{"SEO", "Content", "Branding", "Social Media", "Advertising"}));
	        freelancers.add(new Freelancer("Bob", "pass456", "Bio B", Categories.DEVELOPMENT_AND_IT, new String[]{"Java", "C++", "Python", "JavaScript", "SQL"}));

	        FreelancerSorter.sortByCategory(freelancers);

	        assertEquals(Categories.DESIGN_AND_CREATIVE, freelancers.get(0).getCategory());
	        assertEquals(Categories.DEVELOPMENT_AND_IT, freelancers.get(1).getCategory());
	        assertEquals(Categories.MARKETING_AND_SALES, freelancers.get(2).getCategory());
	    }

	    @Test
	    public void testSortByCategory_Permutation6() {
	        List<Freelancer> freelancers = new ArrayList<>();
	        freelancers.add(new Freelancer("Charlie", "pass789", "Bio C", Categories.DESIGN_AND_CREATIVE, new String[]{"Photoshop", "Illustrator", "Figma", "Sketch", "UI/UX"}));
	        freelancers.add(new Freelancer("Bob", "pass456", "Bio B", Categories.DEVELOPMENT_AND_IT, new String[]{"Java", "C++", "Python", "JavaScript", "SQL"}));
	        freelancers.add(new Freelancer("Alice", "pass123", "Bio A", Categories.MARKETING_AND_SALES, new String[]{"SEO", "Content", "Branding", "Social Media", "Advertising"}));

	        FreelancerSorter.sortByCategory(freelancers);

	        assertEquals(Categories.DESIGN_AND_CREATIVE, freelancers.get(0).getCategory());
	        assertEquals(Categories.DEVELOPMENT_AND_IT, freelancers.get(1).getCategory());
	        assertEquals(Categories.MARKETING_AND_SALES, freelancers.get(2).getCategory());
	    }
	    
	    @Test
	    public void testSortByCategoryAlreadySorted() {
	    	List<Freelancer> freelancers = new ArrayList<>();
	        freelancers.add(new Freelancer("Charlie", "pass789", "Bio C", Categories.DESIGN_AND_CREATIVE,
	            new String[]{"Photoshop", "Illustrator", "Figma", "Sketch", "UI/UX"}));
	        freelancers.add(new Freelancer("Bob", "pass456", "Bio B", Categories.DEVELOPMENT_AND_IT,
	            new String[]{"Java", "C++", "Python", "JavaScript", "SQL"}));
	        freelancers.add(new Freelancer("Alice", "pass123", "Bio A", Categories.MARKETING_AND_SALES,
	            new String[]{"SEO", "Content", "Branding", "Social Media", "Advertising"}));
	        FreelancerSorter.sortByCategory(freelancers);
	        assertEquals(Categories.DESIGN_AND_CREATIVE, freelancers.get(0).getCategory());
	        assertEquals(Categories.DEVELOPMENT_AND_IT, freelancers.get(1).getCategory());
	        assertEquals(Categories.MARKETING_AND_SALES, freelancers.get(2).getCategory());
	    }
	    
	    @Test
	    public void testSortByCategoryWithDuplicates() {
	        List<Freelancer> freelancers = new ArrayList<>();
	        freelancers.add(new Freelancer("Alice", "pass123", "Bio A", Categories.MARKETING_AND_SALES,
	            new String[]{"SEO", "Content", "Branding", "Social Media", "Advertising"}));
	        freelancers.add(new Freelancer("Bob", "pass456", "Bio B", Categories.MARKETING_AND_SALES,
	            new String[]{"Sales", "Negotiation", "Networking", "Public Speaking", "Strategy"}));
	        freelancers.add(new Freelancer("Charlie", "pass789", "Bio C", Categories.DEVELOPMENT_AND_IT,
	            new String[]{"Java", "C++", "Python", "JavaScript", "SQL"}));

	        FreelancerSorter.sortByCategory(freelancers);

	        assertEquals(Categories.DEVELOPMENT_AND_IT, freelancers.get(0).getCategory());
	    }

	    @Test
	    public void testSortByCategoryWithEmptyList() {
	        List<Freelancer> emptyList = new ArrayList<>();
	        FreelancerSorter.sortByCategory(emptyList);
	        assertTrue(emptyList.isEmpty());
	    }

	    @Test
	    public void testSortByCategoryWithNullList() {
	    	new FreelancerSorter();
	        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	            FreelancerSorter.sortByCategory(null);
	        });
	        assertEquals("Freelancers list cannot be null.", exception.getMessage());
	    }

}
