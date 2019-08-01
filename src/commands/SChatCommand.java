package commands;

import Utilities.Rank;
import Utilities.SPermissions;
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
 * Created by Evan on 5/14/2017.
 */
public class SChatCommand extends SUtils implements CommandExecutor
{

    private Survival instance;

    public SChatCommand(Survival value)
    {
        instance = value;
    }

        public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {


        if(sender instanceof Player) {
            Player p = (Player)sender;
            if(!User.isPermissible(p, Rank.MOD)) {
                sender.sendMessage(defaultMessage(instance.permissionDefault(), instance.getMessage()));
                return false;
            }
        }

         if(args.length < 1) {
            sender.sendMessage(color("&7/chat <silence | unmute> || <clear> || <clearself> || <clearothers>"));
        }else {
            if(args.length > 0)
            {
                switch (args[0].toLowerCase())
                {
                    case "clear":
                    case "ci":
                            clear();

                        break;
                    case "clearself":
                    case "cs":
                            selfClear(sender);
                        break;
                    case "help":
                    case "?":
                        sender.sendMessage(color("&7/chat <silence | unmute> || <clear> || <clearself> || <clearothers>"));
                        break;
                    case "clearuser":
                    case "cu":
                    case "clearothers":

                            if(args.length > 1)
                            {
                                Player target = Bukkit.getServer().getPlayer(args[1]);
                                if(target == null)
                                {
                                    sender.sendMessage(color("&7The player, &f"+args[1] +" &7isn't online." ));
                                }else
                                {
                                    clearPlayer(target);
                                    sender.sendMessage(color("&7You have cleared the &fchat &7for the user," +target.getName()));
                                }
                            }

                        break;

                    case "silence":
                    case "disable":
                    case "mute":
                        //Change default methods to use global notification system

                            if(!instance.getConfig().getBoolean("Chat.Enabled")) {
                                sender.sendMessage(color("&7The chat is already &fdisabled&7."));
                            }else{
                                instance.getConfig().set("Chat.Enabled", false);
                                instance.saveConfig();
                                sender.sendMessage(color(instance.getConfig().getString("Chat.chat-disabled")));
                                Bukkit.getServer().broadcastMessage(color("&7The chat has been &fdisabled&7."));
                            }

                        break;

                    case "unmute":

                            if(instance.getConfig().getBoolean("Chat.Enabled")) {
                                sender.sendMessage(color("&7The chat is already &fEnabled&7."));
                            }else{
                                instance.getConfig().set("Chat.Enabled", true);
                                instance.saveConfig();
                                sender.sendMessage(color("&7You have &fenabled &7global chat."));
                                Bukkit.getServer().broadcastMessage(color("&7The chat has been &fenabled&7."));
                            }

                        break;

                    default:
                        sender.sendMessage(color("&7Unknown argument, &a" + args[0] + "&7!"));
                        sender.sendMessage(color("&7/chat <silence | unmute> || <clear> || <clearself> || <clearothers>"));
                }
            }
        }
        return true;
    }
}
