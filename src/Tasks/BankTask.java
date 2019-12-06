package Tasks;

import Scripts.Main;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class BankTask extends Task {

    public static boolean skipPickCheck = false;

    public static final String[] PICKAXES = {"Bronze Pickaxe", "Iron Pickaxe", "Steel Pickaxe", "Mithril Pickaxe", "Adamant Pickaxe", "Rune Pickaxe", "Dragon Pickaxe"};
    /*
    1265 = bronze
    1267 = iron
    1269 = steel
    1271 = mithril
    1273 = adamant
    1275 - rune
    11920 = dragon
     */

    @Override
    public boolean validate() {
        return Inventory.isFull() || Bank.isOpen();
    }

    @Override
    public int execute() {

        Main.updateTime();

        if (Movement.getRunEnergy() > Random.nextInt(25, 50) && !Movement.isRunEnabled()) {
            Movement.toggleRun(true);
        }
        if (Players.getLocal().isHealthBarVisible()) {
            Movement.toggleRun(true);
        }

        Bank.open();
        Time.sleep(Random.high(600, 1300));
        if (Bank.isOpen()) {
            Bank.depositAllExcept(PICKAXES);
            Time.sleep(Random.high(600, 1300));
            if(!withdrawBestPickaxe()) {
                return -1;
            }
            Main.toggleOre();
            Bank.close();
        }

        return Random.high(1000, 2000);
    }

    public static boolean withdrawBestPickaxe() {
        int miningLevel = Skills.getCurrentLevel(Skill.MINING);
        Bank.setWithdrawMode(Bank.WithdrawMode.ITEM);
        if (miningLevel >= 41
                && Bank.contains(PICKAXES[5])
                && !Inventory.contains(PICKAXES[5])) {
            Bank.depositAll(PICKAXES);
            Time.sleep(Random.high(700, 1400));
            Bank.withdraw(PICKAXES[5], 1);
            return true;
        }
        if (miningLevel >= 31
                && Bank.contains(PICKAXES[4])
                && !Inventory.contains(PICKAXES[4])) {
            Bank.depositAll(PICKAXES);
            Time.sleep(Random.high(700, 1400));
            Bank.withdraw(PICKAXES[4], 1);
            return true;
        }
        if (miningLevel >= 21
                && Bank.contains(PICKAXES[3])
                && !Inventory.contains(PICKAXES[3])) {
            Bank.depositAll(PICKAXES);
            Time.sleep(Random.high(700, 1400));
            Bank.withdraw(PICKAXES[3], 1);
            return true;
        }
        if (miningLevel >= 6
                && Bank.contains(PICKAXES[2])
                && !Inventory.contains(PICKAXES[2])) {
            Bank.depositAll(PICKAXES);
            Time.sleep(Random.high(700, 1400));
            Bank.withdraw(PICKAXES[2], 1);
            return true;
        }
        if (Bank.contains(PICKAXES[1])
                && !Inventory.contains(PICKAXES[1])) {
            Bank.depositAll(PICKAXES);
            Time.sleep(Random.high(700, 1400));
            Bank.withdraw(PICKAXES[1], 1);
            return true;
        }
        if (Bank.contains(PICKAXES[0])
                && !Inventory.contains(PICKAXES[0])) {
            Bank.depositAll(PICKAXES);
            Time.sleep(Random.high(700, 1400));
            Bank.withdraw(PICKAXES[0], 1);
            return true;
        }
        Log.severe("No pickaxe on character or in bank. Aborting script.");
        return false;
    }
}
