package cc.worldmandia

import io.papermc.paper.plugin.loader.PluginClasspathBuilder
import io.papermc.paper.plugin.loader.PluginLoader
import io.papermc.paper.plugin.loader.library.impl.MavenLibraryResolver
import org.eclipse.aether.artifact.DefaultArtifact
import org.eclipse.aether.graph.Dependency
import org.eclipse.aether.repository.RemoteRepository


class PaperLoader : PluginLoader {
    override fun classloader(classpathBuilder: PluginClasspathBuilder) {
        //classpathBuilder.addLibrary(JarLibrary(Path.of("dependency.jar"))) for local

        val resolver = MavenLibraryResolver()
        resolver.addDependency(Dependency(DefaultArtifact("com.example:example:version"), null))
        resolver.addRepository(
            RemoteRepository.Builder(
                "paper",
                "default",
                "https://repo.papermc.io/repository/maven-public/"
            ).build()
        )

        classpathBuilder.addLibrary(resolver)
    }
}