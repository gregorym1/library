package jbechave;

import classes.domain.Book;
import classes.domain.Visitor;
import classes.svc.LibraryService;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.embedder.Embedder;
import org.junit.Assert;

import java.util.ArrayList;

/**
 * Created by Vitalii on 20.11.2016.
 */
public class FindSteps extends Embedder {
    LibraryService libraryService;
    Visitor visitor;
    Book book;
    ArrayList resultBook, resultVisitor;

    @Given("a book and a visitor")
    public void init(){
        libraryService = new LibraryService();
        book = new Book("1","2","3","4");
        visitor = new Visitor("1","2","3","4");
        libraryService.addBook(book.getName(),book.getAuthor(),book.getGenre(),book.getYear());
        libraryService.addVisitor(visitor.getName(),visitor.getLastname(),visitor.getPasport(),visitor.getYear());
    }

    @When("user want find book or visitor")
    public void doSome(){
        resultVisitor = libraryService.findVisitors("1");
        resultBook = libraryService.findBooks("1");
    }

    @Then("system give info to user")
    public void check(){
        Assert.assertEquals(true, resultBook.get(1).equals(book.getName()));
        Assert.assertEquals(true, resultVisitor.get(1).equals(visitor.getName()));
    }
}
