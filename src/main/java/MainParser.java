import java.io.*;
import java.util.Properties;

public class MainParser {

    public static void main(String[] args) throws IOException {
        /*DAO_Postgres dp = new DAO_Postgres();
        dp.getData();*/
        WEBParser webParser = new WEBParser();
        webParser.work();


    }
/*
    public static Occupation getOccupation(){

        return occupation;
    }*/
}