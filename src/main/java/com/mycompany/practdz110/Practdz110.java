/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.practdz110;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Andrew B
 */
public class Practdz110 {

    public static void main(String[] args) {
        System.out.println("Богданов Андрей 4 Вариант");
        try {
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            System.out.print("Введите путь к каталогу: ");
            String directoryPath = scanner.nextLine();
            File directory = new File(directoryPath);
            if (!directory.exists() || !directory.isDirectory()) {
                throw new IllegalArgumentException("Недопустимый путь к каталогу");
            }
            List<File> files = searchFiles(directory, "docx");
            printFiles(files);
        } catch (Exception e) {
            System.out.println("Произошла ошибка!: " + e.getMessage());
        }
    }

    private static List<File> searchFiles(File directory, String extension) {
        List<File> filesList = new java.util.ArrayList<>();
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    filesList.addAll(searchFiles(file, extension));
                } else if (file.isFile() && file.getName().endsWith("." + extension)) {
                    filesList.add(file);
                }
            }
        }
        return filesList;
    }

    private static void printFiles(List<File> files) {
        Collections.sort(files, (file1, file2) -> {
            if (file1.isDirectory() && !file2.isDirectory()) {
                return -1;
            } else if (!file1.isDirectory() && file2.isDirectory()) {
                return 1;
            } else {
                return file1.getName().compareTo(file2.getName());
            }
        });

        System.out.println("Finding " + files.get(0).getName() + " in " + files.get(0).getParent());
        for (File file : files) {
            System.out.println(file.getPath() + " " + file.length() + " bytes");
        }
    }
}

