package srvm.lab1;

import static srvm.lab1.BinaryArithmetic.*;

public class Main {

    public static void main(String[] args) {
        BigNumber a = new BigNumber(1);
        System.out.println("1 = " + a.toString());
        BigNumber b = shiftLeft(a, 1);
        System.out.println("1 << 1 = " + b.toString());
        a = shiftRight(b);
        System.out.println("2 >> 1 = " + a.toString());
        b = or(a, b);
        System.out.println("2 | 1 = " + b.toString());
        BigNumber c = and(a, b);
        System.out.println("1 & 3 = " + c.toString());
        b = new BigNumber(2);
        System.out.println("2 = " + b.toString());
        a = xor(c, b);
        System.out.println("1 ^ 2 = " + a.toString());
        c = xor(a, b);
        System.out.println("3 ^ 2 = " + c.toString());
        a = shiftLeft(c, Long.SIZE);
        System.out.println("1 << " + Long.SIZE + " = " + a.toString());
        b = or(a, c);
        System.out.println("10000000000000000 | 1 = " + b.toString());
        b = shiftLeft(c, 4);
        System.out.println("1 << 4 = " + b.toString());
        a = new BigNumber(1);
        System.out.println(shiftLeft(a, 64) + " " + shiftLeft(a, 65) + " " + shiftLeft(a, 128));
        System.out.println(shiftRight(shiftLeft(a, 64), 64) + " " + shiftRight(shiftLeft(a, 65), 65) + " " + shiftRight(shiftLeft(a, 128), 128));
    }
}
