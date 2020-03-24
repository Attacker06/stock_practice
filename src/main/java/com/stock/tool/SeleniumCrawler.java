package com.stock.tool;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SeleniumCrawler {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\TGFC062\\AppData\\Local\\Programs\\Python\\Python38-32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String baseUrl = "https://www.youtube.com/?gl=TW&hl=zh-TW";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl);
        ((ChromeDriver) driver).findElementByName("search_query").sendKeys("Gaben");
        driver.findElement(By.xpath("//*[@id='search-icon-legacy']")).click();
        String pageSource = driver.getPageSource();
        System.out.println();
    }
}
