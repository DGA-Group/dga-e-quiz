package com.dga.equiz.utils;

import com.dga.equiz.Main;
import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.model.word.Word;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.util.List;

public class EquizUtils {
    /**
     * Instantiates a JavaFX NodeObject by loading an FXML file from the specified path.
     *
     * @param path the path to the FXML file to be loaded.
     * @return a NodeObject containing the loaded JavaFX Node and its associated FXMLLoader.
     * @throws IOException if an I/O error occurs while loading the FXML file.
     */
    public static NodeObject Instantiate(String path) throws IOException {
        // Create an FXMLLoader for loading the FXML file from the specified path
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(path));

        // Load the FXML file and get the JavaFX Node
        Node node = fxmlLoader.<Node>load();

        // Create and return a NodeObject containing the Node and FXMLLoader
        return new NodeObject(node, fxmlLoader);
    }

    /**
     * Makes an HTTP API call to the specified URL using the GET method and returns the response.
     *
     * @param apiCall The URL to make the API call to.
     * @return A Response object representing the HTTP response from the API.
     * @throws IOException If an I/O error occurs while making the API call or processing the response.
     */
    public static Response APICall(String apiCall) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(apiCall)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected response code: " + response.code());
        }

        return response;
    }

    /**
     * Fetches information about a target word from an online dictionary API.
     *
     * This method sends a GET request to an online dictionary API using the provided
     * target word and retrieves information about the word. The response from the API
     * is deserialized into a list of Word objects.
     *
     * @param targetWord The word for which information is to be fetched from the API.
     * @return A List of Word objects representing the information about the target word.
     * @throws IOException If an I/O error occurs during the HTTP request or response handling.
     */
    public static List<Word> FetchWordFromDictionary(String targetWord) throws IOException {
        // Construct the API URL for fetching word information
        String apiCall = "https://api.dictionaryapi.dev/api/v2/entries/en/" + targetWord;

        // Create an ObjectMapper to deserialize JSON responses
        ObjectMapper mapper = new ObjectMapper();

        // Make an API call to retrieve word information
        Response response = APICall(apiCall);

        // Deserialize the JSON response into a List of Word objects
        List<Word> wordList = mapper.readValue(response.body().byteStream(), new TypeReference<List<Word>>() {});

        return wordList;
    }

    /**
     * Fetches suggested words or related words for a given input word using an online
     * dictionary API.
     *
     * @param word The input word for which to fetch suggested or related words.
     * @return A List of Word objects containing suggested words or related words for
     *         the input word.
     * @throws IOException If there is an issue with the network communication or data reading.
     */
    public static List<Word> FetchSuggestWordFromDictionary(String word) throws IOException {
        // Construct the API URL for fetching word information
        String apiCall = "https://api.datamuse.com/sug?s=" + word;

        // Create an ObjectMapper to deserialize JSON responses
        ObjectMapper mapper = new ObjectMapper();

        // Make an API call to retrieve word information
        Response response = APICall(apiCall);

        // Deserialize the JSON response into a List of Word objects
        List<Word> wordList = mapper.readValue(response.body().byteStream(), new TypeReference<List<Word>>() {});

        return wordList;
    }

}
