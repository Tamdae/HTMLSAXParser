/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Classes {

    ArrayList<String> elementClasses = new ArrayList<>();

    protected void add(String elementClass) {
        this.elementClasses.add(elementClass);
    }

    public String get(int index) {
        return this.elementClasses.get(index);
    }

    public boolean hasClasses() {
        return !this.elementClasses.isEmpty();
    }

    public int size() {
        return this.elementClasses.size();
    }

}
