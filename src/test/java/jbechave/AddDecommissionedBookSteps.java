package jbechave;

import classes.dal.SqlDalBookManager;
import classes.domain.Book;
import classes.svc.LibraryService;
import interfaces.dal.IDalBookManager;
import junit.framework.Assert;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.embedder.Embedder;

import java.util.List;

/**
 * Created by Vitalii on 10.11.2016.
 */
public class AddDecommissionedBookSteps extends Embedder {
    private LibraryService libraryService;
    private Book book;
    private IDalBookManager dalBookManager;

    @Given("a book in library")
    public void init() {
        book = new Book("Potter","Rouling","genre","1999");
        libraryService = new LibraryService();
        dalBookManager = new SqlDalBookManager();
        dalBookManager.deleteAllBooks();
        libraryService.addBook(book.getName(),book.getAuthor(),book.getGenre(),book.getYear());
    }

    @When("book is \"decommissioned\"")
    public void doing() {
        libraryService.addDecommissionedBook("1","1","1");
    }

    @Then("book is deleted from usual book-list, and added in decommissioned book-list")
    public void check() {
        Assert.assertEquals("0", dalBookManager.checkBookById("1"));
        Assert.assertEquals("1", dalBookManager.checkDecommissionedBookById("1"));
        dalBookManager.deleteAllBooks();
        dalBookManager.deleteAllDecommissionedBooks();
    }
}
