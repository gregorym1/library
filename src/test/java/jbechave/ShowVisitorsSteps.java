package jbechave;

import classes.dal.SqlDalBookManager;
import classes.dal.SqlDalVisitorManager;
import classes.svc.LibraryService;
import interfaces.dal.IDalBookManager;
import interfaces.dal.IDalVisitorManager;
import junit.framework.Assert;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.embedder.Embedder;

import java.util.ArrayList;

/**
 * Created by Vitalii on 20.11.2016.
 */
public class ShowVisitorsSteps extends Embedder {
    private LibraryService libraryService;
    private IDalVisitorManager dalVisitorManager;
    ArrayList visitors;

    @Given("the visitor")
    public void init(){
        libraryService = new LibraryService();
        dalVisitorManager = new SqlDalVisitorManager();
        libraryService.addVisitor("2","3","4","5");
        libraryService.addVisitor("8","9","10","11");
    }

    @When("user want to look on visitorlist")
    public void doSome(){
        visitors = libraryService.getAllVisitors();
    }

    @Then("system give visitorlist")
    public void check() {
        Assert.assertEquals(true, visitors.get(1).equals("2"));
        Assert.assertEquals(true, visitors.get(2).equals("3"));
        Assert.assertEquals(true, visitors.get(3).equals("4"));
        Assert.assertEquals(true, visitors.get(4).equals("5"));
        Assert.assertEquals(true, visitors.get(7).equals("8"));
        Assert.assertEquals(true, visitors.get(8).equals("9"));
        Assert.assertEquals(true, visitors.get(9).equals("10"));
        Assert.assertEquals(true, visitors.get(10).equals("11"));
        dalVisitorManager.deleteAllVisitors();
    }
}
