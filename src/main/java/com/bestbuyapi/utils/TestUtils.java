/*
 * Created By: Hiren Patel
 * Project Name: BestBuyAPI-Serenity-Week-21
 */

package com.bestbuyapi.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Random;

public class TestUtils {
    public static String getRandomValue(){
        Random random = new Random();
        int randomInt = random.nextInt(100);
        return Integer.toString(randomInt);
    }
    public static int getRandomValueInt(){
        Random random = new Random();
        int randomInt = random.nextInt(10000);
        return randomInt;
    }

    public static String getRandomName(){
        String saltChars = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder name = new StringBuilder();
        Random random = new Random();
        while (name.length() < 7) {
            int index = (int) (random.nextFloat() * saltChars.length());
            name.append(saltChars.charAt(index));
        }
        String saltStr = (name.toString());
        return saltStr;
    }
    public static String jsonToString(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String jsonToString = null;
        try {
            jsonToString = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonToString;
    }

}
