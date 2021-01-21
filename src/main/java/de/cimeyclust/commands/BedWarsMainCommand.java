package de.cimeyclust.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import de.cimeyclust.BedWars;

public class BedWarsMainCommand extends Command {

    private BedWars plugin;

    public BedWarsMainCommand(String name, String description, String usageMessage, String[] aliases, BedWars plugin) {
        super(name, description, usageMessage, aliases);
        this.setPermission("bedwars");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender commandSender, String commandLabel, String[] args) {
        if(commandSender instanceof Player)
        {
            Player player = ((Player) commandSender).getPlayer();

            if(player.hasPermission("bedwars")) {
                String option = args[0];

                if (option instanceof String)
                {
                    if(option.equals("create"))
                    {

                    }
                }
                else
                {
                    player.sendMessage("§c"+this.getUsage());
                }
            }
        }
        else
        {
            commandSender.sendMessage("§cYou can only execute this command as a player!");
        }


        return true;
    }
}
