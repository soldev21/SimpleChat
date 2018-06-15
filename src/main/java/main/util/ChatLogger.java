package main.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Sherif on 6/15/2018.
 */
public class ChatLogger {

    public static Logger logger = LogManager.getLogger();

    public static void info(String s){
        logger.info(s);
    }
}
