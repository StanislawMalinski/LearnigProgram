package FrontEnd.FileFormaterPackage;

import java.io.*;

public class FileEditor {
    private static File file;
    private static File reformatted;

    public FileEditor(File file) {
        FileEditor.file = file;
    }

    protected static File getFile(){
        return file;
    }

    public static String getText() {
        try {
            BufferedReader BR = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder builder = new StringBuilder();
            boolean isFirstLine = true;
            while((line = BR.readLine()) != null){
                if(!isFirstLine)
                    builder.append("\n");
                builder.append(line);
                isFirstLine = false;
            }
            return builder.toString();
        }catch (IOException e){
            return null;
        }
    }

    public void getFileFormaterWindow(){
        FileFormaterWindowRunner runner = new FileFormaterWindowRunner();
        runner.run();
    }
}
