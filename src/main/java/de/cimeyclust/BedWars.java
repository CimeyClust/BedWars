package de.cimeyclust;

import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import cn.nukkit.plugin.service.RegisteredServiceProvider;
import com.nukkitx.fakeinventories.inventory.FakeInventories;
import de.cimeyclust.commands.BedWarsMainCommand;

public class BedWars extends PluginBase
{
    @Override
    public void onEnable()
    {
        RegisteredServiceProvider<FakeInventories> provider = getServer().getServiceManager().getProvider(FakeInventories.class);

        if (provider == null || provider.getProvider() == null) {
            this.getServer().getPluginManager().disablePlugin(this);
        }

        this.registerCommand();
        this.registerListener();
        getLogger().info("§aThe BedWars plugin started successfully!");
    }

    @Override
    public void onDisable()
    {
        getLogger().info("§cThe BedWars plugin has been deactivated successfully!");
    }

    private void registerCommand()
    {
        SimpleCommandMap commandMap = this.getServer().getCommandMap();

        commandMap.register("help", new BedWarsMainCommand("bedwars", "BedWars-Plugin Main-Command", "§c/bedwars", new String[]{"bw"}, this));
    }

    private void registerListener()
    {
        PluginManager manager = this.getServer().getPluginManager();

        // manager.registerEvents(new PlayerActionListener(this), this);
    }
}
