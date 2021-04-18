package com.appdetex.sampleparserjavaproject.retrievers.apple;

public class AppleData {
    String name;
    String description;
    Author author;
    Rating aggregateRating;
    Offer offers;

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
