package org.automation.pageobjects;

import io.restassured.RestAssured;

public class BookCartPage {

    public static void setBookCartPage() {
        RestAssured.baseURI = "https://bookcart.azurewebsites.net";
    }
}
