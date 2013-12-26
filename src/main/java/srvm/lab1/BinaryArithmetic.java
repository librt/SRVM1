package srvm.lab1;

public class BinaryArithmetic {
    public static BigNumber and(BigNumber a, BigNumber b) {
        BigNumber result = new BigNumber(a);
        for (int i = 0; i < result.size(); i++) {
            result.numbers[i] &= b.numbers[i];
        }
        return result;
    }

    public static BigNumber or(BigNumber a, BigNumber b) {
        BigNumber result = new BigNumber(a);
        for (int i = 0; i < result.size(); i++) {
            result.numbers[i] |= b.numbers[i];
        }
        return result;
    }

    public static BigNumber xor(BigNumber a, BigNumber b) {
        BigNumber result = new BigNumber(a);
        for (int i = 0; i < result.size(); i++) {
            result.numbers[i] ^= b.numbers[i];
        }
        return result;
    }

    public static BigNumber shiftLeft(BigNumber a) {
        BigNumber result = new BigNumber(a);
        result.numbers[0] <<= 1;
        for (int i = 1; i < a.size(); i++) {
            result.numbers[i] = a.numbers[i] << 1 | (a.numbers[i - 1] >> (Long.SIZE - 1));
        }
        return result;
    }

    public static BigNumber shiftLeft(BigNumber a, int shift) {
        BigNumber result = new BigNumber(a);
        int bigShifts = shift / Long.SIZE;
        int shortShifts = shift % Long.SIZE;
        if (bigShifts > 0) {
            for (int i = 0; i < bigShifts; i++) {
                result.numbers[i] = 0;
            }
            System.arraycopy(a.numbers, 0, result.numbers, bigShifts, result.size() - bigShifts);
        }
        if (shortShifts > 0) {
            result.numbers[0] <<= shortShifts;
            for (int i = 1; i < result.size(); i++) {
                result.numbers[i] = (a.numbers[i] << shortShifts) | (a.numbers[i - 1] >> (Long.SIZE - shortShifts));
            }
        }
        return result;
    }

    public static BigNumber shiftRight(BigNumber a) {
        BigNumber result = new BigNumber(a);
        result.numbers[result.size() - 1] >>= 1;
        for (int i = a.size() - 2; i >= 0; i--) {
            result.numbers[i] = a.numbers[i] >> 1 | (a.numbers[i + 1] & 1);
        }
        return result;
    }

    public static BigNumber shiftRight(BigNumber a, int shift) {
        BigNumber result = new BigNumber(a);
        int bigShifts = shift / Long.SIZE;
        int shortShifts = shift % Long.SIZE;
        if (bigShifts > 0) {
            for (int i = 0; i < bigShifts; i++) {
                result.numbers[result.size() - i] = 0;
            }
            System.arraycopy(a.numbers, 0, result.numbers, bigShifts, result.size() - bigShifts);
        }
        if (shortShifts > 0) {
            result.numbers[result.size() - 1] >>= shortShifts;
            for (int i = 0; i < result.size() - 1; i++) {
                result.numbers[i] = (a.numbers[i] >> shortShifts) | ((a.numbers[i + 1] << (Long.SIZE - shortShifts)) >> (Long.SIZE - shortShifts));
            }
        }
        return result;
    }
}
