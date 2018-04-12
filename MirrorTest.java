import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class MirrorTest {
    

    public static void setRGB(int x, int y, int rgb, BufferedImage image) {
        if ((x >= 0) && (x < image.getWidth()) && (y >= 0) && (y < image.getHeight())) {
            image.setRGB(x, y, rgb);
        }
    }

    public static int getRGB(int x, int y, BufferedImage image) {
        if ((x >= 0) && (x < image.getWidth()) && (y >= 0) && (y < image.getHeight())) {
            return image.getRGB(x, y);
        } else {
            return 0;
        }
    }
    
    public void mirrored(BufferedImage inputImage,String outFile){
        
        BufferedImage outputImage = new BufferedImage(inputImage.getWidth()*2,
                inputImage.getHeight(), inputImage.getType());
        
        for (int x = 0; x < inputImage.getWidth(); x++) {
            
            for (int y = 0; y < outputImage.getHeight(); y++) {
                
                int getRGB = getRGB(x, y, inputImage);
                setRGB(inputImage.getWidth() - x, y, getRGB, outputImage);
                setRGB(inputImage.getWidth() + x, y, getRGB, outputImage);
            }
        }

        boolean error = false; 
                
        File out = new File(outFile);
        try {
            ImageIO.write(outputImage, "jpg", out);
        } catch (IOException ex) {ex.printStackTrace(); error = true;}

        if(!error) System.out.println("Output file was created !");
        
    }
    
    
    public BufferedImage getBufferedImage(String inputFile){
        BufferedImage inputImage = null;
        try {
            inputImage = ImageIO.read(new File(inputFile));
        } catch (IOException ex) {
            System.out.println("Input file was not found !");
            System.exit(0);
        }
        return inputImage;
    }
    
    public static void main(String[] args) {
        int argCount = args.length;
        if(argCount==0){
            System.out.println("Not given any input or output jpg");
        }else if(argCount==1){
            System.out.println("Complete the input parameters");
        }else{
            MirrorTest mt = new MirrorTest();
            mt.mirrored(mt.getBufferedImage(args[0]), args[1]);
        }
        
    }
}
