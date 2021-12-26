package net.auoeke.gronk;

import groovy.lang.Closure;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.api.artifacts.dsl.RepositoryHandler;
import org.gradle.api.artifacts.repositories.MavenArtifactRepository;

@SuppressWarnings("unused")
public class MavenRepositoryExtension extends Closure<Void> {
    private final Project project;

    public MavenRepositoryExtension(Project project, RepositoryHandler owner) {
        super(owner);

        this.project = project;
    }

    public MavenArtifactRepository doCall(Object url, Action<MavenArtifactRepository> configure) {
        return this.repositories().maven(repository -> {
            var url1 = url;

            if (url1 instanceof String string) {
                try {
                    url1 = new URL(string);
                } catch (MalformedURLException __) {
                    url1 = "https://" + string;
                }
            }

            repository.setUrl(url1);
            repository.setName(url1.toString());
            this.project.configure(List.of(repository), configure);

            if (repository.getName().equals("maven")) {
                // Panic because further attempts to access this extension will fail.
                throw new IllegalArgumentException("\"maven\" is a bad repository name.");
            }
        });
    }

    public MavenArtifactRepository doCall(Object url) {
        return this.doCall(url, repository -> {});
    }

    private RepositoryHandler repositories() {
        return (RepositoryHandler) this.getOwner();
    }
}
