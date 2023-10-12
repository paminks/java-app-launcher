package org.example;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;


import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class Main  {
    public static int SelectAction;
    public static Executables executables = new Executables();
    public static int randomNumber;
    public static String dir;
    public static String dirName;
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Random random = new Random();
        randomNumber = random.nextInt();

        System.out.println("To add a new directory press 1, for existing directories press 2");
        SelectAction = scanner.nextInt();
        switch (SelectAction)
        {
            case(1):
                System.out.println("Please enter a name for the directory");
                dirName = scanner.next();
                System.out.println("Please enter the directory of the folder that contains the executable");
                dir = scanner.next();
                executables = new Executables(dir);
                setJson();
                break;
            case(2):
                File folder = new File("/home/eren/Desktop/kotlinTry/hubApp1");
                getJson(folder,"json");


                break;

        }
    }

    public static void setJson()
    {
        try {

            BufferedWriter writer = Files.newBufferedWriter(Paths.get("directory"+dirName+".json"));
            Jsoner.serialize(executables, writer);
            writer.close();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public static String yesNo;
    public static void getJson(File folder, String filterText)
    {
        MyExtFilter extFilter = new MyExtFilter(filterText);
        String[] list = folder.list(extFilter);
        try
        {
            for(int i= 0; i< list.length; i++)
            {
                Reader reader = Files.newBufferedReader(Paths.get(list[i]));
                JsonObject parser = (JsonObject) Jsoner.deserialize(reader);
                String path1 = (String) parser.get("path");
                Path path = Paths.get(path1);
                File file1 = new File(path1);
                File[] files = file1.listFiles();
                if(files != null)
                {
                    for(File file : files)
                    {
                        if(file.isFile())
                        {
                            String filename = file.getName();
                            if(filename.endsWith(".x86_64"))
                            {
                                System.out.println("Would you like to execute " + filename);
                                yesNo = scanner.next();
                                switch(yesNo)
                                {
                                    case("y"):
                                    executeX86_64Binary(file.getAbsolutePath());
                                        break;
                                    case("n"):
                                        break;
                                }
                            }
                        }
                    }
                }



                reader.close();

            }
        }catch (Exception ex)
        {
            ex.printStackTrace();

        }
    }


    private static void executeX86_64Binary(String filePath) {
        try {
            Process process = new ProcessBuilder(filePath).start();
            // You can handle process output and errors as needed
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static class MyExtFilter implements FilenameFilter {

        public String ext;

        public MyExtFilter(String ext) {
            this.ext = ext;
        }

        public boolean accept(File dir, String name) {
            return (name.endsWith(ext));
        }
    }

}