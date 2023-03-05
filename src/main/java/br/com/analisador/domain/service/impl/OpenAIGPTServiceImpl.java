package br.com.analisador.domain.service.impl;

import br.com.analisador.domain.service.OpenAIGPTService;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class OpenAIGPTServiceImpl implements OpenAIGPTService {

    private static final String OPENAI_API_ENDPOINT = "https://api.openai.com/v1/engines/text-davinci-003/completions";

    private final String apiKey;
    private final CloseableHttpClient httpClient;

    public OpenAIGPTServiceImpl(String apiKey) {
        this.apiKey = apiKey;
        this.httpClient = HttpClients.createDefault();
    }

    @Override
    public String generateText(String prompt) throws IOException {
        HttpPost httpPost = new HttpPost(OPENAI_API_ENDPOINT);
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey);

        JSONObject json = new JSONObject();
        json.put("prompt", prompt);
        json.put("max_tokens", 1000);

        StringEntity entity = new StringEntity(json.toString(), StandardCharsets.UTF_8);
        httpPost.setEntity(entity);

        try (var response = httpClient.execute(httpPost)) {
            String jsonResponse = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(jsonResponse);
            String text = jsonObject.getJSONArray("choices").getJSONObject(0).getString("text");
            return text;
            //return jsonResponse;
        }
    }


    @Override
    public void close() throws IOException {
        httpClient.close();
    }
}
