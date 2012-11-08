package tuwien.aic12;

import javax.jws.WebService;

@WebService
public interface HelloWorld {
    String sayHi(String text);
}

