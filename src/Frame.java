import javax.swing.JFrame;

public class Frame {

    public Frame() {
        JFrame frame = new JFrame("Frame title");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(814, 900); //KEEP WIDTH: 814, HEIGHT: 900
        frame.setLocationRelativeTo(null);
        DisplayPanel panel = new DisplayPanel();
        frame.add(panel);
        frame.setVisible(true);
    }
}