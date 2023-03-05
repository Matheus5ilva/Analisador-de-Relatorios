package br.com.analisador.domain.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

public interface OpenAIGPTService {
    String generateText(String apiKey,String prompt) throws IOException;
    void close() throws IOException;
}
