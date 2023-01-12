import endpoints.APIConstants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class bookStoreTests extends baseTest {

    @Test
    public void addBookToCollection() {
        Response response = RestAssured.given().spec(repoSpec)
                .body("{\"userId\":\"" + APIConstants.userId + "\",\"collectionOfIsbns\":[{\"isbn\":\"" + APIConstants.isbnBook + "\"}]}").when()
                .post(APIConstants.booksStorePath);
        response.then().statusCode(201);
    }

    @Test
    public void deleteBookFromCollection() {
        insertBook();
        Response response = RestAssured.given().spec(repoSpec)
                .body("{\"isbn\": \"" + APIConstants.isbnBook + "\",\"userId\": \"" + APIConstants.userId + "\"}")
                .when().delete(APIConstants.bookStorePath);
        response.then().statusCode(204);
    }
}
