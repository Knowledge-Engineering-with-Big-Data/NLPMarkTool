package com.zhang.nlp;

import org.apache.commons.csv.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TsvParser {

    public static ArrayList<List<String>> getTsv(String path) throws IOException {
        Reader in = new FileReader(path);
        CSVFormat format = CSVFormat.newFormat('\t');
        ArrayList<List<String>> res = new ArrayList<>();
        CSVParser parser = CSVParser.parse(in, format);
        for (CSVRecord record : parser) {
            res.add(record.toList());
        }
        return res;
    }

    public synchronized static void saveToTsv(ArrayList<Sentence> sentences, String path) throws IOException {
        CSVFormat.Builder builder = CSVFormat.Builder.create();
        builder.setDelimiter('\t').setAutoFlush(true).setAllowMissingColumnNames(false);
        builder.setQuote(null);
        CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(path,true), builder.build());
        for (Sentence sentence : sentences) {
            csvPrinter.printRecord(sentence.getDocid(), sentence.getSenIndex(), sentence.getSentTxt(),
                    Utils.formatStrings(sentence.getTokens()),
                    Utils.formatStrings(sentence.getLemmas()),
                    Utils.formatStrings(sentence.getPos_tags()),
                    Utils.formatStrings(sentence.getNer_tags()),
                    Utils.formatIntergers(sentence.getDoc_offsets()),
                    Utils.formatStrings(sentence.getDep_types()),
                    Utils.formatIntergers(sentence.getDep_tokens()),
                    sentence.getSentiment()
            );
        }
        csvPrinter.flush();
        csvPrinter.close();
    }

}
