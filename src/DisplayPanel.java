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
        play = new JButton("PLAY"); //ability to put in png for icon
        //button = new JButton("Rules of the Game");
        play.addActionListener(this);
        add(play);

       /* try {
            mastermind = ImageIO.read(new File("src\\mastermind.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }*/

        /*textField = new JTextField(0);
        add(textField);*/

        rules = new JButton("RULES");
        rules.addActionListener(this);
        add(rules);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Eras Bold ITC", Font.BOLD, 60));
        g.setColor(Color.RED);
        g.drawString(message, 150, 250);
        play.setLocation(400, 400);
        play.setSize(200, 70);
        play.setFont(new Font("Arial", Font.BOLD, 30));
        rules.setLocation(400, 480);
        rules.setSize(200, 70);
        rules.setFont(new Font("Arial", Font.BOLD, 30));

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