/*
 * Created on Aug 13, 2004
 */
package org.constella.landmarker;

public class Owner {
    int ordinal ;
    String key ;
    String name ;
    
    
    public Owner(String ordText, String key, String name) {
        this.ordinal = Integer.parseInt(ordText) ;
        this.key = key ;
        this.name = name ;
        // TODO Auto-generated constructor stub
    }
    
    public String toString() {
        return ordinal + ":" + key + "=" + name ;
    }

    public int getNumber() {
        return ordinal ;
    }
    
    public String getKey() {
        return key ;
    }
    
    public String getName() {
        return name ;
    }

    /**
     * @return
     */
    public boolean isLinden() {
        return name.endsWith(" Linden") ;
    }
    
}
