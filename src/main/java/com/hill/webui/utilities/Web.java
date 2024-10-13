package com.hill.webui.utilities;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$$x;

public class Web {
    private static final Logger log = LoggerFactory.getLogger(Web.class);

    public static WebDriver driver() {
        return WebDriverRunner.getWebDriver();
//        return webdriver().driver().getWebDriver();
    }

    public static JavascriptExecutor jse() {
        return (JavascriptExecutor) driver();
    }

    public static Actions acts() {
        return new Actions(driver());
    }

    public static class Wait {
        public static void pageLoading() {
            log.info("pageLoading");
            WebDriverWait wait = new WebDriverWait(driver(), Duration.ofSeconds(Timeouts.PAGE_LOADING));
            wait.until(driver -> Is.pageLoaded());
            log.info("pageLoading OK");
        }

        public static void until(String message, Callable<Boolean> c, int timeout) {
            List<Class<? extends Exception>> ignoring = Arrays.asList(
                    StaleElementReferenceException.class,
                    ElementClickInterceptedException.class,
                    ElementNotInteractableException.class);

            getSettableWait(message, timeout, ignoring).until(driver -> {
                try {
                    return c.call();
                } catch (Exception ignored) {
                    return false;
                }
            });
        }

        private static FluentWait<WebDriver> getSettableWait(String msg, int timeout, Collection<Class<? extends Exception>> ignoring) {
            return new WebDriverWait(driver(), Duration.ofSeconds(timeout)).withMessage(msg).ignoreAll(ignoring);
        }

        public static void forSeconds(int seconds) {
            forMillis(seconds * 1000);
        }

        public static void forMillis(int millis) {
            log.info(String.format("fixed pause for %d millis", millis));
            try {
                TimeUnit.MILLISECONDS.sleep(millis);
            } catch (InterruptedException ignored) {
            }
        }

        public static boolean notStrict(String message, Callable<Boolean> c, int timeout) {
            try {
                until(message, c, timeout);
                return true;
            } catch (Exception e) {
                log.info(message);
                return false;
            }
        }
    }

    public static class Is {

        public static boolean hasPseudoElement(SelenideElement element, String pseudoElement) {
            String script = "return window.getComputedStyle(arguments[0], arguments[1]).getPropertyValue('content');";
            String content = (String) jse().executeScript(script, element, pseudoElement);
            return content != null && !content.equals("none") && !content.isEmpty();
        }

        public static boolean pageLoaded() {
            boolean res = true;
            String[] paths = new String[]{"//div[@class='-loading-inner']", "//div[@class='loader']",
                    "//*[text()='Loading...']", "//*[text()='Загрузка...']", "//*[text()='Processing...']",
                    "//div[contains(@class, 'ProgressIndicator')]"};
            for (String s : paths) {
                res &= $$x(s).stream().noneMatch(WebElement::isDisplayed);
            }
            return jse().executeScript("return document.readyState").equals("complete") && res;
        }
    }

    public static class Click {

        public static void blind(SelenideElement e, int offsetY, int offsetX) {
            blind(e, offsetY, offsetX, false);
        }

        public static void blind(SelenideElement e, int offsetY, int offsetX, boolean isDebug) {
            log.info("Click.blind");
            Actions act = acts().moveToElement(e).moveByOffset(offsetX, offsetY);
            if (isDebug) {
                act.contextClick().build().perform();
            } else {
                act.click().build().perform();
            }
        }
    }

    public static class Get {
        public static String currentUrl() {
            return driver().getCurrentUrl();
        }
    }

    public static int randomIndex(Collection l) {
        return random(l.size());
    }

    public static int random(int max) {
        return (int) (Math.random() * max);
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] addScreenshot() {
        return Selenide.screenshot(OutputType.BYTES);
    }

    @Attachment(value = "{type}", type = "text/plain", fileExtension = ".txt")
    public static byte[] addText(String type, String content) {
        return content.getBytes(StandardCharsets.UTF_8);
    }

}
