package dalTEST;

import classes.dal.SqlDalBookManager;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Vitalii on 04.11.2016.
 */
public class SqlDalBookManagerTest extends TestCase {
    SqlDalBookManager tester;

    protected void setUp(){
        tester = new SqlDalBookManager();
        tester.deleteAllBooks();
        tester.deleteAllDecommissionedBooks();

    }
    protected void tearDown(){
        tester.deleteAllBooks();
        tester.deleteAllDecommissionedBooks();
    }

    @Test
    public void testAddBookInDB(){
        String bookName = "Гарри Поттер Test", bookAuthor = "Роулинг",
                bookGenre = "фэнтези", bookYear = "1999";
        tester.addBookInDB(bookName, bookAuthor, bookGenre, bookYear);
        ArrayList book = (ArrayList) tester.getBookByName("Гарри Поттер Test");
        assertEquals(true, book.get(0).equals(bookName));
        assertEquals(true, book.get(1).equals(bookAuthor));
        assertEquals(true, book.get(2).equals(bookGenre));
        assertEquals(true, book.get(3).equals(bookYear));
    }

    @Test
    public void testSetPresence(){
        String bookName = "Гарри Поттер", bookAuthor = "Роулинг",
                bookGenre = "фэнтези", bookYear = "1999";

        tester.addBookInDB(bookName, bookAuthor, bookGenre, bookYear);
        assertEquals("1", tester.getBookPresence("1"));
        tester.updateBookPresence("1",false);
        assertEquals("0", tester.getBookPresence("1"));
    }

    @Test
    public void testAddDecommissionedBook(){
        String bookName = "Гарри Поттер", bookAuthor = "Роулинг",
                bookGenre = "фэнтези", bookYear = "1999";

        tester.addBookInDB(bookName, bookAuthor, bookGenre, bookYear);
        tester.addDecommissionedBook("1","test","test2");
        assertEquals("0", tester.checkBookById("1"));
        assertEquals("1", tester.checkDecommissionedBookById("1"));
    }

    @Test
    public void testGetListOfAllBooks(){
        String bookName = "Гарри Поттер", bookAuthor = "Роулинг",
                bookGenre = "фэнтези", bookYear = "1999";
        tester.addBookInDB(bookName, bookAuthor, bookGenre, bookYear);
        tester.addBookInDB(bookName, bookAuthor, bookGenre, bookYear);
        ArrayList book = tester.getListOfAllBooks();
        assertEquals(true, book.get(1).equals(bookName));
        assertEquals(true, book.get(2).equals(bookAuthor));
        assertEquals(true, book.get(3).equals(bookGenre));
        assertEquals(true, book.get(4).equals(bookYear));
        assertEquals(true, book.get(7).equals(bookName));
        assertEquals(true, book.get(8).equals(bookAuthor));
        assertEquals(true, book.get(9).equals(bookGenre));
        assertEquals(true, book.get(10).equals(bookYear));
    }
}
