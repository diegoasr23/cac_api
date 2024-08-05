package etecc.cac.cac_api.error;

import lombok.NonNull;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import java.util.logging.Logger;

public class LogFile {

    private static File errorsPathFolder = new File(System.getProperty("user.home")
            + File.separator + "etecc" + File.separator + "cac" + File.separator + "logs"
            + File.separator + "errors" + File.separator);

    private static File actionsPathFolder = new File(System.getProperty("user.home")
            + File.separator + "etecc" + File.separator + "cac" + File.separator + "logs"
            + File.separator + "actions" + File.separator);

    public static File errorLogFile;
    public static File actionsLogFile;

    private final Logger logger = Logger.getLogger(getClass().getName());

    private static StringWriter stringWritter = new StringWriter();
    private static PrintWriter printWriter = new PrintWriter(stringWritter);
    private static String log_mssg_str = Optional.of(stringWritter.toString()).orElse("We've received an empty error message");


    public static void init(){
        if (!errorsPathFolder.exists()) {
            errorsPathFolder.mkdirs();
        }
        if (!actionsPathFolder.exists()) {
            actionsPathFolder.mkdirs();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        errorLogFile = new File(errorsPathFolder.getAbsolutePath() + File.separator + "ETECC_ERROR_LOG_FILE_" +
                simpleDateFormat.format(Calendar.getInstance().getTime()) + ".log");

        actionsLogFile = new File(actionsPathFolder.getAbsolutePath() + File.separator + "ETECC_ACTION_LOG_FILE_" +
                simpleDateFormat.format(Calendar.getInstance().getTime()) + ".log");
    }

    /**
     * Constructs a new LogFile instance with a unique name based on the current date and time.
     * The log file is created in the specified path folder, which is determined by the system's
     * user home directory. The file name follows the format: ETECC_LOG_FILE_yyyy-MM-dd_HH-mm-ss.log.
     *
     * @throws RuntimeException If an error occurs while creating the log file.
     */
    private LogFile() {
    }

    public static <T extends Exception>  void writeLogError(@NonNull T error,boolean toSend) {
        error.printStackTrace(printWriter);
        try {
            FileWriter writer = new FileWriter(errorLogFile);
            String response = String.format("We have run into an issue of type %s", error.toString());
            writer.write(stringWritter.toString());
            if(toSend) new TelegramNotificationError().notifyError(response);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends Exception> void writeLogError(@NonNull T error, @ NonNull String customMssge) throws IllegalArgumentException{
        if(customMssge.isBlank() || customMssge.isEmpty()) throw new IllegalArgumentException("Custom message cannot be null or empty");
        error.printStackTrace(printWriter);
        try {
            FileWriter writer = new FileWriter(errorLogFile);
            String response = String.format(" %s \n ERROR OF TYPE: %s ", customMssge, error.toString());
            writer.write(stringWritter.toString());
            writer.flush();
            writer.close();
            new TelegramNotificationError().notifyError(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Writes a log message to the actions log file.
     *
     * This method appends the given error message to the actions log file.
     * If an error occurs while writing to the file, a RuntimeException is thrown.
     * Additionally, a notification is sent to a Telegram channel using the {@link TelegramNotificationError} class.
     *
     * @param action The error message to be logged.
     * @throws RuntimeException If an error occurs while writing to the file.
     */
    public static void writeLogAction(@NonNull String action) {

        if(action.isBlank() || action.isEmpty()) throw new IllegalArgumentException("Action cannot be null or empty");
        try {
            try (FileWriter writer = new FileWriter(actionsLogFile)) {
                assert action != null;
                writer.write(action);
            }
            new TelegramNotificationError().notifyError(action);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
