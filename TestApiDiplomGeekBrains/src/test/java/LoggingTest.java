import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.lessThan;

public class LoggingTest extends AbstractTest{
    @BeforeAll
    static void setUp(){

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test//получилось авторизоваться
    public void getDataTest() {
        RequestSpecification httpRequest = RestAssured.given().auth().basic("filolog999333", "27791fb46b").headers("X-Auth-Token", "1f67a2d2f24019840fd1a1d14da73717");
        Response res = httpRequest.get("https://test-stand.gb.ru/api/posts");
        ResponseBody body = res.body();
        //Converting the response body to string
        String rbdy = body.asString();
        System.out.println("Data from the GET API- "+rbdy);


    }
    @Test
    void AutorizationTest(){
        given()
                .log().all()
                .auth().basic("filolog999333", "27791fb46b")
                .headers("X-Auth-Token", "1f67a2d2f24019840fd1a1d14da73717")

                .when()
                .contentType(ContentType.JSON)
                .post("https://test-stand.gb.ru/login")

                .then()
                .log().all()
                .assertThat()
                //.cookie("cookieName", "cookieValue")
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"));
    }

    @Test
    void LoginCyrillicTest(){
        given()
                .log().all()
                .auth().basic("Diman", "aa94e6cbee")
                .headers("X-Auth-Token", "229ed0122e51e145275aa620c362569f85")

                .when()
                .contentType(ContentType.JSON)
                .post("https://test-stand.gb.ru/login")

                .then()
                .log().all()
                .assertThat()
                //.cookie("cookieName", "cookieValue")
                .statusCode(401);
    }
    @Test
    void AutorizationWitEpmtyFieldsTest(){
        given()
                .log().all()
                .auth().basic(" ", " ")

                .when()
                .contentType(ContentType.JSON)
                .post("https://test-stand.gb.ru/login")

                .then()
                .log().all()
                .assertThat()
                //.cookie("cookieName", "cookieValue")
                .statusCode(401);
    }

    @Test
    void LoginMoreThanTwentyCharactersTest(){
        given()
                .log().all()
                .auth().basic("qwertyuiopasdfghjklzxcvbnm", "e5daaa90c3")
                .headers("X-Auth-Token", "2236cb7c69e97c2e70416ccd16903320b0")

                .when()
                .contentType(ContentType.JSON)
                .post("https://test-stand.gb.ru/login")

                .then()
                .log().all()
                .assertThat()
                //.cookie("cookieName", "cookieValue")
                .statusCode(401);
    }

    @Test
    void LoginLessThenThreCharactersTest(){
        given()
                .log().all()
                .auth().basic("Gr", "27791fb46b")
                .headers("X-Auth-Token", "222573b963a806a58378d20741e4c66")

                .when()
                .contentType(ContentType.JSON)
                .post("https://test-stand.gb.ru/login")

                .then()
                .log().all()
                .assertThat()
                //.cookie("cookieName", "cookieValue")
                .statusCode(401);
    }

    @Test
    void SpecialCharactersInLoginTest(){
        given()
                .log().all()
                .auth().basic("ARt&#@", "6bd4b6ed70")
                .headers("X-Auth-Token", "2269dd74f8e7bb8ff9d0cced312693d6ff")

                .when()
                .contentType(ContentType.JSON)
                .post("https://test-stand.gb.ru/login")

                .then()
                .log().all()
                .assertThat()
                //.cookie("cookieName", "cookieValue")
                .statusCode(401);
    }


}
