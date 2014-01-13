package srvm.lab1;

import junit.framework.Assert;
import org.jbehave.core.annotations.*;

import static srvm.lab1.Arithmetic.*;

public class BigNumberArithmetic {
    BigNumber a, b, c;

    @BeforeScenario
    public void beforeEveryScenario() {
        a = null;
        b = null;
        c = null;
    }

    @AfterScenario
    public void afterEveryScenario() {
        a = null;
        b = null;
        c = null;
    }

    @Given("big numbers <a>, <b> and <c>")
    public void givenBigNumbers(@Named("a") BigNumber a, @Named("b") BigNumber b, @Named("c") BigNumber c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Then("a plus b is c")
    public void thenSum() {
        Assert.assertEquals(c, plus(a, b));
    }

    @Then("c minus a is b")
    public void thenMinusA() {
        Assert.assertEquals(b, minus(c, a));
    }

    @Then("c minus b is a")
    public void thenMinusB() {
        Assert.assertEquals(a, minus(c, b));
    }

    @Then("a multiplied by b is c")
    public void thenMultiplication() {
        Assert.assertEquals(c, multiplication(a, b));
    }

    @Then("c divided by b is a")
    public void thenDivisionByB() {
        Assert.assertEquals(a, division(c, b));
    }

    @Then("c divided by a is b")
    public void thenDivisionByA() {
        Assert.assertEquals(b, division(c, a));
    }
}
