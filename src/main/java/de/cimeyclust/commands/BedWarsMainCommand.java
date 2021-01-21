package de.cimeyclust.commands;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import de.cimeyclust.BedWars;

public class BedWarsMainCommand extends Command {

    private BedWars plugin;

    public BedWarsMainCommand(String name, String description, String usageMessage, String[] aliases, BedWars plugin) {
        super(name, description, usageMessage, aliases);
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        return false;
    }
}
