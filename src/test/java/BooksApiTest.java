import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.automation.pageobjects.BookCartPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BooksApiTest {

    @BeforeAll
    public static void setUp() {
        BookCartPage.setBookCartPage();
    }

    @Test
    public void verifyGetBookById() {
        Response response =
                given()
                        .accept(ContentType.ANY)
                        .when()
                        .get("/api/Book/2")
                        .then()
                        .extract()
                        .response();
        assertTrue(response.getBody().asPrettyString().contains("bookId\": 2"));
        assertEquals(200, response.statusCode());
    }

    @Test
    public void verifyGetCategoriesList() {
        RequestSpecification request = RestAssured.given();
        request.log().all();
        request.accept(ContentType.JSON);
        Response response = request.get("/api/Book/GetCategoriesList");
        assertEquals(200, response.getStatusCode());
        assertTrue(response.getBody().asPrettyString().contains("Mystery"));
    }

    @Test
    public void verifyGetSimilarBooks() {
        RequestSpecification request = RestAssured.given();
        request.log().all();
        request.header("accept", "application/json");
        Response response = request.get("/api/Book/GetSimilarBooks/2");
//        List<Object> listOfbooks = response.jsonPath().getList("$"); // salveaza raspunsul din json in lista de obiecte

        List<String> listOfBooks = response.jsonPath().get("category"); // salveaza raspunsul din json in lista de strings
        for (String book : listOfBooks) {
            assertEquals("Mystery", book);
        }
        assertEquals(200, response.getStatusCode());
        assertEquals("application/json; charset=utf-8", response.getHeader("content-type"));
    }
}
