import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Setup {
AndroidDriver androidDriver;
@BeforeTest
    public AndroidDriver setup() throws MalformedURLException {

        DesiredCapabilities dcaps = new DesiredCapabilities();
        dcaps.setCapability("platforName", "android");
        dcaps.setCapability("platforVersion", "13");
        dcaps.setCapability("automationName", "UIautomator2");
        dcaps.setCapability("appPackage", "com.google.android.calculator");
        dcaps.setCapability("appActivity", "com.android.calculator2.Calculator");
        dcaps.setCapability("app", "C:\\Users\\emonr\\Downloads\\calculator.apk");

        URL url = new URL("http://127.0.0.1:4723");

        androidDriver = new AndroidDriver(url, dcaps);
        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return androidDriver;
    }
}
