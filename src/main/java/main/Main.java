package main;



import paket.FileManager;
import paket.RepoManager;

import java.util.Scanner;


public class Main {

    private static String local = "filelocal.FileLocalImpl";
    private static String drive = "paket.GoogleDriveImpl";

    public static void main(String[] args){
        System.out.println("1. Local file system\n2. Google Drive");
        Scanner sc = new Scanner(System.in);
        int opt= 5;
        String fileSystem = "";
        while(opt != 1 && opt != 2){
            System.out.println("Please enter 1 or 2!");
            opt = isNumeric(sc.nextLine());
        }
        if(opt == 1)
            fileSystem = local;
        else fileSystem = drive;
        try {
            Class.forName(fileSystem);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static int isNumeric(String str){
        try {
            return Integer.parseInt(str);
        }catch (Exception e){
            return -1;
        }
    }
}
