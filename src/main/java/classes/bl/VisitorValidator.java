package classes.bl;

import classes.domain.Visitor;
import classes.svc.svcdomain.OperationStatus;

/**
 * Created by Vitalii on 05.11.2016.
 */
public class VisitorValidator {
    public OperationStatus operationStatus = new OperationStatus();
    public OperationStatus checkVisitor(Visitor visitor){
        if (visitor.getName().isEmpty()){
            operationStatus.setStatus(false);
            operationStatus.setExeption("Пустое имя");
            return operationStatus;
        } else if(visitor.getLastname().isEmpty()){
            operationStatus.setStatus(false);
            operationStatus.setExeption("Пустая фамилия");
            return operationStatus;
        } else if(visitor.getPasport().isEmpty()){
            operationStatus.setStatus(false);
            operationStatus.setExeption("Пустой паспорт");
            return operationStatus;
        } else if(visitor.getYear().isEmpty()){
            operationStatus.setStatus(false);
            operationStatus.setExeption("Пустой год");
            return operationStatus;
        }
        operationStatus.setStatus(true);
        return operationStatus;


    }
}
