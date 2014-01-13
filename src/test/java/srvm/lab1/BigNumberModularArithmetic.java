package srvm.lab1;

import junit.framework.Assert;
import org.jbehave.core.annotations.*;

import static srvm.lab1.ModularArithmetic.powerByMod;

public class BigNumberModularArithmetic {
    BigNumber x, p, n;

    @BeforeScenario
    public void beforeEveryScenario() {
        x = null;
        p = null;
        n = null;
    }

    @AfterScenario
    public void afterEveryScenario() {
        x = null;
        p = null;
        n = null;
    }

    @Given("big numbers <x>, <p> and <n>")
    public void givenBigNumbers(@Named("x") BigNumber x, @Named("p") BigNumber p, @Named("n") BigNumber n) {
        this.x = x;
        this.p = p;
        this.n = n;
    }

    @Then("x pow p mod n is <result>")
    public void powIs(@Named("result") BigNumber result) {
        Assert.assertEquals(result, powerByMod(x, p, n));
    }
}