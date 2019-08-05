package commands;

import Utilities.Action;
import Utilities.Rank;
import Utilities.SUtils;
import me.ES96.Survival.com.Survival;
import me.ES96.Survival.com.User;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Signifies on 3/15/2019 - 00:02.
 */
public class BroadcastCommand extends SUtils implements CommandExecutor {

    private Survival instance;
    public BroadcastCommand(Survival utils) {
        this.instance = utils;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {

            if(sender instanceof Player) {
                Player p = (Player)sender;
                if(!User.isPermissible(p, Rank.ADMIN)) {
                    sender.sendMessage(defaultMessage(instance.permissionDefault(),Rank.ADMIN, instance.getMessage(),p));
                    return true
                            ;
                }
            }

                if(args.length < 1){
                    sender.sendMessage(color("&7/broadcast <msg> &a||&r &7<msg> <repeat>"));
                }else {
                    StringBuilder builder = new StringBuilder();
                    for(String arg : args) {
                        builder.append(arg + " ");
                    }
                    String result = builder.toString();
                    String format = instance.getConfig().getString("Broadcast.format");
                    format = format.replace("{msg}",result);
                    Bukkit.getServer().broadcastMessage(color(format));

                    //TODO Fix this later. Im fucking tired.
               /*
               *  int msgCount = Integer.parseInt(args[0]);
                if( msgCount> 0) {
                    String r = builder.toString().replace(args[0],"");
                    format = format.replace("{msg}",r);

                    for (int i=0; i <msgCount; i++) {
                        Bukkit.getServer().broadcastMessage(color(format));
                    }
               * */
                }

        return true;
    }

}
