package ru.bulkashmak;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class apiTest {

    User user1 = new User(
            generateRandomString()+"qwer@mail.com",
            "Рест 6"+generateRandomString(),
            new int[]{12},
            new int[]{36,37},
            "Стрельба из лука, Настолки",
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
    private Object[][] userCreation() {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String userData = objectMapper.writeValueAsString(user1);
            return new Object[][] {
                    new Object[] {
                            userData
                    }
            };
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new Object[0][];
    }

    @Test(dataProvider = "jsonDataProvider")
    void simpleTest(String jsonUserData) {

        Response response = given()
                .baseUri("http://users.bugred.ru/tasks")
                .basePath("/rest/createuser")
                .contentType("application/json")
                .body(jsonUserData)

                .when().post()

                .then().contentType(ContentType.JSON)
                .assertThat().statusCode(200)
                .extract().response();

        System.out.println(response.asString());

        JSONObject jsonObject = new JSONObject(jsonUserData);

        Assert.assertEquals(jsonObject.getString("name"), user1.getName());
        Assert.assertEquals(jsonObject.getString("email"), user1.getEmail());
        Assert.assertEquals(jsonObject.getString("hobby"), user1.getHobby());
        Assert.assertEquals(jsonObject.getString("phone"), user1.getPhone());
    }
}