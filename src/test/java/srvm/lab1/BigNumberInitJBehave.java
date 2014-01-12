package srvm.lab1;

public class BigNumberInitJBehave extends JBehaveTest {
    @Override
    protected String[] storyPatterns() {
        return new String[]{"**/blank_big_number_init.story", "**/big_number_init.story" };
    }

    @Override
    protected Object getStoriesRunner() {
        return new BigNumberInit();
    }
}
