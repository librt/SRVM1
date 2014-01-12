package srvm.lab1;

import java.util.logging.Logger;

public class BinaryArithmetic {
    public static BigNumber and(final BigNumber a, final BigNumber b) {
        BigNumber result = new BigNumber(a);
        result.resize(a.size() > b.size() ? a.size() : b.size());
        int i;
        for (i = 0; i < b.size(); i++) {
            result.numbers[i] &= b.numbers[i];
        }
        for (; i < result.size(); i++) {
            result.numbers[i] = 0;
        }
        return result;
    }

    public static BigNumber or(final BigNumber a, final BigNumber b) {
        BigNumber result = new BigNumber(a);
        result.resize(a.size() > b.size() ? a.size() : b.size());
        int i;
        for (i = 0; i < b.size(); i++) {
            result.numbers[i] |= b.numbers[i];
        }
        return result;
    }

    public static BigNumber xor(final BigNumber a, final BigNumber b) {
        BigNumber result = new BigNumber(a);
        result.resize(a.size() > b.size() ? a.size() : b.size());
        int i;
        for (i = 0; i < b.size(); i++) {
            result.numbers[i] ^= b.numbers[i];
        }
        return result;
    }

    public static BigNumber shiftLeft(final BigNumber a) {
        BigNumber result = new BigNumber(a);
        Logger.getAnonymousLogger().info(result.toString());
        result.numbers[0] <<= 1;
        for (int i = 1; i < a.size(); i++) {
            Logger.getAnonymousLogger().info("result[" + i + "]=" + result.numbers[i]);
            result.numbers[i] = (a.numbers[i] << 1) | (a.numbers[i - 1] >>> (Long.SIZE - 1));
        }
        return result;
    }

    public static BigNumber shiftLeft(final BigNumber a, int shift) {
        int bigShifts = shift / Long.SIZE;
        if (bigShifts >= a.size()) {
            return new BigNumber(0, a.size());
        }
        int shortShifts = shift % Long.SIZE;
        BigNumber result = new BigNumber(a);
        //result.resize(result.size()+bigShifts);
        if (bigShifts > 0) {
            for (int i = 0; i < bigShifts; i++) {
                result.numbers[i] = 0;
            }
            System.arraycopy(a.numbers, 0, result.numbers, bigShifts, result.size() - bigShifts);
        }
        if (shortShifts > 0) {
            result.numbers[bigShifts] <<= shortShifts;
            for (int i = 1; i < result.size() - bigShifts; i++) {
                result.numbers[i + bigShifts] = (a.numbers[i] << shortShifts) | (a.numbers[i - 1] >>> (Long.SIZE - shortShifts));
            }
        }
        return result;
    }

    public static BigNumber shiftRight(final BigNumber a) {
        BigNumber result = new BigNumber(a);
        result.numbers[result.size() - 1] >>>= 1;
        for (int i = a.size() - 2; i >= 0; i--) {
            result.numbers[i] = a.numbers[i] >>> 1 | (a.numbers[i + 1] & 1);
        }
        return result;
    }

    public static BigNumber shiftRight(final BigNumber a, int shift) {
        int bigShifts = shift / Long.SIZE;
        if (bigShifts >= a.size()) {
            return new BigNumber(0, a.size());
        }
        BigNumber result = new BigNumber(a);
        int shortShifts = shift % Long.SIZE;
        if (bigShifts > 0) {
            for (int i = 1; i <= bigShifts; i++) {
                result.numbers[result.size() - i] = 0;
            }
            System.arraycopy(a.numbers, bigShifts, result.numbers, 0, result.size() - bigShifts);
        }
        if (shortShifts > 0) {
            result.numbers[result.size() - 1 - bigShifts] >>>= shortShifts;
            for (int i = 0; i < result.size() - 1 - bigShifts; i++) {
                result.numbers[i] = (a.numbers[i + bigShifts] >>> shortShifts) | ((a.numbers[i + 1 + bigShifts] << (Long.SIZE - shortShifts)) >>> (Long.SIZE - shortShifts));
            }
        }
        return result;
    }

    public static BigNumber inversion(final BigNumber a) {
        BigNumber result = new BigNumber(0, a.size());
        for (int i = 0; i < result.size(); i++) {
            result.numbers[i] = ~a.numbers[i];
        }
        return result;
    }

    public static long mostSignificantBit(final BigNumber a) {
        int i;
        for (i = a.size() - 1; i >= 0; i--) {
            if (a.numbers[i] != 0) break;
        }
        if (i == -1) return 0;
        long currentWord = a.numbers[i];
        for (int j = 0; j < Long.SIZE; j++) {
            if (currentWord == 1) return j;
            currentWord >>>= 1;
        }
        return 0;
    }
}
