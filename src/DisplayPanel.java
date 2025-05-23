import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.nio.file.Path;
import java.nio.file.Paths;


public class DisplayPanel extends JPanel implements ActionListener {
    private JButton play;
    private JButton backToHome;
    private JButton rules;
    private JButton submit;
    private JButton clear;
    private JButton again;
    private JLabel message;
    private Icon playButton;
    private Icon rulesButton;
    private Icon backButton;
    private Icon submitButton;
    private Icon clearButton;
    private JLabel background;
    private JFrame frame;


    private boolean pressedRule;
    private boolean pressedPlay;
    private boolean pressedBack;
    private int submitYLoc;

    private JButton[] colors;
    private BufferedImage mastermind;
    private BufferedImage homeBack;
    private BufferedImage rulesBack;
    private BufferedImage playBack;
    private BufferedImage bg;
    private boolean PlayisClicked;
    private String[] options;
    private MastermindLogic game;
    private Player person;
    private Color[] colorObjs;
    private String WLMessage;

    public DisplayPanel() {

        WLMessage = "";
        game = new MastermindLogic();
        person = new Player();
        colors = new JButton[8];
        colorObjs = new Color[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, new Color(139, 69, 19), Color.ORANGE, Color.BLACK, Color.WHITE};
        options = new String[]{"red","green", "blue" , "yellow", "pink", "orange", "black", "white"};
        for(int i = 0; i < options.length; i ++){
            colors[i] = new JButton("");//options[i]);
            colors[i].addActionListener(this);
            add(colors[i]);
            colors[i].setVisible(false);
            colors[i].setBackground(colorObjs[i]);
            colors[i].setOpaque(false);
            colors[i].setContentAreaFilled(false);
            colors[i].setBorderPainted(false);
            //colors[i].setContentAreaFilled(false);


        }

        //TODO: Commented out the src// because it wasn't working on macboook. if src/ doesn't work uncomment

//        ImageIcon rulesButton = new ImageIcon("src//RULESButton.png");
//        ImageIcon submitButton = new ImageIcon("src//SUBMITButton.png");
//        ImageIcon playButton = new ImageIcon("src//PLAYButton.png");
//        ImageIcon backButton = new ImageIcon("src//BACKButton.png");

        //ImageIcon rulesButton = new ImageIcon("src/RULESButton.png");
        ImageIcon rulesButton = new ImageIcon(Paths.get("src", "RULESButton.png").toString());
        ImageIcon submitButton = new ImageIcon(Paths.get("src", "SUBMITButton.png").toString());
        ImageIcon playButton = new ImageIcon(Paths.get("src", "PLAYButton.png").toString());
        ImageIcon clearButton = new ImageIcon(Paths.get("src", "CLEARButton.png").toString());
        ImageIcon backButton = new ImageIcon(Paths.get("src", "BACKButton.png").toString());
        play = new JButton("PLAY"); //ability to put in png for icon
        PlayisClicked = false;
        play.addActionListener(this);
        play.setIcon(playButton);
        add(play);

        backToHome = new JButton("BACK");
        backToHome.setIcon(backButton);
        backToHome.addActionListener(this);
        add(backToHome);
        backToHome.setVisible(false);

        try {
            homeBack = ImageIO.read(new File(Paths.get("src", "HOMEBackground.png").toString()));
            bg = homeBack;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            mastermind = ImageIO.read(new File(Paths.get("src", "mastermind.png").toString()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try{
            rulesBack = ImageIO.read(new File(Paths.get("src", "RULESBackground.png").toString()));
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        try{
            playBack = ImageIO.read(new File(Paths.get("src", "PLAYBackground.png").toString()));
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        //System.out.println("Current dir: " + System.getProperty("user.dir"));

        rules = new JButton("RULES");
        rules.setIcon(rulesButton);
        rules.setBorderPainted(false);
        rules.setBorder(null);
        rules.addActionListener(this);
        add(rules);

        submit = new JButton("SUBMIT");
        submit.addActionListener(this);
        submit.setBorderPainted(false);
        submit.setIcon(submitButton);
        add(submit);
        submit.setVisible(false);

        clear = new JButton("CLEAR");
        clear.addActionListener(this);
        clear.setIcon(clearButton);
        add(clear);
        clear.setVisible(false);

        again = new JButton("PLAY AGAIN");
        again.addActionListener(this);
        add(again);
        again.setVisible(false);
        setComponentZOrder(again,0);

        pressedPlay = false;
        pressedRule = false;
        pressedBack = false;

        submitYLoc = 577;


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawImage(rulesBack, 0, 0,800, 900, null);
        g.drawImage(bg, 0, 0, 800, 900 , null);

//        if(pressedRule){
//          //  g.drawImage(homeBack, 0, 0, 800, 900 , null);
//            g.drawImage(rulesBack, 0, 0, 800, 900 , null);
//            pressedRule = false;
//        }

        if(pressedPlay){
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.setColor(Color.BLACK);
            double[] stats = person.stats();
            //g.drawImage(playBack, 0, 0, 800, 900, null);
            g.drawString("Games played: "+ person.getGamesPlayed(),550,30);
            g.drawString("High Score: "+stats[0],550,60);
            g.drawString("Average Score: "+stats[1],550,90);
            g.drawString("Win percentage: "+stats[2]+"%",550,120);
            g.drawString("Total points: "+ stats[3],550,150);

            g.drawImage(mastermind, -20, 100, 900,700,null);
        }

        if(pressedBack){
            g.drawImage(homeBack, 0, 0, 800, 900, null);
            pressedBack = false;
        }

        play.setLocation(300, 330);
        play.setSize(225, 93);
        play.setFont(new Font("Arial", Font.BOLD, 30));
        play.setMargin(new Insets(0, 0, 0 ,0));
        play.setContentAreaFilled(false);
        g.setFont(new Font("Arial", Font.BOLD, 20));

        g.setColor(Color.BLACK);
        rules.setLocation(300, 430);
        rules.setSize(240, 95);
        rules.setMargin(new Insets(0, 0, 0, 0));
        rules.setContentAreaFilled(false);


        rules.setFont(new Font("Arial", Font.BOLD, 30));

        submit.setLocation(268, submitYLoc);
        submit.setSize(80, 40);
        submit.setFont(new Font("Arial", Font.BOLD, 12));

        clear.setLocation(560, 525);
        clear.setSize(180, 70);
        clear.setFont(new Font("Arial", Font.BOLD, 30));

        again.setLocation(241, 50);
        again.setSize(300, 100);
        again.setFont(new Font("Arial", Font.BOLD, 30));

        backToHome.setFont(new Font("Arial", Font.BOLD, 30));
        backToHome.setLocation(50,100);
        backToHome.setSize(200,70);
         int xVal = 262;
        backToHome.setSize(230,95);
        backToHome.setBorderPainted(false);
        //  backToHome.setMargin(new Insets(0, 0, 0, 0));
        //backToHome.setContentAreaFilled(false);

       // int xVal = 240;
        for (JButton c:colors){
            c.setSize(30,30);
            c.setLocation(xVal,637);
            xVal+=34;

        }

        if (game.getTries()<=0){
            Shapes[] reveal = game.getAnswer();

            for (int i=0;i<4;i++){
                String curr = reveal[i].getColor();
                int idx=0;
                for (int k = 0;k<8;k++){
                    if (curr.equals(options[k])){
                        idx = k;
                        break;
                    }
                }
                g.setColor(colorObjs[idx]);
                g.fillOval(353+36*i,163, 25, 25);
            }
        }



        if (pressedPlay){
            Shapes[][] format = game.getGrid();
            for (int i = 0; i< format.length;i++){
                for (int j = 0;j<4;j++){
                    if (format[i][j]!=null){
                        String currColor = format[i][j].getColor();
                        int idx = 0;
                        for (int k = 0;k<8;k++){
                            if (currColor.equals(options[k])){
                                idx = k;
                                break;
                            }
                        }
                        g.setColor(colorObjs[idx]);
                        g.fillOval(356+36*j,209+42*i, 20, 20);
                    }
                }
                for (int j = 4;j<format[0].length;j++){
                    if (format[i][j]!=null){
                        if (format[i][j].getColor().equals("#W")){
                            g.setColor(Color.WHITE);
                        }else {
                            g.setColor(Color.RED);
                        }
                        int xLoc=440+14*j;
                        int yLoc = 203 + 42*i;
                        if (j>5){
                            xLoc=440+14*(j-2);
                            yLoc+=20;
                        }
                        g.fillOval(xLoc,yLoc, 10, 10);
                    }
                }
            }
        }
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.setColor(Color.BLACK);
        g.drawString(WLMessage, 150,720);



    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton casted = (JButton) e.getSource();
            for(int i = 0; i < colors.length; i ++){
                if(casted == colors[i]&&game.getInputIdx()<4){
                    game.addInput(options[i]);
                    repaint();
                }
            }
            if (game.getInputIdx()==4 && casted == submit){
                game.check();
                if(game.getTries()>0){
                    game.clearInput();
                }


                submitYLoc-=42;
                if (game.isWin() || game.getTries()<=0){
                    if (game.isWin()){
                        WLMessage = "Congrats! You Won!!";
                    }else{
                        WLMessage = "Congrats! You lost!!";
                    }
                    repaint();
                    again.setVisible(true);
                    submit.setVisible(false);
                }

                repaint();
            }

            if (casted == play) {
                bg = playBack;
                play.setVisible(false);
                PlayisClicked = true;
                rules.setVisible(false);
                play.setVisible(false);
                for(int i = 0; i < colors.length; i ++){
                    colors[i].setVisible(true);
                }
                pressedPlay = true;
                submit.setVisible(true);
                clear.setVisible(true);
                repaint();

            }

            if (casted == rules) {
                bg = rulesBack;
                pressedRule = true;
                rules.setVisible(false);
                play.setVisible(false);
                backToHome.setVisible(true);
                repaint();
            }

            if (casted == backToHome){
                bg = homeBack;
                pressedBack = true;
                rules.setVisible(true);
                play.setVisible(true);
                backToHome.setVisible(false);
                repaint();
            }

            if (casted == clear){
                game.clearInput();
                repaint();
            }
            if (casted == again){
                WLMessage = "";
                if (!game.isWin()){
                    person.addScore(-1);
                }else{
                    person.addScore(game.getTries());
                }
                game.setup();
                submit.setVisible(true);
                again.setVisible(false);
                submitYLoc = 577;

                repaint();
            }
        }
    }
}