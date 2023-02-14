import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class MainMyPostsTest extends AbstractTest  {
    @Test//получилось авторизоваться
    public void getDataTest() {
        RequestSpecification httpRequest = RestAssured.given().auth().basic("filolog999333", "27791fb46b").headers("X-Auth-Token", "1f67a2d2f24019840fd1a1d14da73717");
        Response res = httpRequest.get("https://test-stand.gb.ru/api/posts");
        ResponseBody body = res.body();
        //Converting the response body to string
        String rbdy = body.asString();
        System.out.println("Data from the GET API- "+rbdy);

    }

    @Test //get
    void RequestYourPostsTest(){
        given()
                .log().all()
                .auth().basic("filolog999333", "27791fb46b")
                .headers("X-Auth-Token", "1f67a2d2f24019840fd1a1d14da73717")

                .when()
                .contentType(ContentType.JSON)
                .get("https://test-stand.gb.ru/api/posts")

                .then()
                .log().all()
                .assertThat()
                //.cookie("cookieName", "cookieValue")
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"));

    }
    @Test //get
    void RequestYourPostsSortingASCTest(){
        given()
                .log().all()
                .auth().basic("filolog999333", "27791fb46b")
                .headers("X-Auth-Token", "1f67a2d2f24019840fd1a1d14da73717")

                .when()
                .contentType(ContentType.JSON)
                .get("https://test-stand.gb.ru/api/posts?sort=createdAt&order=ASC")

                .then()
                .log().all()
                .assertThat()
                //.cookie("cookieName", "cookieValue")
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"));

    }

    @Test //get
    void SortingYourPostsDESCTest(){
        given()
                .log().all()
                .auth().basic("filolog999333", "27791fb46b")
                .headers("X-Auth-Token", "1f67a2d2f24019840fd1a1d14da73717")

                .when()
                .contentType(ContentType.JSON)
                .get("https://test-stand.gb.ru/api/posts?page=2")

                .then()
                .log().all()
                .assertThat()
                //.cookie("cookieName", "cookieValue")
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"));

    }

    @Test //get
    void UserWithoutPostsTest(){
        given()
                .log().all()
                .auth().basic("Gr", "27791fb46b")
                .headers("X-Auth-Token", "222573b963a806a58378d20741e4c66")

                .when()
                .contentType(ContentType.JSON)
                .get("https://test-stand.gb.ru/api/posts")

                .then()
                .log().all()
                .assertThat()
                //.cookie("cookieName", "cookieValue")
                .statusCode(401);
    }

    @Test //get
    void SortingYourPostsPageValueTest(){
        given()
                .log().all()
                .auth().basic("filolog999333", "27791fb46b")
                .headers("X-Auth-Token", "1f67a2d2f24019840fd1a1d14da73717")

                .when()
                .contentType(ContentType.JSON)
                .get("https://test-stand.gb.ru/api/posts")

                .then()
                .log().all()
                .assertThat()
                //.cookie("cookieName", "cookieValue")
                .statusCode(200);
    }

    @Test //get
    void UserWithoutPostsSortingTest(){
        given()
                .log().all()
                .auth().basic("User8", "d306313ac0")
                .headers("X-Auth-Token", "22189ff5178a8686512ddbc156277f7f5e")

                .when()
                .contentType(ContentType.JSON)
                .get("https://test-stand.gb.ru/api/posts?order=DESC&page=2")

                .then()
                .log().all()
                .assertThat()
                //.cookie("cookieName", "cookieValue")
                .statusCode(401);
    }
}
