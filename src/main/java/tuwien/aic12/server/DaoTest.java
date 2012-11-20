package tuwien.aic12.server;

import tuwien.aic12.model.User;
import tuwien.aic12.server.dao.UserDao;

/**
 *
 * @author vanjalee
 */
public class DaoTest {
    
    public static void main(String [] args) {        
        // insertUser();
    }
    
        
    private static void insertUser(){
    	User user = new User();
    	user.setPassword("123");
    	user.setUsername("123");
    	
        UserDao userDao = new UserDao();
        userDao.create(user);        
    }
    
}
