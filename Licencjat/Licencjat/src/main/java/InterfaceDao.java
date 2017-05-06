import Pojo._Trip;

import java.io.Serializable;
import java.util.List;

public interface InterfaceDao<T, Id extends Serializable> {

    public void save(T entity);  //zapis/update encji
    public T getById(int id);   //dostep przez id
    public T getByKey(String key);  //dostep przez klucz (np login)
    public List<T> getList();      //dostep do listy rekordow
    public List<T> getListByKey(String key);    //lista wyszukiwania wzgledem klucza
    public List<T> getOrderedList(String key);  //lista posortowana wzgledem klucza
    public void delete(T entity);      //kasowanie rekordu
    public void deleteAll();       //kasowanie wszytkich rekordow

}
