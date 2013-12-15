package srvm.lab1;

public class BigNumber {
    long[] numbers;
    static public final int DEFAULT_SIZE = 256 / (Long.SIZE * 8);

    public BigNumber(long[] numbers) {
        this.numbers = numbers;
    }

    public BigNumber() {
        this(DEFAULT_SIZE);
    }

    public BigNumber(long number) {
        this(number, DEFAULT_SIZE);
    }

    public BigNumber(long number, int size) {
        this.numbers = new long[size];
        this.numbers[0] = number;
    }

    public int size() {
        return this.numbers.length;
    }

    @Override
    public String toString() {
        int i = size() - 1;
        for (; i >= 0; i--) {
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
