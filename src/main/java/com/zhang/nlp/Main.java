package com.zhang.nlp;


import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.concurrent.*;

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

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(6, 8, 0,TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(512));
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
        // build pipeline

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            StringReader reader = new StringReader(line);
            ArrayList<List<String>> articles = TsvParser.getTsv(reader);
            for (List<String> article : articles) {
//            ArrayList<Sentence> parseResults = docunmentParse.getParseResults(article.get(0), Utils.cleanTxt(article.get(1)));
//            TsvParser.saveToTsv(parseResults,"./sentences.tsv");
//            taskDoneNum++;
//            System.out.println("[INFO] 第 "+Integer.toString(taskDoneNum)+" 篇文献标记完成！");
                threadPoolExecutor.execute(() -> {
                    String threadInfo = "[ Thread : " + Thread.currentThread().getId() + " ]";
                    try {
                        // build pipeline
                        DocunmentParse docunmentParse = new DocunmentParse(props);
                        ArrayList<Sentence> parseResults = docunmentParse.getParseResults(article.get(0), Utils.cleanTxt(article.get(1)));
                        TsvParser.saveToTsv(parseResults, "./sentences.tsv");
                        System.out.println(threadInfo + " [INFO] " + article.get(0) + " 文献标记完成！");
                    } catch (Exception e) {
                        System.out.println("[ERROR] " + article.get(0) + " 失败！");
                        e.printStackTrace();
                    }
                });
            }

        }

        threadPoolExecutor.shutdown();
        while (true) {
            if (threadPoolExecutor.isTerminated()) {
                System.out.println("SUCCESS!");
                break;
            }
        }
    }
}