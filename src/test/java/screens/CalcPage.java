package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CalcPage {
@FindBy(id = "")
AndroidDriver androidDriver;

public CalcPage(AndroidDriver androidDriver){
    this.androidDriver = androidDriver;
    PageFactory.initElements(new AppiumFieldDecorator(androidDriver),this);
}

public String doSeries(String series) {
    if (!series.isEmpty()) {
        char currentChar = series.charAt(0);

        if (Character.isDigit(currentChar)) {
            androidDriver.findElement(By.id("com.google.android.calculator:id/digit_" + currentChar)).click();
        } else {
            switch (currentChar) {
                case '+':
                    androidDriver.findElement(By.id("com.google.android.calculator:id/op_add")).click();
                    break;
                case '-':
                    androidDriver.findElement(By.id("com.google.android.calculator:id/op_sub")).click();
                    break;


                case '/':
                    androidDriver.findElement(By.id("com.google.android.calculator:id/op_div")).click();
                    break;

                case '*':
                    androidDriver.findElement(By.id("com.google.android.calculator:id/op_mul")).click();
                    break;


                default:
                    throw new IllegalArgumentException("Invalid operator: " + currentChar);

            }
        }
    }
    if (series.length() > 0) {

        doSeries(series.substring(1));
    } else {
        androidDriver.findElement(By.id("com.google.android.calculator:id/eq")).click();
    }
    return androidDriver.findElement(By.id("com.google.android.calculator:id/result_final")).getText();

}


}

