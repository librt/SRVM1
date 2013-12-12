package srvm.lab1;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import java.util.Arrays;
import java.util.List;

public class SimpleJBehave extends JUnitStories {

    public SimpleJBehave() {
        super();
    }

    @Override
    public Configuration configuration() {
        StoryReporterBuilder storyReporterBuilder = new StoryReporterBuilder().withFormats(Format.CONSOLE, Format.HTML).withCrossReference(new CrossReference());
        Configuration configuration = new MostUsefulConfiguration();
        configuration.useStoryReporterBuilder(storyReporterBuilder);
        return configuration;
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new BigNumberInit());
    }

    @Override
    protected List<String> storyPaths() {
        return Arrays.asList("srvm/lab1/big_number_init.story");
    }


}
