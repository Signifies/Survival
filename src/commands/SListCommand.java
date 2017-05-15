package commands;

import Utilities.SPermissions;
import Utilities.SUtils;
import me.ES96.Survival.com.Survival;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

/**
 * Created by Evan on 5/14/2017.
 */
public class SListCommand extends SUtils implements CommandExecutor
{

    public Survival instance;

    public SListCommand(Survival value)
    {
        instance = value;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {

        if(SPermissions.SURVIVAL_MANAGEMENT_LIST.checkPermission(sender))
        {

            if(args.length < 1)
            {
                sendText(instance.getConfig().getStringList("list.format"),sender,instance.getConfig().getString("list.messages"));
            }

        }else
        {
            sender.sendMessage(defaultMessage(instance.getConfig().getBoolean("perm-message.default"), instance.getConfig().getString("perm-message.format")));
        }

        return true;
    }

}
