package cidemo;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;

import java.net.UnknownHostException;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import cidemo.HomeController.Msg;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {
    private static final String MSG = "foo";

    @Mock
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @InjectMocks
    private HomeController subject;

    @Test
    public void obligatory() throws UnknownHostException {

        Mockito.when(namedParameterJdbcTemplate.queryForObject(
                any(String.class), any(Map.class), any(RowMapper.class))).thenReturn(MSG + " bar");

        final Msg msg = subject.spillTheBeans();
        assertThat(msg.msg, containsString(MSG));
    }
}