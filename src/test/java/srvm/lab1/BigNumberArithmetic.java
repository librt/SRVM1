package srvm.lab1;

import junit.framework.Assert;
import org.jbehave.core.annotations.*;

import java.util.logging.Logger;

import static srvm.lab1.Arithmetic.minus;
import static srvm.lab1.Arithmetic.plus;

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
        Logger.getAnonymousLogger().info("b=" + b.toString());
        Assert.assertEquals(c, plus(a, b));
        Logger.getAnonymousLogger().info("b=" + b.toString());
    }

    @Then("c minus a is b")
    public void thenMinusA() {
        Logger.getAnonymousLogger().info("b=" + b.toString() + " minus=" + minus(c, a).toString());
        Assert.assertEquals(b, minus(c, a));
    }

    @Then("c minus b is a")
    public void thenMinusB() {
        Assert.assertEquals(a, minus(c, b));
    }
}