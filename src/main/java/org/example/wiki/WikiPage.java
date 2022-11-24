package org.example.wiki;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WikiPage {

    public WebDriver driver;
    public WikiPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private WebElement waitElement(By by, String error_msg, long timeout){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.withMessage(error_msg);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void clickFindWiki(){
        WebElement element = waitElement(By.xpath("//*[@resource-id='org.wikipedia:id/search_container']"), "Не найдено элемента поиска wiki", 10);
        element.click();
    }

    public void inputFindField(String text){
        WebElement element = waitElement(By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"), "Не найдено поле поиска wiki", 10);
        element.sendKeys(text);
    }

    public void clickFindItem(String text){
        WebElement element = waitElement(By.xpath(String.format("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='%s']", text)), "Не найден искомый элемент", 10);
        element.click();
    }

    public void checkTitle(String text){
        WebElement element = waitElement(By.id("org.wikipedia:id/view_page_title_text"), "Не найдено название статьи", 10);
        Assert.assertEquals("Несовпадение в названии статьи", text, element.getText());
    }

    public void clickDots(){
        WebElement element = waitElement(By.xpath("//android.widget.ImageView[@content-desc=\"Ещё\"]"), "Не найден компонент 'Ещё'", 10);
        element.click();
    }

    public void clickAddRead(){
        WebElement element = waitElement(By.xpath("//*[@resource-id='org.wikipedia:id/title' and @text='Добавить в список для чтения']"), "Не найден пункт 'Добавить в список для чтения'", 10);
        element.click();
    }

    public void clickAnswer(){
        WebElement element = waitElement(By.xpath("//*[@resource-id='org.wikipedia:id/onboarding_button' and @text='ПОНЯТНО']"), "Не найдена кнопка 'Понятно'", 10);
        element.click();
    }

    public void addFieldInput(){
        WebElement element = waitElement(By.id("org.wikipedia:id/text_input"), "Не найдено поле ввода", 10);
        element.clear();
        element.sendKeys("Хоббит");
    }

    public void clickOk(){
        WebElement element = waitElement(By.id("android:id/button1"), "Не найдена кнопка", 10);
        element.click();
    }

    public void clickSnack(){
        WebElement element = waitElement(By.id("org.wikipedia:id/snackbar_action"), "Не найдена кнопка просмотра списка", 10);
        element.click();
    }

    public void clickDotsSnack(){
        WebElement element = waitElement(By.id("org.wikipedia:id/item_overflow_menu"), "Не найдена кнопки дополнительно", 10);
        element.click();
    }

    public void clickDeleteRead(){
        WebElement element = waitElement(By.xpath("//*[@resource-id='org.wikipedia:id/title' and @text='Удалить список']"), "Не найдена элемента 'Удалить список'", 10);
        element.click();
    }


    public void checkDeleteRead(){
        waitElement(By.id("org.wikipedia:id/single_fragment_toolbar"), "Не найдена элемента 'Мои списки'", 10);
        Assert.assertTrue(driver.findElements(By.id("org.wikipedia:id/item_title' and @text='Хоббит']")).isEmpty());
    }
}
