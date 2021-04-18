package com.appdetex.sampleparserjavaproject.retrievers.googleplay;

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
        double ratingValue;
    }

    class Offer {
        double price;
        String priceCurrency;
    }
}
