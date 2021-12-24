package ru.bulkashmak;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Header;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class AppTest {

//    @DataProvider(name = "jsonDataProvider")
    void userDataProvider(){

//        JSONObject userData = new JSONObject();
//
//        userData.put("email", random.ints(5).toString() + "qwer@mail.com");
//        userData.put("name", "Рест 6" + random.ints(5));
//        userData.put();
//        userData.put();
//        userData.put();
//        userData.put();
//
//
//        URL resource = User.class.getClassLoader().getResource("json/userData.json");
//        StringBuilder jsonUserData = new StringBuilder();
//        BufferedReader bufferedReader = null;
//        try {
//            bufferedReader = new BufferedReader(new FileReader(resource.getFile()));
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                jsonUserData.append(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (bufferedReader != null) {
//                try {
//                    bufferedReader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        JSONObject jsonObject = new JSONObject(jsonUserData);
//        JSONObject emailChildObject = jsonObject.getJSONObject("email");
//        JSONObject nameChildObject = jsonObject.getJSONObject("name");
//
//        Random random = new Random();
//        JSONObject modifiedJsonObject = new JSONObject();
//        modifiedJsonObject.put("type", emailChildObject.get("type"));
//        modifiedJsonObject.put("value", random.ints(4).toString()+"qwer@mail.com");
//
//        modifiedJsonObject.put("type", nameChildObject.get("type"));
//        modifiedJsonObject.put("value", random.ints(4).toString()+"qwer@mail.com");


//        System.out.println(stringBuilder.toString());

//        {
//            "email": "qwerty_45@mail.com",
//                "name": "Рест 6_345",
//                "tasks": [12],
//            "companies": [36,37],
//            "hobby":"Стрельба из лука, Настолки",
//                "adres":"адрес 1",
//                "name1":"Тестовый, ясен пень",
//                "surname1":"Иванов",
//                "fathername1":"Петров",
//                "cat":"Маруся",
//                "dog":"Ушастый",
//                "parrot":"Васька",
//                "cavy":"Кто ты?",
//                "hamster":"Хомяк",
//                "squirrel":"Белая горячка к нам пришла",
//                "phone":"333 33 33",
//                "inn":"123456789012",
//                "gender":"m",
//                "birthday":"01.01.1995",
//                "date_start":"11.11.2000"
//        }


        InputStream is = getClass().getClassLoader().getResourceAsStream("json/userData.json");

        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("target/test-classes/json/userData.json");

        try {
            User user1 = objectMapper.readValue(file, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(dataProvider = "jsonDataProvider")
    void simpleTest(User user) {

        given()
                .baseUri("http://users.bugred.ru/tasks")
                .basePath("/rest/createuser")
                .header(new Header("Content-Type", "application/json"))
                .body(user)

                .when().post()

                .then().statusCode(200);
    }
}
