/*
 * Created on Aug 10, 2004
 */
package org.constella.landmarker ;

import java.awt.Color ;
import java.awt.Font;
import java.awt.Graphics2D ;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage ;
import java.awt.image.RenderedImage ;
import java.io.File ;
import java.io.IOException ;
import java.util.ArrayList;
import java.util.Iterator ;
import java.util.Map ;

import javax.imageio.ImageIO ;
//import java.util.Locale;
//import javax.imageio.IIOImage;
//import javax.imageio.ImageWriteParam;
//import javax.imageio.ImageWriter;
//import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
//import javax.imageio.stream.ImageOutputStream;

/**
 * @author orion
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class Mapper {

	final static double CCW_90 = -Math.PI/2 ;
	final static int MAP_SIZE = 512 ;
	final static int ROW_SIZE = 64 ;
	final static double SCALE = MAP_SIZE / ROW_SIZE ;

    public Mapper() {
        super() ;
        // TODO Auto-generated constructor stub
    }

    /**
     * @param completeReports
     */
    public static void process(Map completeReports) {

        for (Iterator it = completeReports.entrySet().iterator(); it.hasNext();) {
            Map.Entry me = (Map.Entry) it.next() ;
            String name = (String) me.getKey() ;
            RegionReport rr = (RegionReport) me.getValue() ;
            System.out.println("mapping: " + name) ;
            RenderedImage rendImage = createImage(rr) ;
            File file = new File("maps/" + name + ".jpg") ;
 
            try {
                ImageIO.write(rendImage, "jpg", file) ;
                // ImageIO.write(rendImage, "png", new File(name + ".png")) ;
            }
            catch (IOException e) {
                System.out.println("exception while writing map.jpeg.") ;
                e.printStackTrace() ;
            }
            
            rendImage = ownerImage(rr) ;
            file = new File("maps/" + name + "Owners.jpg") ;
            try {
                ImageIO.write(rendImage, "jpg", file) ;
                // ImageIO.write(rendImage, "png", new File(name + "Owners.png")) ;
            }
            catch (IOException e) {
                System.out.println("exception while writing owner.jpeg.") ;
                e.printStackTrace() ;
            }            
/*            
//          Find a jpeg writer
            ImageWriter writer = null;
            Iterator iter = ImageIO.getImageWritersByFormatName("jpg");
            if (iter.hasNext()) {
                writer = (ImageWriter)iter.next();
            }
// Prepare output file
            ImageOutputStream ios = null ;
            try {
                ios = ImageIO.createImageOutputStream(file) ;
            }
            catch (IOException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
            writer.setOutput(ios);
//          Set the compression quality
            ImageWriteParam iwparam = new MyImageWriteParam();
            iwparam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT) ;
            iwparam.setCompressionQuality((float) 0.0);
            try {
                // Write the image
                writer.write(null, new IIOImage(rendImage, null, null), iwparam);
            }
            catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
*/

        }

    }

    private static RenderedImage createImage(RegionReport rr) {

        // Create a buffered image in which to draw
        BufferedImage bufferedImage = new BufferedImage(MAP_SIZE, MAP_SIZE,
                BufferedImage.TYPE_INT_RGB) ;
        // Create a graphics contents on the buffered image
        Graphics2D g2d = bufferedImage.createGraphics() ;
        g2d.setColor(Color.black);
        //TODO  need to set default overflow to something other than black,
        // or set this default background to something else.  colors colors colors.
        g2d.fillRect(0, 0, MAP_SIZE, MAP_SIZE);

        // SL coordinates work like math x/y graph
        // Java coordinates start at top left.  Rotate 90 degrees
        // counterclockwise to compensate.
        AffineTransform at = new AffineTransform();
        at.concatenate(AffineTransform.getRotateInstance(CCW_90, MAP_SIZE/2.0, MAP_SIZE/2.0));
        at.scale(SCALE, SCALE);
        g2d.transform(at) ;
        
        String s = rr.getSubParcels() ;

//    	Gerstle
//         s = "192,1,16,2,16,3,16,4,14,5,2,1,16,2,16,3,16,4,14,5,2,1,16,2,16,3,16,4,14,5,2,1,16,2,16,3,16,4,14,5,2,1,16,2,16,3,16,4,14,5,2,1,16,2,16,3,16,4,14,5,2,1,16,2,16,3,16,4,14,5,2,1,16,2,16,3,16,4,14,5,2,1,16,2,16,3,16,4,14,5,2,1,16,2,16,3,16,4,14,5,2,1,16,2,16,3,16,4,14,5,2,1,16,2,16,3,16,4,14,5,2,1,16,2,16,3,16,4,14,5,2,1,16,2,16,3,16,1,14,5,2,1,16,2,16,3,16,1,14,5,2,1,16,2,16,3,4,6,4,7,4,8,4,9,14,5,2,1,16,2,16,10,4,6,4,7,4,8,4,9,14,5,2,1,16,2,16,10,4,6,4,7,4,8,4,9,14,5,2,1,16,2,16,10,4,6,4,7,4,8,4,9,14,5,2,1,16,2,16,10,4,6,4,7,4,8,4,9,14,5,2,1,16,2,16,10,4,6,4,7,4,8,4,9,14,5,2,1,16,2,16,10,4,6,4,7,4,8,4,9,14,5,2,1,16,2,16,10,4,6,4,7,4,8,4,9,14,7,2,1,16,2,16,10,16,1,14,7,2,1,16,2,16,10,16,1,14,7,2,1,16,2,16,10,16,1,14,7,2,1,16,2,16,10,8,7,4,11,4,12,14,7,2,1,16,2,16,10,8,7,4,11,4,12,14,7,2,1,16,2,16,10,8,7,4,11,4,12,14,7,2,1,16,13,16,10,8,7,4,11,4,12,14,7,2,1,16,13,16,10,8,7,4,11,4,12,14,7,2,1,16,13,24,7,4,11,4,12,14,7,2,1,16,13,24,7,4,11,4,12,14,7,2,1,16,13,24,7,4,11,4,12,14,7,2,1,16,13,16,7,16,1,14,7,2,1,16,13,16,7,16,1,14,7,2,1,16,13,16,7,16,1,14,7,2,1,23,13,1,14,38,7,2,1,24,13,38,7,2,1,24,13,38,7,2,1,24,13,38,7,2,1,24,13,38,7,2,1,24,13,38,7,2,1,24,13,38,7,2,1,21,15,3,13,38,7,2,1,21,15,6,13,5,7,16,1,14,7,2,1,21,15,6,13,5,7,16,1,14,7,2,1,21,15,6,13,5,7,16,1,14,7,2,1,21,15,6,13,35,7,2,1,21,15,7,13,34,7,2,1,21,15,7,13,34,7,2,1,21,15,9,13,32,7,2,1,21,15,9,13,32,7,2,1,21,15,11,13,30,7,2,1,21,15,11,13,30,7,2,1,21,15,11,13,30,7,2,1,21,15,11,13,16,1,14,7,2,1,21,15,11,13,16,1,14,7,2,1,21,15,11,13,16,1,14,7,2,1,21,15,11,13,16,1,14,7,2,1,21,15,11,13,16,1,14,7,2,1," ;
//         //   Palomarian
//      String s = "8,1,4,2,4,3,11,4,20,5,17,4,8,1,4,2,4,3,12,4,19,5,17,4,8,1,4,2,4,3,13,4,18,5,17,4,8,1,4,2,4,3,14,4,17,5,17,4,8,1,4,2,4,3,14,4,17,5,17,4,8,1,4,2,4,3,15,4,16,5,17,4,8,1,4,2,4,3,16,4,15,5,11,4,2,6,4,4,8,1,4,2,4,3,16,4,15,5,11,4,2,6,4,4,4,5,4,7,8,8,17,4,16,5,9,4,2,6,4,4,4,5,4,7,8,8,18,4,16,5,8,4,2,6,4,4,4,5,4,7,8,8,22,4,14,5,6,4,2,6,4,4,4,5,4,7,8,8,25,4,12,5,5,4,2,6,4,4,4,5,4,7,8,8,30,4,9,5,3,9,2,6,4,4,4,5,4,7,8,8,31,4,9,5,2,9,2,6,8,5,4,7,8,8,32,4,9,5,1,9,2,6,8,5,4,7,8,8,34,4,8,5,2,6,4,5,17,8,35,4,12,5,17,8,35,4,12,5,17,8,37,4,10,5,17,8,37,4,10,5,17,8,37,4,10,5,17,8,38,4,9,5,17,8,38,4,9,5,18,8,36,4,10,5,18,8,35,4,11,5,18,8,34,4,12,5,18,8,33,4,13,5,18,8,32,4,14,5,18,8,32,4,8,5,6,4,18,8,30,4,9,5,7,4,18,8,30,4,8,5,8,4,18,8,30,4,7,5,9,4,12,5,36,4,7,5,9,4,12,5,36,4,6,5,10,4,12,5,35,4,6,5,11,4,12,5,35,4,5,5,12,4,12,5,34,4,5,5,13,4,14,5,31,4,6,5,13,4,6,10,9,5,30,4,5,5,14,4,6,10,11,5,27,4,5,5,15,4,6,10,4,4,8,5,25,4,5,5,16,4,6,10,6,4,8,5,2,4,1,5,20,4,5,5,16,4,6,10,8,4,12,5,16,4,6,5,16,4,6,10,8,4,12,5,15,4,7,5,16,4,6,10,8,4,15,5,12,4,7,5,16,4,6,10,10,4,16,5,8,4,8,5,16,11,6,10,10,4,18,5,5,4,9,5,16,11,6,10,10,4,32,5,16,11,8,12,8,4,32,5,16,11,8,12,8,4,32,5,16,11,8,12,8,4,32,5,16,11,8,12,8,4,32,5,16,11,8,12,8,4,16,5,1,11,15,5,16,11,8,12,8,4,16,5,3,11,13,5,16,11,8,12,8,4,16,5,6,11,10,5,16,11,8,12,8,4,16,5,7,11,9,5,16,11,8,12,8,13,4,14,4,15,8,4,9,11,7,5,16,11,8,12,8,13,4,14,4,15,8,4,10,11,6,5,16,11,8,12,8,13,4,14,4,15,8,4,11,11,6,5,15,11,8,12,8,13,4,14,4,15,8,4,12,11,5,5,15,11,8,12,8,4,4,14,4,15,8,4,13,11,5,5,14,11,8,12,8,4,4,14,4,15,8,4,14,11,5,5,13,11,8,12,8,4,4,14,4,15,8,4,14,11,6,5,12,11,8,12,8,4,4,14,4,15,8,4,15,11,6,5,11,11," ;
//   Clementina
//      String s = "23,1,41,2,23,1,41,2,23,1,41,2,23,1,20,2,16,1,5,2,25,1,14,2,9,1,4,3,7,1,5,2,48,1,4,3,7,1,5,2,48,1,4,3,7,1,5,2,7,1,8,4,33,1,4,3,7,1,5,2,3,5,4,1,7,5,1,4,33,1,4,3,7,1,5,2,3,5,4,1,8,5,33,1,4,3,7,1,5,2,15,5,15,1,5,2,24,1,5,2,15,5,15,1,5,2,24,1,5,2,15,5,15,1,5,2,24,1,5,2,15,5,15,1,12,6,1,7,16,1,2,7,3,2,15,5,14,1,13,6,13,7,4,1,2,7,3,2,15,5,3,1,24,6,19,7,3,2,15,5,3,1,24,6,19,7,3,2,15,5,3,1,24,6,19,7,3,2,15,5,3,1,10,6,3,4,10,6,20,7,3,2,7,5,8,8,7,9,6,6,4,4,9,6,20,7,3,2,7,5,8,8,7,9,6,6,4,4,9,6,20,7,3,2,7,5,8,8,7,9,6,6,3,4,1,10,9,6,20,7,3,2,1,11,6,5,8,8,7,9,6,6,4,4,9,6,20,7,3,2,7,11,8,8,7,9,6,6,9,4,24,7,3,2,7,11,8,8,7,1,6,6,10,4,23,7,3,2,7,11,12,8,3,1,6,6,5,12,5,4,22,7,3,2,1,1,7,11,16,8,5,6,5,12,4,4,7,13,15,7,4,2,1,1,7,11,8,8,6,5,1,14,6,6,3,12,6,4,7,13,14,7,5,2,1,1,1,11,5,9,1,11,8,8,2,5,5,14,6,6,3,12,6,4,7,13,14,7,5,2,1,1,1,11,6,9,8,8,2,5,5,14,6,6,3,12,6,4,7,13,13,7,1,1,5,2,1,1,1,11,7,9,7,8,7,5,6,6,4,1,5,4,18,15,3,7,5,2,1,1,1,11,7,9,7,8,7,5,6,6,27,15,3,7,5,2,1,1,8,9,7,8,7,5,6,6,27,15,3,7,3,2,3,1,9,9,6,8,7,5,6,6,27,15,3,7,3,2,3,1,9,9,6,8,7,5,6,6,27,15,3,7,2,2,1,16,2,17,1,1,9,9,5,8,8,5,4,6,2,4,13,15,7,7,7,15,3,7,2,2,3,17,1,1,9,9,5,8,8,5,4,6,2,4,13,15,17,7,2,2,4,1,11,9,3,8,8,5,6,2,13,15,17,7,2,2,3,15,1,1,11,9,3,8,1,5,22,8,2,15,2,2,17,7,2,2,3,15,1,1,11,9,3,8,1,5,22,8,2,15,2,2,17,7,2,2,3,15,1,1,11,9,26,8,2,15,2,2,17,7,2,2,3,15,1,1,11,9,26,8,2,2,16,18,3,7,2,2,4,1,11,9,26,8,2,2,16,18,3,7,3,2,3,1,11,9,26,8,2,2,16,18,3,7,3,2,3,1,11,9,26,8,2,2,16,18,3,7,3,2,3,1,15,9,22,8,2,2,16,18,3,7,3,2,3,1,15,9,22,8,5,2,13,18,3,7,3,2,3,1,15,9,22,8,9,2,12,18,3,2,3,1,15,9,22,8,9,2,12,18,3,2,16,1,2,9,22,8,9,2,12,18,3,2,16,1,2,9,22,8,9,2,12,18,5,2,16,1,22,8,5,2,5,19,11,18,5,2,16,1,22,8,5,2,14,19,1,1,6,2,16,1,22,8,5,2,13,19,2,1,6,2,14,1,8,20,15,8,6,2,12,19,3,1,6,2,12,1,10,20,4,21,5,19,7,22,3,19,8,2,6,19,1,1,8,2,11,1,11,20,4,21,5,19,7,22,3,19,8,2,5,19,2,1,8,2,11,1,11,20,4,21,5,19,7,22,3,19,8,2,5,19,2,1,8,2,11,1,11,20,4,21,15,19,8,2,6,19,9,2,1,23,10,1,11,20,4,21,15,19,8,2,5,19,11,2,10,1,11,20,4,21,4,19,17,2,7,19,11,2,10,1,11,20,2,21,41,2,11,1,11,20,1,21,41,2,11,1,12,21,41,2," ;
//   Teal 8/08
//      String s = "19,1,42,2,3,3,19,1,42,2,3,3,31,1,33,3,31,1,33,3,31,1,33,3,31,1,33,3,31,1,33,3,26,1,5,4,33,3,26,1,5,4,23,3,4,5,6,3,26,1,5,4,19,3,8,5,6,3,26,1,5,4,19,3,8,5,6,3,18,1,4,2,4,1,5,4,21,3,12,5,18,1,4,2,4,1,5,4,22,3,11,5,18,1,4,2,4,1,5,4,22,3,11,5,14,3,7,2,17,3,8,2,7,3,11,5,14,3,7,2,17,3,8,2,7,3,11,5,14,3,7,2,17,3,8,2,7,3,11,5,14,3,7,2,17,3,8,2,7,3,11,5,14,3,4,2,20,3,8,2,7,3,11,5,13,3,1,6,4,2,20,3,8,2,7,3,11,5,12,3,6,2,22,3,12,2,12,5,12,3,6,2,22,3,12,2,12,5,12,3,5,2,23,3,12,2,12,5,12,3,5,2,10,3,3,2,10,3,12,2,12,5,12,3,5,2,10,3,3,2,10,3,12,2,1,3,11,7,12,3,5,2,10,3,3,2,10,3,12,2,1,3,11,7,12,3,5,2,10,3,3,2,10,3,12,2,12,7,9,3,8,2,10,3,3,2,13,3,9,2,12,7,9,3,8,2,10,3,3,2,13,3,9,2,12,7,9,3,3,2,15,3,3,2,13,3,9,2,12,7,9,3,3,2,15,3,3,2,13,3,9,2,12,7,9,3,3,2,15,3,3,2,13,3,9,2,12,7,10,2,13,3,5,2,15,3,9,2,3,7,2,8,7,5,10,2,13,3,5,2,15,3,9,2,3,7,2,8,7,5,10,2,13,3,5,2,14,3,1,6,9,2,12,5,10,2,13,3,5,2,13,3,2,6,9,2,12,5,10,2,13,3,5,2,13,3,2,6,9,2,12,5,10,2,13,3,5,2,13,3,2,6,9,2,12,5,10,2,13,3,5,2,10,3,14,2,12,5,10,2,12,3,5,2,11,3,14,2,12,5,22,3,5,2,11,3,14,2,12,5,22,3,5,2,11,3,14,2,12,5,22,3,5,2,11,3,5,2,7,6,14,5,22,3,5,2,4,3,12,2,7,6,14,5,22,3,5,2,4,3,12,2,7,6,14,5,22,3,21,2,7,6,14,5,22,3,21,2,7,6,14,5,22,3,18,2,11,6,13,5,22,3,18,2,15,6,9,5,22,3,16,2,17,6,9,5,22,3,16,2,17,6,9,5,22,3,16,2,17,6,9,5,22,3,16,2,17,6,9,5,22,3,17,2,16,6,9,5,22,3,17,2,16,6,9,5,22,3,17,2,16,6,9,5,22,3,17,2,16,6,9,5,22,3,17,2,16,6,9,5,22,3,17,2,16,6,9,5,22,3,17,2,6,9,8,3,11,5,22,3,17,2,6,9,8,3,11,5,22,3,17,2,6,9,9,3,10,5,22,3,17,2,7,9,8,3,10,5,22,3,17,2,7,9,8,3,10,5," ;
//   Sage
//      String s = "2,1,19,2,16,3,27,4,2,1,19,2,16,3,27,4,21,2,16,3,27,4,21,2,16,3,27,4,21,2,16,3,27,4,21,2,16,3,27,4,21,2,43,4,1,1,20,2,43,4,21,2,43,4,21,2,43,4,21,2,43,4,21,2,43,4,1,2,11,5,9,2,43,4,1,2,11,5,9,2,43,4,12,5,9,2,40,4,3,6,12,5,9,2,40,4,3,6,12,5,3,2,46,4,3,6,61,4,3,6,61,4,3,6,61,4,3,6,61,4,3,6,61,4,3,6,53,4,11,6,53,4,11,6,6,7,22,4,8,8,17,4,11,6,6,7,22,4,8,8,17,4,11,6,6,7,22,4,8,8,17,4,11,6,6,7,22,4,8,8,17,4,11,6,6,7,47,4,11,6,6,7,47,4,11,6,6,7,47,4,11,6,6,7,47,4,11,6,6,7,47,4,11,6,1,4,5,7,47,4,11,6,1,4,5,7,47,4,11,6,53,4,11,6,53,4,11,6,53,4,11,6,53,4,11,6,53,4,11,6,53,4,11,6,1,9,1,4,12,10,39,4,11,6,2,9,12,10,39,4,11,6,2,9,13,10,38,4,11,6,2,9,13,10,29,4,9,9,1,3,10,6,2,9,13,10,29,4,9,9,1,3,10,6,2,9,13,10,29,4,9,9,1,3,10,6,2,9,13,10,27,4,1,11,1,4,9,9,1,3,10,6,2,9,13,10,38,9,1,3,10,6,2,9,13,10,38,9,1,3,10,6,2,9,13,10,38,9,1,3,10,6,53,9,1,3,10,6,53,9,1,3,10,6,55,9,9,12,55,9,9,12,55,9,9,12,55,9,9,12,55,9,9,12,55,9,9,12,55,9,9,12,55,9,9,12,55,9,9,12,55,9,9,12,55,9,9,12," ;
        
        java.util.StringTokenizer st = new java.util.StringTokenizer(s, ",\r\n ") ;

        int xx = 0 ;
        int yy = 0 ;
        while (st.hasMoreTokens()) {
            int count = Integer.parseInt(st.nextToken()) ;
            //BUG:  should really do something more about missing element.
            if (!st.hasMoreTokens()) continue ;
            int ownerNum = Integer.parseInt(st.nextToken()) ;
            Owner owner = rr.getOwner(ownerNum) ;
            Color cc ;
            if (owner.isLinden()) {
                cc = new Color(100,100,100) ;
            }
            else {
                cc = OwnerColors.getOwnerColor(ownerNum) ;   
            }
            g2d.setColor(cc) ;

            while (xx + count >= ROW_SIZE) {
                int finishRow = ROW_SIZE - xx ;
                
                g2d.fillRect(xx, yy, finishRow, 1) ;
                
                // trial of svg
                // <rect x="0" y="8" width="512" height="8"
                //    style="fill:rgb(255,255,255)"/>
//                char q = '"' ;
//                System.out.println(
//                        "<rect x=" + q + xx + q + " y=" + q + yy + q +
//                        " width=" + q + finishRow + q + " height=" + q + "1" + q +
//                        " style=" + q + "fill:rgb(" + 
//                        cc.getRed() + "," + cc.getGreen() + "," + cc.getBlue() 
//                        + ")" + q + "/>") ;
                
                count -= finishRow ;
                yy++ ;
                xx = 0 ;
            }
            if (count > 0) {                
                g2d.fillRect(xx, yy, count, 1) ;
               
//                char q = '"' ;
//                System.out.println(
//                        "<rect x=" + q + xx + q + " y=" + q + yy + q +
//                        " width=" + q + count + q + " height=" + q + "1" + q +
//                        " style=" + q + "fill:rgb(" + 
//                        cc.getRed() + "," + cc.getGreen() + "," + cc.getBlue() 
//                        + ")" + q + "/>") ;

                
                xx += count ;
            }

        } // while more tokens        
        
        // Graphics context no longer needed so dispose it
        g2d.dispose() ;

        return bufferedImage ;
    }

    private static RenderedImage ownerImage(RegionReport rr) {

        // Create a buffered image in which to draw
        BufferedImage bufferedImage = new BufferedImage(MAP_SIZE, MAP_SIZE,
                BufferedImage.TYPE_INT_RGB) ;
        // Create a graphics contents on the buffered image
        Graphics2D g2d = bufferedImage.createGraphics() ;
        g2d.setColor(Color.white);
        //TODO  need to set default overflow to something other than black,
        // or set this default background to something else.  colors colors colors.
        g2d.fillRect(0, 0, MAP_SIZE, MAP_SIZE);
        
        String family = "Sans-Serif";
        int style = Font.PLAIN;
        int size = 12;
        Font font = new Font(family, style, size);
        g2d.setFont(font);

        ArrayList owners = rr.getOwnerMap() ;
        System.out.println(owners.size() + " owners.") ;
        int yy = 20 ;
        int boxX = 14 ;
        int nameX = boxX + 26 ; ;

        for (Iterator all = owners.iterator(); all.hasNext();) {
            Owner owner = (Owner) all.next() ;
            int ownerNum = owner.getNumber() ;
            Color cc = OwnerColors.getOwnerColor(ownerNum) ;
            g2d.setColor(cc) ;
            g2d.fillRect(boxX, yy, 16, 16) ;
            String ownerName = owner.getName() ;
            int spot = ownerName.indexOf("-") ;
            if (spot > 0) {
                ownerName = ownerName.substring(0,8) ;
            }
            g2d.drawString(ownerName, nameX, yy+13) ;
            yy += 24 ;
            
            if (yy+48 > MAP_SIZE) {
                yy = 20 ;
                boxX = boxX + MAP_SIZE/2 ;
                nameX = boxX + 26 ;
                
            }
        }
  
/*
                // trial of svg
                // <rect x="0" y="8" width="512" height="8"
                //    style="fill:rgb(255,255,255)"/>
                char q = '"' ;
                System.out.println(
                        "<rect x=" + q + xx + q + " y=" + q + yy + q +
                        " width=" + q + finishRow + q + " height=" + q + "1" + q +
                        " style=" + q + "fill:rgb(" + 
                        cc.getRed() + "," + cc.getGreen() + "," + cc.getBlue() 
                        + ")" + q + "/>") ;
 */
                        
        // Graphics context no longer needed so dispose it
        g2d.dispose() ;

        return bufferedImage ;
    }
    
}

/*
// This class overrides the setCompressionQuality() method to workaround
// a problem in compressing JPEG images using the javax.imageio package.
class MyImageWriteParam extends JPEGImageWriteParam {
    public MyImageWriteParam() {
        super(Locale.getDefault());
    }

    // This method accepts quality levels between 0 (lowest) and 1 (highest) and simply converts
    // it to a range between 0 and 256; this is not a correct conversion algorithm.
    // However, a proper alternative is a lot more complicated.
    // This should do until the bug is fixed.
    public void setCompressionQuality(float quality) {
        if (quality < 0.0F || quality > 1.0F) {
            throw new IllegalArgumentException("Quality out-of-bounds!");
        }
        this.compressionQuality = 256 - (quality * 256);
    }
}
*/