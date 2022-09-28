package BackEnd.Update;

import BackEnd.Material;
import BackEnd.IOEPackage.Writer;

import java.io.File;
import java.io.IOException;

public class Updater {
    private Material material;
    private File file;

    public Updater(Material material){
        this.material = material;
    }

    public void removeTmpFile(){
        file.delete();
    }

    public File generateNewFile() throws IOException{
        createFile();
        Writer writer = new Writer(file);
        writer.setMaterial(material);
        writer.write();
        return file;
    }

    public File generateNewFile(boolean shouldReplaceFile) throws IOException{
        createFile();
        Writer writer = new Writer(file);
        writer.write();
        if(shouldReplaceFile)
            replaceFiles();
        return file;
    }

    private void createFile() throws IOException{
        String path = material.getFile().getPath().replaceFirst("/" + material.getFile().getName(), "/");
        file = new File(path + "tmp" + material.getFile().getName());
        file.createNewFile();
    }

    private void replaceFiles(){
        String pathName = material.getFile().getPath();
        material.getFile().delete();
        file.renameTo(new File(pathName));
        file = null;
    }
}
