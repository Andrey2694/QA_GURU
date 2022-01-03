package restApi;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static restApi.filters.CustomLogFilter.customLogFilter;

public class DemoWebShopWithAllure {
    String cartValue = null;

    @BeforeAll
    static void configureBaseUrl() {
        RestAssured.baseURI = "http://demowebshop.tricentis.com";
        Configuration.baseUrl = "http://demowebshop.tricentis.com";
//        RestAssured.filters(customLogFilter().withCustomTemplates());

        //Подключаем Selenoid
        Configuration.browserSize = "1920x1080";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    Integer getItemsCountFromCart(String value) {
        if (value != null) {
            return Integer.parseInt(value.substring(1, value.length() - 1));
        }
        return 0;
    }

    @Test
    void addToCartTest() {
        step("Add item to cart as new customer", () -> {
            given()
                    .filter(customLogFilter().withCustomTemplates())
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .body("product_attribute_16_5_4=14&product_attribute_16_6_5=15&" +
                            "product_attribute_16_3_6=19&product_attribute_16_4_7=44&" +
                            "product_attribute_16_8_8=22&addtocart_16.EnteredQuantity=1")
                    .log().uri()
                    .log().body()
                    .when()
                    .post("/addproducttocart/details/16/1")
                    .then()
                    .log().body()
                    .statusCode(200)
                    .body("success", is(true))
                    .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                    .body("updatetopcartsectionhtml", is("(1)"));
        });
    }

    @Test
    void addToCartWithCookieTest() {
        step("open website for adding cookie", () -> {
            open("");
        });

        step("add customer's cookie to website", () -> {
            getWebDriver().manage().addCookie(new Cookie("Nop.customer", "19394418-ab33-4949-b1e7-e2f30bd8cd03"));
        });

        step("check how many items in cart", () -> {
            open("/cart");
            cartValue = $x("//span[@class = 'cart-qty']").getText();
        });

        step("add item to cart with cookie", () -> {
            Response response =
                    given()
                            .filter(customLogFilter().withCustomTemplates())
                            .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                            .body("product_attribute_72_5_18=53&" +
                                    "product_attribute_72_6_19=54&" +
                                    "product_attribute_72_3_20=57&" +
                                    "addtocart_72.EnteredQuantity=1")
                            .cookie("Nop.customer=19394418-ab33-4949-b1e7-e2f30bd8cd03;")
                            .log().uri()
                            .log().cookies()
                            .log().body()
                            .when()
                            .post("/addproducttocart/details/72/1")
                            .then()
                            .log().body()
                            .statusCode(200)
                            .body("success", is(true))
                            .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                            .extract().response();

            step("check that item was added", () -> {
                response
                        .then()
                        .body("updatetopcartsectionhtml", is("(" + (getItemsCountFromCart(cartValue) + 1) + ")"))
                ;
            });
        });
    }

    @Test
    void getSubscribe() {
        step("get subscribe with cookie", () -> {
            given()
                    .filter(customLogFilter().withCustomTemplates())
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .body("email=222%40dsd.com")
                    .cookie("Nop.customer=19394418-ab33-4949-b1e7-e2f30bd8cd03;")
                    .log().cookies()
                    .log().uri()
                    .log().body()
                    .when()
                    .post("/subscribenewsletter")
                    .then()
                    .log().body()
                    .statusCode(200)
                    .body("Success", is(true))
                    .body("Result", is("Thank you for signing up! A verification email has been sent. We appreciate your interest."));
        });
    }
}