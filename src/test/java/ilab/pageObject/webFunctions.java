package ilab.pageObject;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import ilab.reports.reporting;
import ilab.webUtilities.webactions;
import ilab.webpageObject.apply;
import ilab.webpageObject.country;
import ilab.webpageObject.internOpenings;
import ilab.webpageObject.landingPage;
import org.openqa.selenium.WebDriver;

import java.sql.ResultSet;

public class webFunctions extends webactions {
    reporting reporting2 = new reporting();
    String fileName;

    public void landingPage(WebDriver driver, ExtentTest test) {
        landingPage landingpage2 = new landingPage(driver);
        country country2 = new country(driver);
        try {
            fileName = reporting2.CaptureScreenShot(driver);
            clickObject(landingpage2.careerLink, driver);
            if (country2.countryLink.isDisplayed()) {
                test.pass(" SUCCESSFULLY CLICKED CAREER LINK", MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());
            } else {
                test.fail("UNABLE TO CLICK CAREER LINK",MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());
            }
        } catch (Exception e) {
        }


    }

    public void country(WebDriver driver,ExtentTest test) {
        country country2 = new country(driver);
        internOpenings opening2 = new internOpenings(driver);
        try {
            fileName = reporting2.CaptureScreenShot(driver);
            clickObject(country2.countryLink, driver);
            if (opening2.internOpeningLink.isDisplayed()) {
                test.pass(" SUCCESSFULLY CLICKED COUNTRY LINK",MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());
            } else {
                test.fail("UNABLE TO CLICK COUNTRY LINK",MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());
            }
        } catch (Exception e) {
        }


    }

    public void internOpening(WebDriver driver,ExtentTest test) {
        internOpenings opening2 = new internOpenings(driver);
        apply apply2 = new apply(driver);
        try {
            fileName = reporting2.CaptureScreenShot(driver);
            clickObject(opening2.internOpeningLink, driver);
            if (apply2.apply.isDisplayed()) {
                test.pass(" SUCCESSFULLY CLICKED Intern Link ",MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());
            } else {
                test.fail("UNABLE TO CLICK Intern Link ",MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());
            }
        } catch (Exception e) {
        }
    }
    public void apply(WebDriver driver, ExtentTest test, String FIRSTNAME,String EMAIL,String PHONENO) {
        apply apply2 = new apply(driver);
        try {
            fileName = reporting2.CaptureScreenShot(driver);
            clickObject(apply2.apply, driver);
            passData(apply2.Firstname, driver, FIRSTNAME);
            passData(apply2.email, driver, EMAIL);
            passData(apply2.phoneNumber, driver,PHONENO);
            clickObject(apply2.sendApplication, driver);
            if (apply2.errormsg.isDisplayed()) {
                test.pass(" UNSUCCESSFUL ",MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());
            } else {
                test.fail("APPLICATION SUBMISSION SUCCESSFUL ",MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());
            }
        } catch (Exception e) {
        }
    }
}