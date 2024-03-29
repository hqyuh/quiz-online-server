package com.hqh.quizserver.helper.user;

import com.hqh.quizserver.entity.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static com.hqh.quizserver.constant.FileConstant.*;

public class CSVHelper {

    public static ByteArrayInputStream userToCsv(List<User> users) {
        CSVFormat format = CSVFormat.DEFAULT.withHeader(USER_HEADER);
        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(outputStream), format)) {
            for (User user : users) {
                List<String> data = Arrays.asList(
                        user.getId().toString(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPhoneNumber(),
                        user.getDateOfBirth().toString(),
                        user.getJoinDate().toString(),
                        user.getRoles()
                );
                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException exception) {
            throw new RuntimeException("Fail to import data to CSV file: " + exception.getMessage());
        }
    }

}
