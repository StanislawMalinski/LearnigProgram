package BackEnd.IOEPackage;

import BackEnd.Material;
import BackEnd.Question;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;

public class Writer {
    private File stream;
    private Material material;

    public Writer(File stream) {
        this.stream = stream;
    }

    public void setMaterial(Material material){
        this.material = material;
    }

    public void write() throws IOException {
        if(material == null) return;
        BufferedWriter BW = new BufferedWriter(new FileWriter(stream));
        Iterator<Question> iter = material.Iterator();
        Question question;
        String line;
        String infoLine = createInfoLine();
        BW.write(infoLine);
        while(iter.hasNext()){
            question = iter.next();
            line = createLine(question);
            BW.write(line);
        }
        BW.close();
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
}
