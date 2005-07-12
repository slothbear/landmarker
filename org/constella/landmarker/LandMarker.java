package org.constella.landmarker;
/*
 * Created on Aug 9, 2004
 */

/**
 * @author orion
 */
public class LandMarker {


    public LandMarker() {
    // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        
        Mailbox.collectMessages() ;
        
        RegionReport.process(Mailbox.messages()) ;
        
        Mapper.process(RegionReport.getCompleteReports()) ;
        
        System.out.println("LandMarker done.") ;
        }
}
