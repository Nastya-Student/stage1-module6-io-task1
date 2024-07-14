package com.epam.mjc.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {
        StringBuilder data = new StringBuilder();
        try(FileInputStream reader = new FileInputStream(file)){
            int ch;
            while((ch = reader.read()) != -1){
                data.append((char)ch);
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        Map<String, String> parsedData = parse(data.toString());

        return new Profile(parsedData.get("Name"),Integer.valueOf(parsedData.get("Age")),
                parsedData.get("Email"), Long.valueOf(parsedData.get("Phone")));
    }

    private Map<String, String> parse (String data){
        Map<String,String> map = new HashMap<>();
        String [] fields  = data.split("\n");
        for(String field: fields){
            String [] words = field.split(": ");
            map.put(words[0],words[1]);
        }
        return map;
    }
    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
         fileReader.getDataFromFile(new File("src/main/resources/Profile.txt"));
    }

}
