// the e-mail version.  rle of pairs.
// w/link message to agent name lookup.  7Aug04

integer FIRST_INDEX = 0 ;
integer MAX_INDEX = 4096 ;
list ownerTags = [ NULL_KEY ] ;
string result = "" ;
float startTime ;
string sendTo = "maps@rascal.cerier.com" ;

string getTag(key owner) {
    integer spot = llListFindList(ownerTags, [owner]) ;
    if (spot == -1) {
        ownerTags += owner ;
        spot = llGetListLength(ownerTags) - 1 ;
//        llWhisper(0, (string) owner + " added at " + (string) spot) ;
        }
//    llWhisper(0, "found " + (string) owner + " at " + (string) spot) ;
    return (string) spot ;
    }

key getOwnerOf(integer index) {
    vector block = <(integer)index/64*4, index%64*4, 10> ;
    key ok = llGetLandOwnerAt(block) ;
//    llWhisper(0, "[" + (string) index + "] owner of " + (string) block + "=" + (string) ok) ;
    return ok ;    
    }

emailResult(integer count) {
    llEmail(sendTo,
        llGetRegionName() + "," + (string) count,
        result) ;
    result = "" ;
    }

default {
        
    on_rez(integer x) {
        llResetScript() ;
        }

    touch_start(integer total_number) {    
        if (llDetectedKey(0) != llGetOwner())
            return ;

        llInstantMessage(llGetOwner(), "Started in " + llGetRegionName() +
            "(" + (string) llGetPos() + ")") ;
        startTime = llGetWallclock() ;
        integer count = 1 ;

        integer index = FIRST_INDEX ;
        
        key ownerKey = getOwnerOf(index) ;
        
        while (index < MAX_INDEX) {
            llSetText(
                (string) index + "/" + (string) llGetFreeMemory(),
                <127, 127, 201>, 1.0) ;
            integer blockCount = 0 ;
            key prevOwnerKey = ownerKey ;
            integer sameOwner = TRUE ;
    
            while (sameOwner) {
                prevOwnerKey = ownerKey ;
                blockCount++ ;
                index++ ;
                            llSetText(
                (string) index + "/" + (string) llGetFreeMemory(),
                <127, 127, 201>, 1.0) ;
                ownerKey = getOwnerOf(index) ;
                sameOwner = (ownerKey == prevOwnerKey) ;
                }     
    
            result += (string) blockCount + "," + getTag(prevOwnerKey) + "\n" ;
            if (llStringLength(result) > 1536) {
                emailResult(count) ;
                count++ ;
                }
            } // until over the index

        emailResult(count) ;

        integer ix ;
        for (ix = 0; ix < llGetListLength(ownerTags); ix++) {
            llMessageLinked(llGetLinkNumber(), ix, "", llList2Key(ownerTags, ix)) ;
            }
 
        // wait for all the dataserver events to complete .... then ask to dump.
        llSleep(30.0) ;
        llMessageLinked(llGetLinkNumber(), -1, "", NULL_KEY) ;  
                 
        string elapsedTime = " " + 
            (string) llRound(((llGetWallclock() - startTime)/60)) +
            " minutes " ;
        llInstantMessage(llGetOwner(), "Finished in " + llGetRegionName() + elapsedTime) ;
//        llEmail(sendTo,
//            llGetRegionName() + ",status", 
//            elapsedTime +
//            "  free=" + (string) llGetFreeMemory()
//            ) ;
            
        }  // touch_start

    }  // default
