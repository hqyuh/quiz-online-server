package com.hqh.quizserver.constant;

public class FileConstant {

    public static final String FORWARD_SLASH = "/";
    public static final String DOT = ".";
    public static final String EXCLAMATION_MARK = "!";
    public static final String FORWARD_SLASH_A = "\\";
    public static final String SRC = "src";
    public static final String RESOURCES = "resources";
    public static final String MAIN = "main";
    public static final String STATIC = "static";
    public static final String IMAGE_PATH = "image";
    public static final String JPG_EXTENSION = "jpg";
    public static final String PNG_EXTENSION = "png";
    public static final String DIRECTORY_CREATED = "Created directory for: ";
    public static final String FILE_SAVED_IN_FILE_SYSTEM = "Saved file in file system by name: ";
    public static final String TEMP_PROFILE_IMAGE_BASE_URL = "https://api.multiavatar.com/";
    public static final String DEFAULT_USER_IMAGE_PATH = "/user/profile/";
    public static final String USER_IMAGE_PATH = "/user/";
    public static final String QUESTION_IMAGE_PATH = "question/";
    public static final String USER_FOLDER = System.getProperty("user.dir") + "/user/";
    public static final String COULD_NOT_SAVE_FILE = "Could not save file: ";
    public static final String COULD_NOT_DELETE_FILE = "Could not delete file: ";
    public static final String COULD_NOT_REMOVE_DIRECTORY = "Could not remove directory: ";
    public static final String COULD_NOT_DIST_DIRECTORY = "Could not dist directory: ";
    public static final String PLEASE_UPLOAD_AN_IMAGE = "is not an image file. Please upload an image";

    // file
    public static final String ATTACHMENT_FILENAME = "attachment; filename=";
    // csv
    public static final String FAIL_TO_IMPORT_DATA_TO_CSV_FILE = "Fail to import data to CSV file: ";
    public static final String[] USER_HEADER = {"ID", "First name", "Last name", "Username", "Email", "Phone Number", "Date Of Birth", "Join date", "Role"};
    public static final String APPLICATION_CSV = "application/csv";
    public static final String CSV_EXTENSION = "csv";
    // excel
    public static final String XLSX_EXTENSION = "xlsx";
    public static final String APPLICATION_EXCEL = "application/vnd.ms-excel";
    public static final String[] HEADER_QUIZZ = {"", "ID", "Question", "Answer A", "Answer B", "Answer C", "Answer D", "Correct Result", "Correct Essay", "Mark", "Type"};
    public static final String[] HEADER_MARK = {"", "ID", "Full name", "Username", "Mark", "Quizz name", "Completed date"};
    public static final String FAIL_TO_IMPORT_DATA_TO_EXCEL_FILE = "Fail to import data to Excel file: ";
    public static final String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static final String FAIL_TO_PARSE_EXCEL_FILE = "Fail to parse Excel file: ";
    public static final String UPLOADED_THE_FILE_SUCCESSFULLY = "Uploaded the file successfully ";
    public static final String COULD_NOT_UPLOAD_THE_FILE = "Could not upload the file: ";
    public static final String PLEASE_UPLOAD_AN_EXCEL_FILE = "Please upload an excel file!";
    public static final String FAIL_TO_STORE_EXCEL_DATA = "Fail to store excel data: ";

}
