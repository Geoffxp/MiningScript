package Scripts;

import Data.Location;
import Tasks.BankTask;
import Tasks.MiningTask;
import Tasks.Traverse;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.TaskScript;

@ScriptMeta(name = "MiningScript", desc = "My first script!", developer = "Reph3x", category = ScriptCategory.MINING)
public class Main extends TaskScript {

    public static boolean oreToggle = true;

    public static void main(String[] args) {

    }

    @Override
    public void onStart() {
        submit(
            new BankTask(),
            new Traverse(),
            new MiningTask()
        );
    }

    @Override
    public void onStop() {

    }

    public static void toggleOre() {
        oreToggle = !oreToggle;
    }
}
