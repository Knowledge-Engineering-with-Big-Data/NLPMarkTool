package com.zhang.nlp;

import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphEdge;
import edu.stanford.nlp.util.Pair;

import java.util.*;

public class DocunmentParse {
    static StanfordCoreNLP pipeline;
    public DocunmentParse(Properties props){
        pipeline = new StanfordCoreNLP(props);
    }
    public ArrayList<Sentence> getParseResults(String docid,String text){
        ArrayList<Sentence> res = new ArrayList<>();
        CoreDocument document = pipeline.processToCoreDocument(text);
        int index = 1;
        for (CoreSentence sentence : document.sentences()) {
            String sent = sentence.text();
            int sentIndex = index++;
            List<String> tokens = sentence.tokensAsStrings();
            List<String> lemmas = sentence.lemmas();
            List<String> pos_tags = sentence.posTags();
            List<String> ner_tags = sentence.nerTags();
            List<Integer> doc_offsets = new ArrayList<>();
            List<String> dep_types = new ArrayList<>(Collections.nCopies(ner_tags.size()," "));
            List<Integer> dep_parents = new ArrayList<>(Collections.nCopies(ner_tags.size(),0));
            Pair<Integer, Integer> offsets= sentence.charOffsets();
            Integer doc_offsets_bgein = offsets.first;
            for(int i=0;i<tokens.size();i++){
                doc_offsets.add(doc_offsets_bgein);
                doc_offsets_bgein += tokens.get(i).length();
            }
            SemanticGraph semanticGraph = sentence.dependencyParse();
            for(SemanticGraphEdge edge:semanticGraph.edgeIterable()){
                dep_types.set(edge.getTarget().index()-1,edge.getRelation().toString());
                dep_parents.set(edge.getTarget().index()-1,edge.getSource().index());
            }
            Sentence sentence1 = new Sentence(docid, sentIndex, sent, tokens, lemmas, pos_tags, ner_tags, doc_offsets, dep_types, dep_parents,sentence.sentiment());
            res.add(sentence1);
        }
        return res;
    }
}
