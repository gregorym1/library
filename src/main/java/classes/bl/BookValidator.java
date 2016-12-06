package classes.bl;

import classes.domain.Book;
import classes.svc.svcdomain.OperationStatus;

public class BookValidator {
    public OperationStatus operationStatus = new OperationStatus();
    public OperationStatus checkBook(Book book){
        if (book.getName().isEmpty()){
            operationStatus.setStatus(false);
            operationStatus.setExeption("Пустое имя");
            return operationStatus;
        } else if(book.getAuthor().isEmpty()){
            operationStatus.setStatus(false);
            operationStatus.setExeption("Пустой автор");
            return operationStatus;
        }
        operationStatus.setStatus(true);
        return operationStatus;


    }
}
