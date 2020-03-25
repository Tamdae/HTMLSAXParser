package parser;

import org.xml.sax.Attributes;

public interface ContentHandler {

    public void startDocument()
            throws HTMLSAXException;

    public void endDocument()
            throws HTMLSAXException;

    public void startElement(String localName,
            Classes classes, String id)
            throws HTMLSAXException;

    public void endElement(String localName)
            throws HTMLSAXException;

    public void characters(char ch[], int start, int length)
            throws HTMLSAXException;
}
