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

    @Then("<a> and <b> is <c>")
    public void thenAnd(@Named("a") BigNumber a, @Named("b") BigNumber b, @Named("c") BigNumber c) {
        Assert.assertEquals(and(a, b), c);
    }

    @Then("<a> or <b> is <c>")
    public void thenOr(@Named("a") BigNumber a, @Named("b") BigNumber b, @Named("c") BigNumber c) {
        Assert.assertEquals(or(a, b), c);
    }

    @Then("<a> xor <b> is <c>")
    public void thenXor(@Named("a") BigNumber a, @Named("b") BigNumber b, @Named("c") BigNumber c) {
        Assert.assertEquals(xor(a, b), c);
    }
}