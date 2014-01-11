package srvm.lab1;

import junit.framework.Assert;
import org.jbehave.core.annotations.*;

import static srvm.lab1.Arithmetic.plus;

public class BigNumberArithmetic {
    BigNumber a, b;

    @BeforeScenario
    public void beforeEveryScenario() {
        a = null;
        b = null;
    }

    @AfterScenario
    public void afterEveryScenario() {
        a = null;
        b = null;
    }

    @Given("big numbers <a> and <b>")
    public void givenBigNumbers(@Named("a") BigNumber a, @Named("b") BigNumber b) {
        this.a = a;
        this.b = b;
    }

    @Then("sum is <result>")
    public void thenSum(@Named("result") BigNumber result) {
        Assert.assertEquals(result, plus(a, b));
    }
}
