import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.automation.dto.BookDTO;
import org.automation.dto.BookOrderDTO;
import org.automation.pageobjects.BookCartPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

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

    @ParameterizedTest
    @ValueSource(ints = 4)
    public void addBookToShoppingCart(int bookId) {
        RequestSpecification request = RestAssured.given();
        request.log().all();
        request.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoibWlyY2VhLmFsZXgyMDI0Iiwic3ViIjoiVXNlciIsImp0aSI6Ijk4OWNlZGU4LWExNWItNDI3ZS04Mzg1LWVmMDg3OTRlNjI4YSIsImh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9jbGFpbXMvcm9sZSI6IlVzZXIiLCJ1c2VySWQiOiI1Njk5IiwiZXhwIjoxNzQwNTA0MzIzLCJpc3MiOiJodHRwczovL2xvY2FsaG9zdDo0NDM2NC8iLCJhdWQiOiJodHRwczovL2xvY2FsaG9zdDo0NDM2NC8ifQ.DJntt4L0_x7gLiynKNjqIXTTUoBoIhnEvhy3iZELajM");

        request.post("/api/ShoppingCart/AddToCart/5699/" + bookId);
        Response response = request.get("/api/ShoppingCart/5699");
        BookOrderDTO[] books = response.as(BookOrderDTO[].class);

        assertEquals(200, response.getStatusCode());
        assertEquals(bookId, books[0].getBook().getBookId());
    }

    @ParameterizedTest
    @ValueSource(ints = 4)
    public void updateShoppingCart(int bookId) {
        RequestSpecification request = RestAssured.given();
        request.log().all();

        BookDTO updatedBook = new BookDTO(4, "Harry Potter and the Goblet of Fire", "JKR", "Fiction",
                321, "9d31690d-3b1d-4faa-a1d2-3a680a935008HP4.jpg");

        HashMap<String, Object> body = new HashMap<>();

        body.put("quantity", 5);
        body.put("book", updatedBook);

        request.put("/api/ShoppingCart/5699/" + bookId);
        Response response = request.get("/api/ShoppingCart/5699");
        BookOrderDTO[] books = response.as(BookOrderDTO[].class);

        assertEquals(5, books[0].getQuantity());
    }

    @ParameterizedTest
    @ValueSource(ints = 5)
    public void deleteBookById(int bookId) {
        RequestSpecification request = RestAssured.given();
        request.log().all();

        Response response = request.post("/api/ShoppingCart/AddToCart/5699/" + bookId);
        assertEquals(200, response.getStatusCode());

        Response responseFromDelete = request.delete("/api/ShoppingCart/5699/" + bookId);

        assertEquals(200, responseFromDelete.getStatusCode());

        Response cart = request.get("/api/ShoppingCart/5699");
        BookOrderDTO[] books = cart.as(BookOrderDTO[].class);

        for (int i = 0; i < books.length; i++) {
            books[i].getBook().getBookId();
            assertNotEquals(bookId, books[i].getBook().getBookId());
        }
    }
}
