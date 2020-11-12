package org.example.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LogSystem {
    private List<String> logs = new ArrayList<>();

    public List<String> getLogs() { return logs; }

    public void addLog(String message) {
        message = "[INFO][" + LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + "] " + message;
        this.logs.add(message);
        System.out.println(message);
    }
}
