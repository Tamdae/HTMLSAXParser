/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xml.sax.Attributes;
import parser.Classes;
import parser.ContentHandler;
import parser.HTMLSAXException;

/**
 *
 * @author jvazqoter
 */
public class Main {

    static ContentHandler contentHandler = new ContentHandler() {
        @Override
        public void startDocument() throws HTMLSAXException {
            System.out.println("start");
        }

        @Override
        public void endDocument() throws HTMLSAXException {
            System.out.println("end");
        }

        @Override
        public void startElement(String localName, Classes classes, String id) throws HTMLSAXException {
            System.out.println("---tag start---");
            System.out.println(localName);
            if (!id.isEmpty()) {
                System.out.println("id: " + id);
            }
            if (classes.hasClasses()) {
                System.out.print("classes: ");
                for (int i = 0; i < classes.size(); i++) {
                    System.out.print(classes.get(i) + " ");
                }
                System.out.println();
            }
        }

        @Override
        public void endElement(String localName) throws HTMLSAXException {
            System.out.println("---tag start---");
            System.out.println(localName);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws HTMLSAXException {

        }
    };

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, HTMLSAXException {
        File f = new File("index22.html");
        HTMLSAXParser parser = new HTMLSAXParser(contentHandler);
        parser.parse(f);
    }

}
