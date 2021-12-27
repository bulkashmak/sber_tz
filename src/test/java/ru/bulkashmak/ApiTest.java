package ru.bulkashmak;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

public class ApiTest extends AllureRestAssured {

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
    private Object[][] userCreation() {

        return new Object[][]{
                new Object[]{
                        new User(
                                generateRandomString() + "qwer@mail.com",
                                "Rest" + generateRandomString(),
                                new int[]{12},
                                new int[]{36, 37},
                                "Archery",
                                "333 33 33",
                                "123456789012"
                        )
                }
        };
    }

    @Step("Отправка запроса")
    @Test(dataProvider = "jsonDataProvider", description = "Позитивный сценарий создания пользователя")
    void userCreationRequest(User userData) {

        ValidatableResponse response = RestAssured.given()
                .baseUri("http://users.bugred.ru/tasks")
                .basePath("/rest/createuser")
                .contentType("application/json")
                .body(userData)
                .when()
                .filter(new AllureRestAssured())
                .post()
                .then()
                .contentType(ContentType.JSON)
                .assertThat().statusCode(200);

        validateResponse(response, userData);
    }

    @Step("Валидация ответа")
    static void validateResponse(ValidatableResponse response, User userData) {

        response.body("name", Matchers.equalTo(userData.getName()))
                .body("email", Matchers.equalTo(userData.getEmail()))
                .body("hobby", Matchers.equalTo(userData.getHobby()))
                .body("phone", Matchers.equalTo(userData.getPhone()));
    }
}