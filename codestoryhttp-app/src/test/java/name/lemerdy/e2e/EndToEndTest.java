package name.lemerdy.e2e;

import name.lemerdy.e2e.tests.PhantomJsTest;
import name.lemerdy.e2e.tests.ServerRule;
import org.junit.*;

import java.util.regex.Pattern;

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

    @Test
    public void should_create_a_new_employee() {
        browser.goTo("/employees/new")
                .fill("#employee_name").with("Lol")
                .fill("#employee_email").with("fake@provider.com")
                .click("input[type=\"submit\"]")
                .await().untilPage().isLoaded();

        Pattern showEmployeeUriRegex = Pattern.compile("^/employees/[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89ab][a-f0-9]{3}-[a-f0-9]{12}$");
        assertThat(browser.url()).matches(showEmployeeUriRegex);
        assertThat(browser.find("#notice").getText()).isEqualTo("Employee was successfully created.");
        assertThat(browser.find("p").getTexts()).containsSequence(
                "Name: Lol",
                "Email: fake@provider.com"
        );
    }
}