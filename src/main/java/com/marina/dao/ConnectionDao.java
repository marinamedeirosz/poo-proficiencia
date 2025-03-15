package com.marina.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.marina.config.AppConfig;

public class ConnectionDao {

    private static final String BASE_URL = AppConfig.getInstance().getBaseUrl();
    private static final int CONNECTION_TIMEOUT = AppConfig.getInstance().getConnectionTimeout();
    private static final int READ_TIMEOUT = AppConfig.getInstance().getReadTimeout();

    private static HttpURLConnection setupConnection(String endpoint, String method) throws IOException {
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setConnectTimeout(CONNECTION_TIMEOUT);
        connection.setReadTimeout(READ_TIMEOUT);
        return connection;
    }

    public static String makeGetRequest(String endpoint) throws IOException {
        HttpURLConnection connection = setupConnection(endpoint, "GET");
        
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("Error in GET request: " + responseCode);
            }
            return response.toString();
        } catch (IOException e) {
            throw new IOException("Error in GET request: " + e.getMessage(), e);
        }
    }

    public static String makePostRequest(String endpoint, String jsonData) throws IOException {
        HttpURLConnection connection = setupConnection(endpoint, "POST");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonData.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("Error in POST request: " + responseCode);
            }
            return response.toString();
        } catch (IOException e) {
            throw new IOException("Error in POST request: " + e.getMessage(), e);
        }
    }

    public static void makeDeleteRequest(String endpoint) throws IOException {
        HttpURLConnection connection = setupConnection(endpoint, "DELETE");

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Failed to delete resource: " + responseCode);
        }
    }

    public static void makePutRequest(String endpoint, String jsonData) throws IOException {
        HttpURLConnection connection = setupConnection(endpoint, "PUT");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonData.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Failed to update resource: " + responseCode);
        }
    }

    public static String encodeParams(String param) throws UnsupportedEncodingException {
        return URLEncoder.encode(param, "UTF-8");
    }
}
