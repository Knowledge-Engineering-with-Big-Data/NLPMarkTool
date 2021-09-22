package com.zhang.nlp;



import java.io.IOException;
import java.util.*;

public class Main {
    public static String text = "Amongst these two sub-basins which showed high C values, a hypersaline evaporative condition is considered responsible for the necessary enrichment in one, while methanogenesis (possibly in conjunction with sulphate reduction processes) might have caused such enrichment in the other.";

    public static void main(String[] args) throws IOException {
        // set up pipeline properties
        Properties props = new Properties();
        // set the list of annotators to run
        props.setProperty("annotators", "tokenize,cleanxml,ssplit,pos,lemma,ner,depparse");
        props.setProperty("pos.model", "edu/stanford/nlp/models/pos-tagger/english-left3words-distsim.tagger");
        props.setProperty("ner.model", "edu/stanford/nlp/models/ner/english.conll.4class.distsim.crf.ser.gz");
        props.setProperty("parse.model", "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
        props.setProperty("parse.maxlen", "100");
//        props.setProperty("ssplit.boundaryTokenRegex", "[.]|[!?]+|[。]|[！？]+");
        // build pipeline
        DocunmentParse docunmentParse = new DocunmentParse(props);
        ArrayList<List<String>> articles = TsvParser.getTsv("./articles.tsv");
        int taskDoneNum = 0;
        for(List<String> article:articles){
            ArrayList<Sentence> parseResults = docunmentParse.getParseResults(article.get(0), Utils.cleanTxt(article.get(1)));
            TsvParser.saveToTsv(parseResults,"./sentences.tsv");
            taskDoneNum++;
            System.out.println("[INFO] 第 "+Integer.toString(taskDoneNum)+" 篇文献标记完成！");
        }
        System.out.println("SUCCESS!!!");
    }
}