package ru.bulkashmak;


import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AppTest {

    @Test
    void simpleTest() {

        String postRequestData = "{\n" +
                "\"email\": \"qwerasdfty_45@mail.com\",\n" +
                "\"name\": \"Рест 6_3asdf45\",\n" +
                "\"tasks\": [12],\n" +
                "\"companies\": [36,37],\n" +
                "\"hobby\":\"Стрельба из лука, Настолки\",\n" +
                "\"adres\":\"адрес 1\",\n" +
                "\"name1\":\"Тестовый, ясен пень\",\n" +
                "\"surname1\":\"Иванов\",\n" +
                "\"fathername1\":\"Петров\",\n" +
                "\"cat\":\"Маруся\",\n" +
                "\"dog\":\"Ушастый\",\n" +
                "\"parrot\":\"Васька\",\n" +
                "\"cavy\":\"Кто ты?\",\n" +
                "\"hamster\":\"Хомяк\",\n" +
                "\"squirrel\":\"Белая горячка к нам пришла\",\n" +
                "\"phone\":\"333 33 33\",\n" +
                "\"inn\":\"123456789012\",\n" +
                "\"gender\":\"m\",\n" +
                "\"birthday\":\"01.01.1995\",\n" +
                "\"date_start\":\"11.11.2000\"\n" +
                "}";

        given()
                .baseUri("http://users.bugred.ru/tasks")
                .basePath("/rest/createuser")
                .header(new Header("Content-Type", "application/json"))
                .body(postRequestData)

                .when().post()

                .then().statusCode(200);
    }
}
