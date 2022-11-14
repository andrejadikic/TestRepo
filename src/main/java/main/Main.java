package main;



import paket.FileManager;
import paket.RepoManager;


public class Main {

    private static String local = "filelocal.FileLocalImpl";
    private static String drive = "paket.GoogleDriveImpl";

    public static void main(String[] args){
        try {
            Class.forName(drive);
            FileManager fileLocal = RepoManager.getRepo();
            fileLocal.createRoot("C:\\Users\\Vanja\\Desktop", "probaRoot");
            fileLocal.mkdir("", "dir",7, false);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
