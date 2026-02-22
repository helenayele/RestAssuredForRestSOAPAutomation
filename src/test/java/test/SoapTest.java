package test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers;
import org.apache.commons.io.IOUtils;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;

public class SoapTest {
    @Test
    public void validateSoapXML() {
        File file = new File("./SoapRequest/Add.xml");
        FileInputStream fileInputStream;
        String requestBody;
        try {
            fileInputStream = new FileInputStream(file);
            requestBody = IOUtils.toString(fileInputStream, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        if (file.exists()) {
            System.out.println(">>> File exists");
        }

        baseURI = "http://www.dneonline.com/";
        given()
                .contentType("text/xml")
                .accept(ContentType.XML)
                .body(requestBody)
                .when()
                .post("/calculator.asmx")
                .then()
                .statusCode(200).log().all().
                and().
                body("//AddResult.text()",equalTo("7")).log().all();

    }
    @Test
    public void SchemaValidation() {
        File file = new File("./SoapRequest/Add.xml");
        FileInputStream fileInputStream;
        String requestBody;
        try {
            fileInputStream = new FileInputStream(file);
            requestBody = IOUtils.toString(fileInputStream, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        if (file.exists()) {
            System.out.println(">>> File exists");
        }

        baseURI = "http://www.dneonline.com/";
        given()
                .contentType("text/xml")
                .accept(ContentType.XML)
                .body(requestBody)
                .when()
                .post("/calculator.asmx")
                .then()
                .statusCode(200).log().all().
                and().
                body("//AddResult.text()",equalTo("7")).log().all().
                and().
                assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("Calculator.xsd"));
    }
}
