package srvm.lab1;

import org.jbehave.core.annotations.*;

public class BigNumberInit {
    BigNumber a;

    @Given("a big number with size $size and value $value")
    public void givenBigNumberWithSizeAndValue(@Named("value") long value, @Named("size") int size) {
        a = new BigNumber(value, size);
    }

    @Given("a big number with value $value")
    public void givenBigNumberWithValue(@Named("value") long value) {
        a = new BigNumber(value);
    }

    @When("I do nothing")
    @Alias("nothing is happened")
    public void whenNothingIsHappened() {
    }

    @Then("big number size should be $size")
    public void thenSizeShouldBe(@Named("size") int size) {
        if (a.size() != size)
            throw new RuntimeException("size is " + a.size() + ", but should be " + size);
    }

    @Then("big number should be $number")
    public void thenBigNumberShouldBe(@Named("number") String number) {
        if (!a.toString().equals(number))
            throw new RuntimeException("big number is " + a.toString() + ", but should be " + number);
    }

}
