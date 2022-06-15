import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import java.util.Locale;

import static io.restassured.RestAssured.given;

// спецификация нужна для того, чтобы переиспользовать настройки в разных запросах
class DataGenerator {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void sendRequest(RegistrationInfo user) {
        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(new Gson().toJson(user)) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
    }

    static Faker faker = new Faker(new Locale("en"));

    private DataGenerator() {
    }

    public static String getRandomLogin() {
        String login = faker.name().firstName();
        return login;
    }

    public static String getRandomPassword() {
        String password = faker.internet().password();
        return password;
    }

    public static class Registration {
        private Registration() {
        }
    }

    public static RegistrationInfo getUser(String status) {
        RegistrationInfo user = new RegistrationInfo(getRandomLogin(), getRandomPassword(), status);
        return user;
    }

    public static RegistrationInfo getRegisteredUser(String status) {
        RegistrationInfo registeredUser = getUser(status);
        sendRequest(registeredUser);
        return registeredUser;
    }

}