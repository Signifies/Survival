package commands;

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

                               if(User.isPermissible(p,Rank.ADMIN))
                               {
                                   information(instance,p);
                               }else
                               {
                                   p.sendMessage(color("&7Sorry, you do not have the required permission of &9"+ Rank.ADMIN.toString()+"&7."));
                               }
                           }
                            break;

                        case "rank":
                            if(sender instanceof Player)
                            {

                                Player p = (Player)sender;
                                p.sendMessage(color("&7You currently hold the rank of: &a"+User.getRank(p).toString()));

                            }
                            break;

                        case "hasrank":
                            if(sender instanceof Player)
                            {
                                Player p =(Player)sender;
                                if(User.isPermissible(p, Rank.MEMBER))
                                {
                                    p.sendMessage(color("&aCongratulations! You are a Member!"));
                                }else
                                {
                                    p.sendMessage(color("&7Sorry! You haven't been promoted yet!"));
                                }
                            }
                            break;
                        case "inheritance":
                            if(sender instanceof Player)
                            {
                                Player p = (Player)sender;
                                if(User.isPermissible(p,Rank.BUILDER))
                                {
                                    p.sendMessage(color("&cCan inherit permissions??? I think??"));
                                }else
                                {
                                    p.sendMessage(color("&7Sorry, you didn't inherit anything!"));
                                }
                            }
                            break;

                        default:
                            sender.sendMessage(color("&cYou've used an incorrect argument. :/ "));
                    }
                }
            }
        }
        return true;
    }

}
