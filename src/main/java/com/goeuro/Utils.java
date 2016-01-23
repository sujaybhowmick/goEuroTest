package com.goeuro;

import com.goeuro.model.Location;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sujay on 23/01/16.
 */
public class Utils {

    private static final String ENGINE_NAME = "javascript";

    public static List<Location> convertJsonToLocation(final String jsonStr){
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName(ENGINE_NAME);
        List<Location> locations = new ArrayList<Location>();
        try {
            ScriptObjectMirror jsonObj =
                    (ScriptObjectMirror)engine.eval(jsonStr);
            for(Map.Entry<String, Object> entry: jsonObj.entrySet()){
                ScriptObjectMirror object = (ScriptObjectMirror)entry.getValue();
                long id = Long.valueOf(String.valueOf(object.get("_id")));
                ScriptObjectMirror geoPosition = (ScriptObjectMirror)object.get("geo_position");
                double lat = geoPosition.get("latitude") != null ?
                        Double.valueOf(String.valueOf(geoPosition.get("latitude"))) :
                        0.0;
                double lon = geoPosition.get("latitude") != null ?
                        Double.valueOf(String.valueOf(geoPosition.get("longitude"))) :
                        0.0;
                int distance = object.get("distance") != null ?
                        Integer.valueOf(String.valueOf(object.get("distance"))) :
                        0;
                Location location =
                        new Location.Builder(id)
                        .key(String.valueOf(object.get("key")))
                        .name(String.valueOf(object.get("name")))
                        .fullName(String.valueOf(object.get("fullName")))
                        .type(String.valueOf(object.get("type")))
                        .coreCountry(Boolean.valueOf(String.valueOf(object.get("coreCountry"))))
                        .country(String.valueOf(object.get("country")))
                        .countryCode(String.valueOf(object.get("countryCode")))
                        .iataAirportCode(String.valueOf(object.get("iata_airport_code")))
                        .inEurope(Boolean.valueOf(String.valueOf(object.get("inEurope"))))
                        .distance(distance).geoPosition(lat, lon)
                        .build();
                locations.add(location);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return locations;
    }


    public static void writeCSV(List<Location> locations){
        final String fileName = "locations.csv";
        BufferedWriter buf = null;
        try {
            File csvFile = new File(fileName);
            FileWriter writer = new FileWriter(csvFile);
            buf = new BufferedWriter(writer);
            for(Location location: locations) {
                buf.write(location.getCommaSeparatedProperties());
                buf.write("\n");
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }finally{
            if(buf != null){
                try {
                    buf.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
