package com.example.hotelbooking.statistics.service;

import com.example.hotelbooking.statistics.model.KafkaMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaMessageService {

    private final List<KafkaMessage> messages = new ArrayList<>();

    private final List<KafkaMessage> messagesDTO = new ArrayList<>();

    public void add(KafkaMessage message) {
        messages.add(message);
    }

    public void addDTO(KafkaMessage messageDTO) {
        messagesDTO.add(messageDTO);
    }

    public String print() {
        return messages.toString();
}

    public void saveInDb(KafkaMessage message) {
    }
}
