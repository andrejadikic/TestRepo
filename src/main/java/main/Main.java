package main;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    private static String local = "filelocal.FileLocalImpl";
    private static String drive = "paket.GoogleDriveImpl";
    private static List<Integer> options;
    private static String menuOptions;

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        String fileSystem = getStorage(sc);
        try {
            Class.forName(fileSystem);
        }catch (Exception e){
            e.printStackTrace();
        }
        fillOptions();
        System.out.println(menuOptions);
        int opt;
        while ((opt = getOption(sc)) != 19){
            System.out.println("Option: " + opt);
            System.out.println(menuOptions);
        }

    }



    private static int getOption(Scanner sc){
        menuOptions();
        int opt = chooseOption(sc.nextLine());
        while(opt == -1){
            System.out.println("Please enter valid number!");
            opt = chooseOption(sc.nextLine());
        }
        return opt;
    }

    private static int chooseOption(String str){
        try {
            int i = Integer.parseInt(str);
            if(!options.contains(i))
                return -1;
            return i;
        }catch (Exception e){
            return -1;
        }
    }

    private static String getStorage(Scanner sc){
        System.out.println("1. Local file system\n2. Google Drive");
        int opt = chooseStorage(sc.nextLine());
        while(opt != 1 && opt != 2){
            System.out.println("Please enter 1 or 2");
            opt = chooseStorage(sc.nextLine());
        }
        if(opt == 1)
            return local;
        return drive;
    }

    private static int chooseStorage(String str){
        try {
            int i = Integer.parseInt(str);
            if(i != 1 && i != 2)
                return -1;
            return i;
        }catch (Exception e){
            return -1;
        }
    }

    private static void fillOptions(){
        options = new ArrayList<>();
        for(int i=1; i <= 19; i++)
            options.add(i);
        menuOptions();
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
    }
}
