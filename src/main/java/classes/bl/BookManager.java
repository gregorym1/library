package classes.bl;

import classes.domain.Book;
import classes.svc.svcdomain.OperationStatus;
import interfaces.dal.IDalBookManager;

import java.util.ArrayList;

/**
 * Created by Vitalii on 04.11.2016.
 */
public class BookManager {
    IDalBookManager dalBookManager;
    public BookValidator bookValidator = new BookValidator();
    public OperationStatus operationStatus = new OperationStatus();

    public BookManager(IDalBookManager dalBookManager){
        this.dalBookManager = dalBookManager;
    }

    public OperationStatus addBook(Book book){
        OperationStatus operationStatus = bookValidator.operationStatus;
        if (bookValidator.checkBook(book).isStatus()) {
            dalBookManager.addBookInDB(book.getName(), book.getAuthor(), book.getGenre(), book.getYear());
        }
        return operationStatus;
    }

    public ArrayList getAllBooks(){
        return dalBookManager.getListOfAllBooks();
    }

    public ArrayList getVisitorBooks(){
        ArrayList allBooks = dalBookManager.getListOfAllBooks();
        ArrayList visitorBooks = new ArrayList();

        for (int i = 0, takenBook = 5; i < allBooks.size(); i++) {
            if ( i % takenBook == 0 && i != 0){
                if ( allBooks.get(i).equals("0") ){
                    visitorBooks.add(allBooks.get(i-5));
                    visitorBooks.add(allBooks.get(i-4));
                    visitorBooks.add(allBooks.get(i-3));
                    visitorBooks.add(allBooks.get(i-2));
                    visitorBooks.add(allBooks.get(i-1));
                    visitorBooks.add(allBooks.get(i));
                }
                takenBook = takenBook + 6;
            }
        }
        return visitorBooks;
    }

    public ArrayList getLibraryBooks(){
        ArrayList allBooks = dalBookManager.getListOfAllBooks();
        ArrayList libraryBooks = new ArrayList();

        for (int i = 0, takenBook = 5; i < allBooks.size(); i++) {
            if ( i % takenBook == 0 && i != 0){
                if ( !allBooks.get(i).equals("0") ){
                    libraryBooks.add(allBooks.get(i-5));
                    libraryBooks.add(allBooks.get(i-4));
                    libraryBooks.add(allBooks.get(i-3));
                    libraryBooks.add(allBooks.get(i-2));
                    libraryBooks.add(allBooks.get(i-1));
                    libraryBooks.add(allBooks.get(i));
                }
                takenBook = takenBook + 6;
            }
        }
        return libraryBooks;
    }

    public ArrayList findBooks(String query){
        ArrayList allBooks = dalBookManager.getListOfAllBooks();
        ArrayList result = new ArrayList();

        for (int i = 0, name = 1; i < allBooks.size(); i++) {
            if ( i % name == 0 && i != 0){
                if ( allBooks.get(i).equals(query) ||
                        allBooks.get(i+1).equals(query) ||
                        allBooks.get(i+2).equals(query) ||
                        allBooks.get(i+3).equals(query)){
                    result.add(allBooks.get(i-1));
                    result.add(allBooks.get(i));
                    result.add(allBooks.get(i+1));
                    result.add(allBooks.get(i+2));
                    result.add(allBooks.get(i+3));
                    result.add(allBooks.get(i+4));
                }
                name = name + 6;
            }
        }
        return result;
    }

    public OperationStatus checkBook(String book_id){
        if (!book_id.isEmpty()) {
            if (dalBookManager.checkBookById(book_id).equals("1")) {
                operationStatus.setStatus(true);
            } else {
                operationStatus.setStatus(false);
                operationStatus.setExeption("Книги не существует");
            }
        } else {
            operationStatus.setStatus(false);
            operationStatus.setExeption("Пустое имя");
        }
        return operationStatus;
    }

    public OperationStatus giveBook(String book_id){
        if (dalBookManager.getBookPresence(book_id) == "1") {
            dalBookManager.updateBookPresence(book_id, false);
            operationStatus.setStatus(true);
        } else {
            operationStatus.setStatus(false);
            operationStatus.setExeption("Книга уже выдана");
        }

        return operationStatus;
    }

    public OperationStatus takeBook(String book_id){
        if (dalBookManager.getBookPresence(book_id).equals("0")) {
            dalBookManager.updateBookPresence(book_id, true);
            operationStatus.setStatus(true);
        } else {
            operationStatus.setStatus(false);
            operationStatus.setExeption("Эта книга уже в библиотеке");
        }

        return operationStatus;
    }

    public OperationStatus addDecommissionedBook(String bookId,String who, String reason) {
        if (checkBook(bookId).isStatus() && !who.isEmpty() && !reason.isEmpty()){
            dalBookManager.addDecommissionedBook(bookId,who,reason);
        } else {
            operationStatus.setStatus(false);
            operationStatus.setExeption("Пустое имя сотрудника или причина списывания книги");
        }
        return operationStatus;
    }
}
