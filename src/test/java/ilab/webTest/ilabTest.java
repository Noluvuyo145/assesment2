package ilab.webTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.mysql.cj.protocol.Resultset;
import ilab.database.dataBaseConnection;
import ilab.generatePhone.phoneNumber;
import ilab.pageObject.webFunctions;
import ilab.reports.reporting;
import ilab.webUtilities.webUtilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ilabTest {
    webUtilities webUtilities2 = new webUtilities();
    phoneNumber phoneNumber2 = new phoneNumber();
    webFunctions webFunctions2 = new webFunctions();
    String sURL, sBrowser;
    reporting reporting2 = new reporting();
    ExtentReports ExtentReports2;
    dataBaseConnection dataBaseConnection2 = new dataBaseConnection();
    @Parameters({"ILABURL","Browser"})
@BeforeTest
    public void initialiseBrowser(String suRL, String browser) {
        sURL = suRL;
        sBrowser = browser;
        webUtilities2.setWebDriver(webUtilities2.initializeWebDriver(sBrowser));
        ExtentReports2 = reporting2.initializeExtentReports("reports/ilabReports.html");
    }
@Test
    public void ilab() throws InterruptedException {
    ExtentTest test = ExtentReports2.createTest("ilabReports");
    test.assignAuthor("NOLUVUYO");
    ExtentTest Node;
    ResultSet RS;

        try {
            RS = dataBaseConnection2.ConnectAndQuerySQL("jdbc:mysql://localhost:3306/ilab",
                    "root", "noluvuyo@2019", "Select * from ilabData");
            int iRow = dataBaseConnection2.rowCount(RS);
            webUtilities2.navigate(sURL);
            for (int i = 1; i <= iRow; i++) {
                if (RS.next()) {
                    webUtilities2.navigate(sURL);
                    webFunctions2.landingPage(webUtilities2.getWebDriver(), test);
                    webFunctions2.country(webUtilities2.getWebDriver(), test);
                    webFunctions2.internOpening(webUtilities2.getWebDriver(), test);
                    webFunctions2.apply(webUtilities2.getWebDriver(), test ,RS.getString("FIRSTNAME"),
                            RS.getString("EMAIL"),phoneNumber2.randomGenerator() );
                }
            }
            RS.close();
        }
            catch(Exception e){
        }
    }
    @AfterTest
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        webUtilities.getWebDriver().quit();
        ExtentReports2.flush();
    }
    }