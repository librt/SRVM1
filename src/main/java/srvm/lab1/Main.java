package srvm.lab1;

import static srvm.lab1.BinaryArithmetic.*;
import static srvm.lab1.ModularArithmetic.powerByMod;

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
        a = new BigNumber("1FFFFFFFFFFFFFFFE");
        System.out.println(shiftRight(a, 1));
        a = new BigNumber("0FFFFFFFFFFFFFFFF");
        System.out.println(shiftLeft(a));
        System.out.println(powerByMod(new BigNumber("11D8FDB13747A7B184016450536BEFE66763ECFB3B38E749EDA9BC56364FD2D0"), new BigNumber("9B13AF897553FC9D606B864576045410ADECA1C542F4310601874E53DAB876F4"), new BigNumber("35FB52F3435C2D7BD5EAC5F9B478971B7D7D24B95A8F4C6FD974CA2A2D9211E")) + "=" + new BigNumber("22AC57D9B087FD38617AC517B56BFCA2C59F57E2084DDAA48B191C199E2BC6A"));
    }
}
