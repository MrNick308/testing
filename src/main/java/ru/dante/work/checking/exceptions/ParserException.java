package ru.dante.work.checking.exceptions;

import org.jsoup.nodes.Document;

public class ParserException extends Exception {
    public ParserException(Document document) {
        super("Parsing error. Checked document: " + document);
    }
}
