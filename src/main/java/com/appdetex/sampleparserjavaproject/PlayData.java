package com.appdetex.sampleparserjavaproject;

import java.util.List;

public class PlayData{
    String name;
    String description;
    Author author;
    Rating aggregateRating;
    List<Offer> offers;

    class Author {
        String name;
    }

    class Rating {
        String ratingValue;
    }

    class Offer {
        double price;
        String priceCurrency;
    }
}
