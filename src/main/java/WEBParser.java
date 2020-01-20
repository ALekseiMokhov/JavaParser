import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WEBParser {
/*
    Map<Integer,String> map = new HashMap<>();*/


    public void parse() throws IOException {
        BufferedReader bufferedReader
                =new BufferedReader( new InputStreamReader( "C:\\Users\\admin\\Desktop\\Jobs.txt" ));

       try{


               while (true) {
                   String line = bufferedReader.readLine();
                   if (line == null) break;


               }

       }

        catch (FileNotFoundException f){
            System.out.println("File hasnt been found!");
        }
        finally {
           try {
               bufferedReader.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    }

    public void filter(String s){
        int column=0;

        switch (column){


        }

    }
}
