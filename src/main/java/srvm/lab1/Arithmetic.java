package srvm.lab1;

public class Arithmetic {
    static final long LAST_BIT = (long) 1 << (Long.SIZE - 1);

    public static BigNumber plus(BigNumber a, BigNumber b) {
        BigNumber result = new BigNumber(a);
        result.resize(1 + (a.size() > b.size() ? a.size() : b.size()));
        long firstBits, carry = 0;
        int i;
        for (i = 0; i < b.size(); i++) {
            firstBits = (result.numbers[i] & 1) + (b.numbers[i] & 1) + carry;

            result.numbers[i] >>>= 1;
            b.numbers[i] >>>= 1;
            result.numbers[i] += b.numbers[i] + ((firstBits & 2) >> 1);
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
}
