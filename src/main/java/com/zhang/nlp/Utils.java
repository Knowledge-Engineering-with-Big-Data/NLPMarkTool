package com.zhang.nlp;

import java.util.List;

public class Utils {
    public static String formatIntergers(List<Integer> integerList){
        String res = integerList.toString();
        return res.replace(" ","").replace("[","{").replace("]","}");
    }
    public static String formatStrings(List<String> stringList){
        String res = "{";
        for(int i=0;i<stringList.size();i++){
            if(i<stringList.size()-1){
                res += '"'+stringList.get(i)+'"'+",";
            }else{
                res += '"'+stringList.get(i)+'"';
            }
        }
        res += "}";
        return res;
    }
    public static String cleanTxt(String t){
        return t.replace("\t","").replace("\n","").replace('"',' ');
    }
}
