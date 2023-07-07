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
        resolver.addDependency(Dependency(DefaultArtifact("com.h2database:h2:2.1.214"), null))
        resolver.addDependency(Dependency(DefaultArtifact("org.mariadb.jdbc:mariadb-java-client:3.1.4"), null))
        resolver.addDependency(Dependency(DefaultArtifact("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.5.1"), null))
        resolver.addDependency(Dependency(DefaultArtifact("org.litote.kmongo:kmongo-coroutine-serialization:4.9.0"), null))
        resolver.addDependency(Dependency(DefaultArtifact("org.mongodb:mongodb-driver-sync:4.10.1"), null))
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