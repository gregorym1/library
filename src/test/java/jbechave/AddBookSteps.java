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
 * Created by Vitalii on 05.11.2016.
 */
public class AddBookSteps extends Embedder {

    private LibraryService libraryService;
    private Book book;
    private IDalBookManager dalBookManager;


    @Given("a book")
    public void initBook() {
        book = new Book("Potter","Rouling","genre","1999");
    }

    @When("the book added")
    public void addBookInDB() {
        libraryService = new LibraryService();
        dalBookManager = new SqlDalBookManager();
        libraryService.addBook(book.getName(),book.getAuthor(),book.getGenre(),book.getYear());
    }

    @Then("system save the book")
    public void checkBookInDB() {
        List book = dalBookManager.getBookByName(this.book.getName());
        Assert.assertEquals(true, this.book.getName().equals(book.get(0)));
        Assert.assertEquals(true, this.book.getAuthor().equals(book.get(1)));
        Assert.assertEquals(true, this.book.getGenre().equals(book.get(2)));
        Assert.assertEquals(true, this.book.getYear().equals(book.get(3)));
        dalBookManager.deleteAllBooks();
    }
}


