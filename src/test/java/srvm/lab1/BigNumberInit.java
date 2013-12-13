package srvm.lab1;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Pending;
import org.jbehave.core.annotations.Then;

public class BigNumberInit {
    BigNumber a;

    @Given("a big number with size $size")
    public void givenBigNumberWithSize(@Named("size") int size) {
        a = new BigNumber(size);
    }

    @Then("big number size should be $size")
    public void thenSizeShouldBe(@Named("size") int size) {
        if (a.size() != size)
            throw new RuntimeException("size is " + a.size() + ", but should be " + size);
    }

    @Then("big number should be $number")
    @Pending
    public void thenBigNumberShouldBe(@Named("number") String number) {
        if (!a.toString().equals(number))
            throw new RuntimeException("big number is " + a.toString() + ", but should be " + number);
    }

}
