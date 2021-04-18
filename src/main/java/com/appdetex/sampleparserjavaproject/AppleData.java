package com.appdetex.sampleparserjavaproject;

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
        String ratingValue;
    }

    class Offer {
        String price;
    }
}
