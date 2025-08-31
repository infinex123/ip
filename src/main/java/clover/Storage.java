package clover;

import java.io.*;
    import java.nio.charset.StandardCharsets;
    import java.nio.file.*;
    import java.util.*;




    public class Storage {
        private Path file;

        public Storage(String... relative) {
            this.file = Paths.get("", relative);
        }

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
