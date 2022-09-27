package BackEnd.Update;

import BackEnd.Material;
import BackEnd.Question;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;

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
        String path = material.getFile().getPath().replaceFirst("/" + material.getFile().getName(), "/");
        file = new File(path + "tmp" + material.getFile().getName());
        BufferedWriter BW = null;
        file.createNewFile();
        BW = new BufferedWriter(new FileWriter(file));
        Iterator<Question> iter = material.Iterator();
        Question question;
        String info = createInfoLine();
        BW.write(info);
        String line;
        while(iter.hasNext()) {
            question = iter.next();
            line = createLine(question);
            BW.write(line);
        }
        BW.close();
        return file;
    }

    public File generateNewFile(boolean shouldReplaceFile) throws IOException{
        String path = material.getFile().getPath().replaceFirst("/" + material.getFile().getName(), "/");
        file = new File(path + "tmp" + material.getFile().getName());
        BufferedWriter BW = null;
        file.createNewFile();
        BW = new BufferedWriter(new FileWriter(file));
        Iterator<Question> iter = material.Iterator();
        Question question;
        String info = createInfoLine();
        BW.write(info);
        String line;
        while(iter.hasNext()) {
            question = iter.next();
            line = createLine(question);
            BW.write(line);
        }
        BW.close();
        if(shouldReplaceFile)
            replaceFiles();
        return file;
    }

    private void replaceFiles(){
        String pathName = material.getFile().getPath();
        material.getFile().delete();
        file.renameTo(new File(pathName));
        file = null;
    }

    private String createInfoLine(){
        LocalDate myObj = LocalDate.now();
        int time = material.getTime();
        int hours = Math.round(time/3600);
        int minutes = time % 60;
        return "$(<lastLearned>" + myObj+ ")$(<time>" + hours + ":" + minutes + ")$\n";
    }

    private String createLine(Question question){
        StringBuilder line = new StringBuilder();
        line.append("$(<id>").append(question.id).append(")");
        line.append("$(<def>").append(question.definition).append(")");
        line.append("$(<ans>").append(question.answer).append(")");
        line.append("$(<sequence>").append(question.sequence).append(")");
        line.append("$(<att>").append(question.attempts).append(")");
        line.append("$(<suc>").append(question.successes).append(")");
        if(question.hint != null)
            line.append("$(<hint>").append(question.hint).append(")");
        if(question.format != null)
            line.append("$(<format>").append(question.format).append(")");
        for(int i = 0; i < question.numberOfAspects; i++)
            line.append("$(<asspect" + i + ">").append(question.aspects[i]).append(")");
        line.append("$\n");
        return line.toString();
    }

    public File getTmpFile() {
        return file;
    }
}
