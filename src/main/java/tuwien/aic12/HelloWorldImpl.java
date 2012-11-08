
package tuwien.aic12;

import javax.jws.WebService;

@WebService(endpointInterface = "tuwien.aic12.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    public String sayHi(String text) {
        return "Hello " + text;
    }
}

