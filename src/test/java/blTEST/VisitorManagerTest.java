package blTEST;

import classes.bl.VisitorManager;
import classes.dal.SqlDalVisitorManager;
import classes.domain.Visitor;
import junit.framework.TestCase;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by Vitalii on 05.11.2016.
 */
public class VisitorManagerTest extends TestCase{
    VisitorManager tester;
    SqlDalVisitorManager sqlDalVisitorManager;

    protected void setUp(){
        sqlDalVisitorManager = mock(SqlDalVisitorManager.class);
        when(sqlDalVisitorManager.checkVisitorById("777")).thenReturn("1");
        when(sqlDalVisitorManager.checkVisitorById("888")).thenReturn("0");
        when(sqlDalVisitorManager.getVisitorTakenBook("777")).thenReturn("1");
        when(sqlDalVisitorManager.getVisitorTakenBook("888")).thenReturn("0");
        tester = new VisitorManager(sqlDalVisitorManager);
    }

    protected void tearDown(){

    }

    @Test
    public void testAddVisitor(){
        Visitor visitor = new Visitor("Виталий","Бабенко","ао1298364","1995");
        tester.addVisitor(visitor);
        verify(sqlDalVisitorManager).addVisitorInDB(visitor.getName(), visitor.getLastname(),visitor.getPasport(),
                visitor.getYear());

        visitor = new Visitor("","","","");
        tester.addVisitor(visitor);
        verify(sqlDalVisitorManager, never()).addVisitorInDB(visitor.getName(), visitor.getLastname(),visitor.getPasport(),
                visitor.getYear());
    }

    @Test
    public void testCheckVisitor(){
        assertEquals(true,  tester.checkVisitor("777").isStatus());
        assertEquals(false,  tester.checkVisitor("888").isStatus());
        assertEquals(false,  tester.checkVisitor("").isStatus());
    }

    @Test
    public void testTakeBook(){
        assertEquals(false,  tester.takeBook("777","1").isStatus());
        assertEquals( true,  tester.takeBook("888","1").isStatus());
    }

    @Test
    public void testGiveBook(){
        assertEquals(true,  tester.takeBook("888","1").isStatus());
        assertEquals(false ,  tester.takeBook("777","1").isStatus());
    }


}
