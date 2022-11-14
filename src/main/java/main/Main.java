package main;


import Data.MyException;
import paket.Configuration;
import paket.FileManager;
import paket.RepoManager;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {

    private static String local = "filelocal.FileLocalImpl";
    private static String drive = "paket.GoogleDriveImpl";

    public static void main(String[] args) throws ClassNotFoundException, MyException {

        Class.forName(drive);
        FileManager fileLocal = RepoManager.getRepo();
        fileLocal.createRoot("", "probaRoot");
        fileLocal.mkdir("", "dir",7, false);

    }
}
