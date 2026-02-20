import POJO.AddBook;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class TestSample {

    //@Test
    public void TC_001_readDataFromExcel() throws IOException {
        DataDriven data = new DataDriven();
        ArrayList<String> excelData = data.getData("Add profile","Class D");  //Login, delete profile
        System.out.println(excelData.get(0));
        System.out.println(excelData.get(1));
        System.out.println(excelData.get(2));
        System.out.println(excelData.get(3));

    }


    @Test
    public void TC_002_AddBookWithPojo() throws IOException {
        String Id;
        JsonPath js;

        AddBook book = new AddBook();
        book.setBook_name("GitKraken and GitHub");
        book.setIsbn("0014");
        book.setAisle("vvMar");
        book.setAuthor("Jack Foe Sr");

        RestAssured.baseURI="http://216.10.245.166";
        //RestAssured.useRelaxedHTTPSValidation();
        Response res = given().log().all()
                .headers("Content-Type","application/json")
                .body(book)
                .when()
                .post("/Library/Addbook.php");

        // Log the entire raw response
        int statusCode = res.getStatusCode();
        String responseBody = res.getBody().asString();

        System.out.println("Status Code: " + statusCode);
        System.out.println("Raw Response Body: " + responseBody);

        js = BaseLib.rawFromJSONResponse(responseBody);
        /* POST Response from SWAGGER is below:
        {
                "Msg": "successfully added",
                "ID": "bcd227"
        }
        */
        // Only try to parse if we got a successful status code
        if (statusCode == 200 && responseBody != null && !responseBody.isEmpty()) {
            try {

                String id = js.getString("ID");
                System.out.println("Book Id is ---> " + id);
            } catch (Exception e) {
                System.out.println("Failed to parse JSON response: " + e.getMessage());
                System.out.println("Response was: " + responseBody);
            }
        } else {
            System.out.println("Request failed with status code: " + statusCode);
        }

    }


    @Test
    public void TC_003_AddBookWithHashMap(){

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "John O'Connor and Terminator");
        map.put("isbn", "r735");
        map.put("aisle", "0092");
        map.put("author", "Jack Fish Jr");
        RestAssured.baseURI="http://216.10.245.166";
        RestAssured.useRelaxedHTTPSValidation();
        String res = given().log().all()
                .headers("Content-Type","application/json")
                .body(map)
                .when()
                .post("/Library/Addbook.php")
                .then()
                .assertThat().statusCode(200)
                .extract().response().asString();


    }

    @Test
    public void TC_003_AddBookDataFromExcel() throws IOException {

        DataDriven data = new DataDriven();
        ArrayList<String> excelData = data.getData("AddBook","Books");
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", excelData.get(1));
        map.put("isbn", excelData.get(2));
        map.put("aisle", excelData.get(3));
        map.put("author", excelData.get(4));
        RestAssured.baseURI="http://216.10.245.166";
        RestAssured.useRelaxedHTTPSValidation();
        String res = given().log().all()
                .headers("Content-Type","application/json")
                .body(map)
                .when()
                .post("/Library/Addbook.php")
                .then()
                .assertThat().statusCode(200)
                .extract().response().asString();

    }


}
