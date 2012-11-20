package tuwien.aic12.server.service.impl;

import javax.jws.WebService;
import tuwien.aic12.model.User;
import tuwien.aic12.server.dao.DBManager;
import tuwien.aic12.server.dao.UserDao;
import tuwien.aic12.server.service.UserService;

@WebService(endpointInterface = "tuwien.aic12.server.service.UserService")
public class UserServiceImpl implements UserService {

    private String USER_NOT_FOUND = "User not found!";
    private String TOKEN_INVALID = "Token is invalid!";
    private String LOGOUT_SUCCESS = "User is now logged out!";
    UserDao userDao = new UserDao();

    @Override
    public String test(String testParam) {
        return "U rock Dude!!! " + testParam;
    }

    @Override
    public String registerUser(String username, String password) {

        User user = new User();
        user.setPassword(username);
        user.setUsername(password);
        DBManager.getInstance().getEntityManager().getTransaction().begin();
        user = userDao.create(user);
        DBManager.getInstance().getEntityManager().getTransaction().commit();

        return "Hello " + user.getUsername() + " ... user saved with id : " + user.getId();
    }

    @Override
    public String login(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        DBManager.getInstance().getEntityManager().getTransaction().begin();
        user = userDao.find(user);
        DBManager.getInstance().getEntityManager().getTransaction().commit();

        if (user != null) {
            System.out.print("Token :" + user.getToken());
            return user.getToken().toString();
        } else {
            return USER_NOT_FOUND;
        }
    }

    @Override
    public String logout(String token) {
        User user = new User();
        user.setToken(token);
        DBManager.getInstance().getEntityManager().getTransaction().begin();
        user = userDao.findUserbyToken(user);
        DBManager.getInstance().getEntityManager().getTransaction().commit();

        if (user != null) {
            return LOGOUT_SUCCESS;
        } else {
            return TOKEN_INVALID;
        }
    }
}
