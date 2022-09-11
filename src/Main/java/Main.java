import BackEnd.Material;
import BackEnd.Reader;
import BackEnd.Teacher;
import FrontEnd.GUIrunner;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        GUIrunner guirunner = new GUIrunner();
        guirunner.run(args);
    }

    public void NewGame() {
        File file = new File("Cos.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Reader reader = new Reader(file);
        Material material = reader.read();
        Teacher teacher = new Teacher(material);
    }
}
