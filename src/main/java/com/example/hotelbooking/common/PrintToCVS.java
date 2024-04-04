package com.example.hotelbooking.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import static com.example.hotelbooking.common.Links.PATH;

@AllArgsConstructor
@NoArgsConstructor
public class PrintToCVS {

    public void save(String data) {
        try {
            Path path = Path.of(PATH);
            final String head = "id,type,name,status,description,epic" + System.lineSeparator();
            String dataToWrite = "";
//            String data = head +
//                    taskToString() + System.lineSeparator() +
//                    HistoryManager.historyToString(historyManager);
          Files.writeString(path, dataToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка, при записи файла произошел сбой!");
        }
    }
}
