package dalTEST;

import classes.dal.SqlDalVisitorManager;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Vitalii on 05.11.2016.
 */
public class SqlDalVisitorManagerTest extends TestCase {
    SqlDalVisitorManager tester;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        tester = new SqlDalVisitorManager();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        tester.deleteAllVisitors();
    }

    @Test
    public void testAddVisitorInDB(){
        String visitorName = "Виталий", visitorLastname = "Бабенко",
                visitorPasport = "ао123456", visitorYear = "1995";
        tester.addVisitorInDB(visitorName, visitorLastname, visitorPasport, visitorYear);
        ArrayList visitor = (ArrayList) tester.getVisitorByPasport("ао123456");
        assertEquals(true, visitor.get(0).equals(visitorName));
        assertEquals(true, visitor.get(1).equals(visitorLastname));
        assertEquals(true, visitor.get(2).equals(visitorPasport));
        assertEquals(true, visitor.get(3).equals(visitorYear));
    }

    @Test
    public void testTakeBook(){
        String visitorName = "Виталий", visitorLastname = "Бабенко",
                visitorPasport = "ао123456", visitorYear = "1995";
        tester.addVisitorInDB(visitorName, visitorLastname, visitorPasport, visitorYear);
        tester.updateTakenBook("1","1");
        assertEquals("1", tester.getVisitorTakenBook("1"));
    }
}
