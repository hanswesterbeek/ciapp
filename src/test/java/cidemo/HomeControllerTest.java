package cidemo;

import static org.junit.Assert.assertThat;

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
  public void spillTheBeans() {

    final Msg msg = subject.spillTheBeans();
    assertThat(msg.oops, Matchers.equalTo(MSG));
  }
}
