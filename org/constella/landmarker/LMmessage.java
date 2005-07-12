/*
 * Created on Aug 10, 2004
 */
package org.constella.landmarker;

/**
 * @author orion
 */
public class LMmessage {
//    private String date ;
    private String subject ;
    private String content ;
    
    public LMmessage(String subject, String content) {
        this.subject = subject ;
        this.content = content ;
    }
    
    public String getContent() {
        return content ;
    }
    

    public String getSubject() {
        return subject ;
    }
}
