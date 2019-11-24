package Scripts;

import javax.swing.*;
import java.awt.*;

public class MiningGui extends JFrame {

    public JLabel timeRunning;
    public JLabel[] oreMined;
    public JLabel gpPerHour;

    public MiningGui() {
        super("Mining GUI");
        timeRunning = new JLabel();
        oreMined = new JLabel[4];
        gpPerHour = new JLabel();

        for(int i=0; i<oreMined.length; i++) {
            oreMined[i] = new JLabel();
        }

        timeRunning.setText("Time Running: "+Main.currentTime+" seconds");

        add(timeRunning);
        //add(gpPerHour);

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        pack();
    }
}
