package interfaces.dal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitalii on 05.11.2016.
 */
public interface IDalVisitorManager {
    void addVisitorInDB(String name, String lastname, String pasport, String year);

    void updateTakenBook(String idV, String idB);

    String getVisitorTakenBook(String id);

    List getVisitorByPasport(String pasport);
    void deleteAllVisitors();

    ArrayList getListOfAllVisitors();

    String checkVisitorById(String id);
}
