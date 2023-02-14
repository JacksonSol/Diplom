import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class MainOtherPeoplePostsTest extends AbstractTest{

    @Test
        //get
    void OtherPeoplePostsTest(){
        given()
                .log().all()
                .auth().basic("filolog999333", "27791fb46b")
                .headers("X-Auth-Token", "1f67a2d2f24019840fd1a1d14da73717")

                .when()
                .contentType(ContentType.JSON)
                .get("https://test-stand.gb.ru/api/posts?sort=createdAt&order=ASC&page=1&owner=notMe")

                .then()
                .log().all()
                .assertThat()
                //.cookie("cookieName", "cookieValue")
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"));

    }
    @Test
    void RequestNotMyPostsSortingDESCTest(){
        given()
                .log().all()
                .auth().basic("filolog999333", "27791fb46b")
                .headers("X-Auth-Token", "1f67a2d2f24019840fd1a1d14da73717")

                .when()
                .contentType(ContentType.JSON)
                .get("https://test-stand.gb.ru/api/posts?sort=createdAt&order=DESC&page=2&owner=notMe")

                .then()
                .log().all()
                .assertThat()
                //.cookie("cookieName", "cookieValue")
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"));
    }

    @Test
    void RequestNotMyPostsSortingPageTest(){
        given()
                .log().all()
                .auth().basic("filolog999333", "27791fb46b")
                .headers("X-Auth-Token", "1f67a2d2f24019840fd1a1d14da73717")

                .when()
                .contentType(ContentType.JSON)
                .get("https://test-stand.gb.ru/api/posts?sort=createdAt&order=ASC&page=20&owner=notMe")

                .then()
                .log().all()
                .assertThat()
                //.cookie("cookieName", "cookieValue")
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"));
    }
    @Test
    void RequestUnauthorizedNotMyPostsTest(){
        given()
                .log().all()
                .auth().basic("filolog999333", "27791fb46b")
                .headers("X-Auth-Token", "1f67a2d2f24019840fd1a1d14da73717")
                .when()
                .contentType(ContentType.JSON)
                .get("https://test-stand.gb.ru/api/posts?owner=NotMe")

                .then()
                .log().all()
                .assertThat()
                //.cookie("cookieName", "cookieValue")
                .statusCode(401);

    }
    @Test
    void NonExistentPageTest(){
        given()
                .log().all()
                .auth().basic(" ", " ")

                .when()
                .contentType(ContentType.JSON)
                .get("https://test-stand.gb.ru/api/posts?order=ASC&page=-1&owner=notMe")

                .then()
                .log().all()
                .assertThat()
                //.cookie("cookieName", "cookieValue")
                .statusCode(401);

    }
}
