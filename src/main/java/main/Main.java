package main;
import paket.FileManager;
import paket.RepoManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    private static String local = "filelocal.FileLocalImpl";
    private static String drive = "paket.GoogleDriveImpl";
    private static String menuOptions;
    private static String createRootOptions;
    private static Scanner sc;
    private static FileManager fileManager;

    public static void main(String[] args){

        sc = new Scanner(System.in);
        String fileSystem = getStorage();
        try {
            Class.forName(fileSystem);
            fileManager = RepoManager.getRepo();
        }catch (Exception e){
            e.printStackTrace();
        }
        menuOptions();
        int opt;
        while ((opt = getOption(19, menuOptions)) != 19){
            switch (opt){
                case 1:
                    opt = getOption(4, createRootOptions);
                    switch (opt){
                        case 1:
                            String[] strings = getStringString();
                            fileManager.createRoot(strings[0],strings[1]);
                    }

                    break;
                case 5:
                    String[] strings = getStringString();
                    fileManager.download(strings[0],strings[1]);
                default:
                    break;
            }
        }

    }
    private static String[] getStringString(){
        String s1 = sc.nextLine();
        return s1.split(",");
    }


    private static int getOption(int maxNum, String messageOptions){
        System.out.println(messageOptions);
        int opt = chooseOption(sc.nextLine(), 19);
        while(opt == -1){
            System.out.println("Please enter valid number!");
            opt = chooseOption(sc.nextLine(), 19);
        }
        return opt;
    }

    private static int chooseOption(String str, int maxNum){
        try {
            int i = Integer.parseInt(str);
            if(i < 1 && i > maxNum)
                return -1;
            return i;
        }catch (Exception e){
            return -1;
        }
    }

    private static String getStorage(){
        System.out.println("1. Local file system\n2. Google Drive");
        int opt = chooseOption(sc.nextLine(),2);
        while(opt != 1 && opt != 2){
            System.out.println("Please enter 1 or 2");
            opt = chooseOption(sc.nextLine(),2);
        }
        if(opt == 1)
            return local;
        return drive;
    }


    // TODO na izlasku odraditi save config
    private static void menuOptions(){
        StringBuilder sb = new StringBuilder();
        sb.append("1. create root\n");
        sb.append("2. make directory\n");
        sb.append("3. delete\n");
        sb.append("4. move\n");
        sb.append("5. download\n");
        sb.append("6. upload\n");
        sb.append("7. rename\n");
        sb.append("8. search directory\n");
        sb.append("9. search subdirectories\n");
        sb.append("10. search all\n");
        sb.append("11. filter by extension\n");
        sb.append("12. search substring\n");
        sb.append("13. does name exist\n");
        sb.append("14. does list of names exist\n");
        sb.append("15. get parent path\n");
        sb.append("16. sort by\n");
        sb.append("17. filter by data\n");
        sb.append("18. filter by period\n");
        sb.append("19. exit\n");
        menuOptions =  sb.toString();

        sb = new StringBuilder();
        sb.append("Mkdir:\n");
        sb.append("1. String path, String name\n");
        sb.append("2. String path, String name, int numberOfFilesConstraint\n");
        sb.append("3. String path,String name, Configuration configuration\n");
        sb.append("4. String path, String name, Configuration configuration, int numberOfFilesConstraint\n");
        createRootOptions = sb.toString();

        sb = new StringBuilder();
        sb.append("Mkdir:\n");
        sb.append("1. String path, String name\n");
        sb.append("2. String path, String name, int numberOfFilesConstraint\n");
        sb.append("3. String path,String name, Configuration configuration\n");
        sb.append("4. String path, String name, Configuration configuration, int numberOfFilesConstraint\n");
    }

}
