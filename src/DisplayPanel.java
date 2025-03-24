import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DisplayPanel extends JPanel implements ActionListener {
    //private String message;
    private String rulesText;
    private JButton play;
    private JButton backToHome;
    private JButton rules;
    private JButton submit;
    private JLabel message;
    private boolean pressedRule;
    private boolean pressedPlay;
    private boolean pressedBack;
    private int submitYLoc;

    private JButton[] colors;
    private BufferedImage mastermind;
    private boolean PlayisClicked;
    private String[] options;
    private MastermindLogic game;



    //TODO: Create a welcome panel and then have mastermind board show up when button pressed
    //TODO: Will add different button reactions and text visuals
    //TODO: All buttons will be changed wtih different icons


    public DisplayPanel() {

        message = new JLabel ("Welcome to Mastermind");
        message.setVisible(true);
        add(message);
        game = new MastermindLogic("Kira");
        colors = new JButton[8];
        options = new String[]{"red","green", "blue" , "yellow", "brown", "orange", "black", "white"};
        for(int i = 0; i < options.length; i ++){
            colors[i] = new JButton(options[i]);
            colors[i].addActionListener(this);
            add(colors[i]);
            colors[i].setVisible(false);
        }
        //message = ("Welcome to Mastermind!");
        play = new JButton("PLAY"); //ability to put in png for icon
        PlayisClicked = false;
        rulesText = ("");
        //button = new JButton("Rules of the Game");
        play.addActionListener(this);
        add(play);

        backToHome = new JButton("BACK");
        backToHome.addActionListener(this);
        add(backToHome);
        backToHome.setVisible(false);


        try {
            mastermind = ImageIO.read(new File("src\\mastermind.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        rules = new JButton("RULES");
        rules.addActionListener(this);
        add(rules);

        submit = new JButton("SUBMIT");
        submit.addActionListener(this);
        add(submit);
        submit.setVisible(false);

        pressedPlay = false;
        pressedRule = false;
        pressedBack = false;

        submitYLoc = 480;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        message.setFont(new Font("Eras Bold ITC", Font.BOLD, 60));
        message.setLocation(150, 250);

        if(pressedRule){
            message.setText("Rules");
            message.setLocation(450, 100);
            pressedRule = false;
        }

        if(pressedBack){
            message.setText("Welcome to Mastermind");
            message.setLocation(150, 250);
            pressedBack = false;
        }

        play.setLocation(400, 400);
        play.setSize(200, 70);
        play.setFont(new Font("Arial", Font.BOLD, 30));
        g.setFont(new Font("Arial", Font.BOLD, 20));

        g.drawString(rulesText,500,500);
        if(PlayisClicked) {
            g.drawImage(mastermind, 200, 50, null);
        }
        g.setColor(Color.BLACK);
        rules.setLocation(400, 480);
        rules.setSize(200, 70);
        rules.setFont(new Font("Arial", Font.BOLD, 30));

        submit.setLocation(400, submitYLoc);
        submit.setSize(200, 70);
        submit.setFont(new Font("Arial", Font.BOLD, 30));


        backToHome.setFont(new Font("Arial", Font.BOLD, 30));
        backToHome.setLocation(50,100);
        backToHome.setSize(200,70);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton casted = (JButton) e.getSource();
            for(int i = 0; i < colors.length; i ++){
                if(casted == colors[i]){
                    System.out.println(options[i]);
                    game.addInput(options[i]);
                    game.printGrid();
                }
            }
            if (game.getInputIdx()==4 && casted == submit){
                game.check();
                game.clearInput();
                submitYLoc-=40;

            }
            if (game.isWin() || game.getTries()<0){
                System.out.println("DONEE");//put something here(offer new game?)
            }
            if (casted == play) {
                //message = "CLICKED!";
                play.setVisible(false);
                PlayisClicked = true;
                rules.setVisible(false);
                play.setVisible(false);
                for(int i = 0; i < colors.length; i ++){
                    colors[i].setVisible(true);
                }
                //game.play();
                pressedPlay = true;
                submit.setVisible(true);
                repaint();

            }

            if (casted == rules) {
                pressedRule = true;
                // update message to the entered text

                rulesText = "Hello Rules are here";
                rules.setVisible(false);
                play.setVisible(false);
                backToHome.setVisible(true);

                repaint();
            }

            if (casted == backToHome){
                pressedBack = true;
                message.setText("Welcome to Mastermind");
                rulesText = "";
                rules.setVisible(true);
                play.setVisible(true);
                backToHome.setVisible(false);
                repaint();

            }
        }
    }
}