package br.com.analisador.domain.service;

import java.io.IOException;

public interface OpenAIGPTService {
    String generateText(String prompt) throws IOException;
    void close() throws IOException;
}
