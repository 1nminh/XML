/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.PersistenceContext;

/**
 *
 * @author adler
 */
public class DBUtilities {

    public DBUtilities() {
    }
    private static EntityManagerFactory emf;
    private static final Object LOCK = new Object();

    public static EntityManager getEntityManager() {
        synchronized (LOCK) {
            if (emf == null) {
                try {
                    emf = PersistenceContext.createEntityManagerFactory("TPGamingGearPU");                   
                } catch (Exception e) {
                    Logger.getLogger(DBUtilities.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return emf.createEntityManagerFactory;
    }
}
