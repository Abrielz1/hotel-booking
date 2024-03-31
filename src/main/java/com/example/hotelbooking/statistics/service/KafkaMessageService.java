package com.example.hotelbooking.statistics.service;

import com.example.hotelbooking.statistics.model.KafkaMessage;
import com.example.hotelbooking.statistics.model.KafkaMessageDTO;
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

    private final List<KafkaMessageDTO> messagesDTO = new ArrayList<>();

    public void add(KafkaMessage message) {
        messages.add(message);
    }

    public void addDTO(KafkaMessageDTO messageDTO) {
        messagesDTO.add(messageDTO);
    }

    public String print() {
        return messages.toString();
}
}
