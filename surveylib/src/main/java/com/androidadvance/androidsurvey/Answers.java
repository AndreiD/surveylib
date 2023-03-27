package com.androidadvance.androidsurvey;

import com.google.gson.Gson;


import java.util.LinkedHashMap;

//Singleton Answers ........

public class Answers {
    private volatile static Answers uniqueInstance;
    private final LinkedHashMap<String, String> answered_hashmap = new LinkedHashMap<>();


    private Answers() {
    }

    public void put_answer(String key, String value) {
        answered_hashmap.put(key, value);
    }

    public String get_json_object() {
        Gson gson = new Gson();
        return gson.toJson(answered_hashmap,LinkedHashMap.class);
    }

    @Override
    public String toString() {
        return String.valueOf(answered_hashmap);
    }

    public static Answers getInstance() {
        if (uniqueInstance == null) {
            synchronized (Answers.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Answers();
                }
            }
        }
        return uniqueInstance;
    }

    /** Call this so that the HashMap with the answers can reset completely.
     * By calling this you only get returned the answer json from one survey
     * and not all the surveys together in one set.
     **/
    public void reset() {
        uniqueInstance = new Answers();
    }
}
