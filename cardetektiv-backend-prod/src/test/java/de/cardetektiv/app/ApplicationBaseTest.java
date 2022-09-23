package de.cardetektiv.app;

import de.cardetektiv.app.config.TestContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterTest;

import java.io.File;
import java.io.IOException;

@Slf4j
@ContextConfiguration(classes = TestContextConfiguration.class)
public abstract class ApplicationBaseTest extends AbstractTestNGSpringContextTests {

    @AfterTest
    public void tearDown() throws IOException {
        System.out.println("tearDown " + new File("./").getAbsolutePath());

        /*
        Stream<String> lines = Files.lines(new File("./test-output/emailable-report.html").toPath());
        String data = lines.collect(Collectors.joining("\n"));
        lines.close();
        EmailUtils.sendEmail("dmytro.salyha@indealpro.com", "test", data);
         */
    }
}
