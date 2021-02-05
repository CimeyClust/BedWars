package de.cimeyclust.listener;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.blockentity.BlockEntitySign;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import de.cimeyclust.BedWars;

public class RoundListener implements Listener
{
    private BedWars plugin;

    public RoundListener(BedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoinRoundEvent(PlayerInteractEvent event)
    {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        if(block.getLevel().getBlockEntity(block.getLocation()) instanceof BlockEntitySign) {
            BlockEntitySign blockEntitySign = (BlockEntitySign) block.getLevel().getBlockEntity(block.getLocation());
            String roundName = null;
            for(String name : this.plugin.getBedWarsAPI().getNames())
            {
                if(this.plugin.getBedWarsAPI().getJoinSign(name).getLocation().equals(blockEntitySign) && blockEntitySign.getText()[0].contains("BedWars"))
                {
                    break;
                }
                roundName = name;
            }
            if(roundName != null && this.plugin.getRoundManager().checkIfRoundExists(roundName))
            {
                player.sendMessage(roundName);
            }
        }
    }
}
