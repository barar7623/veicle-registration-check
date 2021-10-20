package stepdefinitions;

import com.factory.DriverFactory;
import com.pages.ReadRegistrationNumber;
import com.pages.VehicleCheckPage;
import com.pages.VehicleDetailsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class vehicleSearchSteps {

  private VehicleCheckPage vehicleCheckPage = new VehicleCheckPage(DriverFactory.getDriver());
  private VehicleDetailsPage vehicleDetailsPage = new VehicleDetailsPage(DriverFactory.getDriver());
  private ReadRegistrationNumber readRegistrationNumber =
      new ReadRegistrationNumber(DriverFactory.getDriver());

  @Given("user is on car check application page")
  public void user_is_on_car_check_application_page() {
    DriverFactory.getDriver().get("https://cartaxcheck.co.uk/");
  }

  @When("user gets the title of the page")
  public void user_gets_the_title_of_the_page() throws Exception {
    vehicleCheckPage.getVehiclePageTitle();
  }

  @Then("page title should be {string}")
  public void page_title_should_be(String expectedTitleName){
    String title = vehicleCheckPage.getVehiclePageTitle();
    System.out.println("Vehicle page Title is:" + title);
    Assert.assertTrue(title.contains(expectedTitleName));
  }


  @Given("user navigated to carCheck application")
  public void user_navigated_to_car_check_application() {
    DriverFactory.getDriver().get("https://cartaxcheck.co.uk/");
  }

  @When("user gets Vehicle Registration number from input text file")
  public void user_gets_vehicle_registration_number_from_input_text_file() throws Exception {
    readRegistrationNumber.extractInputRegistrationNumberAndVerifyRegistrationNumber();
  }

  @When("user clicks on Get a Full Check button")
  public void user_clicks_on_get_a_full_check_button() {
    vehicleCheckPage.clickOnGetAFullCheck();
    vehicleCheckPage.clickOnVehicleCheck();
  }

  @Then("User should able see car details")
  public void user_should_able_see_car_details()  {
    // vehicleDetailsPage.vehicleDetails();
  }

  @When("Vehicle details read from input text file")
  public void vehicle_details_read_from_input_text_file() {}


}
