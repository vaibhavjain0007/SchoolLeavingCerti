package org.example.tests;

import org.example.pages.SchoolLeavingCerti;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.time.Duration;
import java.util.List;


public class SchoolLeavingCertiTest {

    private WebDriver driver;
    private SchoolLeavingCerti schoolLeavingCerti;

    private static final String URL = "https://teach-with-fun.com"; // dummy url
    private static final String USERNAME = "Admin";
    private static final String PASSWORD = "admin123";
    private static final String CERTIFICATE_NAME = "School Leaving Certificate";
    private static final String STUDENT_NAME = "Nilesh Dubey";
    private static final String REMARKS = "Always been a disciplined and studious student";

    @BeforeClass()
    public void setUp() {
        // Set path to chrome driver
        System.setProperty("webdriver.chrome.driver",
                "/Users/vaibhav.jain12/Downloads/chromedriver-mac-arm64/chromedriver");
    }

    @BeforeMethod()
    public void init() {
        // Set up the WebDriver instance
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().to(URL);
        schoolLeavingCerti = new SchoolLeavingCerti(driver);
    }

    @Test()
    public void testLogin() {
        schoolLeavingCerti.login(USERNAME, PASSWORD);
        Assert.assertEquals(schoolLeavingCerti.getPageTitle(), "DemoPage", "Page title should match");
    }

    @Test(dependsOnMethods = {"testLogin"})
    public void testNavigationAndSelectionOfCertificate() {
        schoolLeavingCerti.navigateToCertificates();
        schoolLeavingCerti.selectCertificate("School Leaving Certificate");
    }

    @Test(dependsOnMethods = {"testNavigationAndSelectionOfCertificate"})
    public void testSearchAndSelectStudent() {
        schoolLeavingCerti.setSearchStudent(STUDENT_NAME);
        schoolLeavingCerti.selectCertificate(CERTIFICATE_NAME);
    }

    @Test(dependsOnMethods = {"testSearchAndSelectStudent"})
    public void testRemarksUpdateAndGenerateCerti() {
        schoolLeavingCerti.setRemarks(REMARKS);
        schoolLeavingCerti.generateCerti();
        schoolLeavingCerti.isDownloadBtnDisplayed();
    }

    @Test(dependsOnMethods = {"testRemarksUpdateAndGenerateCerti"})
    public void validateCertiHistory() {
        schoolLeavingCerti.checkHistory();
        List<String> actualHistory = schoolLeavingCerti.getCertiHistory();
        Assert.assertTrue(actualHistory.contains(CERTIFICATE_NAME),
                "History should include " + CERTIFICATE_NAME);
    }

    @AfterMethod()
    public void tearDown() {
        // close all the instances of browser
        driver.quit();
    }
}
