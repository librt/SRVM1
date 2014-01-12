package srvm.lab1;

public class ArithmeticJBehave extends JBehaveTest {
    @Override
    protected String[] storyPatterns() {
        return new String[]{"**/big_number_arithmetic.story" };
    }

    @Override
    protected Object getStoriesRunner() {
        return new BigNumberArithmetic();
    }
}