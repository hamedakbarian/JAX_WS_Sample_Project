package example;

import org.apache.log4j.Logger;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * Created by weblogic12 on 4/19/2015.
 */
@WebService()
public class HelloWorld {

    static Logger log = Logger.getLogger(HelloWorld.class.getName());

    @WebMethod
    public String sayHelloWorldFrom(String from) {
        String result = "Hello, world, from " + from;
        log.debug("Debug");
        log.info("Info");
        System.out.println(result);
        return result;
    }

    @WebMethod
    public String sayHowAreYouFrom(String from) {
        String result = "Hello, HowAreYou " + from;
        log.debug("Debug");
        log.info("Info");
        System.out.println(result);
        return result;
    }

    public static void main(String[] argv) {
        Object implementor = new HelloWorld();
        String address = "http://localhost:9000/HelloWorld";
        Endpoint.publish(address, implementor);
    }
}
