package com.empresa.libraryapi.service;

import java.util.List;

public interface EmailService {
    void sendMaisl(String message, List<String> maillist);

}
