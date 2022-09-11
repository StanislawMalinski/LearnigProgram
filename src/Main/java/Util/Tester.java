package Util;

import java.io.File;

public class Tester {
    public static void main(String[] args) {
        /*File file = new File(Information.locationOfTheSubjects + "/enctmp.txt");
        Encryptor encryptor = new Encryptor(file);
        encryptor.HideContentOfFile();*/
        Encryptor encryptor1 = new Encryptor(new File(Information.locationOfTheSubjects + "/enctmpH.txt"));
        encryptor1.ShowContentOfFile();
    }
}
