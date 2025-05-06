package steps;

import com.eurotechstudy.Calculator;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CalculatorSteps {

    private Calculator calculator;
    private double factResult;

    @Then("the result should be {double}")
    public void theResultShouldBe(double result) {
        Assert.assertEquals(result, factResult, 0.0);
    }

    @Given("I opened a calculator")
    public void i_opened_a_calculator() {
        calculator = new Calculator();
    }

    @When("I add {int} and {int}")
    public void iAddAnd(int num1, int num2) {
        factResult = calculator.add(num1, num2);
    }

    @When("I multiply {double} and {double}")
    public void iMultiplyAnd(double num1, double num2) {
        factResult = calculator.multiply(num1, num2);
    }

    @And("print string {string}")
    public void printString(String value) {

        System.out.println(value);

    }


    @When("from {int} I substract {int}")
    public void fromISubstract(int num1, int num2) {

        factResult = calculator.subtract(num1, num2);

    }

    @When("I divide {double} by {double}")
    public void iDivideBy(double num1, double num2) {
        factResult = calculator.divide(num1, num2);
    }

    @Then("the result should be NaN")
    public void the_result_should_be_na_n() {
        Assert.assertTrue(Double.isNaN(factResult));
    }
}
