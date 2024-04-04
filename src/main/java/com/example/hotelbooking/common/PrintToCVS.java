package com.example.hotelbooking.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import static com.example.hotelbooking.common.Links.PATH;
import static com.example.hotelbooking.common.Links.HEAD;

@AllArgsConstructor
@NoArgsConstructor
public class PrintToCVS {

    public void save(String data) {
        try {

            Path path = Path.of(PATH);
            String dataToWrite = HEAD + System.lineSeparator() + data + System.lineSeparator();
            Files.writeString(path, dataToWrite);

        } catch (IOException e) {
            throw new RuntimeException("Ошибка, при записи файла произошел сбой!");
        }
    }
}
