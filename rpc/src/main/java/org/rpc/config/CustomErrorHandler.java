package org.rpc.config;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CustomErrorHandler implements ResponseErrorHandler {

    private ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();

    /**
     * 不能处理connected refused的异常
     * @param response
     * @throws IOException
     */
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        String body = convertStreamToString(response.getBody());
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusText());
        try {
            errorHandler.handleError(response);
        } catch (RestClientException scx) {
            throw new RuntimeException(body);
        }
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return errorHandler.hasError(response);
    }

    // inputStream 装换为 string
    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}


