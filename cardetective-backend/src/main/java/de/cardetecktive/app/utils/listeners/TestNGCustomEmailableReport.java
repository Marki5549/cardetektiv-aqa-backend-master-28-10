package de.cardetecktive.app.utils.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class TestNGCustomEmailableReport implements IReporter{

    private static final Logger LOGGER = LoggerFactory.getLogger(TestNGCustomEmailableReport.class);

    private static final String ROW_TEMPLATE = "<table class=\"result\"><tr class=\"param stripe\"><td>%s</td></tr><tr><th>%s</th></tr><tr><td><div class=\"stacktrace\">\n" +
            "%s</div></td></tr></table><p class=\"totop\"></p>";

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        String reportTemplate = initReportTemplate();

        final String body = suites
                .stream()
                .flatMap(suiteToResults())
                .collect(Collectors.joining());

        saveReportTemplate(outputDirectory, reportTemplate.replace("%replace%", body));
    }

    private Function<ISuite, Stream<? extends String>> suiteToResults() {
        return suite -> suite.getResults().entrySet()
                .stream()
                .flatMap(resultsToRows(suite));
    }

    private Function<Map.Entry<String, ISuiteResult>, Stream<? extends String>> resultsToRows(ISuite suite) {
        return e -> {
            ITestContext testContext = e.getValue().getTestContext();

            Set<ITestResult> failedTests = testContext
                    .getFailedTests()
                    .getAllResults();
            Set<ITestResult> passedTests = testContext
                    .getPassedTests()
                    .getAllResults();
            Set<ITestResult> skippedTests = testContext
                    .getSkippedTests()
                    .getAllResults();

            String suiteName = suite.getName();

            return Stream
                    .of(failedTests, passedTests, skippedTests)
                    .flatMap(results -> generateReportRows(e.getKey(), suiteName, results).stream());
        };
    }

    private List<String> generateReportRows(String testName, String suiteName, Set<ITestResult> allTestResults) {
        return allTestResults.stream()
                .map(testResultToResultRow(testName, suiteName))
                .collect(toList());
    }

    private Function<ITestResult, String> testResultToResultRow(String testName, String suiteName) {
        return testResult -> {
            try {
                Throwable throwable = testResult.getThrowable();
                String exception = "";
                if (throwable != null) {
                    final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    final String utf8 = StandardCharsets.UTF_8.name();
                    try (PrintStream ps = new PrintStream(baos, true, utf8)) {
                        throwable.printStackTrace(ps);
                    }
                    exception = baos.toString(utf8);
                }

                if (testResult.getStatus() == ITestResult.FAILURE) {
                    return String.format(ROW_TEMPLATE,
                            Arrays.toString(testResult.getParameters()),
                            "Exception / " + testResult.getInstanceName() + " / " + testResult.getName(),
                            exception
                    );
                }
                return "";
            } catch (Exception ex) {
                LOGGER.error("Problem testResultToResultRow", ex);
                ex.printStackTrace();
                return "error";
            }
        };
    }

    private String initReportTemplate() {
        String template = null;
        byte[] reportTemplate;
        try {
            reportTemplate = Files.readAllBytes(Paths.get("src/test/resources/custom-emailable-report-template.html"));
            template = new String(reportTemplate, StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error("Problem initializing template", e);
        }
        return template;
    }

    private void saveReportTemplate(String outputDirectory, String reportTemplate) {
        new File(outputDirectory).mkdirs();
        try {
            LOGGER.info("Output directory:" + outputDirectory);
            System.out.println("Output directory: " + outputDirectory);
            PrintWriter reportWriter = new PrintWriter(new BufferedWriter(new FileWriter(new File(outputDirectory, "custom-emailable-report.html"))));
            reportWriter.println(reportTemplate);
            reportWriter.flush();
            reportWriter.close();
        } catch (IOException e) {
            LOGGER.error("Problem saving template", e);
        }
    }
}
