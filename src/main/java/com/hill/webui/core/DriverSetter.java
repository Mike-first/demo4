package com.hill.webui.core;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class DriverSetter {
    public static void set() {

        WebDriverManager.chromedriver()
                .driverVersion(PropertyReader.get("browser.version"))
                .setup();

        Map<String, Object> preferences = new HashMap<>();
        preferences.put("profile.default_content_setting_values.notifications", 2);
        preferences.put("profile.default_content_settings.popups", 0);
        preferences.put("download.prompt_for_download", "false");
        preferences.put("download.default_directory", PropertyReader.get("download.path"));
        preferences.put("directory_upgrade", true);
        preferences.put("safebrowsing.enabled", "false");

        ChromeOptions chromeOptions = new ChromeOptions()
                .setExperimentalOption("prefs", preferences)
                .addArguments("--start-maximized")
                .addArguments("--incognito")
                .addArguments("--enable-strict-powerful-feature-restrictions")
                .addArguments("--disable-geolocation")
                .addArguments("--deny-permission-prompts");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        Configuration.browserCapabilities = capabilities;
        Configuration.browser = "chrome";
//        Configuration.browserSize = PropertyReader.get("screen.resolution");

        Configuration.headless = false;
        Configuration.timeout = 1000 * 40L;
    }
}
