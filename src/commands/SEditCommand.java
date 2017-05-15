package commands;

import Utilities.SPermissions;
import Utilities.SUtils;
import me.ES96.Survival.com.Survival;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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



        if(cmd.getName().equalsIgnoreCase("edit"))
        {
            if(!SPermissions.EDIT_COMMAND.checkPermission(sender))
            {
                sender.sendMessage(defaultMessage(instance.permissionDefault(), instance.getMessage()));
            }else
            {
                if(args.length < 1)
                {
                    sender.sendMessage(color("&7> &aHelp messages have not been implemented yet."));
                }else
                {
                    switch (args[0])
                    {
                        case "info":
                        case "i":
                           if(sender instanceof Player)
                           {

                               Player p = (Player)sender;

                               information(instance,p);
                               break;
                           }

                        default:
                            sender.sendMessage(color("&cYou've used an incorrect argument. :/ "));
                    }
                }
            }
        }
        return true;
    }

}
