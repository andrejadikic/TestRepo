package main;


import Data.MyException;
import paket.Configuration;
import paket.FileManager;
import paket.RepoManager;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

echo "# TestRepo" >> README.md
        git init
        git add README.md
        git commit -m "first commit"
        git branch -M main
        git remote add origin https://github.com/andrejadikic/TestRepo.git
        git push -u origin main

public class Main {

    private static String local = "filelocal.FileLocalImpl";
    private static String drive = "paket.GoogleDriveImpl";

    public static void main(String[] args) throws ClassNotFoundException, MyException {

        Class.forName(drive);
        FileManager fileLocal = RepoManager.getRepo();
        fileLocal.createRoot("/Users/andrejadikic/Documents/SKProjekat","root",new Configuration(),8);
        File file = new File("/Users/andrejadikic/Documents/SKProjekat");
        fileLocal.mkdir("","dir1",7,true);
    }
}
