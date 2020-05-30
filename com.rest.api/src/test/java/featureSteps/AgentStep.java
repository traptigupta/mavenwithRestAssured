package featureSteps;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
 
import java.util.Map;
 
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class AgentStep {
	
	 
	 
		private Response response;
		private ValidatableResponse json;
		private RequestSpecification request=RestAssured.given();
	    private String ENDPOINT_GET_BOOK_BY_ISBN = "https://www.googleapis.com/books/v1/volumes";
	 
		 @Given("^a Agent  exists with an AgentCode of (\\d+)$")
		public void aAgentExistsWithAnAgentCodeOf(String isbn) throws Throwable {
			System.out.println(isbn);
			//RequestSpecification request = RestAssured.given();
			request.param("q", "isbn:" + isbn);
		}

		@When("^a user retrieves the AgentDetails by API$")
		public void aUserRetrievesTheAgentDetailsByAPI() throws Throwable {
			response = request.when().get(ENDPOINT_GET_BOOK_BY_ISBN);
			System.out.println("response: " + response.prettyPrint());
		}
		
		

		/**
		 * asserts on json fields with single values
		 */
		
		@Then("^the respose status code is (\\d+)$")
		public void theResposeStatusCodeIs(int statusCode) throws Throwable {
			 int responseCode = response.getStatusCode();
			 System.out.println("response: done "+responseCode);
			Assert.assertEquals("got status code 200",
					statusCode, responseCode);
		}
		
		



		
	 
	}




