package de.cimeyclust;

import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import cn.nukkit.plugin.service.RegisteredServiceProvider;
import com.nukkitx.fakeinventories.inventory.FakeInventories;

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
        getLogger().info("§aDas Plugin wurde erfolgreich gestartet!");
    }

    @Override
    public void onDisable()
    {
        getLogger().info("§cDas Plugin wurde erfolgreich deaktiviert!");
    }

    private void registerCommand()
    {
        SimpleCommandMap commandMap = this.getServer().getCommandMap();

        // commandMap.register("help", new CancelCommand("cancel", "Bricht eine angefangene Aktion ab!", "§cUsage: /cancel", this));
    }

    private void registerListener()
    {
        PluginManager manager = this.getServer().getPluginManager();

        // manager.registerEvents(new PlayerActionListener(this), this);
    }
}
