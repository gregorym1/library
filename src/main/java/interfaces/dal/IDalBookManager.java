package interfaces.dal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitalii on 04.11.2016.
 */
public interface IDalBookManager {
    void addBookInDB(String name, String author, String genre, String year);
    List getBookByName(String name);

    ArrayList getListOfAllBooks();

    void deleteAllBooks();

    void deleteAllDecommissionedBooks();

    void updateBookPresence(String id, boolean presence);

    String checkBookById(String id);

    String getBookPresence(String id);

    void addDecommissionedBook(String id, String who, String reason);

    String checkDecommissionedBookById(String id);
}
