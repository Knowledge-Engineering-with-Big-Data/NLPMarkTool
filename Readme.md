## NLPTool with corenlp 4.2.2

### 1. input file
The input file is articles.tsv, contains two columns of docid and content, and the file does not require a header.
The file is placed at the root of the project.
### 2. run
```bash
# java version 8+
# maven version 3.8.2
mvn clean
mvn install
mvn exec:java -Dexec.mainClass="com.zhang.nlp.Main"
```
### 3.output file
A sentences.tsv file with ten columns per row.
```
- doc_id         text
- sentence_index int
- sentence_text  text
- tokens         text[]
- lemmas         text[]
- pos_tags       text[]
- ner_tags       text[]
- doc_offsets    int[]
- dep_types      text[]
- dep_tokens     int[]
```
