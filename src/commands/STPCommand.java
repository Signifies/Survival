package commands;

import Utilities.Action;
import Utilities.SPermissions;
import Utilities.SUtils;
import me.ES96.Survival.com.Survival;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

/**
 * Created by Evan on 5/14/2017.
 */
public class STPCommand extends SUtils implements CommandExecutor
{

    private Survival instance;

    public STPCommand(Survival value)
    {
        instance = value;
    }



    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {


        if(sender instanceof Player)
        {
            Player p = (Player)sender;

            if(cmd.getName().equalsIgnoreCase("tp"))
            {
                if(!SPermissions.SURVIVAL_COMMAND_TP.checkPermission(p))
                {
                    p.sendMessage(color(Action.PERMISSION.getMessage() +"You don't have a high enough rank to execute this command."));
                }else
                {
                    if(args.length ==0)
                    {
                        p.sendMessage(color("&7You need to specify a player to teleport to."));
                    }else if(args.length >0)
                    {
                        String arg = args[0];
                        Player target = Bukkit.getServer().getPlayer(arg);
                        if(target == null)
                        {
                            p.sendMessage(color("&7Error - Unable to find the player, &a" + arg + "&7..."));
                        }else
                        {
                                p.teleport(target.getLocation());
                                p.sendMessage(color("&7Teleporting to &a "+target.getName() + "&7..."));
                        }
                    }
                }
            }
        }
        return true;
    }
}
