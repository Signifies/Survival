package me.ES96.Survival.com;

import Utilities.Debug;
import Utilities.Rank;
import Utilities.SPermissions;
import Utilities.SUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;
import java.util.UUID;

/**
 * Created by Evan on 5/14/2017.
 */
public class CommandListeners extends SUtils implements CommandExecutor, Listener
{

    private Survival instance;

    public CommandListeners(Survival value)
    {
        instance=value;
    }

    @EventHandler
    public void onCmd(PlayerCommandPreprocessEvent event)
    {
        Debug.log(getPrefix() + " &aCommand pre-process event.");
        Player p = event.getPlayer();
        UUID uuid = p.getUniqueId();

        String denied = this.instance.getConfig().getString("restriction-msg");
        denied = denied.replace("%player%", p.getName());
        String message[] = event.getMessage().split(" ");
        String command = message[0];
        List<String> denyList = this.instance.getConfig().getStringList("blocked-cmds");

        if(denyList.contains(command))
        {
            if(p.isOp() || !SPermissions.COMMAND_BYPASS.checkPermission(p))
            {
                event.setCancelled(true);
                p.sendMessage(color(denied));
                for(Player staff : Bukkit.getServer().getOnlinePlayers())
                {
                    if(instance.getNotifications().contains(staff.getUniqueId()))
                    {
                        staff.sendMessage(color("&c&l> &c" + p.getName() + " &7used the &4blocked cmd: &c" + command));
                    }
                }
            }
        }else
        {
            event.setCancelled(false);
        }
    }

    public void evaluate(Player player) {
        if(User.evaluateNotificationSettings(player.getUniqueId())) {
            instance.getNotifications().remove(player.getUniqueId());
            player.sendMessage(color("&7You are now receiving general &anotifications."));
        }else {
            instance.getNotifications().add(player.getUniqueId());
            player.sendMessage(color("&7You are no longer receiving command &anotifications"));
        }
    }

    public void evaluateWL(Player player) {
        if(User.evaluateWhitelistNotifications(player.getUniqueId())) {
            instance.getWhitelistNotifications().remove(player.getUniqueId());
            player.sendMessage(color("&7You are now receiving whitelist &anotifications."));
        }else {
            instance.getWhitelistNotifications().add(player.getUniqueId());
            player.sendMessage(color("&7You are no longer receiving whitelist &anotifications"));
        }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {

        if(!(sender instanceof Player))
        {
            sender.sendMessage(color("%prefix% &cNot for use by the console."));
            return true;
        }

        Player p =(Player)sender;

        if(cmd.getName().equalsIgnoreCase("notifications") && !User.isPermissible(p, Rank.MOD))
        {
            p.sendMessage(defaultMessage(instance.getConfig().getBoolean("use-default-msg"), "restriction-msg"));
        }else if(args.length < 1)
        {
            p.sendMessage(color("&a&l>> &7Command usage: /notify [general] [whitelist] [staff]"));
        }else if(args.length > 0)
        {
            switch (args[0])
            {
                case "general":
                case "galerts":
                    evaluate(p);
                    break;

                case "whitelist":
                case "wl":
                    evaluateWL(p);
                    break;

                default:
                    p.sendMessage(color("&7Unknown argument..."));
                    p.sendMessage(color("&7Command usage: /notify <&b&l|| &a[notifications]&f>"));
            }
        }

        return true;
    }

}
