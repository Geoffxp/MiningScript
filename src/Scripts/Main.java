package Scripts;

import Tasks.*;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.TaskScript;

import java.math.BigDecimal;
import java.math.RoundingMode;

@ScriptMeta(name = "MiningScript", desc = "My first script!", developer = "Reph3x", category = ScriptCategory.MINING)
public class Main extends TaskScript {

    private MiningChatListener xpListener = new MiningChatListener();

    public static boolean oreToggle = true;
    public static long startingTime;
    public static double currentTime;
    public static int[] oreCount;

    public static MiningGui gui = new MiningGui();

    public static void main(String[] args) {

    }

    @Override
    public void onStart() {
        submit(
            new NoPickaxeTask(),
            new BankTask(),
            new Traverse(),
            new MiningTask(),
            new CurrentlyMiningTask()
        );
        startingTime = System.currentTimeMillis();
        currentTime = 0;
        oreCount = new int[4];
        gui.setVisible(true);
    }

    @Override
    public void onStop() {
        xpListener.close();
        gui.setVisible(false);
    }

    public static void toggleOre() {
        oreToggle = !oreToggle;
    }
    public static void updateTime() {
        currentTime = (System.currentTimeMillis() - startingTime)/(1000.0*60.0*60.0);
        gui.timeRunning.setText("Time Running: "+truncate(currentTime+"", 2)+" hours");
    }
    public static void incrementOreCount(String message) {
        if(message.contains("coal"))
            oreCount[3]++;
        if(message.contains("iron"))
            oreCount[2]++;
        if(message.contains("copper"))
            oreCount[1]++;
        if(message.contains("tin"))
            oreCount[0]++;
    }

    public static void updateOreCount() {
        int index = 0;
        for(int ore : oreCount) {
            if(ore != 0) {
                String oreName = "error";
                switch(index) {
                    case 0:
                        oreName = "Tin";
                        break;
                    case 1:
                        oreName = "Copper";
                        break;
                    case 2:
                        oreName = "Iron";
                        break;
                    case 3:
                        oreName = "Coal";
                        break;
                }
                gui.oreMined[index].setText(oreName+" mined: "+ore);
                gui.add(gui.oreMined[index]);
            }
            index++;
        }
    }

    static String truncate(String value, int places) {
        return new BigDecimal(value)
                .setScale(places, RoundingMode.DOWN)
                .stripTrailingZeros()
                .toString();
    }

    public void closeScript() {
        setStopping(true);
    }

}


