import java.awt.image.BufferedImage;
    import java.io.File;
    import java.io.IOException;
    import javax.imageio.ImageIO;
    import javax.swing.ImageIcon;
    import javax.swing.JLabel;
    import javax.swing.JOptionPane;
    import javax.swing.JTextField;

    public class Captcha {

        /**
         * @param args the command line arguments
         */
        public static void main(String[] args) {
            String CaptchaImage = "C:\\Users\\sabyasachi.mishra\\Desktop\\image.jpg";        

            try {
                BufferedImage image = ImageIO.read(new File(CaptchaImage));
                JLabel picLabel = new JLabel(new ImageIcon(image));
                JTextField captchaInput = new JTextField();

                String SolvedCaptcha = JOptionPane.showInputDialog(null, picLabel);

                System.out.println(SolvedCaptcha);
            } catch (IOException e){
                System.out.println("ERROR: File not found");
            }
        }
    }