package restAssuredTests;

import org.json.simple.JSONObject;
import org.testng.Assert;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Trello_RestAssured_CreateListLimit {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        String key = "0e757b5650b37154bf953abf98fc73c5";
        String token = "ATTA11039b305c8c9329a938c286c8b6aa08a21dee5fd2f9a4653e1addc2143b097b4DF0BA71";
        Faker faker = new Faker();
        String RandomListName = faker.animal().name();
        String boardID = "6594cbff6b5176cfe1f56874";
        int numLists = 0;

        RestAssured.baseURI = "https://api.trello.com/1/lists";

        // create request object
        RequestSpecification httpRequest = RestAssured.given();

        httpRequest.header("Content-Type", "application/json");

        // create lists until the maximum limit is reached

        while (true) {
            JSONObject requestBody = new JSONObject();
            requestBody.put("name", RandomListName + (numLists + 1));
            requestBody.put("idBoard", boardID);
            requestBody.put("key", key);
            requestBody.put("token", token);

            Response myResponse = httpRequest.body(requestBody.toString()).post();

            int statusCode = myResponse.getStatusCode();

            if (statusCode == 200) {
                numLists++;
                System.out.println("List " + numLists + " created");
            } else {
                System.out.println("Failed to create list");
                myResponse.prettyPrint(); // print the response for the further inspection
                break; // stop creating lists if an error occurs
            }

        }

        System.out.println("Number of lists created " + numLists);

    }
}


