package ua.step.example.part2.printer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessagePrinter {
    @Autowired
    private MessageService service;

    public void printMessage() {
        System.out.println(service.getMessage());
    }
}