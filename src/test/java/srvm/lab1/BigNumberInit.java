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
            throw new RuntimeException("Constructor should fail with size " + size + " and value " + value + ", but it didn't");
        } catch (IllegalArgumentException ignored) {
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("IllegalArgumentException expected but " + e + " is thrown");
        }
    }

    @Given("wrong big number from string <string>")
    public void givenWrongBigNumberFromString(@Named("string") String value) {
        try {
            a = null;
            a = new BigNumber(value);
            throw new RuntimeException("Constructor should fail with string " + value + ", but it didn't");
        } catch (IllegalArgumentException ignored) {
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("IllegalArgumentException expected but " + e + " is thrown");
        }
    }

    @Given("a big number from array $array")
    @Alias("a big number from array <array>")
    public void givenBigNumberFromArray(@Named("array") List<Long> numbers) {
        a = null;
        Long[] arr = new Long[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            arr[i] = numbers.get(i);
        }
        a = new BigNumber(arr);
    }

    @Given("a big number from string $string")
    @Alias("a big number from string <string>")
    public void givenBigNumberFromString(@Named("string") String number) {
        a = null;
        a = new BigNumber(number);
    }

    @When("I do nothing")
    @Alias("nothing is happened")
    public void whenNothingIsHappened() {
    }

    @Then("big number size should be $size")
    @Alias("big number size should be <size>")
    public void thenSizeShouldBe(@Named("size") int size) {
        Assert.assertEquals(size, a.size());
    }

    @Then(value = "big number size should be default", priority = 1)
    @Composite(steps = {"Then big number size should be " + BigNumber.DEFAULT_SIZE})
    public void thenSizeShouldBe() {
    }

    @Then("big number should not be initialized")
    public void thenBigNumberShouldBeNull() {
        Assert.assertNull(a);
    }

    @Then("big number should be $number")
    public void thenBigNumberShouldBe(@Named("number") String number) {
        Assert.assertEquals(number, a.toString());
    }
}
