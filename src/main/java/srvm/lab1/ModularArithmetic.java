package srvm.lab1;

import static srvm.lab1.Arithmetic.mod;
import static srvm.lab1.Arithmetic.multiplication;
import static srvm.lab1.BinaryArithmetic.and;
import static srvm.lab1.BinaryArithmetic.shiftRight;

public class ModularArithmetic {
    public static BigNumber modMultiplication(BigNumber a, BigNumber b, BigNumber n) {
        return mod(multiplication(a, b), n);
    }

    public static BigNumber modSquare(BigNumber a, BigNumber n) {
        return modMultiplication(a, a, n);
    }

    public static BigNumber powerByMod(BigNumber x, BigNumber p, BigNumber n) {
        BigNumber result = new BigNumber(1);
        BigNumber power = new BigNumber(p);
        BigNumber multiplier = mod(x, n);
        BigNumber one = new BigNumber(1);
        while (!power.equals(new Long(0))) {
            if (and(power, new BigNumber(1)).equals(one)) {
                result = modMultiplication(result, multiplier, n);
            }
            if (result.equals(one) || multiplier.equals(one) || result.equals(new Long(0))) break;
            multiplier = modSquare(multiplier, n);
            power = shiftRight(power);
        }
        return result;
    }
}
