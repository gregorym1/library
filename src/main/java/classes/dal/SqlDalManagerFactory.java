package classes.dal;

import interfaces.dal.IDalBookManager;
import interfaces.dal.IDalManagerFactory;
import interfaces.dal.IDalVisitorManager;

/**
 * Created by Vitalii on 04.11.2016.
 */
public class SqlDalManagerFactory implements IDalManagerFactory {
    public IDalBookManager createBookDalManager(){
        return new SqlDalBookManager();
    }
    public IDalVisitorManager createVisitorDalManager() {
        return new SqlDalVisitorManager();
    }
}
