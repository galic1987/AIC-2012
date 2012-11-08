/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tuwien.aic12.server;

import tuwien.aic12.model.User;
import tuwien.aic12.server.dao.DBManager;

/**
 *
 * @author vanjalee
 */
public class DaoTest {
    
    public static void main(String [] args) {
        
        insertUser();
        
    }
    
        
    private static void insertUser(){
    	User user = new User();
    	user.setPassword("123");
    	user.setUsername("123");
    	
        DBManager.getInstance().getEntityManager().getTransaction().begin();
    	DBManager.getInstance().getEntityManager().persist(user);
    	DBManager.getInstance().getEntityManager().getTransaction().commit();
        
    }
    
}
