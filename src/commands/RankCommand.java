package commands;

import Utilities.Rank;
import me.ES96.Survival.com.Survival;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Signifies on 4/2/2018 - 22:02.
 */
public class RankCommand implements CommandExecutor {

    private Survival instance;

    public RankCommand(Survival instance) {
        this.instance = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {
        if(!(sender instanceof Player))
        {
            sender.sendMessage(ChatColor.RED + "Not implemented for console use yet.");
            return true;
        }

        Player player = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("rank"))
        {
            if(args.length < 1)
            {
                player.sendMessage(ChatColor.RED + "Usage -> /rank <player> <rank>");
            }else
            {
                Player target = Bukkit.getPlayer(args[0]);
                if(target == null)
                {
                    player.sendMessage(ChatColor.RED + "Sorry, unable to locate that player!");
                }else
                {
                    instance.getRankManagement().manageRank(target,player,Rank.valueOf(args[1]));
                }
            }

        }
        return true;
    }

}
