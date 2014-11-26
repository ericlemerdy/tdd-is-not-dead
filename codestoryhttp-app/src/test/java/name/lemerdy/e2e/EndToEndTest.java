package name.lemerdy.e2e;

import name.lemerdy.e2e.tests.PhantomJsTest;
import name.lemerdy.e2e.tests.ServerRule;
import org.junit.*;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

public class EndToEndTest {
    private PhantomJsTest browser;

    @Rule
    public ServerRule serverRule = new ServerRule();

    @Before
    public void setUp() {
        browser = new PhantomJsTest(format("http://localhost:%d", serverRule.port()));
        browser.starting();
    }

    @After
    public void tearDown() {
        browser.getDriver().close();
    }

    @Test
    public void should_show_home_page() {
        browser.goTo("/employees");
        
        assertThat(browser.title()).isEqualTo("Employees");
    }
}