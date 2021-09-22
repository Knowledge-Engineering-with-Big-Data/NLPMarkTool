package com.zhang.nlp;

import java.util.List;

public class Sentence {
    private String docid;
    private Integer senIndex;
    private String sentTxt;
    private List<String> tokens;
    private List<String> lemmas;
    private List<String> pos_tags;
    private List<String> ner_tags;
    private List<Integer> doc_offsets;
    private List<String> dep_types;
    private List<Integer> dep_tokens;
    private String sentiment;

    public Sentence(String docid, Integer senIndex, String sentTxt, List<String> tokens, List<String> lemmas, List<String> pos_tags, List<String> ner_tags, List<Integer> doc_offsets, List<String> dep_types, List<Integer> dep_tokens, String sentiment) {
        this.docid = docid;
        this.senIndex = senIndex;
        this.sentTxt = sentTxt;
        this.tokens = tokens;
        this.lemmas = lemmas;
        this.pos_tags = pos_tags;
        this.ner_tags = ner_tags;
        this.doc_offsets = doc_offsets;
        this.dep_types = dep_types;
        this.dep_tokens = dep_tokens;
        this.sentiment = sentiment;

    }

    public String getDocid() {
        return docid;
    }

    public Integer getSenIndex() {
        return senIndex;
    }

    public String getSentTxt() {
        return sentTxt;
    }

    public List<String> getTokens() {
        return tokens;
    }

    public List<String> getLemmas() {
        return lemmas;
    }

    public List<String> getPos_tags() {
        return pos_tags;
    }

    public List<String> getNer_tags() {
        return ner_tags;
    }

    public List<Integer> getDoc_offsets() {
        return doc_offsets;
    }

    public List<String> getDep_types() {
        return dep_types;
    }

    public List<Integer> getDep_tokens() {
        return dep_tokens;
    }
    public String getSentiment() {
        return sentiment;
    }


}
