package edu.westga.cs3211.hyre_defyer_project.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.hyre_defyer_project.model.Categories;
import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;
import edu.westga.cs3211.hyre_defyer_project.model.RosterHelper;
import edu.westga.cs3211.hyre_defyer_project.server.ServerInterface;

class TestRosterHelper {
	
	private Freelancer freelancer1;
    private Freelancer freelancer2;
    private Freelancer freelancer3;
    private Freelancer freelancer4;
	
	@BeforeEach
    void setUp() {
		new RosterHelper();
        freelancer1 = new Freelancer("Ben Dover", "Bio", Categories.BUSINESS_AND_FINANCE);
        freelancer2 = new Freelancer("Jane Smith", "Bio", Categories.BUSINESS_AND_FINANCE);
        freelancer3 = new Freelancer("Mike Jones", "Bio", Categories.BUSINESS_AND_FINANCE);
        freelancer4 = new Freelancer("Mark Thomas", "Bio", Categories.BUSINESS_AND_FINANCE);
        ServerInterface.addFreelancer(freelancer1);
    	ServerInterface.addFreelancer(freelancer2);
    	ServerInterface.addFreelancer(freelancer3);
    }
    
    @AfterEach
    void tearDown() {
    	RosterHelper.removeFreelancerFromServer(freelancer1);
        RosterHelper.removeFreelancerFromServer(freelancer2);
        RosterHelper.removeFreelancerFromServer(freelancer3);
    }

    @Test
    public void testGetFreelancersReturnsCorrectList() {
    	List<Freelancer> roster = RosterHelper.getFreelancerRoster().getAllFreelancers();
    	assertTrue(roster.contains(freelancer1));
    	assertTrue(roster.contains(freelancer2));
    	assertTrue(roster.contains(freelancer3));
    }

    @Test
    void testAddFreelancerToServer() {
    	assertTrue(RosterHelper.addFreelancerToServer(freelancer4));
    	RosterHelper.removeFreelancerFromServer(freelancer4);
    }

    @Test
    void testEditFreelancerToServerCallsRemoveThenAdd() {
    	Freelancer before = new Freelancer("Before", "Bio", Categories.BUSINESS_AND_FINANCE);
    	ServerInterface.addFreelancer(before);
    	Freelancer after = new Freelancer("After", "Bio2", Categories.DESIGN_AND_CREATIVE);
    	RosterHelper.editFreelancerToServer(before, after);
    	
    	assertFalse(RosterHelper.getFreelancerRoster().getAllFreelancers().contains(before));
    	assertTrue(RosterHelper.getFreelancerRoster().getAllFreelancers().contains(after));
    	
    	RosterHelper.removeFreelancerFromServer(after);
    }

    @Test
    void testRemoveFreelancerFromServer() {
    	RosterHelper.addFreelancerToServer(freelancer4);
    	assertTrue(RosterHelper.removeFreelancerFromServer(freelancer4));
    }

}
