package Util;

import java.io.File;

public class Crypto {
    private File file;

    public Crypto(File file){
        this.file = file;
    }

    public boolean isCrypted(){
        return false;
    }

    public File Encrypt(){
        return new File("");
    }

    public File Decrypt(){
        return new File("");
    }
}
