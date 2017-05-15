package commands;

import Utilities.SUtils;
import me.ES96.Survival.com.Survival;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Evan on 5/14/2017.
 */
public class SEditCommand extends SUtils implements CommandExecutor {
    private Survival instance;

    public SEditCommand(Survival value) {
        instance = value;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {



        return true;
    }

}
