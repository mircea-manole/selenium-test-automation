import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.automation.dto.PatchDTO;
import org.automation.dto.Post;
import org.automation.dto.PostDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonPlaceholderTest {

    Logger log = LogManager.getRootLogger();

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void createPosts() {
        RequestSpecification request = RestAssured.given();
        request.log().all();

        request.header("Content-Type", ContentType.JSON);

        HashMap<String, String> map = new HashMap<>();
        map.put("title", "this is a test");
        map.put("body", "testing something here");
        request.body(map);

        Response response = request.post("/posts");
        PostDTO responsePost = response.getBody().as(PostDTO.class);

        log.info(responsePost.getBody());
        log.info(responsePost.getId());
        log.info(responsePost.getTitle());

        assertEquals(map.get("title"), responsePost.getTitle());
        assertEquals(map.get("body"), responsePost.getBody());
        assertEquals(101, responsePost.getId());
    }

    @Test
    public void putPosts() {
        RequestSpecification request = RestAssured.given();
        request.log().all();

        request.header("Content-Type", ContentType.JSON);
        HashMap<String, String> map = new HashMap<>();
        map.put("title", "updated title");
        map.put("body", "testing something here");
        request.body(map);

        Response response = request.put("/posts/1");
        PostDTO responsePost = response.getBody().as(PostDTO.class);

        log.info(responsePost.getBody());
        log.info(responsePost.getId());
        log.info(responsePost.getTitle());

        assertEquals(map.get("title"), responsePost.getTitle());
        assertEquals(map.get("body"), responsePost.getBody());
        assertEquals(1, responsePost.getId());
    }

    @Test
    public void patchPost() {
        RequestSpecification request = RestAssured.given();
        request.log().all();

        request.header("Content-Type", ContentType.JSON);
        HashMap<String, String> map = new HashMap<>();
        map.put("body", "request patch executed");

        request.body(map);
        Response response = request.patch("/posts/1");

        PatchDTO responsePatch = response.getBody().as(PatchDTO.class);

        log.info(responsePatch.getBody());
        log.info(responsePatch.getId());
        log.info(responsePatch.getTitle());
        log.info(responsePatch.getUserId());

        assertEquals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", responsePatch.getTitle());
        assertEquals(map.get("body"), responsePatch.getBody());
        assertEquals(1, responsePatch.getId());
        assertEquals(1, responsePatch.getUserId());
    }

    @Test
    public void postWithBuilder() throws Exception {
        RequestSpecification request = RestAssured.given();
        request.log().all();
        request.header("Content-Type", ContentType.JSON);

        Post requestPost = new Post.Builder()
                .body("book chapter")
                .title("book title")
                .build();

        request.body(new ObjectMapper().writeValueAsString(requestPost));

        Response response =  request.post("/posts");

        PostDTO responsePost = response.getBody().as(PostDTO.class);

        log.info(responsePost.getBody());
        log.info(responsePost.getId());
        log.info(responsePost.getTitle());

        assertEquals("book title", responsePost.getTitle());
        assertEquals("book chapter", responsePost.getBody());
        assertEquals(101, responsePost.getId());
    }
}
