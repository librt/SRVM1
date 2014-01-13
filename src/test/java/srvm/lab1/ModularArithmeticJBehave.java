package srvm.lab1;

public class ModularArithmeticJBehave extends JBehaveTest {
    @Override
    protected String[] storyPatterns() {
        return new String[]{"**/big_number_modular_arithmetic.story"};
    }

    @Override
    protected Object getStoriesRunner() {
        return new BigNumberModularArithmetic();
    }
}
