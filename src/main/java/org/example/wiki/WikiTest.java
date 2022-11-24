package org.example.wiki;


import io.appium.java_client.AppiumDriver;
import org.example.AppiumProperties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class WikiTest {
    public static AppiumDriver driver;
    public static WikiPage wikiPage;


    @Before
    public void start() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", AppiumProperties.getProperty("platformName"));
        capabilities.setCapability("appium:deviceName", AppiumProperties.getProperty("deviceName"));
        capabilities.setCapability("appium:platformVersion", AppiumProperties.getProperty("platformVersion"));
        capabilities.setCapability("appium:appPackage", AppiumProperties.getProperty("appPackage"));
        capabilities.setCapability("appium:appActivity", AppiumProperties.getProperty("appActivity"));
        capabilities.setCapability("app", AppiumProperties.getProperty("app"));

        driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        wikiPage = new WikiPage(driver);
    }

    @After
    public void down(){
        driver.quit();
    }

    @Test
    public void testSearch(){
        wikiPage.clickFindWiki();
        wikiPage.inputFindField("Кемеровский государственный университет");
        wikiPage.clickFindItem("Высшее учебное заведение в Кемерове");
        wikiPage.checkTitle("Кемеровский государственный университет");
//        wikiPage.checkTitle("Хоббит");
    }

    @Test
    public void HobbitSearch(){
        wikiPage.clickFindWiki();
        wikiPage.inputFindField("Хоббит, или Туда и обратно");
        wikiPage.clickFindItem("Повесть английского писателя Джона Р. Р. Толкина");
        wikiPage.checkTitle("Хоббит, или Туда и обратно");
        wikiPage.clickDots();
        wikiPage.clickAddRead();
        wikiPage.clickAnswer();
        wikiPage.addFieldInput();
        wikiPage.clickOk();
        wikiPage.clickSnack();
        wikiPage.clickDotsSnack();
        wikiPage.clickDeleteRead();
        wikiPage.checkDeleteRead();

    }


}
