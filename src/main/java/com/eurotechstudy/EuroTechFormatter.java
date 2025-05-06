package com.eurotechstudy;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.Plugin;
import io.cucumber.plugin.event.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EuroTechFormatter implements Plugin, EventListener {

    private final String reportDir;
    PrintWriter csvWriter;
    private String currentScenario = "";
    private String currentFeature = "";

    public EuroTechFormatter() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = LocalDateTime.now().format(dateTimeFormatter);
        this.reportDir = "target/cucumber-reports/" + timestamp;
        new File(reportDir).mkdir();
    }

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        try {
            csvWriter = new PrintWriter(new FileWriter(reportDir + "/report.csv"));

            csvWriter.println("Фича,Сценарий,Шаг,Статус,Ошибка");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        eventPublisher.registerHandlerFor(TestSourceRead.class, this::handleFeature);
        eventPublisher.registerHandlerFor(TestCaseStarted.class, this::handleScenario);
        eventPublisher.registerHandlerFor(TestStepFinished.class, this::handleStepFinished);
        eventPublisher.registerHandlerFor(TestRunFinished.class, event -> {
            csvWriter.close();
        });
    }

    private void handleStepFinished(TestStepFinished testStepFinished) {
        if (!(testStepFinished.getTestStep() instanceof PickleStepTestStep)) return;

        PickleStepTestStep step = (PickleStepTestStep) testStepFinished.getTestStep();
        String stepText = step.getStep().getKeyword() + step.getStep().getText();
        String status = testStepFinished.getResult().getStatus().name();
        String error = testStepFinished.getResult().getError() != null
                ? testStepFinished.getResult().getError().getMessage().replaceAll("[\\r\\n]+", " ")
                : "";
        csvWriter.printf("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"%n",
                currentFeature, currentScenario, stepText, status.equals("PASSED") ? "ПРОЙДЕН" : "ОШИБКА", error);

    }

    private void handleScenario(TestCaseStarted testCaseStarted) {
        currentScenario = testCaseStarted.getTestCase().getName();
    }

    private void handleFeature(TestSourceRead testSourceRead) {
        String uri = testSourceRead.getUri().toString();
        currentFeature = uri.substring(uri.lastIndexOf("/") + 1).replace(".feature", "");
    }
}
