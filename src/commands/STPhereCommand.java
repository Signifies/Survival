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
public class STPhereCommand extends SUtils implements CommandExecutor
{

    private Survival instance;

    public STPhereCommand(Survival value)
    {
        instance = value;
    }



    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {


        if(sender instanceof Player)
        {
            Player p = (Player)sender;

            if(cmd.getName().equalsIgnoreCase("tphere"))
            {
                if(!SPermissions.SURVIVAL_COMMAND_TPHERE.checkPermission(p))
                {
                    p.sendMessage(defaultMessage(instance.permissionDefault(), instance.getMessage()));
                }else
                {
                    if(args.length ==0)
                    {
                        p.sendMessage(color("&7You need to specify a player."));
                    }else if(args.length >0)
                    {
                        String arg = args[0];
                        Player target = Bukkit.getServer().getPlayer(arg);
                        if(target == null)
                        {
                            p.sendMessage(color("&7Error - Unable to find the player, &a" + arg + "&7..."));
                        }else
                        {

                                target.teleport(p.getLocation());
                                p.sendMessage(color("&7Teleporting "+target.getName() + "&7..."));
                                target.sendMessage(color("&7Teleporting..."));
                        }
                    }
                }
            }
        }
        return true;
    }
}
