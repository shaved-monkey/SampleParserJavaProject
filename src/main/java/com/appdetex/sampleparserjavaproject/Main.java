package com.appdetex.sampleparserjavaproject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Main Java Class
 *
 * This class will use Jsoup to retrieve a provided URL
 * and parse out certain data, printing that data to
 * stdout in a JSON format.
 */
public class Main {

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main( String[] args ) {
        // Put code here
        var url = args[0];
        var retriever = new GooglePlay();
        var outputList = retriever.getPage(url);
        String outputJson = gson.toJson(outputList.get(0));
        System.out.println(outputJson);
    }

}
