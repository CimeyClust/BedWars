package de.cimeyclust.listener;


import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.level.Level;
import de.cimeyclust.BedWars;

public class SetupListener implements Listener
{
    private BedWars plugin;

    public SetupListener(BedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerSendMessage(PlayerChatEvent event)
    {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if(this.plugin.getBedWarsAPI().getSetupWorld())
        {
            if(message.equals("cancel"))
            {
                player.sendMessage("§cSetup was canceled successfully!");
                event.setCancelled(true);
                return;
            }

            for(Level level : this.plugin.getServer().getLevels().values())
            {
                if(level.getName().equals(message))
                {
                    this.plugin.getBedWarsAPI().setWorld(level);
                    player.sendMessage("§aThis map was solidified in the world "+level.getName()+".");
                    player.sendMessage("§aType in how many teams should there ever be a game?");
                    event.setCancelled(true);
                    return;
                }
            }
            player.sendMessage("§cThe world with the given name was not found; Cancellation!");
            event.setCancelled(true);
            return;
        }
        else if(this.plugin.getBedWarsAPI().getSetupTeamNumber())
        {
            if(message.equals("cancel"))
            {
                player.sendMessage("§cSetup was canceled successfully!");
                event.setCancelled(true);
                return;
            }

            if(this.plugin.getFunctions().isNumeric(message))
            {

            }
            else
            {
                player.sendMessage("§cYou have to enter a whole positive number!");
            }
        }
    }
}
