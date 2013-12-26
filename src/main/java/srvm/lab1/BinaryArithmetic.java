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
        // TODO: доделать
        BigNumber result = new BigNumber(a);
        int bigShifts = shift / Long.SIZE;
        int shortShifts = shift % Long.SIZE;
        if (bigShifts > 0) {
            for (int i = 0; i < bigShifts; i++) {

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
}
