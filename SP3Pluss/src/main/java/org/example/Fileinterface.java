package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public interface Fileinterface {

    public ArrayList<String> readUserData(String filePath);

    public void saveUserData(String Filepath, HashMap<String, String> Users);

    public void createUserFolder(User user);

    public void createTextFile(File folder, String fileName, ArrayList<Media> dataList);

    public ArrayList<String> readMediaData(String filePath);
}
