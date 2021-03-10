package com.company;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) throws IOException {

        for(int i = 0; i < args.length;i++){
            if(args[i].equals("-niu")){
                System.out.println("Ne me psuvai a ti ne izkriva kancheto");
            }
        }

        int width = 256*4;
        int height = 256*4;
        int colorVariationStep = 10;

        if(args.length > 0){
            if(Integer.parseInt(args[0]) > 0){
                width = Integer.parseInt(args[0]);
            }
            if(Integer.parseInt(args[1]) > 0){
                height = Integer.parseInt(args[1]);
            }
            if(Integer.parseInt(args[2]) > 0){
                colorVariationStep = Integer.parseInt(args[2]);
            }

        }

        System.out.println("Generating " + width + "x" + height + " map");

        // Constructs a BufferedImage of one of the predefined image types.
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Create a graphics which can be used to draw into the buffered image
        Graphics2D g2d = bufferedImage.createGraphics();

        // fill all the image with white
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, width, height);

        int a = (int)(Math.random()*250);

        // create a circle with black
        int x = height/2;
        int y = width/2;

        for(int i = 0; i < args.length; i++){
            if(args[i].equals("-r")){
                x = (int)(Math.random()*width);
                y = (int)(Math.random()*height);
            }
        }

        for(int i = 0; i < width*height; i++){
            int as = (int)(Math.random()*2);
            if(as == 0){
                a = a + (int)(Math.random()*colorVariationStep);
            }else if(as == 1){
                a = a - (int)(Math.random()*colorVariationStep);
            }

            if(a > 255 - colorVariationStep){
                a = 255 - colorVariationStep;
            }else if(a < colorVariationStep){
                a = colorVariationStep;
            }

            g2d.setColor(new Color(a,a,a));

            int direction = (int)(Math.random()*8);
            switch (direction){
                case 0:
                    x++;
                    break;
                case 1:
                    y++;
                    break;
                case 2:
                    x--;
                    break;
                case 3:
                    y--;
                    break;
                case 4:
                    x++;
                    y++;
                    break;
                case 5:
                    x++;
                    y--;
                    break;
                case 6:
                    x--;
                    y--;
                    break;
                case 7:
                    x--;
                    y++;
                    break;
            }
            if(x >= width){
                x = 0;
            }else if(x < 0){
                x = width-1;
            }

            if(y >= height){
                y = 0;
            }else if(y < 0){
                y = height-1;
            }

            g2d.fillOval(x,y,10,10);
        }

        // Disposes of this graphics context and releases any system resources that it is using.
        g2d.dispose();

        // Save as PNG
        File file = new File("noise.png");
        ImageIO.write(bufferedImage, "png", file);
        DisplayImage displayImage = new DisplayImage();
        displayImage.setVisible(true);
        /* Save as JPEG
        file = new File("myimage.jpg");
        ImageIO.write(bufferedImage, "jpg", file);*/
    }
}
