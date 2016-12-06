package classes.bl;

import interfaces.dal.IDalManagerFactory;

/**
 * Created by Vitalii on 04.11.2016.
 */
public class ManagerFactory {
    IDalManagerFactory dalManagerFactory;

    public ManagerFactory(IDalManagerFactory dalManagerFactory){
        this.dalManagerFactory = dalManagerFactory;
    }

    public BookManager createBookManager(){
        return new BookManager(dalManagerFactory.createBookDalManager());
    }
    public VisitorManager createVisitorManager(){
        return new VisitorManager(dalManagerFactory.createVisitorDalManager());
    }

}
