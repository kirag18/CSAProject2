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
    private JButton clear;
    private JButton again;
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
        message.setVisible(false);
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

        clear = new JButton("CLEAR");
        clear.addActionListener(this);
        add(clear);
        clear.setVisible(false);

        again = new JButton("PLAY AGAIN");
        again.addActionListener(this);
        add(again);
        again.setVisible(false);

        pressedPlay = false;
        pressedRule = false;
        pressedBack = false;

        submitYLoc = 480;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
      /*  message.setFont(new Font("Eras Bold ITC", Font.BOLD, 60));
        message.setLocation(150, 250);*/

        if(pressedRule){
           /* message.setText("Rules");
            message.setLocation(500, 100);*/
            pressedRule = false;
        }

        if(pressedBack){
            /*message.setText("Welcome to Mastermind");
            message.setLocation(150, 100);*/
            pressedBack = false;
        }

        if(pressedPlay){
           /* message.setText("Welcome to Mastermind");
            message.setLocation(145, 100);*/
            g.drawImage(mastermind, 200, 200, null);
        }

        play.setLocation(400, 400);
        play.setSize(200, 70);
        play.setFont(new Font("Arial", Font.BOLD, 30));
        g.setFont(new Font("Arial", Font.BOLD, 20));

        g.drawString(rulesText,500,500);
        g.setColor(Color.BLACK);
        rules.setLocation(400, 480);
        rules.setSize(200, 70);
        rules.setFont(new Font("Arial", Font.BOLD, 30));

        submit.setLocation(400, submitYLoc);
        submit.setSize(200, 70);
        submit.setFont(new Font("Arial", Font.BOLD, 30));

        clear.setLocation(550, 525);
        clear.setSize(200, 70);
        clear.setFont(new Font("Arial", Font.BOLD, 30));

        again.setLocation(550, 525);
        again.setSize(300, 70);
        again.setFont(new Font("Arial", Font.BOLD, 30));

        backToHome.setFont(new Font("Arial", Font.BOLD, 30));
        backToHome.setLocation(50,100);
        backToHome.setSize(200,70);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton casted = (JButton) e.getSource();
            for(int i = 0; i < colors.length; i ++){
                if(casted == colors[i]&&game.getInputIdx()<4){//Todo:print message when full already
                    System.out.println(options[i]);
                    game.addInput(options[i]);
                    game.printGrid();
                }
            }
            if (game.getInputIdx()==4 && casted == submit){
                game.check();
                if(game.getTries()>0){
                    game.clearInput();
                }


                submitYLoc-=40;
                if (game.isWin() || game.getTries()<0){
                    //Todo:print some win/loss messages
                    System.out.println("DONEE");//put something here(offer new game?)
                    again.setVisible(true);
                }
            } else if (casted==submit) {
                System.out.println("You cant submit yet");//create a display message for this!!

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
                clear.setVisible(true);
                repaint();

            }

            if (casted == rules) {
                pressedRule = true;
                // update message to the entered text

                rules.setVisible(false);
                play.setVisible(false);
                backToHome.setVisible(true);

                repaint();
            }

            if (casted == backToHome){
                pressedBack = true;
               // message.setText("Welcome to Mastermind");
                rulesText = "";
                rules.setVisible(true);
                play.setVisible(true);
                backToHome.setVisible(false);
                repaint();

            }

            if (casted == clear){
                game.clearInput();
            }
        }
    }
}