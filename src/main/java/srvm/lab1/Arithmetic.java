package srvm.lab1;

import java.util.logging.Logger;

import static srvm.lab1.BinaryArithmetic.*;

public class Arithmetic {
    static final long LAST_BIT = (long) 1 << (Long.SIZE - 1);

    public static BigNumber plus(final BigNumber a, final BigNumber b) {
        BigNumber result = new BigNumber(a);
        result.resize(1 + (a.size() > b.size() ? a.size() : b.size()));
        long firstBits, carry = 0;
        int i;
        for (i = 0; i < b.size(); i++) {
            firstBits = (result.numbers[i] & 1) + (b.numbers[i] & 1) + carry;

            result.numbers[i] >>>= 1;
            result.numbers[i] += (b.numbers[i] >>> 1) + ((firstBits & 2) >> 1);
            carry = (result.numbers[i] & LAST_BIT) >>> (Long.SIZE - 1);
            result.numbers[i] = (result.numbers[i] << 1) | (firstBits & 1);
        }
        if (carry == 1) {
            for (i = b.size(); i < result.size(); i++) {
                result.numbers[i] += 1;
                if (result.numbers[i] > 0) break;
            }
        }
        return result;
    }

    public static BigNumber minus(final BigNumber a, final BigNumber b) {
        BigNumber result = new BigNumber(b);
        result.resize(a.size() > b.size() ? a.size() : b.size());
        result = plus(inversion(result), new BigNumber(1));
        result = plus(a, result);
        result.resize(a.size() > b.size() ? a.size() : b.size());
        return result;
    }

    public static BigNumber getLowParts(final BigNumber a) {
        BigNumber result = new BigNumber(0, a.size() * 2);
        for (int i = 0; i < a.size(); i++) {
            result.numbers[2 * i] = (a.numbers[i] << (Long.SIZE / 2)) >>> (Long.SIZE / 2);
            result.numbers[2 * i + 1] = a.numbers[i] >>> (Long.SIZE / 2);
            Logger.getAnonymousLogger().info("a=" + String.format("%X", a.numbers[i])
                    + " resLow=" + String.format("%X", result.numbers[2 * i]) + " resHigh=" + String.format("%X", result.numbers[2 * i + 1]));
        }
        return result;
    }

    public static BigNumber multiplication(final BigNumber a, final BigNumber b) {
        BigNumber result = new BigNumber(0, 1);
        BigNumber multiplier = mostSignificantBit(a) > mostSignificantBit(b) ? b : a;
        BigNumber temp = mostSignificantBit(a) > mostSignificantBit(b) ? a : b;
        temp.resize(temp.size() + multiplier.size() + 1);
        for (int i = mostSignificantBit(multiplier); i >= 0; i--) {
            if (and(multiplier, new BigNumber(1)).equals(new BigNumber(1))) {
                result = plus(result, temp);
            }
            temp = shiftLeft(temp);
            multiplier = shiftRight(multiplier);
        }
        return result;
    }

    public static BigNumber division(final BigNumber a, final BigNumber b) {
        if (a.lessThan(b)) return new BigNumber(0);
        if (a.equals(b)) return new BigNumber(1);
        BigNumber dividend = new BigNumber(a);
        BigNumber divider = new BigNumber(b);
        BigNumber result = new BigNumber(0, a.size());
        divider.resize(a.size());
        Logger.getAnonymousLogger().info("divider=" + divider.toString() + ",b=" + b.toString());
        int iterations = mostSignificantBit(dividend) - mostSignificantBit(divider);
        Logger.getAnonymousLogger().info("Dividend=" + dividend.toString() + " divider=" + divider.toString());
        Logger.getAnonymousLogger().info("msb(Dividend)=" + mostSignificantBit(dividend)
                + " msb(Divider)=" + mostSignificantBit(divider));
        Logger.getAnonymousLogger().info("Iterations=" + iterations);
        divider = shiftLeft(divider, iterations);
        Logger.getAnonymousLogger().info(mostSignificantBit(dividend) + "==" + mostSignificantBit(divider));
        while (true) {
            result = shiftLeft(result);
            if (!dividend.lessThan(divider)) {
                dividend = minus(dividend, divider);
                result = or(result, new BigNumber(1));
            }
            Logger.getAnonymousLogger().info("Divider=" + divider);
            if (divider.equals(b)) break;
            divider = shiftRight(divider);
        }
        Logger.getAnonymousLogger().info("Result is " + result.toString());
        return result;
    }
}
