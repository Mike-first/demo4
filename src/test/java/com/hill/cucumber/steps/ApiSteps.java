package com.hill.cucumber.steps;

import com.hill.api.MockOkhttpReq;
import com.hill.webui.utilities.StringUtils;
import com.hill.webui.utilities.pojo.CityPojo;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class ApiSteps {
    @Then("i send req and check resp")
    @Step("i send request and check response {table}")
    public void iSendReqAndCheckResp(DataTable table) {
        SoftAssert sa = new SoftAssert();
        int[] i=new int[]{0};
        List<CityPojo> sets = table.asList(CityPojo.class);
        sets.forEach(cityExpected -> {
            System.out.printf("parameter set #%d. \nexpected: \n", ++i[0]);
            System.out.println("code: " + cityExpected.getCode());
            System.out.println("city: " + cityExpected.getCity());
            System.out.println("guid: " + cityExpected.getGuid());

            //mock
            String bodyActual = MockOkhttpReq.cityReq(cityExpected.getCode());
            CityPojo cityActual = StringUtils.parseJson(bodyActual, CityPojo.class);
            String code = "200";

            //real curl
//            String url = UrlBuilder.start()
//                    .withUrl("https://www.invitro.ru/local/ajax/current-city.php")
//                    .withParameter("CODE", cityExpected.getCode())
//                    .build();
//
//            String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36";
//            String cookiesString = String.join(" ", FileUtils.readText(Paths.get("src/main/resources/tmp/cookie2.txt").toAbsolutePath()));
//
//            CurlBuilder curlBuilder = CurlBuilder.start()
//                    .withOptions("-v")
//                    .withMethod("GET")
//                    .withUrl(url)
//                    .withUserAgent(userAgent)
//                    .withCookies(cookiesString);
//
//            List<String> response = CommandR.runCurl(curlBuilder);
//            String code = CurlParser.getCurlOutputCode(response);
//            String bodyActual = CurlParser.getCurlOutputBody(response);
//            CityPojo cityActual = StringUtils.parseJson(bodyActual, CityPojo.class);
            //real curl

            System.out.println("\nactual:");
            System.out.println("resp code: " + code);
            System.out.println("code: " + cityActual.getCode());
            System.out.println("city: " + cityActual.getCity());
            System.out.println("guid: " + cityActual.getGuid() + "\n==============\n");

            sa.assertEquals(cityActual.getCity(), cityExpected.getCity(), "bad city for code: " + cityExpected.getCode());
            sa.assertEquals(cityActual.getGuid(), cityExpected.getGuid(), "bad guid for code: " + cityExpected.getCode());

        });
        sa.assertAll();
    }
}
