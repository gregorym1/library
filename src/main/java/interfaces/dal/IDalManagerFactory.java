package interfaces.dal;

/**
 * Created by Vitalii on 04.11.2016.
 */
public interface IDalManagerFactory {
    IDalBookManager createBookDalManager();
    IDalVisitorManager createVisitorDalManager();
}
