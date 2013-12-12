package srvm.lab1;

import org.apache.maven.plugin.MojoExecutionException;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.*;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.mojo.UnpackViewResources;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class SimpleJBehave extends JUnitStories {

    public SimpleJBehave() {
        super();
    }

    @Override
    public Configuration configuration() {
        StoryReporterBuilder storyReporterBuilder = new StoryReporterBuilder() {
            @Override
            public StoryReporter reporterFor(String storyPath, Format format) {
                switch (format) {
                    case HTML:
                        return new HtmlTemplateOutput(new File("out.html"), new Keywords());
                    default:
                        return super.reporterFor(storyPath, format);
                }
            }
        };
        storyReporterBuilder.withFormats(Format.CONSOLE, Format.HTML).withCrossReference(new CrossReference());
        Configuration configuration = new MostUsefulConfiguration();
        configuration.useStoryReporterBuilder(storyReporterBuilder);
        configuration.useViewGenerator(new FreemarkerViewGenerator());

        //configuredEmbedder().useConfiguration(configuration);

        try {
            UnpackViewResources unpackViewResources = new UnpackViewResources();
            unpackViewResources.execute();
        } catch (MojoExecutionException e) {
            e.printStackTrace();
        }
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
