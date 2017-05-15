package commands;

import Utilities.SPermissions;
import Utilities.SUtils;
import me.ES96.Survival.com.Survival;
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



    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {

        if(cmd.getName().equalsIgnoreCase("chat") && !(SPermissions.SURVIVAL_CHAT.checkPermission(sender)))
        {
            sender.sendMessage(defaultMessage(instance.permissionDefault(), instance.getMessage()));
        }else if(args.length < 1)
        {
            sender.sendMessage(color("&7=======[&eHelp&7]======="));
            sender.sendMessage(color("&8/chat permissions &b&l--&b&l> &aLists all permissions.\n" +
                    "&8/chat help &8&l--&b&l> &aLists configuration options/help.\n"));
        }else {
            if(args.length > 0)
            {
                switch (args[0])
                {
                    case "clear":
                    case "c":
                        if (!SPermissions.SURVIVAL_CHAT_CLEAR.checkPermission(sender));
                        else
                            clear();
//                        Bukkit.getServer().broadcastMessage(ChatColor.GOLD +"Chat has been cleared...");
                        break;

                    case "clearself":
                    case "cs":
                        if(!SPermissions.SURVIVAL_CHAT_CLEARSELF.checkPermission(sender));
                        else
                            selfClear(sender);
                        break;

                    case "help":
                    case "?":
                        sender.sendMessage(color(
                                "&b&l████████ &7&l████████ &b&l████████\n"
                                        + "&7Plugin, " + instance.pdfFile.getName() + ChatColor.GREEN + " v" + instance.pdfFile.getVersion() + " &7created by," + instance.pdfFile.getAuthors() + "\n"
                                        + "&7Command usage: &e/chat < [enabled] || [disabled] || [clear] || [clearself || [reload] || [clearuser] <user> >\n"
                                        + "&7Permissions: &2Build.cmd &2&l|| &7Build.bypass\n"
                                        + "&7Questions? &7Comments? &a&oBug reports?\n Use /chat-report to help out!"
                        ));
                        break;

                    case "permissions":
                        if(!SPermissions.SURVIVAL_CHAT.checkPermission(sender));
                        else
                            sender.sendMessage(color("&7=======[&ePermissions&7]======="));
                        sender.sendMessage(color("&7Build.* &b&l--&b&l> &bGrants all access to the plugin. \n" +
                                "&7Build.reload &8&l--&b&l> &bGrants permission to reload the plugin. \n" +
                                "&7Build.cmd &8&l--&b&l> &bGrants permission for base command. \n" +
                                "&7Build.enable &8&l--&b&l> &bAllows you to enable Chat.\n" +
                                "&7Build.disable &8&l--&b&l> &bAllows you to disable Chat.\n" +
                                "&7Build.bypass &8&l--&b&l> &bAllows you to bypass chat lock.\n" +
                                "&7Build.clear &8&l--&b&l> &bAllows you to clear Global chat.\n" +
                                "&7Build.clearself &b&l--&b&l> &bAllows you to clear your own chat.\n" +
                                "&7Build.permissions &8&l--&b&l> &bAllows you to see permissions.\n" +
                                "&7Build.clearothers &8&l--&b&l> &bAllows you to clear another users chat."));
                        break;

                    case "clearuser":
                    case "cu":
                        if(!SPermissions.SURVIVAL_CHAT_CLEAR_OTHERS.checkPermission(sender));
                        else if(args.length == 1)
                            sender.sendMessage(color("&7Command usage is &a&n/chat clearuser <username>"));
                        else if(args.length > 1)
                        {
                            Player target = Bukkit.getServer().getPlayer(args[1]);
                            if(target == null)
                            {
                                sender.sendMessage(color("&7Warning: The user, &a&o" + args[1] + " &7couldn't be found."));
                            }else {
                                clearPlayer(target);
                                sender.sendMessage(color("&7You have &ocleared &7the chat for the user, &a&o" +target.getName() + "&7."));
                            }
                        }
                        break;
                    default:
                        sender.sendMessage(color("&7Unknown argument, &a" + args[0] + "&7!"));
                }
            }
        }


        return true;
    }
}
