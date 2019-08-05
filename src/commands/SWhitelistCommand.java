package commands;

import Utilities.Action;
import Utilities.Rank;
import Utilities.SPermissions;
import Utilities.SUtils;
import me.ES96.Survival.com.Survival;
import me.ES96.Survival.com.User;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Evan on 5/14/2017.
 */
public class SWhitelistCommand extends SUtils implements CommandExecutor
{
    private Survival instance;

    public SWhitelistCommand(Survival value)
    {
        instance = value;
    }
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {

        if(sender instanceof Player) {
            Player p =(Player)sender;
            if(!User.isPermissible(p, Rank.ADMIN)){
                sender.sendMessage(defaultMessage(instance.permissionDefault(),Rank.ADMIN, instance.getMessage(),p));
                return false;
            }
        }
                if(args.length < 1)
                {
                    sender.sendMessage(color(Action.USAGE.getMessage()+"&f/whitelist &7<[add] [remove] [clear] [enforce] [list] [on] [off]>"));
                }else
                {
                    switch (args[0].toLowerCase())
                    {

                        case "maintenance":
                        case "repair":

                             if(instance.getConfig().getBoolean("Maintenance.enabled")){
                                //wl logic and message.
                                instance.getConfig().set("Maintenance.enabled", false);
                                instance.saveConfig();
                                Bukkit.getServer().setWhitelist(false);
                                Bukkit.getServer().reloadWhitelist();
                                sender.sendMessage(color(""+check(instance.getConfig().getBoolean("Maintenance.enabled"), "server maintenance has been")));
                                //adminNotifications(sender.getName(), "disabled server maintenance.");
                            }else {
                                instance.getConfig().set("Maintenance.enabled", true);
                                instance.saveConfig();
                                enforceWhitelist(sender, instance.getConfig().getString("Maintenance.msg"));
                                Bukkit.getServer().setWhitelist(true);
                                Bukkit.getServer().reloadWhitelist();
                                sender.sendMessage(color(""+check(instance.getConfig().getBoolean("Maintenance.enabled"), "server maintenance has been")));
                                //adminNotifications(sender.getName(), "server maintenance enabled.");
                            }
                            break;

                        case "list":

                            StringBuilder str = new StringBuilder();

                            for(OfflinePlayer player : Bukkit.getServer().getWhitelistedPlayers())
                            {
                                if(str.length() > 0)
                                {
                                    str.append(", ");
                                }
                                str.append(player.getName());
                            }

                            sender.sendMessage(color("&7    ----- &aWhitelisted Players &7-----"));
//                            sender.sendMessage(color("&a" + Bukkit.getServer().getWhitelistedPlayers().toString()));
                            sender.sendMessage(color("&6"+str.toString()));
                            break;
                        case "on":
                        case "enabled":
                            Bukkit.getServer().setWhitelist(true);
                            sender.sendMessage(color("&7You have turned on the &awhitelist&7."));
                            adminNotifications(sender.getName(), "Turned on the whitelist.");
                            break;
                        case "off":
                        case "disabled":
                            Bukkit.getServer().setWhitelist(false);
                            sender.sendMessage(color("&7You have turned off the &awhitelist&7."));
                            adminNotifications(sender.getName(),"Turned off the whitelist.");
                            break;

                        case "add":
                        case "+":
                            if(args.length >1)
                            {
                                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                                target.setWhitelisted(true);

                                sender.sendMessage(color("&7The player, &a"+ target.getName() + "&7 has been added to the whitelist."));
                                adminNotifications(sender.getName(), "Whitelisted the player, " +args[1] +".");
                            }else
                            {
                                sender.sendMessage(color("&7/whitelist &aadd &f<playername>"));
                            }
                            break;
                        case "remove":
                        case "rm":
                        case "-":

                            if(args.length >1)
                            {
                                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                                target.setWhitelisted(false);
                                sender.sendMessage(color("&7The player, &a"+ target.getName() + "&7 has been removed to the whitelist."));
                                adminNotifications(sender.getName(), "Removed the player, " +args[1] +" from the whitelist.");
                            }else
                            {
                                sender.sendMessage(color("&7/whitelist &cremove &f<playername>"));
                            }
                            break;

                        case "enforce":
                            enforceWhitelist(sender);
                            break;

                        case "clear":
                        case "ci":

//                            Bukkit.getServer().getWhitelistedPlayers().clear();
                            for(OfflinePlayer p : Bukkit.getWhitelistedPlayers())
                            {
                                p.setWhitelisted(false);
                            }
                            sender.sendMessage(color("&7Cleared the &fwhitelist&7."));
                            Bukkit.getServer().reloadWhitelist();
                            adminNotifications(sender.getName(), "Cleared the entire whitelist.");
                            break;
                        default:
                            sender.sendMessage(color("&7/whitelist"));
                    }
                }
        return true;
    }

}

