package com.goeuro;

import com.goeuro.command.Command;
import com.goeuro.command.HttpCommand;

/**
 * Created by sujay on 23/01/16.
 */
public class Driver {

    public static void main(String args[]){
        Command cmd = new HttpCommand();

        String cityName = null;
        if(args != null && args.length == 1){
            cityName = args[0];
            System.out.println(String.format("Writing CSV file for city %s...", cityName));
            cmd.execute(cityName);
        }else {
            printHelp();
        }

        System.out.println(String.format("Finished writing csv file %s.", cityName + "_locations.csv"));
    }

    private static void printHelp(){
        System.out.println("Usage: java -jar goEuroTest.jar <CITY_NAME>");
        System.exit(1);
    }
}
