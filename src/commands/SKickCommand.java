package commands;

import Utilities.Action;
import Utilities.Rank;
import Utilities.SPermissions;
import Utilities.SUtils;
import me.ES96.Survival.com.Survival;
import me.ES96.Survival.com.User;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Evan on 5/14/2017.
 */
public class SKickCommand extends SUtils implements CommandExecutor
{

    private Survival instance;

    public SKickCommand(Survival value)
    {
        instance = value;
    }



    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {

        if(sender instanceof Player) {
            Player p = (Player)sender;

                if(!User.isPermissible(p,Rank.MOD)) {
                    sender.sendMessage(defaultMessage(instance.permissionDefault(),Rank.ADMIN, instance.getMessage(),p));
                    return false;
                    }
                }

                if(args.length < 1)
                {
                    sender.sendMessage(color(Action.KICK.getMessage()+"&7/kick <player> <message>"));
                }else if(args.length > 0)
                {
                    Player target = Bukkit.getPlayer(args[0]);

                    if(target== null)
                    {
                        sender.sendMessage(color("&7Error - Unable to find the player &a"+args[0]));
                    }else
                    {
                        if(args.length > 1)
                        {
                            StringBuilder str = new StringBuilder();

                            for (String arg : args) {
                                str.append(arg + " ");
                            }
                            String msg = str.toString().replace(args[0], "");
                            String format = instance.getConfig().getString("kick-format");

                            format = format.replace("%staff%",sender.getName());
                            format = format.replace("%player%", target.getName());
                            format = format.replace("%msg%",msg);

                            target.kickPlayer(color(format));
                            Bukkit.broadcastMessage(color(format));
                        }else
                        {
                            Bukkit.broadcastMessage(color(Action.KICK.getMessage()+"&a" + target.getName() +"&7 has been kicked."));
                            target.kickPlayer(color(Action.KICK.getMessage()+"&7You have been kicked from the &cserver."));
                        }
                    }
                }
        return true;
    }
}
