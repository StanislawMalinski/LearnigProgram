package Util;

import java.io.*;
import java.sql.BatchUpdateException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.Random;

public class GrammerGenerator {
    private File file;
    private String [] keys;
    private String [] defs;
    private int cursor;
    private int counter;

    public GrammerGenerator(File file){
        try {
            this.file = file;
            keys = new String[1];
            defs = new String[1];
            cursor = 0;
            grammerLoad();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void grammerLoad() throws IOException {
        BufferedReader BR = new BufferedReader(new FileReader(file));
        String line;
        String [] buff;
        while((line = BR.readLine()) != null){
            line = line.replaceAll(" ", "");
            buff = line.split("=");
            SizeUp();
            keys[cursor] = buff[0];
            defs[cursor] = buff[1];
            cursor++;
        }
    }

    private void SizeUp(){
        String [] nKeys = new String[cursor+1];
        String [] nDefs = new String[cursor+1];
        System.arraycopy(keys,0,nKeys,0,cursor);
        System.arraycopy(defs,0,nDefs, 0,cursor);
        keys = nKeys;
        defs = nDefs;
    }

    public String generateRandomeWord(int N){
        String root = keys[0];
        counter = 0;
        Random random = new Random();
        String [] buff;
        while( counter < N){
            System.out.println(root);
            counter++;
            buff = root.split("\\+");
            for( int i = 0; i < buff.length; i++){
                String [] option = getDefsForKey(buff[i]);
                if( option.length > 0)
                    buff[i] = option[random.nextInt(0,option.length)];
            }
             root = mergeStrings(buff);
        }
        return root;
    }

    private String [] getDefsForKey(String key){
        String [] defs = new String [1];
        defs [0] = key;
        int cursor = 1;
        for( int i = 0; i < keys.length; i++){
            if(key.equals(keys[i])){
                defs = SizeUpTable(defs);
                defs[cursor] = this.defs[i];
                cursor++;
            }
        }
        return defs;
    }

    private String [] SizeUpTable(String [] table){
        String [] nTabele = new String[table.length + 1];
        System.arraycopy(table, 0, nTabele, 0, table.length);
        return nTabele;
    }

    private String mergeStrings(String ...a){
        String out = a[0];
        for(int i = 1; i < a.length; i++)
            out += "+" + a[i];
        return out;
    }
}
