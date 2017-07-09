package classes.bl;

import classes.domain.Visitor;
import classes.svc.svcdomain.OperationStatus;
import interfaces.dal.IDalVisitorManager;

import java.util.ArrayList;

/**
 * Created by Vitalii on 05.11.2016.
 */
public class VisitorManager {
    private IDalVisitorManager dalVisitorManager;
    public VisitorValidator visitorValidator = new VisitorValidator();
    public OperationStatus operationStatus = new OperationStatus();

    public VisitorManager(IDalVisitorManager dalVisitorManager){
        this.dalVisitorManager = dalVisitorManager;
    }

    public OperationStatus addVisitor(Visitor visitor){
        OperationStatus operationStatus = visitorValidator.operationStatus;
        if (visitorValidator.checkVisitor(visitor).isStatus()) {
            dalVisitorManager.addVisitorInDB(visitor.getName(), visitor.getLastname(), visitor.getPasport(), visitor.getYear());
        }
        return operationStatus;
    }

    public ArrayList findVisitors(String query){
        ArrayList allVisitors = dalVisitorManager.getListOfAllVisitors();
        ArrayList result = new ArrayList();

        for (int i = 0, name = 1; i < allVisitors.size(); i++) {
            if ( i % name == 0 && i != 0){
                if ( allVisitors.get(i).equals(query) ||
                        allVisitors.get(i+1).equals(query) ||
                        allVisitors.get(i+2).equals(query) ||
                        allVisitors.get(i+3).equals(query)){
                    result.add(allVisitors.get(i-1));
                    result.add(allVisitors.get(i));
                    result.add(allVisitors.get(i+1));
                    result.add(allVisitors.get(i+2));
                    result.add(allVisitors.get(i+3));
                    result.add(allVisitors.get(i+4));
                }
                name = name + 6;
            }
        }
        return result;
    }

    public  OperationStatus checkVisitor(String visitor_id){
        if (!visitor_id.isEmpty()){
            if (dalVisitorManager.checkVisitorById(visitor_id).equals("1")){
                operationStatus.setStatus(true);
            } else {
                operationStatus.setExeption("Посетителя не существует");
                operationStatus.setStatus(false);
            }
        } else {
            operationStatus.setExeption("Введите номер посетителя");
            operationStatus.setStatus(false);
        }
        return operationStatus;
    }

    public ArrayList getAllVisitors(){
        return dalVisitorManager.getListOfAllVisitors();
    }

    public ArrayList getActiveVisitors() {
        ArrayList allVisitors = dalVisitorManager.getListOfAllVisitors();
        ArrayList activeVisitors = new ArrayList();

        for (int i = 0, takenBook = 5; i < allVisitors.size(); i++) {
            if (i % takenBook == 0 && i != 0) {
                if (!allVisitors.get(i).equals("0")) {
                    activeVisitors.add(allVisitors.get(i - 5));
                    activeVisitors.add(allVisitors.get(i - 4));
                    activeVisitors.add(allVisitors.get(i - 3));
                    activeVisitors.add(allVisitors.get(i - 2));
                    activeVisitors.add(allVisitors.get(i - 1));
                    activeVisitors.add(allVisitors.get(i));
                }
                takenBook = takenBook + 6;
            }
        }
        return activeVisitors;
    }

    public ArrayList getPassiveVisitors() {
        ArrayList allVisitors = dalVisitorManager.getListOfAllVisitors();
        ArrayList passiveVisitors = new ArrayList();

        for (int i = 0, takenBook = 5; i < allVisitors.size(); i++) {
            if (i % takenBook == 0 && i != 0) {
                if (allVisitors.get(i).equals("0")) {
                    passiveVisitors.add(allVisitors.get(i - 5));
                    passiveVisitors.add(allVisitors.get(i - 4));
                    passiveVisitors.add(allVisitors.get(i - 3));
                    passiveVisitors.add(allVisitors.get(i - 2));
                    passiveVisitors.add(allVisitors.get(i - 1));
                    passiveVisitors.add(allVisitors.get(i));
                }
                takenBook = takenBook + 6;
            }
        }
        return passiveVisitors;
    }

    public OperationStatus takeBook(String visitor_id, String book_id){
        if (dalVisitorManager.getVisitorTakenBook(visitor_id).equals("0")){
            dalVisitorManager.updateTakenBook(visitor_id,book_id);
            operationStatus.setStatus(true);
        } else {
            operationStatus.setExeption("Посетитель уже взял другую книгу");
            operationStatus.setStatus(false);
        }
        return operationStatus;
    }

    public OperationStatus giveBook(String visitor_id, String book_id){
        if (dalVisitorManager.getVisitorTakenBook(visitor_id).equals(book_id)){
            dalVisitorManager.updateTakenBook(visitor_id, "0");
            operationStatus.setStatus(true);
        } else {
            operationStatus.setExeption("У посетителя нет этой книги");
            operationStatus.setStatus(false);
        }
        return operationStatus;
    }
}
