package com.oleske;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantControllerView.class)
public class RestaurantControllerViewTest {
    @Autowired
    private WebDriver webDriver;

    @Test
    public void getHome_hasHeader() throws Exception {
        webDriver.get("/");
        WebElement header = webDriver.findElement(By.tagName("h1"));
        assertThat(header.getText()).isEqualTo("Restaurant List");
    }
}