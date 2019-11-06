package com.neotys.ascode.swagger.client.datamodel;

import java.util.Optional;

public class NeoLoadDescriptionData {

    private String projectName;
    private Optional<String> version;
    private Optional<String> revision;
    private Optional<String> testplan;
    private Optional<String> testEnvironment;

    public NeoLoadDescriptionData(String projectName, Optional<String> version, Optional<String> revision, Optional<String> testplan, Optional<String> testEnvironment) {
        this.projectName = projectName;
        this.version = version;
        this.revision = revision;
        this.testplan = testplan;
        this.testEnvironment = testEnvironment;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Optional<String> getVersion() {
        return version;
    }

    public void setVersion(Optional<String> version) {
        this.version = version;
    }

    public Optional<String> getRevision() {
        return revision;
    }

    public void setRevision(Optional<String> revision) {
        this.revision = revision;
    }

    public Optional<String> getTestplan() {
        return testplan;
    }

    public void setTestplan(Optional<String> testplan) {
        this.testplan = testplan;
    }

    public Optional<String> getTestEnvironment() {
        return testEnvironment;
    }

    public void setTestEnvironment(Optional<String> testEnvironment) {
        this.testEnvironment = testEnvironment;
    }
}
