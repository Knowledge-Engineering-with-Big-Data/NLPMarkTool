package com.zhang.nlp;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

/**
 * @author zhang
 */
public class Main {

    public static Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) throws IOException{
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,cleanxml,ssplit,pos,lemma,ner,depparse,parse");
        props.setProperty("pos.model", "edu/stanford/nlp/models/pos-tagger/english-left3words-distsim.tagger");
        props.setProperty("ner.model", "edu/stanford/nlp/models/ner/english.conll.4class.distsim.crf.ser.gz");
        props.setProperty("depparse.model", "edu/stanford/nlp/models/parser/nndep/english_UD.gz");
        props.setProperty("parse.maxlen", "100");
        props.setProperty("ssplit.boundaryTokenRegex", "[.]|[!?]+|[。]|[！？]+");
        props.setProperty("threads","64");
        DocunmentParse docunmentParse = new DocunmentParse(props);

        LineIterator iterator = FileUtils.lineIterator(new File("./articles.tsv"), "UTF-8");
        while (iterator.hasNext()){
            String line = iterator.nextLine();
            StringReader reader = new StringReader(line);
            ArrayList<List<String>> articles = TsvParser.getTsv(reader);
            for (List<String> article : articles) {
                ArrayList<Sentence> parseResults = docunmentParse.getParseResults(article.get(0), Utils.cleanTxt(article.get(1)));
                TsvParser.saveToTsv(parseResults, "./sentences.tsv");
                logger.info(article.get(0) + " 文献标记完成！");
            }
        }
        iterator.close();

        logger.info("SUCCESS!");
    }
}