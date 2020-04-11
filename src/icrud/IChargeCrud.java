/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icrud;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Crow
 */
public interface IChargeCrud <E> {
     void ajouter(E e) throws SQLException;
    void delete(E e) throws SQLException;
    void update(E e) throws SQLException;
    List<E> readAll() throws SQLException;
    
}
