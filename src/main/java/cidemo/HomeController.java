package cidemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    public HomeController(@Value("${my.secret}") String mySecret) {

        this.mySecret = mySecret;
    }

    private final String mySecret;

    @GetMapping
    public Msg spillTheBeans(){ // don't try this at home
        return new Msg(mySecret);
    }

    public static class Msg {

        public Msg(String oops) {
            this.oops = oops;
        }

        public final String oops;
    }
}
