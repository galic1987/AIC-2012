package tuwien.aic12.server.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name="userService")
public interface UserService {

	public String test(@WebParam(name = "testParam") String testParam);

        public String sayHi(@WebParam(name = "text") String text);
        
        public String login(@WebParam(name = "username") String username, @WebParam(name = "password") String password);
        
}
