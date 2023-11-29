package edu.graffwhitley.ripdash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class LevelLoader {

    private LevelLoader() {
    }

    public void readFilesInDirectory(String directoryPath) {
        // Get the FileHandle for the directory
        FileHandle dirHandle = Gdx.files.internal(directoryPath);

        // List all files in the directory
        FileHandle[] files = dirHandle.list();

        // Iterate through the files
        for (FileHandle file : files) {
            String contents = file.readString();
            System.out.println("File: " + file.name() + ", Contents: " + contents);
        }
    }

}
