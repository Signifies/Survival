package commands;

import Utilities.Action;
import Utilities.Rank;
import Utilities.SPermissions;
import Utilities.SUtils;
import me.ES96.Survival.com.Survival;
import me.ES96.Survival.com.User;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.security.Permission;

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
            Player p = (Player)sender;
            if(!User.isPermissible(p, Rank.MOD))
            {
                sender.sendMessage(defaultMessage(instance.permissionDefault(),Rank.MOD, instance.getMessage(),p));
            }else
            {
                if(args.length < 1)
                {
                    sender.sendMessage(color(Action.USAGE.getMessage()+"&f/edit &7[reload] [tps] [rank]"));
                }else
                {
                    switch (args[0])
                    {

                        case "reload":
                        case "rl":
                            if(!(sender instanceof Player)) {
                                instance.reloadConfig();
                                sender.sendMessage(color("&7Util configuration has been &breloaded&7."));
                            }else {
                                if (!User.isPermissible(p, Rank.DEV)) {
                                    sender.sendMessage(defaultMessage(instance.permissionDefault(), Rank.DEV, instance.getMessage(), p));
                                } else {
                                    instance.reloadConfig();
                                    sender.sendMessage(color("&7Util configuration has been &breloaded&7."));
                                }
                            }
                            break;

                        case "info":
                        case "i":
                        case "tps":

                               if(User.isPermissible(p,Rank.ADMIN))
                               {
                                   information(instance,p);
                               }else
                               {
                                   p.sendMessage(color("&7Sorry, you do not have the required permission of &9"+ Rank.ADMIN.toString()+"&7."));
                               }

                            break;

                        case "rank":
                            if(sender instanceof Player)
                            {
                                sender.sendMessage(color("&7You currently hold the rank of: &a"+User.getRank(p).toString()));
                            }else {
                                sender.sendMessage("Console can't check their rank");
                            }
                            break;

                        case "hasrank":

                                if(User.isPermissible(p, Rank.GUEST))
                                {
                                    p.sendMessage(color("&aCongratulations! You are a GUEST!"));
                                }else
                                {
                                    p.sendMessage(color("&7Sorry! You haven't been promoted yet!"));
                                }
                            break;
                        case "inheritance":

                                if(User.isPermissible(p,Rank.MOD))
                                {
                                    p.sendMessage(color("&cCan inherit permissions??? I think??"));
                                }else
                                {
                                    p.sendMessage(color("&7Sorry, you didn't inherit anything!"));
                                }
                            break;

                        default:
                            sender.sendMessage(color(Action.USAGE.getMessage()+"&f/edit &7[reload] [tps] [rank]"));
                    }
                }
            }
        }
        return true;
    }

}
