package junit.rules;

import org.junit.rules.ExternalResource;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.Iterator;

import static java.nio.file.Files.createTempDirectory;

public class DirectoryRule extends ExternalResource implements Path{

    private Path tempDirectoryPath;

    public Path getTempDirectoryPath() {
        return tempDirectoryPath;
    }

    @Override
    protected void before() throws Throwable {
        System.out.println("Create temporary directory");
        Path parentDirectoryPath = Paths.get("./temp");
        try {
            tempDirectoryPath = createTempDirectory(parentDirectoryPath, "prefix");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void after() {
        System.out.println("Remove temporary directory");
        File directory = new File(String.valueOf(tempDirectoryPath));
        for (File c : directory.listFiles()) {
            c.delete();
        }
        directory.delete();
    }

    public FileSystem getFileSystem() {
        return tempDirectoryPath.getFileSystem();
    }

    public boolean isAbsolute() {
        return tempDirectoryPath.isAbsolute();
    }

    public Path getRoot() {
        return tempDirectoryPath.getRoot();
    }

    public Path getFileName() {
        return tempDirectoryPath.getFileName();
    }

    public Path getParent() {
        return tempDirectoryPath.getParent();
    }

    public int getNameCount() {
        return tempDirectoryPath.getNameCount();
    }

    public Path getName(int index) {
        return tempDirectoryPath.getName(index);
    }

    public Path subpath(int beginIndex, int endIndex) {
        return tempDirectoryPath.subpath(beginIndex, endIndex);
    }

    public boolean startsWith(Path other) {
        return tempDirectoryPath.startsWith(other);
    }

    public boolean startsWith(String other) {
        return tempDirectoryPath.startsWith(other);
    }

    public boolean endsWith(Path other) {
        return tempDirectoryPath.endsWith(other);
    }

    public boolean endsWith(String other) {
        return tempDirectoryPath.endsWith(other);
    }

    public Path normalize() {
        return tempDirectoryPath.normalize();
    }

    public Path resolve(Path other) {
        return tempDirectoryPath.resolve(other);
    }

    public Path resolve(String other) {
        return tempDirectoryPath.resolve(other);
    }

    public Path resolveSibling(Path other) {
        return tempDirectoryPath.resolveSibling(other);
    }

    public Path resolveSibling(String other) {
        return tempDirectoryPath.resolveSibling(other);
    }

    public Path relativize(Path other) {
        return tempDirectoryPath.relativize(other);
    }

    public URI toUri() {
        return tempDirectoryPath.toUri();
    }

    public Path toAbsolutePath() {
        return tempDirectoryPath.toAbsolutePath();
    }

    public Path toRealPath(LinkOption... options) throws IOException {
        return tempDirectoryPath.toRealPath(options);
    }

    public File toFile() {
        return tempDirectoryPath.toFile();
    }

    public WatchKey register(WatchService watcher, WatchEvent.Kind<?>[] events, WatchEvent.Modifier... modifiers) throws IOException {
        return tempDirectoryPath.register(watcher, events, modifiers);
    }

    public WatchKey register(WatchService watcher, WatchEvent.Kind<?>[] events) throws IOException {
        return tempDirectoryPath.register(watcher, events);
    }

    public Iterator<Path> iterator() {
        return tempDirectoryPath.iterator();
    }

    public int compareTo(Path other) {
        return tempDirectoryPath.compareTo(other);
    }
}
