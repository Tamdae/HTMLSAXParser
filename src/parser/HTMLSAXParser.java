/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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

/**
 *
 * @author Usuario
 */
public class HTMLSAXParser {
    
    private ContentHandler contentHandler;

    public HTMLSAXParser(ContentHandler contentHandler) {
        if (contentHandler == null) {
            throw new NullPointerException("An ContentHandler Object is Required in order to process the html file");
        }
        this.contentHandler=contentHandler;
    }
    
    public void parse(String fileName){
        parse(new File(fileName));
    }
    
    public void parse(File file){
        String line;
        ArrayList<String> html = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        } catch (FileNotFoundException ex) {
        }
        Pattern tag = Pattern.compile("</?([\\w-]+)(\\s*(class|id|[\\w-]+=['\"][/\\.\\w-\\s]*['\"]))*/?>");
        Pattern tagOpen = Pattern.compile("<([\\w-]+)((\\s*(class|id|[\\w-]+=['\"][/\\.\\w-\\s]*['\"]))*)/?>");
        Pattern tagClose = Pattern.compile("</?([\\w-]+)>");
        try {
            while ((line = br.readLine()) != null) {
                line = line.replaceAll(">", ">#N#");
                line = line.replaceAll("<", "#N#<");
                String[] split = line.split("#N#");
                for (String string : split) {
                    string = string.trim();
                    Matcher matcher = tag.matcher(string);
                    if (matcher.lookingAt()) {
                        html.add(string);
                    }
                }
            }
        } catch (IOException ex) {
        }
        
        try {
            contentHandler.startDocument();
        } catch (HTMLSAXException ex) {
        }
        for (String string : html) {
            Matcher matcherTagOpen = tagOpen.matcher(string);
            Matcher matcherTagClose = tagClose.matcher(string);
            if (matcherTagOpen.lookingAt()) {
                if (matcherTagOpen.groupCount() > 2) {
                    //tagName
                    String[] split = matcherTagOpen.group(2).split("'\\s+");
                    String id = null;
                    Classes classes = new Classes();
                    for (String attr : split) {
                        String[] attrPart = attr.trim().split("=");
                        if (attrPart.length > 1) {
                            String attrName = attrPart[0].trim();
                            String attrValue = attrPart[1].trim().substring(1);
                            int comilla = attrValue.lastIndexOf("\"");
                            if (comilla != -1) {
                                attrValue = attrValue.substring(0, comilla);
                            } else {
                                comilla = attrValue.lastIndexOf("'");
                                if (comilla != -1) {
                                    attrValue = attrValue.substring(0, comilla);
                                }
                            }
//                            System.out.println(attrValue);
                            //atribute
                            if (attrName.equalsIgnoreCase("id")) {
                                id = attrValue;
                            } else if (attrName.equalsIgnoreCase("class")) {
                                if (!attrValue.isEmpty()) {
                                    String[] split1 = attrValue.split(" ");
                                    for (String string1 : split1) {
                                        if (!string1.isEmpty()) {
                                            classes.add(string1);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (id == null) {
                        id = "";
                    }
                    try {
                        contentHandler.startElement(matcherTagOpen.group(1), classes, id);
                    } catch (HTMLSAXException ex) {
                    }
                }
            } else if (matcherTagClose.lookingAt()) {
                try {
                    contentHandler.endElement(matcherTagClose.group(1));
                } catch (HTMLSAXException ex) {
                }
            }
        }
        try {
            contentHandler.endDocument();
        } catch (HTMLSAXException ex) {
        }
    }
    
    
    
}
