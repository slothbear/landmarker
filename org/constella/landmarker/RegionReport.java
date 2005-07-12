/*
 * Created on Aug 10, 2004
 */
package org.constella.landmarker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * @author orion
 */
public class RegionReport {

    private String name ;
    // TODO  date should be date, not string
//    private String date ;
    private Map reportParts ;
    private String subParcels = "" ;
    private ArrayList ownerMap ;
    static private HashMap reports = new HashMap() ;

    /*
    static {
        String gerstle = "192,1,16,2,16,3,16,4,14,5,2,1,16,2,16,3,16,4,14,5,2,1,16,2,16,3,16,4,14,5,2,1,16,2,16,3,16,4,14,5,2,1,16,2,16,3,16,4,14,5,2,1,16,2,16,3,16,4,14,5,2,1,16,2,16,3,16,4,14,5,2,1,16,2,16,3,16,4,14,5,2,1,16,2,16,3,16,4,14,5,2,1,16,2,16,3,16,4,14,5,2,1,16,2,16,3,16,4,14,5,2,1,16,2,16,3,16,4,14,5,2,1,16,2,16,3,16,4,14,5,2,1,16,2,16,3,16,1,14,5,2,1,16,2,16,3,16,1,14,5,2,1,16,2,16,3,4,6,4,7,4,8,4,9,14,5,2,1,16,2,16,10,4,6,4,7,4,8,4,9,14,5,2,1,16,2,16,10,4,6,4,7,4,8,4,9,14,5,2,1,16,2,16,10,4,6,4,7,4,8,4,9,14,5,2,1,16,2,16,10,4,6,4,7,4,8,4,9,14,5,2,1,16,2,16,10,4,6,4,7,4,8,4,9,14,5,2,1,16,2,16,10,4,6,4,7,4,8,4,9,14,5,2,1,16,2,16,10,4,6,4,7,4,8,4,9,14,7,2,1,16,2,16,10,16,1,14,7,2,1,16,2,16,10,16,1,14,7,2,1,16,2,16,10,16,1,14,7,2,1,16,2,16,10,8,7,4,11,4,12,14,7,2,1,16,2,16,10,8,7,4,11,4,12,14,7,2,1,16,2,16,10,8,7,4,11,4,12,14,7,2,1,16,13,16,10,8,7,4,11,4,12,14,7,2,1,16,13,16,10,8,7,4,11,4,12,14,7,2,1,16,13,24,7,4,11,4,12,14,7,2,1,16,13,24,7,4,11,4,12,14,7,2,1,16,13,24,7,4,11,4,12,14,7,2,1,16,13,16,7,16,1,14,7,2,1,16,13,16,7,16,1,14,7,2,1,16,13,16,7,16,1,14,7,2,1,23,13,1,14,38,7,2,1,24,13,38,7,2,1,24,13,38,7,2,1,24,13,38,7,2,1,24,13,38,7,2,1,24,13,38,7,2,1,24,13,38,7,2,1,21,15,3,13,38,7,2,1,21,15,6,13,5,7,16,1,14,7,2,1,21,15,6,13,5,7,16,1,14,7,2,1,21,15,6,13,5,7,16,1,14,7,2,1,21,15,6,13,35,7,2,1,21,15,7,13,34,7,2,1,21,15,7,13,34,7,2,1,21,15,9,13,32,7,2,1,21,15,9,13,32,7,2,1,21,15,11,13,30,7,2,1,21,15,11,13,30,7,2,1,21,15,11,13,30,7,2,1,21,15,11,13,16,1,14,7,2,1,21,15,11,13,16,1,14,7,2,1,21,15,11,13,16,1,14,7,2,1,21,15,11,13,16,1,14,7,2,1,21,15,11,13,16,1,14,7,2,1," ;
        String palomarian = "8,1,4,2,4,3,11,4,20,5,17,4,8,1,4,2,4,3,12,4,19,5,17,4,8,1,4,2,4,3,13,4,18,5,17,4,8,1,4,2,4,3,14,4,17,5,17,4,8,1,4,2,4,3,14,4,17,5,17,4,8,1,4,2,4,3,15,4,16,5,17,4,8,1,4,2,4,3,16,4,15,5,11,4,2,6,4,4,8,1,4,2,4,3,16,4,15,5,11,4,2,6,4,4,4,5,4,7,8,8,17,4,16,5,9,4,2,6,4,4,4,5,4,7,8,8,18,4,16,5,8,4,2,6,4,4,4,5,4,7,8,8,22,4,14,5,6,4,2,6,4,4,4,5,4,7,8,8,25,4,12,5,5,4,2,6,4,4,4,5,4,7,8,8,30,4,9,5,3,9,2,6,4,4,4,5,4,7,8,8,31,4,9,5,2,9,2,6,8,5,4,7,8,8,32,4,9,5,1,9,2,6,8,5,4,7,8,8,34,4,8,5,2,6,4,5,17,8,35,4,12,5,17,8,35,4,12,5,17,8,37,4,10,5,17,8,37,4,10,5,17,8,37,4,10,5,17,8,38,4,9,5,17,8,38,4,9,5,18,8,36,4,10,5,18,8,35,4,11,5,18,8,34,4,12,5,18,8,33,4,13,5,18,8,32,4,14,5,18,8,32,4,8,5,6,4,18,8,30,4,9,5,7,4,18,8,30,4,8,5,8,4,18,8,30,4,7,5,9,4,12,5,36,4,7,5,9,4,12,5,36,4,6,5,10,4,12,5,35,4,6,5,11,4,12,5,35,4,5,5,12,4,12,5,34,4,5,5,13,4,14,5,31,4,6,5,13,4,6,10,9,5,30,4,5,5,14,4,6,10,11,5,27,4,5,5,15,4,6,10,4,4,8,5,25,4,5,5,16,4,6,10,6,4,8,5,2,4,1,5,20,4,5,5,16,4,6,10,8,4,12,5,16,4,6,5,16,4,6,10,8,4,12,5,15,4,7,5,16,4,6,10,8,4,15,5,12,4,7,5,16,4,6,10,10,4,16,5,8,4,8,5,16,11,6,10,10,4,18,5,5,4,9,5,16,11,6,10,10,4,32,5,16,11,8,12,8,4,32,5,16,11,8,12,8,4,32,5,16,11,8,12,8,4,32,5,16,11,8,12,8,4,32,5,16,11,8,12,8,4,16,5,1,11,15,5,16,11,8,12,8,4,16,5,3,11,13,5,16,11,8,12,8,4,16,5,6,11,10,5,16,11,8,12,8,4,16,5,7,11,9,5,16,11,8,12,8,13,4,14,4,15,8,4,9,11,7,5,16,11,8,12,8,13,4,14,4,15,8,4,10,11,6,5,16,11,8,12,8,13,4,14,4,15,8,4,11,11,6,5,15,11,8,12,8,13,4,14,4,15,8,4,12,11,5,5,15,11,8,12,8,4,4,14,4,15,8,4,13,11,5,5,14,11,8,12,8,4,4,14,4,15,8,4,14,11,5,5,13,11,8,12,8,4,4,14,4,15,8,4,14,11,6,5,12,11,8,12,8,4,4,14,4,15,8,4,15,11,6,5,11,11," ;
        String clementina = "23,1,41,2,23,1,41,2,23,1,41,2,23,1,20,2,16,1,5,2,25,1,14,2,9,1,4,3,7,1,5,2,48,1,4,3,7,1,5,2,48,1,4,3,7,1,5,2,7,1,8,4,33,1,4,3,7,1,5,2,3,5,4,1,7,5,1,4,33,1,4,3,7,1,5,2,3,5,4,1,8,5,33,1,4,3,7,1,5,2,15,5,15,1,5,2,24,1,5,2,15,5,15,1,5,2,24,1,5,2,15,5,15,1,5,2,24,1,5,2,15,5,15,1,12,6,1,7,16,1,2,7,3,2,15,5,14,1,13,6,13,7,4,1,2,7,3,2,15,5,3,1,24,6,19,7,3,2,15,5,3,1,24,6,19,7,3,2,15,5,3,1,24,6,19,7,3,2,15,5,3,1,10,6,3,4,10,6,20,7,3,2,7,5,8,8,7,9,6,6,4,4,9,6,20,7,3,2,7,5,8,8,7,9,6,6,4,4,9,6,20,7,3,2,7,5,8,8,7,9,6,6,3,4,1,10,9,6,20,7,3,2,1,11,6,5,8,8,7,9,6,6,4,4,9,6,20,7,3,2,7,11,8,8,7,9,6,6,9,4,24,7,3,2,7,11,8,8,7,1,6,6,10,4,23,7,3,2,7,11,12,8,3,1,6,6,5,12,5,4,22,7,3,2,1,1,7,11,16,8,5,6,5,12,4,4,7,13,15,7,4,2,1,1,7,11,8,8,6,5,1,14,6,6,3,12,6,4,7,13,14,7,5,2,1,1,1,11,5,9,1,11,8,8,2,5,5,14,6,6,3,12,6,4,7,13,14,7,5,2,1,1,1,11,6,9,8,8,2,5,5,14,6,6,3,12,6,4,7,13,13,7,1,1,5,2,1,1,1,11,7,9,7,8,7,5,6,6,4,1,5,4,18,15,3,7,5,2,1,1,1,11,7,9,7,8,7,5,6,6,27,15,3,7,5,2,1,1,8,9,7,8,7,5,6,6,27,15,3,7,3,2,3,1,9,9,6,8,7,5,6,6,27,15,3,7,3,2,3,1,9,9,6,8,7,5,6,6,27,15,3,7,2,2,1,16,2,17,1,1,9,9,5,8,8,5,4,6,2,4,13,15,7,7,7,15,3,7,2,2,3,17,1,1,9,9,5,8,8,5,4,6,2,4,13,15,17,7,2,2,4,1,11,9,3,8,8,5,6,2,13,15,17,7,2,2,3,15,1,1,11,9,3,8,1,5,22,8,2,15,2,2,17,7,2,2,3,15,1,1,11,9,3,8,1,5,22,8,2,15,2,2,17,7,2,2,3,15,1,1,11,9,26,8,2,15,2,2,17,7,2,2,3,15,1,1,11,9,26,8,2,2,16,18,3,7,2,2,4,1,11,9,26,8,2,2,16,18,3,7,3,2,3,1,11,9,26,8,2,2,16,18,3,7,3,2,3,1,11,9,26,8,2,2,16,18,3,7,3,2,3,1,15,9,22,8,2,2,16,18,3,7,3,2,3,1,15,9,22,8,5,2,13,18,3,7,3,2,3,1,15,9,22,8,9,2,12,18,3,2,3,1,15,9,22,8,9,2,12,18,3,2,16,1,2,9,22,8,9,2,12,18,3,2,16,1,2,9,22,8,9,2,12,18,5,2,16,1,22,8,5,2,5,19,11,18,5,2,16,1,22,8,5,2,14,19,1,1,6,2,16,1,22,8,5,2,13,19,2,1,6,2,14,1,8,20,15,8,6,2,12,19,3,1,6,2,12,1,10,20,4,21,5,19,7,22,3,19,8,2,6,19,1,1,8,2,11,1,11,20,4,21,5,19,7,22,3,19,8,2,5,19,2,1,8,2,11,1,11,20,4,21,5,19,7,22,3,19,8,2,5,19,2,1,8,2,11,1,11,20,4,21,15,19,8,2,6,19,9,2,1,23,10,1,11,20,4,21,15,19,8,2,5,19,11,2,10,1,11,20,4,21,4,19,17,2,7,19,11,2,10,1,11,20,2,21,41,2,11,1,11,20,1,21,41,2,11,1,12,21,41,2," ;
        
        RegionReport rr ;
        rr = new RegionReport("Palomarian,1") ;
        rr.subParcels = palomarian ;
        rr = new RegionReport("Gerstle,1") ;
        rr.subParcels = gerstle ;
        rr = new RegionReport("Clementina,1") ;
        rr.subParcels = clementina ;

    } */
    
    static public void add(String subject, String content) {
        RegionReport rr ;
        StringTokenizer st = new StringTokenizer(subject, ",") ;
        String regionName = st.nextToken();
        
        if (reports.containsKey(regionName)) {
             rr = (RegionReport) reports.get(regionName) ;
        }
        else {
            rr = new RegionReport(regionName) ;
        }
        Integer spot = Integer.decode(st.nextToken()) ;
        String realContent = getReal(content) ;
        if (spot.intValue() == 0) {
            rr.ownerMap = RegionReport.parseOwners(realContent) ;
        }
        else {
            rr.reportParts.put(spot, realContent) ;
        }
        
    }
    
    /**
0,00000000-0000-0000-0000-000000000000,00000000-0000-0000-0000-000000000000
1,3d6181b0-6a4b-97ef-18d8-722652995cf1,Governor Linden
2,b6ee373f-6ac0-45bd-b0a5-261d74eaf361,Nova Linden
3,ec1ee348-37cb-48e0-897b-858edeb5ee11,Soren Romulus
4,1af2271b-46c8-4baa-91b7-b425efee72bc,Osemab Kothari
5,53b3cec7-83bf-a252-caf6-111c246fc14e,2143cd0a-a1ee-d979-2513-bdc045133611
6,e34ac3f2-7786-4c90-a21a-658af3ff5a6f,Kitra Kothari
7,ecffd2a7-3432-4841-9d95-bc58af256693,valacia Leviathan
8,3d924400-038e-6ad9-920b-cfbb9b40585c,Ryan Linden
9,0812c66a-eda5-9fa5-e493-dbb395ee180f,4b301320-6cb5-c6bc-ce36-f49ed3b5f249
10,3df67be8-6f48-a7f3-989d-9b65b523d1ea,Char Linden
11,617b6a00-bbf6-4826-9b1e-e35923c0783f,David Valentino

     */
    private static ArrayList parseOwners(String realContent) {
        BufferedReader r = new BufferedReader(new StringReader(realContent)) ;
        String line ;
        ArrayList result = new ArrayList() ;
        
        try {
            while (null != (line = r.readLine())) {
                StringTokenizer st = new StringTokenizer(line, ",") ;
                //BUG: need better error checking here.
                if (st.countTokens() != 3) continue ;
                result.add(new Owner(st.nextToken(),st.nextToken(),st.nextToken())) ;
            }
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }       
        
        return result ;
    }

    static private String getReal(String content) {
        final String REAL_MARKER = "\r\n\r\n" ;
        final int MARKER_SIZE = REAL_MARKER.length() ;
        
        String result = "" ;
        int spot = content.indexOf("\r\n\r\n") ;
        if (spot != -1 && content.length() > spot+MARKER_SIZE) {
            result = content.substring(spot+MARKER_SIZE) ;
        }

        return result ;
    }
    
    
    public RegionReport(String nomo) {
        name = nomo ;
        reportParts = new TreeMap() ;
        reports.put(name, this) ;
    }
   
	static public int size() {
	    return reports.size() ;
	    }
	
	public ArrayList getOwnerMap() {
	    return ownerMap ;
	    }
	
    public String getName() {
        return name ;
    }

    public int getPartsSize() {
//        return reportParts.size() ;
        return 0 ;
    }
    
    public String getSubParcels() {
        return subParcels ;
    }    
    
    public String getOwnerName() {
        return "Adam Marker" ;
    }
    
	static public void removeAll() {
	    reports = new HashMap() ;
	}

	static public void process(ArrayList messages) {

	    for (Iterator iter = messages.iterator(); iter.hasNext();) {
            LMmessage lm  = (LMmessage) iter.next() ;
            add(lm.getSubject(), lm.getContent()) ;
        }
	    
	    // coalesce any multi-part msgs....
	    //BUG  assume they are complete for now.
	    for (Iterator mi = reports.values().iterator(); mi.hasNext();) {
            RegionReport rep = (RegionReport) mi.next() ;
            for (Iterator parti = rep.reportParts.values().iterator(); parti.hasNext();) {
                String part = (String) parti.next() ;
                rep.subParcels += part ;
            }  
        }
	}

    public static Map getCompleteReports() {
        return reports ;
    }

    public Owner getOwner(int ownerNum) {
        
        if (ownerNum >= ownerMap.size()) return null ;
        
        return (Owner) ownerMap.get(ownerNum) ;

    }


}
