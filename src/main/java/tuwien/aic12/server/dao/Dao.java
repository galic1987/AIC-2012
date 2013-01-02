/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tuwien.aic12.server.dao;

/**
 *
 * @author vanjalee
 */
public interface Dao<T> {

    public T create(T t);

    public T update(T t);

    public boolean delete(T t);

    public T read(long id);
}
