package cidemo;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
    private final String mySecret;

    public HomeController(@Value("${my.secret}") String mySecret) {

        this.mySecret = mySecret;
    }

    @GetMapping
    public Msg spillTheBeans() throws UnknownHostException { // don't try this at home

        String hostname = InetAddress.getLocalHost().getHostName();
        return new Msg(mySecret + ", from: " + hostname);
    }

    public static class Msg {

        public final String msg;

        public Msg(String msg) {
            this.msg = msg;
        }
    }
}
