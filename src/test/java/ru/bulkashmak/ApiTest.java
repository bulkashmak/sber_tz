package ru.bulkashmak;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;


public class ApiTest {

    User user1 = new User(
            generateRandomString()+"qwer@mail.com",
            "Rest"+generateRandomString(),
            new int[]{12},
            new int[]{36,37},
            "Archery",
            "333 33 33",
            "123456789012"
    );

    static String generateRandomString() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 5;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @DataProvider(name = "jsonDataProvider")
    private Object[][] userCreation() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String userData = objectMapper.writeValueAsString(user1);
        return new Object[][] {
            new Object[] {userData}
        };

    }

    @Test(dataProvider = "jsonDataProvider", description = "Позитивный сценарий создания пользователя")
    void simpleTest(String jsonUserData) {

        RestAssured.given()
                .baseUri("http://users.bugred.ru/tasks")
                .basePath("/rest/createuser")
                .contentType("application/json")
                .body(jsonUserData)
                .when()
                    .post()
                .then()
                    .contentType(ContentType.JSON)
                    .assertThat().statusCode(200)
                    .body("name", Matchers.equalTo(user1.getName()))
                    .body("email", Matchers.equalTo(user1.getEmail()))
                    .body("hobby", Matchers.equalTo(user1.getHobby()))
                    .body("phone", Matchers.equalTo(user1.getPhone()));
    }
}