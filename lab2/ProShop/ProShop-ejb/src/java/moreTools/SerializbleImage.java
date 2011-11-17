/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package moreTools;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

/**
 *
 * @author Admin
 */
public class SerializbleImage implements Serializable {
   int width;
   int height;
   int[][] colors = null;

   private void ofBufferedImage(BufferedImage image)
   {
       this.width = image.getWidth();
       this.height = image.getHeight();
       this.colors = new int[width][height];
       for (int x = 0; x < width; x++)
           for (int y = 0; y < height; y++)
               colors[x][y]=image.getRGB(x, y);
   }

   public SerializbleImage(BufferedImage image)
   {
        this.ofBufferedImage(image);
   }

   public SerializbleImage(File file) throws Exception
   {
       try
       {
            ofBufferedImage(javax.imageio.ImageIO.read(file));
       }
       catch(IOException ex)
       {
           throw new Exception("Ошибка чтения картинки");
       }
   }

   public SerializbleImage(java.io.InputStream stream) throws Exception
   {
       try
       {
            ofBufferedImage(javax.imageio.ImageIO.read(stream));
       }
       catch(IOException ex)
       {
           throw new Exception("Ошибка чтения картинки");
       }
   }
   
   public BufferedImage getImage() {
       BufferedImage result = new BufferedImage(width,
               height, BufferedImage.TYPE_INT_RGB);
       for (int i=0;i<width;i++)
           for (int j=0;j<height;j++)
               result.setRGB(i,j,colors[i][j]);

       return result;
   }


}

