package srvm.lab1;

import junit.framework.Assert;
import org.jbehave.core.annotations.*;

import static srvm.lab1.BinaryArithmetic.*;

public class BigNumberBinaryArithmetic {
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

    @Then("a and b is <c>")
    public void thenAnd(@Named("c") BigNumber c) {
        Assert.assertEquals(and(a, b), c);
    }

    @Then("a or b is <d>")
    public void thenOr(@Named("d") BigNumber d) {
        Assert.assertEquals(or(a, b), d);
    }

    @Then("a xor b is <e>")
    public void thenXor(@Named("e") BigNumber e) {
        Assert.assertEquals(xor(a, b), e);
    }

    @Then("swap works")
    public void swapWorks() {
        BigNumber newA = a;
        BigNumber newB = b;
        newA = xor(newA, newB);
        newB = xor(newA, newB);
        newA = xor(newA, newB);
        Assert.assertEquals(newA, b);
        Assert.assertEquals(newB, a);
    }
}