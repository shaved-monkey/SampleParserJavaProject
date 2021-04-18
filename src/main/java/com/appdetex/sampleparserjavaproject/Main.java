package com.appdetex.sampleparserjavaproject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Main Java Class
 *
 * This class will use Jsoup to retrieve a provided URL
 * and parse out certain data, printing that data to
 * stdout in a JSON format.
 */
public class Main {

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    static Collection<Retriever> retrievers = Arrays.asList(new GooglePlay());

    public static void main( String[] args ) {
        // Put code here
        var url = args[0];
        var outputList = retrievers.stream()
                .filter(retrievers -> retrievers.matchesUrl(url))
                .map(retriever -> retriever.getPage(url))
                .map(outputs -> outputs.get(0))
                .map(gson::toJson)
                .collect(Collectors.toList());
        System.out.println(outputList.get(0));
    }

}
