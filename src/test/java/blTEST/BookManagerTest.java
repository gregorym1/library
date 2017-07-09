package blTEST;

import classes.bl.BookManager;
import classes.dal.SqlDalBookManager;
import classes.domain.Book;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

/**
 * Created by Vitalii on 05.11.2016.
 */
public class BookManagerTest extends TestCase{
    private BookManager tester;
    private SqlDalBookManager sqlDalBookManager;

    protected void setUp(){
        sqlDalBookManager = mock(SqlDalBookManager.class);
        ArrayList books = new ArrayList();
        for (int i = 1; i <= 12; i++) {
            books.add(""+i);
        }
        when(sqlDalBookManager.getListOfAllBooks()).thenReturn(books);
        when(sqlDalBookManager.checkBookById("777")).thenReturn("1");
        when(sqlDalBookManager.checkBookById("888")).thenReturn("0");
        when(sqlDalBookManager.getBookPresence("777")).thenReturn("1");
        when(sqlDalBookManager.getBookPresence("888")).thenReturn("0");
        tester = new BookManager(sqlDalBookManager);
    }

    protected void tearDown(){

    }

    @Test
    public void testAddBook(){
        Book book = new Book("Гарри Поттер","Роулинг","фэнтези","1998");
        tester.addBook(book);
        verify(sqlDalBookManager).addBookInDB(book.getName(),book.getAuthor(),book.getGenre(),
                book.getYear());

        book = new Book("","","фэнтези","1998");
        tester.addBook(book);
        verify(sqlDalBookManager, never()).addBookInDB(book.getName(),
                        book.getAuthor(),book.getGenre(),book.getYear());
    }

    @Test
    public void testCheckBook(){
        assertEquals(true,  tester.checkBook("777").isStatus());
        assertEquals(false,  tester.checkBook("888").isStatus());
        assertEquals(false,  tester.checkBook("").isStatus());
    }

    @Test
    public void testGiveBook(){
        assertEquals(true,  tester.giveBook("777").isStatus());
        assertEquals(false,  tester.giveBook("888").isStatus());
    }

    @Test
    public void testTakeBook(){
        assertEquals(true,  tester.giveBook("777").isStatus());
        assertEquals(false,  tester.giveBook("888").isStatus());
    }

    @Test
    public void testAddDecommissionedBook(){
        assertEquals(true,  tester.addDecommissionedBook("777","1","1").isStatus());
        assertEquals(false,  tester.addDecommissionedBook("888","1","1").isStatus());
        assertEquals(false,  tester.addDecommissionedBook("888","","1").isStatus());
        assertEquals(false,  tester.addDecommissionedBook("888","1","").isStatus());
    }

    @Test
    public void testGetAllBooks(){
        ArrayList books = tester.getAllBooks();
        assertEquals(true, books.get(0).equals("1"));
        assertEquals(true, books.get(6).equals("7"));
    }


}
