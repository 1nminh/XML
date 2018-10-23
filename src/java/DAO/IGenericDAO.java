/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;

/**
 *
 * @author adler
 */
public interface IGenericDAO<T, PK> {

    public List<T> getAll(String namedQuery);

    public T create(T t);

    public T findByID(PK t);

    public T update(T t);

    public boolean delete(T t);
}
