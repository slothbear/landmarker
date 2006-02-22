string sendTo = "maps@rascal.cerier.com" ;

// thanks to the LSL Wiki page regarding lists!
list ListReplaceList(list dest, list src, integer pos) {
    if (pos < 0)
        pos = llGetListLength(dest) + pos;
    return llListInsertList(llDeleteSubList(dest, pos, pos), src, pos);
}

dumpOwners() {
    integer ix ;
    string result = "" ;
        
    for (ix = 0; ix < llGetListLength(ownerNames); ix+=3) {
        result +=
            (string) llList2Integer(ownerNames, ix) + "," +
            (string) llList2Key(ownerNames, ix+1) + "," + 
            llList2String(ownerNames, ix+2) + "\n" ;
    }
        
    llEmail(sendTo,
        llGetRegionName() + ",0",
        result) ;
    }  // dumpOwners


list ownerNames = [ ] ;

default {

    link_message(integer sender_num, integer cmd, string str, key id) {
//        llWhisper(0, "sender=" + (string) sender_num +
//            "  cmd=" + (string) cmd +
//            "  string=" + str +
//            "  key=" + (string) id) ;
            
        if (cmd == -1) {
            dumpOwners() ;
            llDie() ;
        }
        else if (id == NULL_KEY) {
            ownerNames += [cmd, id, id] ;
        }
        else {
            key queryid = llRequestAgentData(id, DATA_NAME) ;
            ownerNames += [cmd, id, queryid] ;
            llSetTimerEvent(5.0) ;
            }
    }
    
    dataserver(key queryid, string ownerName) {
        integer spot = llListFindList(ownerNames, [queryid]) ;
        
        if (spot != -1) {
            ownerNames = ListReplaceList(ownerNames, [ownerName], spot) ;
        }
        
        else {
            llInstantMessage(llGetOwner(), "uh oh .... query not found in list: "
                + (string) queryid
                + "   data: " + ownerName) ;
        }
    }  // dataserver
    
    
    timer() {
        llSetTimerEvent(0.0) ;
    }  // timer

}  // default
