/*
 * Created on Aug 10, 2004
 */
package org.constella.landmarker;

import java.util.ArrayList;
import java.util.Iterator;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

public class Mailbox {

    private static Session _session ;
    private static ArrayList _messages = new ArrayList() ;
    
	public static void collectMessages() {

		String protocol = "imap";
		String host = "mail.rascal.cerier.com";
		String user = "maps";
		String password = "ldollars";
		String root = "INBOX";
	
		_session = Session.getDefaultInstance(
			System.getProperties(), null);
		_session.setDebug(false);
		try {
			Store store = _session.getStore(protocol);
			store.connect(host, user, password);
			Folder rf = store.getFolder(root);
			getMessages(rf);
			store.close() ;
		}
		catch (Exception e) {
		    System.out.println("exception while getting the mail\n" + e) ;
		}
	}

	private static void getMessages(Folder folder) throws Exception {
		folder.open(Folder.READ_WRITE);
		Message[] msgs = folder.getMessages();
		System.out.println(msgs.length +" messages in " + folder.getName() + '(' +
			folder.getFullName() + ')');

		for (int i=0; i<msgs.length; i++) {
			Message m = msgs[i];							
	        String content = (String) m.getContent() ;
	        LMmessage lm = new LMmessage(m.getSubject(), content) ;
	        _messages.add(lm) ;
		}  // for each msg

		folder.close(true) ;
	}  // listfolder

	public static int howMany() {
	    return _messages.size() ;
	}
	
	public static void printMessages() {
	    for (Iterator iter = _messages.iterator(); iter.hasNext();) {
            LMmessage lm  = (LMmessage) iter.next() ;
            System.out.println(lm) ;
        }
	}
	
	public static ArrayList messages() {
	    return _messages ;
	}
	
}
