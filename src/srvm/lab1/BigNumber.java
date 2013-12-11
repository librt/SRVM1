package srvm.lab1;

public class BigNumber {
    long[] numbers;
    static public final int DEFAULT_SIZE = 256 / Long.SIZE;

    public BigNumber(int size) {
        this.numbers = new long[size];
    }

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
        this(size);
        this.numbers[0] = number;
    }
}
