package featureSteps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.when;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class testStep {
	

	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request=RestAssured.given();
    private String ENDPOINT_GET_BOOK_BY_ISBN = "https://www.googleapis.com/books/v1/volumes";
 
	@Given("^a book exists with an isbn of (\\d+)$")
	public void a_book_exists_with_an_isbn_of(String isbn) throws Throwable {
		System.out.println(isbn);
		//RequestSpecification request = RestAssured.given();
		request.param("q", "isbn:" + isbn);
	   
	}

	@When("^a user retrieves the book by isbn$")
	public void a_user_retrieves_the_book_by_isbn() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		response = request.when().get(ENDPOINT_GET_BOOK_BY_ISBN);
		System.out.println("response: " + response.prettyPrint());
	}

	@Then("^the status code is (\\d+)$")
	public void the_status_code_is(int statusCode) throws Throwable {
		json = response.then().statusCode(statusCode);
		int responseCode = response.getStatusCode();
		 System.out.println("response: done "+responseCode);
		Assert.assertEquals("got status code 200",
				statusCode, responseCode);
	  
	}
	
	@And("response includes the following$")
	public void response_equals(Map<String,String> responseFields){
		//System.out.println(""+responseFields.get("totalItems"));
		
		 for (Map.Entry<String, String> field : responseFields.entrySet()) {
		  if(StringUtils.isNumeric(field.getValue())){ 
			  json.body(field.getKey(),
		  equalTo(Integer.parseInt(field.getValue()))); } 
		  else{
		 json.body(field.getKey(), equalTo(field.getValue()));
		 } 
		  }
		 
	}




}
