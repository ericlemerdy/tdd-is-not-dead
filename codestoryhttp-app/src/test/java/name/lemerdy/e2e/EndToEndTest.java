package name.lemerdy.e2e;

import name.lemerdy.e2e.tests.PhantomJsTest;
import name.lemerdy.e2e.tests.ServerRule;
import org.junit.*;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

public class EndToEndTest {
    private PhantomJsTest phantomJsTest;

    @Rule
    public ServerRule serverRule = new ServerRule();

    @Before
    public void setUp() {
        phantomJsTest = new PhantomJsTest(format("http://localhost:%d", serverRule.port()));
        phantomJsTest.starting();
    }

    @After
    public void tearDown() {
        phantomJsTest.getDriver().close();
    }

    @Test
    public void should_show_home_page() {
        phantomJsTest.goTo("/employees");
        
        assertThat(phantomJsTest.title()).isEqualTo("Employees");
    }
}