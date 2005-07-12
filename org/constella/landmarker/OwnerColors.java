/*
 * Created on Aug 10, 2004
 */
package org.constella.landmarker;

import java.awt.Color ;

class OwnerColors {
    static private Color[] palette = {
//	        new Color(255, 255, 255), 
//	        new Color(136,187,238), 
//	        new Color(255,255,238), 
//	        new Color(204,153,51), 
//	        new Color(51,102,153), 
//	        new Color(102,153,204), 
//	        new Color(51,102,153), 
//	        new Color(192,192,192), 
//	        new Color(51, 255, 204), 
            
            
            new Color(129,96,82),
            new Color(88,160,87),  // current stampshady
            new Color(112,135,169),
            new Color(103,121,81),
            new Color(146,142,188),
            new Color(120,198,182),
            new Color(223,141,57),
            new Color(91,106,178),
            new Color(203,106,114),
            new Color(107,74,122),
            new Color(173,197,78),
            new Color(233,175,61),
            new Color(65,75,163),
            new Color(205,163,144),  // morgan mandala
            new Color(186,70,73),
            new Color(240,208,47),
            new Color(197,103,162),
            new Color(0,146,178),
         
                    // above are from http://www.efg2.com/Lab/Graphics/Colors/ColorCharts.htm
	    
                    
                    new Color(184, 138, 0), 
	        new Color(245, 184, 0), 
	        new Color(255, 102, 51), 
	        new Color(51, 255, 102), 
	        new Color(102, 255, 51), 
	        new Color(204, 255, 51), 
	        new Color(255, 204, 51), 
	        new Color(255, 255, 51), 
	        new Color(245, 245, 0), 
	        new Color(0, 0, 184), 
	        new Color(0, 0, 245), 
	        new Color(153, 51, 255), 
	        new Color(255, 51, 153), 
	        new Color(255, 153, 51), 
	        new Color(228, 149, 175), 
	        new Color(149, 228, 202), 
	        new Color(51, 102, 204), 
	        new Color(51, 179, 204), 
	        new Color(255, 255, 255),		// ff ff ff		white
	        new Color(192, 192, 192),		// c0 c0 c0		silver
	        new Color(128, 128, 128),		// 80 80 80		grey
	        new Color(128, 0, 0),			// 80 00 00		maroon 
	        new Color(255, 0, 0),			// ff 00 00 		red 
	        new Color(128, 0, 128),		// 80 00 80		purple 
	        new Color(255, 0, 255),		// ff 00 ff		fuchsia 
	        new Color(0, 128, 0),			// 00 80 00		green 
	        new Color(0, 255, 0),			// 00 ff 00		lime 
	        new Color(128, 128, 0),		// 80 80 00		olive 
	        new Color(255, 255, 0),		// ff ff 00		yellow 
	        new Color(0, 0, 128),			// 00 00 80		navy 
	        new Color(0, 0, 255),			// 00 00 ff		blue 
	        new Color(0, 128, 128),		// 00 80 80		teal 
	        new Color(0, 255, 255),		// 00 ff ff		aqua 
	        new Color(129, 204, 51)
	        } ;	


    public static Color getOwnerColor(int owner) {
        if (owner < palette.length) {
            return palette[owner] ;
        }
        else {
            return new Color(0, 0, 0) ;
        }
        
    }


}
