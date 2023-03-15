package br.com.analisador.domain.service;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class OpenAIGPTService {

    private static final String OPENAI_API_ENDPOINT = "https://api.openai.com/v1/engines/text-davinci-003/completions";

    private final CloseableHttpClient httpClient;

    public OpenAIGPTService() {
        this.httpClient = HttpClients.createDefault();
    }

    public String generateText(String apiKey, String csv) throws IOException {

        String prompt = "Analise o relatorio " + csv + " e realize a decisão para aumentar os lucros e reduzir gastos.";

        HttpPost httpPost = new HttpPost(OPENAI_API_ENDPOINT);
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey);

        JSONObject json = new JSONObject();
        json.put("prompt", prompt);
        json.put("max_tokens", 900);

        StringEntity entity = new StringEntity(json.toString(), StandardCharsets.UTF_8);
        httpPost.setEntity(entity);

        try (var response = httpClient.execute(httpPost)) {
            String jsonResponse = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(jsonResponse);
            //String text = jsonObject.getJSONArray("choices").getJSONObject(0).getString("text");
            //return text;
            return jsonResponse;
        }
    }

    public void close() throws IOException {
        httpClient.close();
    }
}
