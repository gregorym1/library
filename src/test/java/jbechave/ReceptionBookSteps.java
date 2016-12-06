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

/**
 * Created by Vitalii on 10.11.2016.
 */
public class ReceptionBookSteps extends Embedder {
    private LibraryService libraryService;
    private IDalBookManager dalBookManager;
    private IDalVisitorManager dalVisitorManager;
    private String idV, idB;

    @Given("a visitor_id and a book_id")
    public void init() {
        dalBookManager = new SqlDalBookManager();
        dalVisitorManager = new SqlDalVisitorManager();
        idB = "1";
        idV = "1";
    }

    @When("taking book from visitor")
    public void doing() {
        libraryService = new LibraryService();
        dalBookManager.deleteAllBooks();
        dalVisitorManager.deleteAllVisitors();
        libraryService.addBook("Гарри Поттер", "Роулинг","фентези","1998");
        libraryService.addVisitor("Виталий", "Бабенко","ао123456","1995");
        libraryService.giveBookToVisitor(idV, idB);
        libraryService.takeBookFromVisitor(idV, idB);
    }

    @Then("book presence is true, the visitor is no more has book")
    public void check() {
        String takenBookIdV = dalVisitorManager.getVisitorTakenBook(idV);
        String presenceBookIdB = dalBookManager.getBookPresence(idB);
        Assert.assertEquals("0", takenBookIdV);
        Assert.assertEquals("1", presenceBookIdB);
        dalBookManager.deleteAllBooks();
        dalVisitorManager.deleteAllVisitors();
    }
}
