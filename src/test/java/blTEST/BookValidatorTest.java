package blTEST;

import classes.bl.BookValidator;
import classes.domain.Book;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Vitalii on 04.11.2016.
 */
public class BookValidatorTest extends TestCase {
    BookValidator tester;
    protected void setUp(){
        tester = new BookValidator();
    }

    protected void tearDown(){

    }

    @Test
    public void testCheckBook(){
        Book book1 = new Book("Гарри Поттер","Роулинг","фэнтези","1994");
        Book book4 = new Book("Гарри Поттер","","фэнтези","1994");
        Book book5 = new Book("","Роулинг","фэнтези","1994");
        assertEquals(true,  tester.checkBook(book1).isStatus());
        assertEquals(false, tester.checkBook(book4).isStatus());
        assertEquals(false, tester.checkBook(book5).isStatus());
    }
}
