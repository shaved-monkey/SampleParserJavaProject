package com.appdetex.sampleparserjavaproject;

import com.appdetex.sampleparserjavaproject.retrievers.Retriever;
import com.appdetex.sampleparserjavaproject.retrievers.apple.Apple;
import com.appdetex.sampleparserjavaproject.retrievers.googleplay.GooglePlay;
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
    // with proper injection the retrievers can be aggregated without modifying this file
    // allowing new retrievers to be added without touching existing files
    static Collection<Retriever> retrievers = Arrays.asList(new GooglePlay(), new Apple());

    public static void main( String[] args ) {
        // Put code here
        var url = args[0];
        var outputList = retrievers.stream()
                .filter(retrievers -> retrievers.compatibleWith(url))
                .map(retriever -> retriever.extractFrom(url))
                .map(results -> results.get(0))
                .map(gson::toJson)
                .collect(Collectors.toList());
        System.out.println(outputList.get(0));
    }

}
