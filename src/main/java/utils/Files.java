package utils;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Files {

    public void writeResponseToFile(String response, String path) {
        File file = new File(path);
        try {
            // Create parent directories if not exist
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            // If file exists and is not empty, clear it first
            if (file.exists() && file.length() != 0) {
                try (FileWriter clearer = new FileWriter(file, false)) {
                    clearer.write("");
                }
            }
            // Now write the new JSON content
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(response);
            }
        } catch (IOException ignored) {}
    }

    public void deleteFilesFromDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()){return;}
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    file.delete();
                }
            }
        }
    }

    public Object[][] readCsvFile() {
        List<Object[]> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/data.csv"))) {
            reader.readNext(); // Skip header
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (line.length < 5) continue;

                int activityID = Integer.parseInt(line[0].trim());
                int authorId   = Integer.parseInt(line[1].trim());
                int bookID     = Integer.parseInt(line[2].trim());
                int photoID    = Integer.parseInt(line[3].trim());
                int userID     = Integer.parseInt(line[4].trim());

                data.add(new Object[]{activityID, authorId, bookID, photoID, userID});
            }
        } catch (Exception ignored) {}
        return data.toArray(new Object[0][]);
    }
}
