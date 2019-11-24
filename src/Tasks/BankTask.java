package Tasks;

import Scripts.Main;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

public class BankTask extends Task {


    @Override
    public boolean validate() {
        return Inventory.isFull() || Bank.isOpen();
    }

    @Override
    public int execute() {

        Main.updateTime();

        if(Movement.getRunEnergy() > Random.nextInt(25,50) && !Movement.isRunEnabled()) {
            Movement.toggleRun(true);
        }
        if(Players.getLocal().isHealthBarVisible()) {
            Movement.toggleRun(true);
        }

        Bank.open();
        Time.sleep(Random.high(600,1300));
        if (Bank.isOpen()) {
            Bank.depositAllExcept(1265, 1267, 1269, 1271, 1273, 1275, 11920);
            Main.toggleOre();
            Bank.close();
        }

        return Random.high(1000, 2000);
    }
}
