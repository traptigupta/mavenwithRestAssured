package featureSteps;



	
	import static io.restassured.RestAssured.when;
	import static org.hamcrest.Matchers.containsInAnyOrder;
	import static org.hamcrest.Matchers.equalTo;
	 
	import java.util.Map;
	 
	import org.apache.commons.lang3.StringUtils;
	 
	import cucumber.api.java.en.And;
	import cucumber.api.java.en.Given;
	import cucumber.api.java.en.Then;
	import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
	import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
	 
	public class test {
	 
		private Response response;
		private ValidatableResponse json;
		private RequestSpecification request=RestAssured.given();
	    private String ENDPOINT_GET_BOOK_BY_ISBN = "https://www.googleapis.com/books/v1/volumes";
	 
		@Given("a book exists with an isbn of (.*)")
		public void a_book_exists_with_isbn(String isbn){
			//request = request.param("q", "isbn:" + isbn);
			System.out.println(isbn);
			//RequestSpecification request = RestAssured.given();
			request.param("q", "isbn:" + isbn);
		}
	 
		@When("a user retrieves the book by isbn")
		public void a_user_retrieves_the_book_by_isbn(){
			//Response response= RestAssured.
			response = request.when().get(ENDPOINT_GET_BOOK_BY_ISBN);
			System.out.println("response: " + response.prettyPrint());
		}
	 
		@Then("the status code is (\\d+)")
		public void verify_status_code(int statusCode){
			//json = response.then().statusCode(statusCode);
			
		 int responseCode = response.getStatusCode();
		 System.out.println("response: done "+responseCode);
		Assert.assertEquals("got status code 200",
				statusCode, responseCode);
		
		}
		
	 
		/**
		 * asserts on json fields with single values
		 */
		@And("response includes the following$")
		public void response_equals(Map<String,String> responseFields){
			
			System.out.println(" Process response: ");
		}
		
		
		/**
		 * asserts on json arrays
		 */
		@And("response includes the following in any order")
		public void response_contains_in_any_order(Map<String,String> responseFields){
			System.out.println(" Process response: ");
		}
	 
	}


