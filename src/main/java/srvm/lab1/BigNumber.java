package srvm.lab1;

import java.util.ArrayList;
import java.util.Arrays;

public class BigNumber {
    long[] numbers;
    static public final int DEFAULT_SIZE = 256 / Long.SIZE;

    public BigNumber(long[] numbers) {
        this.numbers = numbers;
    }

    public BigNumber(Long[] numbers) {
        this.numbers = new long[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            this.numbers[i] = numbers[i];
        }
    }

    public BigNumber(String number, int base) {
        this(stringToLongArray(number, base));
    }

    public BigNumber(String number) {
        this(number, 16);
    }

    public BigNumber() {
        this(0, DEFAULT_SIZE);
    }

    public BigNumber(long number) {
        this(number, DEFAULT_SIZE);
    }

    public BigNumber(long number, int size) {
        if (size < 1) throw new IllegalArgumentException();
        this.numbers = new long[size];
        this.numbers[0] = number;
    }

    public int size() {
        return this.numbers.length;
    }

    @Override
    public String toString() {
        int i;
        for (i = size() - 1; i >= 0; i--) {
            if (numbers[i] != 0)
                break;
        }
        if (i == -1)
            return "0";
        String result = String.format("%X", numbers[i]);
        i--;
        if (i == -1)
            return result;
        String longFormat = String.format("%%0%dX", Long.SIZE / 16);
        for (; i >= 0; i--) {
            result += String.format(longFormat, numbers[i]);
        }
        return result;
    }

    public void fromString(String input) {
        this.fromString(input, 16);
    }

    public void fromString(String input, int base) {
        this.numbers = stringToLongArray(input, base);
    }

    static public long[] stringToLongArray(String input, int base) {
        ArrayList<Integer> availableBases = new ArrayList<Integer>(Arrays.asList(2, 8, 16));
        if (!availableBases.contains(base)) {
            throw new IllegalArgumentException("The base should be one of " + availableBases + " but " + base + " is given");
        }
        int basePow = 0;
        while (base > 1) {
            basePow++;
            base >>= 1;
        }
        base <<= basePow;
        final int BASE_SIZE = basePow * Long.SIZE / base;
        long[] result = new long[input.length() / BASE_SIZE + (input.length() % BASE_SIZE == 0 ? 0 : 1)];
        int i;
        for (i = 0; i < result.length - 1; i++) {
            result[i] = Long.parseLong(input.substring(input.length() - i * BASE_SIZE - BASE_SIZE / 2, input.length() - i * BASE_SIZE), base);
            result[i] |= (Long.parseLong(input.substring(input.length() - (i + 1) * BASE_SIZE, input.length() - i * BASE_SIZE - BASE_SIZE / 2), base)) << Long.SIZE / 2;
        }
        if (input.length() % BASE_SIZE > BASE_SIZE / 2) {
            result[i] = Long.parseLong(input.substring(BASE_SIZE / 2, input.length() % BASE_SIZE), base);
            result[i] |= Long.parseLong(input.substring(0, input.length() % BASE_SIZE / 2), base) << Long.SIZE / 2;
        } else if (input.length() % BASE_SIZE > 0) {
            result[i] = Long.parseLong(input.substring(0, input.length() % BASE_SIZE), base);
        }
        return result;
    }
}
