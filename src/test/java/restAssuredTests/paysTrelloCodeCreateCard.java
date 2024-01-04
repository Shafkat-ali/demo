package restAssuredTests;

import com.github.javafaker.Faker;
import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class paysTrelloCodeCreateCard {

	public static void main(String[] args) {

		String key = "0e757b5650b37154bf953abf98fc73c5";
		String token = "ATTA11039b305c8c9329a938c286c8b6aa08a21dee5fd2f9a4653e1addc2143b097b4DF0BA71";
		String listId = "6595c1a180a7f168cbd94015";
		Faker faker = new Faker();
		String RandomListName = faker.animal().name();

		// rest assured base url
		RestAssured.baseURI = "https://api.trello.com/1/cards";

		// create request object
		RequestSpecification httpRequest = RestAssured.given();

		httpRequest.header("Content-Type", "application/json");

		int maxCards = 10001;
		int numCards = 0;

		while (numCards < maxCards) {
			JSONObject requestBody = new JSONObject();
			requestBody.put("name", RandomListName + (numCards + 1));
			requestBody.put("idList", listId);
			requestBody.put("key", key);
			requestBody.put("token", token);

			Response myResponse = httpRequest.body(requestBody.toString()).post();

			int statusCode = myResponse.getStatusCode();
			if (statusCode == 200) {
				numCards++;
				System.out.println("Cards " + numCards + " created");
			} else {
				System.out.println("Failed to create card");
				myResponse.prettyPrint();
				break;
			}

		}

		System.out.println("Number of cards created: " + numCards);

	}

}

