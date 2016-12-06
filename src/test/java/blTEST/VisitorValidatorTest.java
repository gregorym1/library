package blTEST;

import classes.bl.VisitorValidator;
import classes.domain.Visitor;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Vitalii on 05.11.2016.
 */
public class VisitorValidatorTest extends TestCase {
    VisitorValidator tester;
    protected void setUp(){
        tester = new VisitorValidator();
    }

    protected void tearDown(){

    }

    @Test
    public void testCheckBook(){
        Visitor visitor1 = new Visitor("Виталий","Бабенко","ао123456","1994");
        Visitor visitor2 = new Visitor("Виталий","Бабенко","ао123456","");
        Visitor visitor3 = new Visitor("Виталий","Бабенко","","1994");
        Visitor visitor4 = new Visitor("Виталий","","ао123456","1994");
        Visitor visitor5 = new Visitor("","Бабенко","ао123456","1994");
        assertEquals(true,  tester.checkVisitor(visitor1).isStatus());
        assertEquals(false, tester.checkVisitor(visitor2).isStatus());
        assertEquals(false, tester.checkVisitor(visitor3).isStatus());
        assertEquals(false, tester.checkVisitor(visitor4).isStatus());
        assertEquals(false, tester.checkVisitor(visitor5).isStatus());
    }
}
