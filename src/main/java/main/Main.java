package main;
import paket.Configuration;
import paket.FileManager;
import paket.RepoManager;

import java.util.*;


public class Main {

    private static String local = "filelocal.FileLocalImpl";
    private static String drive = "paket.GoogleDriveImpl";
    private static String menuOptions;
    private static String createRootOptions;
    private static String mkdirOptions;
    private static String filterOptions;

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
            return;
        }
        menuOptions();
        int opt;
        while ((opt = getOption(19, menuOptions)) != 19){
            String line;
            switch (opt){
                case 1:
                    opt = getOption(4, createRootOptions);
                    line = sc.nextLine();
                    try {
                        switch (opt) {
                            case 1 -> fileManager.createRoot(getStringString(line)[0], getStringString(line)[1]);
                            case 2 -> fileManager.createRoot((String) getStringStringInt(line).get(0), (String) getStringStringInt(line).get(1), (Integer) getStringStringInt(line).get(2));
                            case 3 -> fileManager.createRoot((String) getStringStringLongSet(line).get(0), (String) getStringStringLongSet(line).get(1),
                                    new Configuration((Long) getStringStringLongSet(line).get(2),(Set<String>)getStringStringLongSet(line).get(3)));
                            case 4 -> fileManager.createRoot((String) getStringStringLongSetInt(line).get(0),(String) getStringStringLongSetInt(line).get(1),new Configuration((Long) getStringStringLongSetInt(line).get(2),(Set<String>) getStringStringLongSetInt(line).get(3)),(Integer) getStringStringLongSetInt(line).get(4));
                            default -> System.out.println("Invalid option!");
                        }
                    }catch (Exception e){
                        System.out.println("Error during parsing!");
                    }
                    break;
                case 2:
                    opt = getOption(8,mkdirOptions);
                    line = sc.nextLine();
                    try {
                        switch (opt){
                            case 1 -> fileManager.mkdir(getStringString(line)[0], getStringString(line)[1]);
                            case 2 -> fileManager.mkdir((String) getStringList(line).get(0), (List<String>) getStringList(line).get(1));
                            case 3 -> fileManager.mkdir((String) getStringListInt(line).get(0), (List<String>) getStringListInt(line).get(1),(Integer) getStringListInt(line).get(2));
                            case 4 ->{
                                List<Object> l = getStringStringIntBoolean(line);
                                fileManager.mkdir((String) l.get(0), (String) l.get(1), (Integer) l.get(2), (boolean) l.get(3));
                            }
                            case 5 ->{
                                List<Object> l = getStringStringIntInt(line);
                                fileManager.mkdir((String) l.get(0), (String) l.get(1), (Integer) l.get(2), (Integer) l.get(3));
                            }
                            case 6 -> fileManager.mkdir(line);
                            case 7 -> fileManager.mkdir(List.of(line.split(",")));
                            case 8 -> {
                                List<Object> l = getStringInt(line);
                                fileManager.mkdir((String) l.get(0), (Integer) l.get(1));
                            }
                        }
                    }catch (Exception e){
                        System.out.println("Error during parsing!");
                    }
                    break;
                case 5:
                    line = sc.nextLine();
                    fileManager.download(getStringString(line)[0],getStringString(line)[1]);
                case 19:
                    fileManager.saveConfig();
                    return;
                default:
                    break;
            }
        }

    }


    private static String[] getStringString(String line){
        return line.split(",");
    }

    private static List<Object> getStringStringInt(String line){
        String[] s = line.split(",");
        List<Object> list = new ArrayList<>();
        list.add(s[0]);
        list.add(s[1]);
        list.add(Integer.parseInt(s[2]));
        return list;
    }

    private static List<Object> getStringList(String line){
        String[] s = line.split(",");
        List<Object> list = new ArrayList<>();
        list.add(s[0]);
        String[] nms = s[1].split(";");
        List<String> names = new ArrayList<>(List.of(nms));
        list.add(names);
        return list;
    }

    private static List<Object> getStringInt(String line){
        String[] s = line.split(",");
        List<Object> list = new ArrayList<>();
        list.add(s[0]);
        list.add(Integer.parseInt(s[1]));
        return list;
    }

    private static List<Object> getStringListInt(String line){
        String[] s = line.split(",");
        List<Object> list = new ArrayList<>();
        list.add(s[0]);
        String[] nms = s[1].split(";");
        List<String> names = new ArrayList<>(List.of(nms));
        list.add(names);
        list.add(Integer.parseInt(s[2]));
        return list;
    }

    private static List<Object> getStringStringIntInt(String line){
        String[] s = line.split(",");
        List<Object> list = new ArrayList<>();
        list.add(s[0]);
        list.add(s[1]);
        list.add(Integer.parseInt(s[2]));
        list.add(Integer.parseInt(s[3]));
        return list;
    }

    private static List<Object> getStringStringIntBoolean(String line){
        String[] s = line.split(",");
        List<Object> list = new ArrayList<>();
        list.add(s[0]);
        list.add(s[1]);
        System.out.println("pre int");
        list.add(Integer.parseInt(s[2]));
        System.out.println("pre bool");
        list.add(s[3].equalsIgnoreCase("true"));
        return list;
    }


    private static List<Object> getStringStringLongSet(String line){
        String[] s = line.split(",");
        List<Object> list = new ArrayList<>();
        list.add(s[0]);
        list.add(s[1]);
        list.add(Long.parseLong(s[2]));
        String[] ext = s[3].split(";");
        Set<String> extensions = new HashSet<>(List.of(ext));
        list.add(extensions);
        return list;
    }

    private static List<Object> getStringStringLongSetInt(String line){
        String[] s = line.split(",");
        List<Object> list = new ArrayList<>();
        list.add(s[0]);
        list.add(s[1]);
        list.add(Long.parseLong(s[2]));
        String[] ext = s[3].split(";");
        Set<String> extensions = new HashSet<>(List.of(ext));
        list.add(extensions);
        list.add(Integer.parseInt(s[4]));
        return list;
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
        sb.append("CreateRoot:\n");
        sb.append("1. String path, String name\n");
        sb.append("2. String path, String name, int numberOfFilesConstraint\n");
        sb.append("3. String path,String name, Configuration configuration<Long size,Set<String> extenstions divided by ; >\n");
        sb.append("4. String path, String name, Configuration configuration<Long size,Set<String> extenstions divided by ;>, int numberOfFilesConstraint\n");
        createRootOptions = sb.toString();

        sb = new StringBuilder();
        sb.append("Mkdir:\n");
        sb.append("1. String path, String name\n");
        sb.append("2. String path, List<String> names divided by ;\n");
        sb.append("3. String path, List<String> names divided by ; ,int file_n\n");
        sb.append("4. String path, String name, int n, boolean file_n (true if it is constraint)\n");
        sb.append("5. String path, String name, int n, int file_n<no of files contstraint>\n");
        sb.append("6. String name\n");
        sb.append("7. List<String> names\n");
        sb.append("8. String name, int n\n");
        mkdirOptions = sb.toString();

        sb = new StringBuilder();
        sb.append("Filter by extension:\n");
        sb.append("1. String path, String ext\n");
        sb.append("2. String ext\n");
        filterOptions = sb.toString();

        sb = new StringBuilder();

    }

}
