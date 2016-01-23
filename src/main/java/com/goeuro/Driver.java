package com.goeuro;

import com.goeuro.command.Command;
import com.goeuro.command.HttpCommand;

/**
 * Created by sujay on 23/01/16.
 */
public class Driver {

    public static void main(String args[]){
        Command cmd = new HttpCommand();
        System.out.println("Writing CSV file...");
        final String cityName;
        if(args != null && args.length == 1){
            cityName = args[1];
        }else {
            printHelp();
        }
        cmd.execute("Berlin");
        System.out.println("Finished writing csv file 'locations.csv'");
    }

    private static void printHelp(){
        System.out.println("Usage: java -jar goEuroTest.jar <CITY_NAME>");
        System.exit(1);
    }
}
