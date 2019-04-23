package cidemo;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.*;

import java.net.UnknownHostException;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import cidemo.HomeController.Msg;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {
    private static final String MSG = "foo";

    private HomeController subject = new HomeController(MSG);

    @Test
    public void spillTheBeans() throws UnknownHostException {

        final Msg msg = subject.spillTheBeans();
        assertThat(msg.msg, startsWith(MSG));
    }
}