package com.zhang.nlp;


import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        // set up pipeline properties
        Properties props = new Properties();
        // set the list of annotators to run
        props.setProperty("annotators", "tokenize,cleanxml,ssplit,pos,lemma,ner,depparse,parse");
        props.setProperty("pos.model", "edu/stanford/nlp/models/pos-tagger/english-left3words-distsim.tagger");
//        props.setProperty("ner.model", "edu/stanford/nlp/models/ner/english.conll.4class.distsim.crf.ser.gz");
        props.setProperty("depparse.model", "edu/stanford/nlp/models/parser/nndep/english_UD.gz");
        props.setProperty("parse.maxlen", "100");
        props.setProperty("ssplit.boundaryTokenRegex", "[.]|[!?]+|[。]|[！？]+");
        props.setProperty("threads","2");

        ArrayList<List<String>> articles = TsvParser.getTsv("./articles.tsv");
        DocunmentParse docunmentParse = new DocunmentParse(props);

        for (List<String> article : articles) {
            ArrayList<Sentence> parseResults = docunmentParse.getParseResults(article.get(0), Utils.cleanTxt(article.get(1)));
            TsvParser.saveToTsv(parseResults, "./sentences.tsv");
            System.out.println( " [INFO] " + article.get(0) + " 文献标记完成！");

        }
        System.out.println("SUCCESS!");
    }
}