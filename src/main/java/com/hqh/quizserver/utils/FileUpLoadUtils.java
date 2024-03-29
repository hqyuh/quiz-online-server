package com.hqh.quizserver.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static com.hqh.quizserver.constant.FileConstant.*;

@Slf4j
public class FileUpLoadUtils {

    public static void saveFile(String uploadDir,
                                String fileName,
                                MultipartFile multipartFile) throws IOException {
        // file name
        Path uploadPath = Paths.get(uploadDir);

        if(!Files.exists(uploadPath)) {
            // create file
            Files.createDirectories(uploadPath);
            log.info("Created directory for: {}", uploadPath);
        }

        // get input stream from multipartFile
        try (InputStream inputStream = multipartFile.getInputStream()) {
            // call resolve() to create resolved Path
            Path filePath = uploadPath.resolve(fileName);

            // REPLACE_EXISTING – replace a file if it exists
            // Files.copy(source, target, ...)
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new IOException(COULD_NOT_SAVE_FILE + fileName, ex);
        }
    }

    // clear directory
    public static void clearDir(String dir) {
        Path dirPath = Paths.get(dir);

        try {
            Files.list(dirPath).forEach(file -> {
                // isisDirectory
                // Check if the specified path
                // is a directory or not
                if(!Files.isDirectory(file)) {
                    try {
                        Files.delete(file);
                    } catch (IOException ex) {
                        System.out.println(COULD_NOT_DELETE_FILE + file);
                    }
                }
            });
        } catch (IOException ex) {
            System.out.println(COULD_NOT_DIST_DIRECTORY + dirPath);
        }
    }


    public static void removeDir(String dir) {
        clearDir(dir);

        try {
            Files.delete(Paths.get(dir));
        } catch (IOException e) {
            log.error("Could not remove directory: " + dir);
        }
    }

}
