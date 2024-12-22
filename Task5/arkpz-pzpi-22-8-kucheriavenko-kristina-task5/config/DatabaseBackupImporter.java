package com.BiologicalMaterialsSystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@SpringBootApplication
public class DatabaseBackupImporter {

    @Value("classpath:db/backup.backup")
    private Resource backupFile;

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public CommandLineRunner importDatabase() {
        return args -> {
            try {
                System.out.println("Початок імпорту бекапу...");

                String pgRestorePath = "C:\\Program Files\\PostgreSQL\\17\\bin\\pg_restore.exe";
                String backupFilePath = backupFile.getFile().getAbsolutePath();

                String disableFKCheckCommand = "SET session_replication_role = replica;";
                executeSQLCommand(disableFKCheckCommand);

                String[] restoreTablesWithoutFK = {
                        pgRestorePath,
                        "--host", "localhost",
                        "--port", "5432",
                        "--username", username,
                        "--dbname", getDatabaseNameFromUrl(databaseUrl),
                        "--no-password",
                        "--data-only",
                        "--table", "donors",
                        "--table", "users",
                        backupFilePath
                };
                executeRestoreCommand(restoreTablesWithoutFK);

                String[] restoreTablesWithFK = {
                        pgRestorePath,
                        "--host", "localhost",
                        "--port", "5432",
                        "--username", username,
                        "--dbname", getDatabaseNameFromUrl(databaseUrl),
                        "--no-password",
                        "--data-only",
                        "--table", "biological_materials",
                        "--table", "event_logs",
                        "--table", "notifications",
                        "--table", "reports",
                        "--table", "storage_condition",
                        backupFilePath
                };
                executeRestoreCommand(restoreTablesWithFK);

                String enableFKCheckCommand = "SET session_replication_role = DEFAULT;";
                executeSQLCommand(enableFKCheckCommand);

                System.out.println("База даних успішно відновлена.");

            } catch (Exception e) {
                System.err.println("Помилка під час відновлення бекапу: " + e.getMessage());
                e.printStackTrace();
            }
        };
    }

    private void executeRestoreCommand(String[] command) throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.environment().put("PGPASSWORD", password);
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            System.err.println("Помилка при виконанні pg_restore, код завершення: " + exitCode);
        }
    }

    private void executeSQLCommand(String command) throws Exception {
        String psqlPath = "C:\\Program Files\\PostgreSQL\\17\\bin\\psql.exe";
        String[] sqlCommand = {
                psqlPath,
                "--host", "localhost",
                "--port", "5432",
                "--username", username,
                "--dbname", getDatabaseNameFromUrl(databaseUrl),
                "--command", command
        };

        ProcessBuilder processBuilder = new ProcessBuilder(sqlCommand);
        processBuilder.environment().put("PGPASSWORD", password);
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            System.err.println("Помилка при виконанні SQL команди, код завершення: " + exitCode);
        }
    }

    private String getDatabaseNameFromUrl(String url) {
        String prefix = "jdbc:postgresql://";
        if (url.startsWith(prefix)) {
            return url.substring(url.lastIndexOf("/") + 1, url.contains("?") ? url.indexOf("?") : url.length());
        } else {
            throw new IllegalArgumentException("Неправильний формат URL бази даних: " + url);
        }
    }
}