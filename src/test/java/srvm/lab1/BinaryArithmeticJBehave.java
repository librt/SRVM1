package srvm.lab1;

public class BinaryArithmeticJBehave extends JBehaveTest {
    @Override
    protected String[] storyPatterns() {
        return new String[]{"**/big_number_binary_arithmetic.story" };
    }

    @Override
    protected Object getStoriesRunner() {
        return new BigNumberBinaryArithmetic();
    }
}
