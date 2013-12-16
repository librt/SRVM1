package srvm.lab1;

import junit.framework.Assert;
import org.jbehave.core.annotations.*;

import java.util.List;

public class BigNumberInit {
    BigNumber a;

    @Given("a big number with size $size and value $value")
    public void givenBigNumberWithSizeAndValue(@Named("value") long value, @Named("size") int size) {
        a = null;
        a = new BigNumber(value, size);
    }

    @Given("a big number with value $value")
    public void givenBigNumberWithValue(@Named("value") long value) {
        a = null;
        a = new BigNumber(value);
    }

    @Given("wrong big number with size $size and value $value")
    public void givenWrongBigNumberWithSizeAndValue(@Named("value") long value, @Named("size") int size) {
        try {
            a = null;
            a = new BigNumber(value, size);
            throw new RuntimeException("Constructor should fail with size " + size + " and value " + value + " but it didn't");
        } catch (IllegalArgumentException ignored) {
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("IllegalArgumentException expected but " + e + " is thrown");
        }
    }

    @Given("a big number from array $numbers")
    public void givenBigNumberFromArray(@Named("numbers") List<Long> numbers) {
        a = null;
        Long[] arr = new Long[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            arr[i] = numbers.get(i);
        }
        a = new BigNumber(arr);
    }

    @Given("a big number from string $number")
    public void givenBigNumberFromString(@Named("number") String number) {
        a = null;
        a = new BigNumber(number);
    }

    @When("I do nothing")
    @Alias("nothing is happened")
    public void whenNothingIsHappened() {
    }

    @Then("big number size should be $size")
    public void thenSizeShouldBe(@Named("size") int size) {
        Assert.assertEquals(a.size(), size);
    }

    @Then("big number should not be initialized")
    public void thenBigNumberShouldBeNull() {
        Assert.assertNull(a);
    }

    @Then("big number should be $number")
    public void thenBigNumberShouldBe(@Named("number") String number) {
        Assert.assertEquals(a.toString(), number);
    }
}
