package com.automation.homework3;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HarryPotterAPI {
    /**
     * In this assignment, you will test Happy Potter API created based on Harry Potter movie series.
     * Full documentation for Happy Potter API can be found here: https://www.potterapi.com/.
     * Watch all the movies or read all the books to better understanding of this API (this is optional).
     * To test this API, you have to register and get access key.
     * Sign up for access key. Access key is required for most of the requests.
     */
    @BeforeAll
    public static void setup() {
        baseURI = "https://www.potterapi.com/v1/";
    }
    /**
     * Import the Postman collection using this link:
     * https://www.getpostman.com/collections/5ceaa3e2705188383075In
     * postman create an environment for this API.
     * Add the following variables:
     * 1. Variable name:  baseURL
     * Variable value: https://www.potterapi.com/v1/2.
     * Variable name:  apiKey
     * Variable value: your api key from the https://www.potterapi.com/
     */

    /**
     * Automate the given test cases.
     * You can use any existing project.
     * You can automate all test cases in same class or different classes.
     * For verifying all of the use pojos.
     * Create pojo classes for Character and House in pojos package based on the provided json files.
     * <p>
     * <p>
     * Verify sorting hat  1.Send a get request to /sortingHat.
     * Request includes :2.Verify status code 200, content type application/json; charset=utf-83.
     * Verify that response body contains one of the following houses: "Gryffindor", "Ravenclaw", "Slytherin", "Hufflepuff"
     */
    @Test
    @DisplayName("Verify sorting hat ")
    public void verifyResponseContainsHouses() {
        Response response =
                given().queryParam("apiKey", "$2a$10$8U30d60AJUAvvY407fdewOndCLvGySAgG8GU2UXfzSqkX4tRzPswS").
                        when().get("/sortingHat").prettyPeek();

        response.then().
                statusCode(200).
                contentType(ContentType.JSON);

        String school = response.body().asString().replace("\"", "");
        System.out.println("school = " + school);

        List<String> list = new ArrayList<>(Arrays.asList("Gryffindor", "Ravenclaw", "Slytherin", "Hufflepuff"));
        System.out.println("list = " + list);
        assertTrue(list.contains(school));
    }

    /**
     * Verify bad key
     * 1.Send a get request to /characters. Request includes :
     * •Header Accept with value application/json
     * •Query param key with value invalid
     * 2.Verify status code 401, content type application/json;
     * charset=utf-83.Verify response status line include message Unauthorized4.
     * Verify that response body says"error":"API Key Not Found"
     */


    @Test
    @DisplayName("Verify bad key")
    public void verifyResponseBodyError() {
        Response response =
                given().
                        queryParam("apiKey", "$2a$10$8U30d60AJUAvvY407fdewOndCLvGySAgG8GU2UXfzSqkX4tRzPswS").
                        queryParam("key", "invalid").
                        header("Accept", "application/json").
                        when().
                        get("/characters").prettyPeek();
        response.then().
                statusCode(401).
                contentType(ContentType.JSON);
        assertTrue(response.getStatusLine().contains("Unauthorized"));
        assertTrue(response.body().asString().contains("\"error\":\"API Key Not Found\""));
    }
/**
 * Verify no key
 * 1.Send a get request to /characters.
 * Request includes :•Header Accept with value application/json
 * 2.Verify status code 409, content type application/json;
 * charset=utf-83.
 * Verify response status line include message Conflict
 * 4.Verify that response body says"error":"Must pass API key for request"
 */
@Test
@DisplayName("Verify no key")
public void verifyErrorMustPassKey() {
    Response response =
            given().
                    queryParam("apiKey", "$2a$10$8U30d60AJUAvvY407fdewOndCLvGySAgG8GU2UXfzSqkX4tRzPswS").
                    header("Accept", "application/json").
                    when().
                    get("/characters").prettyPeek();
    response.then().
            statusCode(409).
            contentType(ContentType.JSON);
    assertTrue(response.getStatusLine().contains("Conflict"));
    assertTrue(response.body().asString().contains("\"error\":\"Must pass API key for request\""));
}
/**
 * Verify number of characters
 * 1.Send a get request to /characters.
 * Request includes :•Header Accept with value application/json
 * •Query param key with value {{apiKey}}
 * 2.Verify status code 200, content type application/json; charset=utf-83.
 * Verify response contains 194 characters
 */

@Test
    @DisplayName("Verify number of characters")
    public void verifyResponseContainsChar(){
    Response response=
                    given().queryParam("key", "$2a$10$8U30d60AJUAvvY407fdewOndCLvGySAgG8GU2UXfzSqkX4tRzPswS").
                    header("Accept", "application/json").
                    when().get("/characters").prettyPeek();

   response.then().statusCode(200).contentType(ContentType.JSON);


    }
    /**
     * Verify number of character id and house
     * 1.Send a get request to /characters.
     * Request includes :•Header Accept with value application/json•
     * Query param key with value {{apiKey}}
     * 2.Verify status code 200, content type application/json; charset=utf-83.
     * Verify all characters in the response have id field which is not empty
     * 4.Verify that value type of the field dumbledoresArmy is a boolean in all characters in the response
     * 5.Verify value of the house in all characters in the response is one of the following:
     * "Gryffindor", "Ravenclaw", "Slytherin", "Hufflepuff"
     */
}
