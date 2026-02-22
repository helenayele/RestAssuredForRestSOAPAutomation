package test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestExample {
   @Test
    public void test_Get_method(){
        baseURI = "https://api.zippopotam.us/";
        Response response = get("https://api.zippopotam.us/");
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.getHeader("Content-Type"));

        int statusCode = response.getStatusCode();
       Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void test_status_code(){
        baseURI = "https://api.zippopotam.us/";
        given().get("us/90210").then().statusCode(200).body("places[0].longitude", equalTo("-118.4065"))
                .log().all();
    }

    @Test
    public void test_post_method() {
        baseURI = "https://api.zippopotam.us/";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("post code", "1000");
        jsonObject.put("country", "Ethiopia");
        jsonObject.put("country abbreviation", "ET");
        jsonObject.put("places[0].longitude", "-118.4065");
        jsonObject.put("places[0].latitude", "34.0901");
        jsonObject.put("places[0].state", "34.0901");
        jsonObject.put("places[0].state abbreviation", "34.0901");
        jsonObject.put("places[0].place name", "Beverly Hills");
        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(jsonObject.toJSONString()).
                when().
                post("/postalcode").
                then().statusCode(201).log().all();
    }
    @Test
    public void test_put_method() {
        baseURI = "https://api.zippopotam.us/";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("post code", "1000");
        jsonObject.put("country", "Ethiopia");
        jsonObject.put("country abbreviation", "ET");
        jsonObject.put("places[0].longitude", "-118.4065");
        jsonObject.put("places[0].latitude", "34.0901");
        jsonObject.put("places[0].state", "34.0901");
        jsonObject.put("places[0].state abbreviation", "34.0901");
        jsonObject.put("places[0].place name", "Beverly Hills");
        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(jsonObject.toJSONString()).
                when().
                put("/postalcode/1").
                then().statusCode(200).log().all();
    }
    @Test
    public void test_patch_method() {
        baseURI = "https://api.zippopotam.us/";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("post code", "1000");
        jsonObject.put("country", "Ethiopia");
        jsonObject.put("country abbreviation", "ET");
        jsonObject.put("places[0].longitude", "-118.4065");
        jsonObject.put("places[0].latitude", "34.0901");
        jsonObject.put("places[0].state", "34.0901");
        jsonObject.put("places[0].state abbreviation", "34.0901");
        jsonObject.put("places[0].place name", "Beverly Hills");
        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(jsonObject.toJSONString()).
                when().
                patch("/postalcode/2").
                then().statusCode(200).log().all();
    }

    @Test
    public void test_delete_method() {
        baseURI = "https://api.zippopotam.us/";
       when().
       delete("/postalcode/2").
                then().statusCode(204).log().all();
    }
}
