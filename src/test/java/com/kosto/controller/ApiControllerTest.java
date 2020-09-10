package com.kosto.controller;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletResponse;

@WebMvcTest(ApiController.class)
@ExtendWith(SpringExtension.class)
class ApiControllerTest {

    @Autowired
    private MockMvc mvcMock;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mvcMock);
    }

    @Test
    void testPing() {
        RestAssuredMockMvc
                .given()
                .when()
                .get("/ping")
                .then()
                .statusCode(HttpServletResponse.SC_OK);
    }

}
