import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DisplayPanel extends JPanel implements ActionListener {
    private String message;
    private JButton play;
    private JButton rules;
    private BufferedImage mastermind;
    private JTextField textField;

    //TODO: Create a welcome panel and then have mastermind board show up when button pressed
    //TODO: Will add different button reactions and text visuals


    public DisplayPanel() {
        message = ("Welcome to Mastermind!");
        play = new JButton("PLAY");
        //button = new JButton("Rules of the Game");
        play.addActionListener(this);
        add(play);

       /* try {
            mastermind = ImageIO.read(new File("src\\mastermind.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }*/

        textField = new JTextField(10);
        add(textField);

        rules = new JButton("RULES");
        rules.addActionListener(this);
        add(rules);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.setColor(Color.RED);
        g.drawString(message, 300, 100);
        play.setLocation(300, 300);
        g.drawImage(mastermind, 200, 50, null);
        g.setColor(Color.BLACK);
        //g.drawString("goomba!", 200, 110);
        //textField.setLocation(50, 60);
        rules.setLocation(300, 330);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton casted = (JButton) e.getSource();
            if (casted == play) {
                message = "CLICKED!";
                repaint();
            }

            if (casted == rules) {
                // obtain string from text field
                String enteredText = textField.getText();

                // update message to the entered text
                message = enteredText;

                // refresh the screen so that the updated message gets displayed
                repaint();
            }
        }
    }
}