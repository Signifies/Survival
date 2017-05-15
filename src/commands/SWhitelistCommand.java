package commands;

import Utilities.SPermissions;
import Utilities.SUtils;
import me.ES96.Survival.com.Survival;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

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

        if(cmd.getName().equalsIgnoreCase("whitelist"))
        {
            if(!SPermissions.SURVIVAL_COMMAND_WHITELIST.checkPermission(sender))
            {
                sender.sendMessage(defaultMessage(instance.permissionDefault(), instance.getMessage()));
            }else
            {
                if(args.length < 1)
                {
                    sender.sendMessage(color("&7> &f/whitelist &7<[add] [remove] [clear] [list] [on] [off]>"));
                }else
                {
                    switch (args[0].toLowerCase())
                    {
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
                            break;
                        case "off":
                        case "disabled":
                            Bukkit.getServer().setWhitelist(false);
                            sender.sendMessage(color("&7You have turned off the &awhitelist&7."));
                            break;

                        case "add":
                        case "+":
                            if(args.length >1)
                            {
                                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                                target.setWhitelisted(true);

                                sender.sendMessage(color("&7The player, &a"+ target.getName() + "&7 has been added to the whitelist."));
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
                            }else
                            {
                                sender.sendMessage(color("&7/whitelist &cremove &f<playername>"));
                            }
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
                            break;
                        default:
                            sender.sendMessage(color("&7/whitelist"));
                    }
                }
            }
        }

        return true;
    }
}

