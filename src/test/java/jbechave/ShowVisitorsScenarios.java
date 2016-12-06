package jbechave;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromRelativeFile;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Vitalii on 20.11.2016.
 */
public class ShowVisitorsScenarios extends JUnitStory {
    @Override
    public Configuration configuration() {
        URL storyURL = null;
        try {
            // This requires you to start Maven from the project directory
            storyURL = new URL("file://" + System.getProperty("user.dir")
                    + "/src/main/resources/stories/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return new MostUsefulConfiguration().useStoryLoader(
                new LoadFromRelativeFile(storyURL)).useStoryReporterBuilder(
                new StoryReporterBuilder().withDefaultFormats().withFormats(StoryReporterBuilder.Format.CONSOLE));
    }

    @Override
    public List<CandidateSteps> candidateSteps() {
        return new InstanceStepsFactory(configuration(), new ShowVisitorsSteps())
                .createCandidateSteps();
    }

    @Override

    @Test
    public void run() {
        try {
            super.run();
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
