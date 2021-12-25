package net.auoeke.gronk;

import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPluginExtension;

public class GronkExtension {
    private final Project project;

    public GronkExtension(Project project) {
        this.project = project;
    }

    public void javaVersion(Object version) {
        Util.extension(this.project, JavaPluginExtension.class, extension -> {
            extension.setSourceCompatibility(version);
            extension.setTargetCompatibility(version);
        });
    }
}
