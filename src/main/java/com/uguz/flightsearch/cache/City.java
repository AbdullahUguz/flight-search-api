package com.uguz.flightsearch.cache;

import java.util.Locale;
import java.util.Set;

public class City {
    private static final City INSTANCE = new City();
    private final Set<String> cities = Set.of("ANKARA","ISTANBUL","IZMIR","KAYSERI","KONYA");

    private City(){

    }

    public static City of(){
        return INSTANCE;
    }

    public boolean isExistCity(String city) {
       return cities.contains(city.toUpperCase(Locale.ROOT));
    }
}
