package jbechave;

import classes.dal.SqlDalBookManager;
import classes.dal.SqlDalVisitorManager;
import classes.domain.Visitor;
import classes.svc.LibraryService;
import interfaces.dal.IDalBookManager;
import interfaces.dal.IDalVisitorManager;
import junit.framework.Assert;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.embedder.Embedder;

import java.util.List;

/**
 * Created by Vitalii on 09.11.2016.
 */
public class GiveBookSteps extends Embedder {

    private LibraryService libraryService;
    private IDalBookManager dalBookManager;
    private IDalVisitorManager dalVisitorManager;
    private String idV, idB;

    @Given("a book_id and visitor_id")
    public void init() {
        dalBookManager = new SqlDalBookManager();
        dalVisitorManager = new SqlDalVisitorManager();
        idB = "1";
        idV = "1";
    }

    @When("giving book to visitor")
    public void doing() {
        libraryService = new LibraryService();
        dalBookManager.deleteAllBooks();
        dalVisitorManager.deleteAllVisitors();
        libraryService.addBook("Гарри Поттер", "Роулинг","фентези","1998");
        libraryService.addVisitor("Виталий", "Бабенко","ао123456","1995");
        libraryService.giveBookToVisitor(idV,idB);
    }

    @Then("visitor take a book, book presence is false")
    public void check() {
        String takenBookIdV = dalVisitorManager.getVisitorTakenBook(idV);
        String presenceBookIdB = dalBookManager.getBookPresence(idB);
        Assert.assertEquals(idB, takenBookIdV);
        Assert.assertEquals("0", presenceBookIdB);
        dalBookManager.deleteAllBooks();
        dalVisitorManager.deleteAllVisitors();
    }
}
