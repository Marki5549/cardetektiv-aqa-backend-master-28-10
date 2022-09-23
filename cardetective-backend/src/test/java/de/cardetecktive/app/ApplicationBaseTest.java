package de.cardetecktive.app;

import de.cardetecktive.app.config.TestContextConfiguration;
import de.cardetecktive.app.steps.AuthenticationSteps;
import de.cardetecktive.app.utils.EmailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@ContextConfiguration(classes = TestContextConfiguration.class)
public abstract class ApplicationBaseTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private AuthenticationSteps authenticationSteps;

    @Value("${system.username}")
    private String userName;

    @BeforeMethod
    public void setUp(ITestContext context) {
        int removed = authenticationSteps.removeUserSession(userName);
        log.info("Default user session was removed with result: {}", removed);
    }

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
