package cidemo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {


    private final NamedParameterJdbcTemplate jdbcTemplate;

    public HomeController(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public Msg spillTheBeans() throws UnknownHostException { // don't try this at home

        String hostname = InetAddress.getLocalHost().getHostName();
        return new Msg(String.format("Hello from %s, running on: %s", this.getFromDb(), hostname));
    }

    private String getFromDb() {
        return this.jdbcTemplate.queryForObject("SELECT first_name AS fn, lastt_name AS ln FROM person WHERE first_name='Dave'",
                Collections.emptyMap(),
                (rs, rowNum) -> rs.getString("fn") + " " + rs.getString("ln"));
    }

    public static class Msg {

        public final String msg;

        public Msg(String msg) {
            this.msg = msg;
        }
    }
}
