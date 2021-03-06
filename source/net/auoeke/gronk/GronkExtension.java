package net.auoeke.gronk;

import java.util.HashSet;
import org.gradle.api.Project;

public class GronkExtension {
    public String fallbackVersion = "+";

    private final Project project;

    public GronkExtension(Project project) {
        this.project = project;
    }

    public void fallbackVersion(String version) {
        this.fallbackVersion = version;
    }

    public void javaVersion(Object version) {
        Util.javaExtension(this.project, extension -> {
            extension.setSourceCompatibility(version);
            extension.setTargetCompatibility(version);
        });
    }

    public void uncheck() {
        this.project.afterEvaluate(project -> Util.javaExtension(project, java -> {
            var names = new HashSet<>();
            project.getConfigurations().all(configuration -> names.add(configuration.getName()));
            project.getRepositories().mavenCentral();

            java.getSourceSets().all(set -> {
                if (names.contains(set.getAnnotationProcessorConfigurationName())) {
                    Util.repository(project, "https://maven.auoeke.net");
                    project.getDependencies().add(set.getAnnotationProcessorConfigurationName(), "net.auoeke:uncheck");
                }
            });
        }));
    }
}
