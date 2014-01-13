package srvm.lab1;

import java.util.ArrayList;
import java.util.Arrays;

public class BigNumber {
    long[] numbers;
    static public final int DEFAULT_SIZE = 256 / Long.SIZE;

    public BigNumber(long[] numbers) {
        if (numbers.length < 1) throw new IllegalArgumentException();
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

    public BigNumber(BigNumber bigNumber) {
        this.numbers = new long[bigNumber.size()];
        System.arraycopy(bigNumber.numbers, 0, this.numbers, 0, size());
    }

    @Override
    protected Object clone() {
        long[] array = new long[size()];
        System.arraycopy(numbers, 0, array, 0, size());
        return new BigNumber(array);
    }

    public void setNull() {
        for (int i = 0; i < size(); i++)
            numbers[i] = 0;
    }

    public void resize(int size) {
        if (size < 1) throw new IllegalArgumentException();
        if (size == this.size()) return;
        long array[] = new long[size];
        System.arraycopy(this.numbers, 0, array, 0, this.size() < size ? this.size() : size);
        this.numbers = array;
    }

    public void crop() {
        int i;
        for (i = this.numbers.length - 1; i >= 0; i--)
            if (this.numbers[i] != 0) break;
        this.resize(i + 1);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
            int i;
            int minSize = ((BigNumber) obj).size() < this.size() ? ((BigNumber) obj).size() : this.size();
            int maxSize = ((BigNumber) obj).size() > this.size() ? ((BigNumber) obj).size() : this.size();
            for (i = 0; i < minSize; i++) {
                if (this.numbers[i] != ((BigNumber) obj).numbers[i])
                    return false;
            }
            if (((BigNumber) obj).size() > this.size()) {
                for (; i < maxSize; i++)
                    if (((BigNumber) obj).numbers[i] != 0)
                        return false;
            } else {
                for (; i < maxSize; i++)
                    if (this.numbers[i] != 0)
                        return false;
            }
            return true;
        } else if (obj.getClass() == Long.class || obj.getClass() == Integer.class) {
            if (numbers[0] != (Long) obj) return false;
            for (int i = 1; i < size(); i++) {
                if (numbers[i] != 0) return false;
            }
            return true;
        } else {
            return super.equals(obj);
        }
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
        String longFormat = String.format("%%0%dX", Long.SIZE / 4);
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
        if (input.contains("-")) {
            throw new IllegalArgumentException("The number should be positive or 0, but '-' found");
        }
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
            result[i] |= (Long.parseLong(input.substring(input.length() - (i + 1) * BASE_SIZE, input.length() - i * BASE_SIZE - BASE_SIZE / 2), base)) << (Long.SIZE / 2);
        }
        if (input.length() % BASE_SIZE > BASE_SIZE / 2) {
            int right = input.length() % BASE_SIZE;
            int left = right - BASE_SIZE / 2;
            result[i] = Long.parseLong(input.substring(left, right), base);
            result[i] |= Long.parseLong(input.substring(0, left), base) << (Long.SIZE / 2);
        } else if (input.length() % BASE_SIZE > 0) {
            result[i] = Long.parseLong(input.substring(0, input.length() % BASE_SIZE), base);
        } else {
            result[i] = Long.parseLong(input.substring(input.length() - i * BASE_SIZE - BASE_SIZE / 2, input.length() - i * BASE_SIZE), base);
            result[i] |= (Long.parseLong(input.substring(input.length() - (i + 1) * BASE_SIZE, input.length() - i * BASE_SIZE - BASE_SIZE / 2), base)) << (Long.SIZE / 2);
        }
        return result;
    }

    public boolean biggerThan(final BigNumber a) {
        int i;
        if (size() > a.size()) {
            for (i = size() - 1; i >= a.size(); i--) {
                if (numbers[i] != 0) return true;
            }
        } else {
            for (i = a.size() - 1; i >= size(); i--) {
                if (a.numbers[i] != 0) return false;
            }
        }
        for (; i >= 0; i--) {
            if (numbers[i] < 0 && a.numbers[i] >= 0) return true;
            else if (numbers[i] >= 0 && a.numbers[i] < 0) return false;
            else if (numbers[i] != a.numbers[i]) return numbers[i] > a.numbers[i];
        }
        return false;
    }

    public boolean lessThan(BigNumber a) {
        return !(this.equals(a) || this.biggerThan(a));
    }
}
