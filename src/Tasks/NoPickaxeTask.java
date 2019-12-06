package Tasks;

import Scripts.Main;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class NoPickaxeTask extends Task {
    @Override
    public boolean validate() {
        return !Inventory.contains(BankTask.PICKAXES)
                && !Bank.isOpen();
    }

    @Override
    public int execute() {
        Main.updateTime();

        Log.fine("No pickaxe in inventory. Going to bank to grab best pickaxe. Script will fail if no suitable pickaxes in bank.");

        if(Movement.getRunEnergy() > Random.nextInt(25,50) && !Movement.isRunEnabled()) {
            Movement.toggleRun(true);
        }
        if(Players.getLocal().isHealthBarVisible()) {
            Movement.toggleRun(true);
        }

        Bank.open();
        Time.sleep(Random.high(600,1300));
        if (Bank.isOpen()) {
            Bank.depositInventory();
            Time.sleep(Random.high(600, 1300));
            if(!BankTask.withdrawBestPickaxe()) {
                return -1;
            }
            Time.sleep(Random.high(700, 1250));
            Bank.close();
        }
        return Random.high(650, 1250);
    }
}
