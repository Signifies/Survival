package commands;

import Utilities.Action;
import Utilities.Rank;
import Utilities.SUtils;
import me.ES96.Survival.com.Survival;
import me.ES96.Survival.com.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Signifies on 4/2/2018 - 22:02.
 */
public class RankCommand extends SUtils implements CommandExecutor {

    private Survival instance;

    public RankCommand(Survival instance) {
        this.instance = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {
        if (!(sender instanceof Player)) {
            if (args.length < 1) {
                sender.sendMessage(Action.USAGE.getMessage()+""+ChatColor.RED + "/rank <player> <rank>");
            } else {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage(ChatColor.RED + "Sorry, unable to locate that player!");
                } else {
                    User.setDesiredRank(target, Rank.valueOf(args[1]));
                    sender.sendMessage(ChatColor.GRAY + "You have set the user, " + ChatColor.WHITE + target.getName() + "'s" + ChatColor.GRAY + " rank to " + Rank.valueOf(args[1]) + ".");
                }
            }
        }else {
            Player p = (Player) sender;
            if (!User.isPermissible(p, Rank.ADMIN)) {
                sender.sendMessage(defaultMessage(instance.permissionDefault(),Rank.ADMIN, instance.getMessage(),p));
                return true;
            }
            if (args.length < 1) {
                sender.sendMessage(Action.USAGE.getMessage()+ChatColor.RED + "Usage -> /rank <player> <rank>");
            } else {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage(ChatColor.RED + "Sorry, unable to locate that player!");
                } else {

                    instance.getRankManagement().manageRank(target, p, Rank.valueOf(args[1]));
                }
            }
        }
        return true;
    }
}
