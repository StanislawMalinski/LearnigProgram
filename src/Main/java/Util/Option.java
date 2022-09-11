package Util;

import java.util.function.Consumer;

public class Option {
    private String [] MethodNames;
    private Consumer<Object> [] MethodReferances;
    private int Size;

    public Option(){
        Size = 0;
        MethodNames = new String[1];
        MethodReferances = new Consumer[1];
    }

    public void addOption(String optionName,Consumer<Object> method){
        sizeUp();
        MethodNames[Size-1] = optionName;
        MethodReferances[Size-1] = method;
    }

    public void addOption(Option addedOption){
        for(int i = 0; i < addedOption.getSize(); i++){
            addOption(addedOption.getMethodNames()[i],addedOption.getMethodReferances()[i]);
        }
    }


    private void sizeUp(){
        String [] NMethodNames = new String[Size+1];
        Consumer<Object> [] NMethodReferances = new Consumer[Size+1];

        System.arraycopy(MethodNames,0,NMethodNames,0,Size);
        System.arraycopy(MethodReferances,0,NMethodReferances,0,Size);

        MethodNames = NMethodNames;
        MethodReferances = NMethodReferances;

        Size++;
    }

    public String [] getMethodNames(){
        return getMethodNames();
    }

    public Consumer<Object> [] getMethodReferances(){
        return MethodReferances;
    }

    public int getSize(){
        return Size;
    }
}
