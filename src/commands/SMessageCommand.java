package commands;

import Utilities.SPermissions;
import Utilities.SUtils;
import me.ES96.Survival.com.Survival;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Evan on 5/14/2017.
 */
@Deprecated
public class SMessageCommand extends SUtils implements CommandExecutor
{

    private Survival instance;

    public SMessageCommand(Survival value)
    {
        instance = value;
    }

/*
*
messaging:
  format:
    from: "&6[&4PM&6]&r &6From: &a%sender% &f%msg%"
    to: "&6[&4PM&6]&r &6To: &c%target% &f%msg%"
#
*
*
* */

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {

        if(cmd.getName().equalsIgnoreCase("message"))
        {
            if(!SPermissions.SURVIVAL_MESSAGE_COMMAND.checkPermission(sender))
            {
                sender.sendMessage(defaultMessage(instance.permissionDefault(), instance.getMessage()));
            }else if(args.length < 1)
            {
                sender.sendMessage(color("&7/message <playername> <message>"));
            }else
            {
                Player target = Bukkit.getServer().getPlayer(args[0]);

                if(target == null)
                {
                    sender.sendMessage(color("&7Unable to send a message to " + args[0] + "&7 are they online?"));
                    return true;
                }

                StringBuilder str = new StringBuilder();

                for (String arg : args) {
                    str.append(arg + " ");
                }

                String msg = str.toString().replace(args[0], "");
                String to = instance.getConfig().getString("messaging.format.to");
                to = to.replace("%sender%",sender.getName());
                to = to.replace("%target%", target.getName());
                to = to.replace("%msg%",msg);

                String from = instance.getConfig().getString("messaging.format.from");
                from = from.replace("%sender%",sender.getName());
                from = from.replace("%target%", target.getName());
                from = from.replace("%msg%",msg);


                sender.sendMessage(color(to));
                target.sendMessage(color(from));
            }
        }

        return true;
    }
}
