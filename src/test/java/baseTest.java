import endpoints.APIConstants;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static io.restassured.config.RestAssuredConfig.newConfig;

public class baseTest {
    protected RequestSpecification repoSpec;
    private String bearerToken;

    public void insertBook() {
        RestAssured.given().spec(repoSpec)
                .body("{\"userId\":\"" + APIConstants.userId + "\"," +
                        "\"collectionOfIsbns\":[{\"isbn\":\"" + APIConstants.isbnBook + "\"}]}").when()
                .post(APIConstants.booksStorePath);
    }

    @BeforeClass
    protected void generateBearer() {
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .body("{\"userName\": \"" + APIConstants.userName + "\"," +
                        "\"password\": \"" + APIConstants.password + "\"}").when()
                .post(APIConstants.baseUrl + APIConstants.accountGenTokenPath);
        bearerToken = "Bearer " + response.jsonPath().get("token").toString();
    }

    @BeforeMethod
    protected void configSetUp() {
        repoSpec = new RequestSpecBuilder().setBaseUri(APIConstants.baseUrl).setConfig(newConfig())
                .addHeader("Authorization", bearerToken).setContentType("application/json").build();
    }

    @AfterMethod
    protected void deleteData() {
        RestAssured.given().spec(repoSpec).queryParam("UserId", APIConstants.userId)
                .when().delete(APIConstants.booksStorePath);
    }
}
