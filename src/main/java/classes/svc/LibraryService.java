package classes.svc;

import classes.bl.BookManager;
import classes.bl.ManagerFactory;
import classes.bl.VisitorManager;
import classes.domain.Book;
import classes.domain.Visitor;
import classes.svc.svcdomain.OperationStatus;
import interfaces.dal.IDalManagerFactory;

import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Vitalii on 04.11.2016.
 */
public class LibraryService {
    private BookManager bookManager;
    private VisitorManager visitorManager;
    private ManagerFactory managerFactory;

    public OperationStatus operationStatus = new OperationStatus();

    private static final String BUILDER_CLASS_DAL_MANAGER = "dalManager";

    public LibraryService(){
        try {
            ResourceBundle pr = ResourceBundle.getBundle("builder");
            String classNameDalManager = pr.getString(BUILDER_CLASS_DAL_MANAGER);

            Class classDalManagerFactory = Class.forName(classNameDalManager);
            IDalManagerFactory dalManagerFactory =(IDalManagerFactory) classDalManagerFactory.newInstance();
            managerFactory = new ManagerFactory(dalManagerFactory);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e){
            throw new RuntimeException("Exception with load dal-class from properties file", e);
        }

        bookManager = managerFactory.createBookManager();
        visitorManager = managerFactory.createVisitorManager();
    }

    public OperationStatus addBook(String name,String author, String genre, String year){
        operationStatus = bookManager.addBook(new Book(name,author,genre,year));
        return operationStatus;
    }

    public ArrayList getAllBooks(){
        return bookManager.getAllBooks();
    }

    public ArrayList getVisitorBooks(){
        return bookManager.getVisitorBooks();
    }

    public ArrayList getLibararyBooks(){
        return bookManager.getLibraryBooks();
    }

    public ArrayList getAllVisitors(){
        return visitorManager.getAllVisitors();
    }

    public ArrayList findBooks(String query){
        return bookManager.findBooks(query);
    }

    public ArrayList findVisitors(String query){
        return visitorManager.findVisitors(query);
    }

    public ArrayList getActiveVisitors(){
        return visitorManager.getActiveVisitors();
    }

    public ArrayList getPassiveVisitors(){
        return visitorManager.getPassiveVisitors();
    }

    public OperationStatus addVisitor(String name,String lastname, String pasport, String year){
        visitorManager.addVisitor(new Visitor(name,lastname,pasport,year));
        operationStatus = visitorManager.visitorValidator.operationStatus;
        return operationStatus;
    }

    public OperationStatus giveBookToVisitor(String book_id, String visitor_id){
        if(bookManager.checkBook(book_id).isStatus()) {
            if (visitorManager.checkVisitor(visitor_id).isStatus()) {
                if (bookManager.giveBook(book_id).isStatus()) {
                    if (visitorManager.takeBook(visitor_id, book_id).isStatus()) {
                        operationStatus = visitorManager.operationStatus;
                        return operationStatus;
                    } else {
                        bookManager.takeBook(book_id);
                        operationStatus = visitorManager.operationStatus;
                        operationStatus.setStatus(false);
                        return operationStatus;
                    }
                } else {
                    operationStatus = bookManager.operationStatus;
                    operationStatus.setStatus(false);
                    return operationStatus;
                }
            } else {
                operationStatus = visitorManager.operationStatus;
                operationStatus.setStatus(false);
                return operationStatus;
            }
        } else {
            operationStatus = bookManager.operationStatus;
            operationStatus.setStatus(false);
            return operationStatus;
        }
    }

    public OperationStatus takeBookFromVisitor(String book_id, String visitor_id){
        if(bookManager.checkBook(book_id).isStatus()) {
            if (visitorManager.checkVisitor(visitor_id).isStatus()) {
                if (bookManager.takeBook(book_id).isStatus()) {
                    if (visitorManager.giveBook(visitor_id, book_id).isStatus()) {
                        operationStatus = visitorManager.operationStatus;
                        return operationStatus;
                    } else {
                        operationStatus = visitorManager.operationStatus;
                        operationStatus.setStatus(false);
                        return operationStatus;
                    }
                } else {
                    operationStatus = bookManager.operationStatus;
                    operationStatus.setStatus(false);
                    return operationStatus;
                }
            } else {
                operationStatus = visitorManager.operationStatus;
                operationStatus.setStatus(false);
                return operationStatus;
            }
        } else {
            operationStatus = bookManager.operationStatus;
            operationStatus.setStatus(false);
            return operationStatus;
        }
    }

    public OperationStatus addDecommissionedBook(String bookId, String who, String reason) {
        bookManager.addDecommissionedBook(bookId,who,reason);
        operationStatus = bookManager.operationStatus;
        return operationStatus;
    }
}
