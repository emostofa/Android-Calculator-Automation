package testRunner;

import config.Setup;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.CalcPage;
import utills.Utills;

public class CalcTestRunner extends Setup {
    @Test(priority = 1, description = "Do calculation of the given series")
    public void doSumOfTheSeries(){
        CalcPage calcPage = new CalcPage(androidDriver);
        Utills utills = new Utills();

        String series = "100/10*5-10+60";

        String result = calcPage.doSeries(series);
        String expectedResult = utills.calculateSeries(series);
        Assert.assertEquals(result,expectedResult);

    }
}
