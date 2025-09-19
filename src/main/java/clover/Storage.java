package clover;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

/**
 * Handles saving and loading tasks to and from a file.
 * <p>
 * Tasks are serialized into a storage string (via {@link Task#toStorageString()})
 * and parsed back (via {@link Task#fromStorageString(String)}).
 */
public class Storage {
    private Path file;

    /**
     * Constructs a {@code Storage} object with a relative file path.
     *
     * @param relative the path components to the storage file
     */
    public Storage(String... relative) {
        this.file = Paths.get("", relative);
    }

    /**
     * Loads tasks from the storage file.
     * <p>
     * If the file or its parent directories do not exist, they are created.
     * Lines that cannot be parsed into tasks are skipped with a warning.
     *
     * @return a list of tasks loaded from the file
     * @throws UncheckedIOException if an I/O error occurs while reading
     */
    public List<Task> load() {
        try {
            Path parent = file.getParent();
            if (parent != null && Files.notExists(parent)) {
                Files.createDirectories(parent);
            }
            if (Files.notExists(file)) {
                Files.createFile(file);
                return new ArrayList<>();
            }

            List<Task> tasks = new ArrayList<>();
            try (BufferedReader br = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
                String line;
                int lineNo = 0;
                while ((line = br.readLine()) != null) {
                    lineNo++;
                    line = line.trim();
                    if (line.isEmpty()) continue;
                    try {
                        tasks.add(Task.fromStorageString(line));
                    } catch (Exception ex) {
                        System.err.println("Skipping corrupted line " + lineNo + ": " + line);
                    }
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to load tasks", e);
        }
    }

    /**
     * Saves the given list of tasks to the storage file.
     * <p>
     * Writes tasks to a temporary file first and then atomically replaces
     * the original file. If atomic move is not supported, falls back
     * to a normal replace.
     *
     * @param tasks the list of tasks to save
     * @throws UncheckedIOException if an I/O error occurs while writing
     */
    public void save(List<Task> tasks) {
        try {
            Path parent = file.getParent();
            if (parent != null && Files.notExists(parent)) {
                Files.createDirectories(parent);
            }

            Path tmp = file.resolveSibling(file.getFileName() + ".tmp");

            try (BufferedWriter bw = Files.newBufferedWriter(tmp, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                for (Task t : tasks) {
                    bw.write(t.toStorageString());
                    bw.newLine();
                }
            }

            Files.move(tmp, file,
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.ATOMIC_MOVE);

        } catch (AtomicMoveNotSupportedException e) {
            try {
                Files.move(file.resolveSibling(file.getFileName() + ".tmp"), file,
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}

