default
{
    state_entry()
    {
        llSetTimerEvent(30*60) ;
    }

    timer() {
        llInstantMessage(llGetOwner(), "30 minutes passed .... self destruct sequence started in " + llGetRegionName() + " at " + (string) llGetPos()) ;
        llDie() ;
    }
}
