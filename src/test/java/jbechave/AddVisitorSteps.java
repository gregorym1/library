package jbechave;

import classes.dal.SqlDalVisitorManager;
import classes.domain.Visitor;
import classes.svc.LibraryService;
import interfaces.dal.IDalVisitorManager;
import junit.framework.Assert;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.embedder.Embedder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitalii on 05.11.2016.
 */
public class AddVisitorSteps extends Embedder {

    private LibraryService libraryService;
    private Visitor visitor;
    private IDalVisitorManager dalVisitorManager;

    @Given("a visitor")
    public void initVisitor() {
        visitor = new Visitor("Виталий","Бабенко","АО090909","1995");
    }

    @When("the visitor added")
    public void addVisitorInDB() {
        libraryService = new LibraryService();
        dalVisitorManager = new SqlDalVisitorManager();
        libraryService.addVisitor(visitor.getName(),visitor.getLastname(),visitor.getPasport(),visitor.getYear());
    }

    @Then("system save the visitor")
    public void checkVisitorInDB() {
        ArrayList visitor = (ArrayList) dalVisitorManager.getVisitorByPasport(this.visitor.getPasport());
        Assert.assertEquals(true, this.visitor.getName().equals(visitor.get(0)));
        Assert.assertEquals(true, this.visitor.getLastname().equals(visitor.get(1)));
        Assert.assertEquals(true, this.visitor.getPasport().equals(visitor.get(2)));
        Assert.assertEquals(true, this.visitor.getYear().equals(visitor.get(3)));
        dalVisitorManager.deleteAllVisitors();
    }
}
