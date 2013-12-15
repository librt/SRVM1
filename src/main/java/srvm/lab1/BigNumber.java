package srvm.lab1;

import java.util.logging.Logger;

public class BigNumber {
    long[] numbers;
    static public final int DEFAULT_SIZE = 256 / Long.SIZE;

    public BigNumber(long[] numbers) {
        this.numbers = numbers;
    }

    public BigNumber() {
        this(0, DEFAULT_SIZE);
    }

    public BigNumber(long number) {
        this(number, DEFAULT_SIZE);
    }

    public BigNumber(long number, int size) {
        Logger.getAnonymousLogger().info("number " + number + "; size " + size);
        this.numbers = new long[size];
        Logger.getAnonymousLogger().info("success...");
        this.numbers[0] = number;
        Logger.getAnonymousLogger().info("success!");
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
        String longFormat = String.format("%%0%dX", Long.SIZE);
        for (; i >= 0; i--) {
            result += String.format(longFormat, numbers[i]);
        }
        return result;
    }
}
