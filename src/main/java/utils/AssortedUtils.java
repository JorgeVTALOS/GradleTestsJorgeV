package utils;

import org.apache.tools.ant.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;

public class AssortedUtils {

    public static String captureScreenshot(WebDriver driver, String capturedscreenshotName)
    {
        TakesScreenshot ts = (TakesScreenshot)driver;

        File source = ts.getScreenshotAs(OutputType.FILE);

        String path = System.getProperty("user.dir")+"/Screenshots/"+capturedscreenshotName+".png";

        File destination = new File(path);

        System.out.println("Screenshot taken");

        try {

            FileUtils.getFileUtils().copyFile(source, destination);

        } catch (IOException e) {

            System.out.println("Exception while taking Screeshot is : "+ e.getMessage());
        }

        return path;
    }

}
