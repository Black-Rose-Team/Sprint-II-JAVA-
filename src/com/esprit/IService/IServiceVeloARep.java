/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.IService;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author OMEN
 */
public interface IServiceVeloARep<V> {
     void ajouter(V v) throws SQLException;
    void delete(V v) throws SQLException;
    void update(V v) throws SQLException;
    List<V> readAll() throws SQLException;
            
}
