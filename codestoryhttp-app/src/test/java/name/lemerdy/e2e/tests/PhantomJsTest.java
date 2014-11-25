package name.lemerdy.e2e.tests;

import org.fluentlenium.core.Fluent;
import org.fluentlenium.core.FluentAdapter;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static java.lang.Runtime.getRuntime;
import static org.openqa.selenium.phantomjs.PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY;

public class PhantomJsTest extends FluentAdapter {
    private static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(1024, 768);
    private final String defaultUrl;
    private WebDriver driver;

    public PhantomJsTest(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }

    public Fluent goTo(String url) {
        withDefaultUrl(defaultUrl);
        return super.goTo(url);
    }

    public void starting() {
        if (null == driver) {
            getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    if (driver != null) {
                        driver.quit();
                    }
                }
            });

            driver = createDriver();
        }

        driver.manage().deleteAllCookies();
        driver.manage().window().setSize(DEFAULT_WINDOW_SIZE);
        initFluent(driver);
    }

    private WebDriver createDriver() {
        File phantomJsExe = new PhantomJsDownloader().downloadAndExtract();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomJsExe.getAbsolutePath());

        PhantomJSDriverService service = PhantomJSDriverService.createDefaultService(capabilities);

        return new PhantomJSDriver(service, capabilities);
    }
}
