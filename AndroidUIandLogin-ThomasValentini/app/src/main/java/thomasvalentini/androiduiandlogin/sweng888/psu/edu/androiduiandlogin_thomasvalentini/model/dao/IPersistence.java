package thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.model.dao;

import java.util.ArrayList;

/**
 * @author ezt157
 * It defines basic interface to support database operations over different entities/tables
 * @param <T>
 */
public interface IPersistence<T> {

    public void insert(T t);

    public void delete(T t);

    public void edit (T t);

    public ArrayList<T> getDataFromDB();

}
