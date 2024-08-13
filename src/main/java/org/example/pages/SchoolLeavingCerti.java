package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class SchoolLeavingCerti {

    private WebDriver driver;

    private static final Duration SHORTWAIT = Duration.ofSeconds(20);

    @FindBy(name = "username")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(xpath = "//button[text()=' Login ']")
    private WebElement loginBtn;

    @FindBy(xpath = "//button[text()=' Logout ']")
    private WebElement logoutBtn;

    @FindBy(partialLinkText = "certificates")
    private WebElement certificates;

    @FindBy(id = "certificates")
    private WebElement certificateTypes;

    @FindBy(id = "searchStudent")
    private WebElement searchStudent;

    @FindBy(id = "remarks")
    private WebElement updateRemarks;

    @FindBy(xpath = "//button[text()=' Generate ']")
    private WebElement generateCertiBtn;

    @FindBy(xpath = "//button[text()=' Download ']")
    private WebElement downloadBtn;

    @FindBy(partialLinkText = "history")
    private WebElement history;

    @FindBy(className = "certi-history")
    private List<WebElement> certiHistory;


    public SchoolLeavingCerti(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String usrname) {
        username.sendKeys(usrname);
    }

    public void enterPassword(String passwd) {
        password.sendKeys(passwd);
    }

    public boolean login(String usrname, String passwd) {
        waitUntilElementIsDisplayed(driver, username, SHORTWAIT);
        enterUsername(usrname);
        enterPassword(passwd);
        loginBtn.click();
        waitUntilElementIsDisplayed(driver, loginBtn, SHORTWAIT);
        return logoutBtn.isDisplayed();
    }

    public void logout() {
        logoutBtn.click();
    }

    public void navigateToCertificates() {
        waitUntilElementIsDisplayed(driver, certificates, SHORTWAIT);
        certificates.click();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public static void waitUntilElementIsDisplayed(WebDriver driver, WebElement element, Duration seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void selectCertificate(String certificate) {
        Select selectCerti = new Select(certificateTypes);
        selectCerti.selectByValue(certificate);
    }

    public void setSearchStudent(String name) {
        searchStudent.sendKeys(name);
    }

    public void setRemarks(String remarks) {
        updateRemarks.sendKeys(remarks);
    }

    public boolean isDownloadBtnDisplayed() {
        return downloadBtn.isDisplayed();
    }

    public void generateCerti() {
        generateCertiBtn.click();
    }

    public void checkHistory() {
        history.click();
    }

    public List<String> getCertiHistory() {
        return certiHistory.stream().map(WebElement::getText).toList();
    }
}
