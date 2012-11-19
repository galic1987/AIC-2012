package tuwien.aic12.server.service.impl;

import javax.jws.WebService;
import tuwien.aic12.model.User;
import tuwien.aic12.server.dao.DBManager;
import tuwien.aic12.server.dao.UserDao;
import tuwien.aic12.server.service.UserService;

@WebService(endpointInterface="tuwien.aic12.server.service.UserService")
public class UserServiceImpl implements UserService {

     UserDao userDao = new UserDao();
    
    @Override
    public String test(String testParam) {
        return "U rock Dude!!! " + testParam;
    }
   
    @Override
    public String sayHi(String text) {

        User user = new User();
        user.setPassword("asd");
        user.setUsername("asd");
        DBManager.getInstance().getEntityManager().getTransaction().begin();
        user = userDao.create(user);
        DBManager.getInstance().getEntityManager().getTransaction().commit();

        return "Hello " + text + " ... user saved with id : " + user.getId();
    }

    @Override
    public String login(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        UserDao udao = new UserDao();
        DBManager.getInstance().getEntityManager().getTransaction().begin();
        user = udao.find(user);
        DBManager.getInstance().getEntityManager().getTransaction().commit();
        System.out.print("Token :" + user.getToken());
        return user.getToken().toString();
        
    }
}
