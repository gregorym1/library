package jbechave;

import classes.dal.SqlDalBookManager;
import classes.svc.LibraryService;
import interfaces.dal.IDalBookManager;
import junit.framework.Assert;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.embedder.Embedder;

import java.util.ArrayList;

/**
 * Created by Vitalii on 11.11.2016.
 */
public class GetAllBooksSteps extends Embedder {
    private LibraryService libraryService;
    private IDalBookManager dalBookManager;
    ArrayList books;

    @When("user want to get list of all books")
    public void getAllBooks() {
        libraryService = new LibraryService();
        dalBookManager = new SqlDalBookManager();
        dalBookManager.deleteAllBooks();
        libraryService.addBook("2","3","4","5");
        libraryService.addBook("8","9","10","11");
    }

    @Then("system give this list")
    public void check() {
        books = libraryService.getAllBooks();
        Assert.assertEquals(true, books.get(1).equals("2"));
        Assert.assertEquals(true, books.get(2).equals("3"));
        Assert.assertEquals(true, books.get(3).equals("4"));
        Assert.assertEquals(true, books.get(4).equals("5"));
        Assert.assertEquals(true, books.get(7).equals("8"));
        Assert.assertEquals(true, books.get(8).equals("9"));
        Assert.assertEquals(true, books.get(9).equals("10"));
        Assert.assertEquals(true, books.get(10).equals("11"));
        dalBookManager.deleteAllBooks();
    }
}
