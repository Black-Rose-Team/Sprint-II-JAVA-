/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Utils;

import com.esprit.Entite.User;
import java.util.Optional;


/**
 *
 * @author hamza
 */
public class Session {
    private static Optional<User>  user;
   

    public static void start(User currentUser) {
        user  = user.of(currentUser);
    }

    public static void close() throws Exception {
        if (user != null) {
            user = null;
        } else {
            throw new Exception("Session has not started yet!");
        }
    }
    public static Optional<User> getCurrentUser()
    {
     return user;   
    }
}
