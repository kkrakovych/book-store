package com.kosto.controller;

import com.kosto.model.BookDTO;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BookControllerTest {


    @BeforeEach
    void setUp() {
        RestAssured.port = 8080;
    }

    @Test
    void testAddBook() {
        BookDTO bookDTO = new BookDTO("testName", "testAuthor", 10);
        RestAssured
                .given()
                .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .body(bookDTO)
                .post("/addBook")
                .then()
                .statusCode(HttpServletResponse.SC_OK);

        List<BookDTO> expected = new ArrayList<>();
        expected.add(bookDTO);

        RestAssured
                .given()
                .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .with().queryParam("bookName", "old")
                .get("/findBook")
                .then()
                .statusCode(HttpServletResponse.SC_OK)
                .log().all();
//                .assertThat()
//                .body("", Matchers.hasItem(expected));
    }

}
