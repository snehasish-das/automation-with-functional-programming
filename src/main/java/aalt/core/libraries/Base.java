package aalt.core.libraries;

import aalt.core.external.lib.ExtentReportsLib;
import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Base {
    public HashMap jsonFileToMap(String filePath) {
        InputStream inputStream = null;
        HashMap result = null;
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            inputStream = classLoader.getResourceAsStream(filePath);
            if (inputStream == null) {
                return null;
            }
            result = new ObjectMapper().readValue(inputStream, HashMap.class);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return result;
    }

    public void info(String msg){
        LoggerFactory.getLogger(this.getClass().getSimpleName()).info(getCurrentDateTime()+":"+msg);
        if(ExtentReportsLib.getExtentTest() != null){
            ExtentReportsLib.getExtentTest().log(Status.INFO, msg);
        }
    }

    public void warn(String msg){
        LoggerFactory.getLogger(this.getClass().getSimpleName()).warn(getCurrentDateTime()+":"+msg);
        if(ExtentReportsLib.getExtentTest() != null){
            ExtentReportsLib.getExtentTest().log(Status.WARNING, msg);
        }
    }

    public void debug(String msg){
        LoggerFactory.getLogger(this.getClass().getSimpleName()).debug(getCurrentDateTime()+":"+msg);
    }

    /**
     * returns current date and time in dd-mm-yyyy hh:mm:ss format
     * @return
     */
    public String getCurrentDateTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }
}