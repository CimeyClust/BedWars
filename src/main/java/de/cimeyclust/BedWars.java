package de.cimeyclust;

import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import cn.nukkit.plugin.service.RegisteredServiceProvider;
import com.nukkitx.fakeinventories.inventory.FakeInventories;
import de.cimeyclust.commands.BedWarsMainCommand;
import de.cimeyclust.util.BedWarsAPI;
import de.cimeyclust.util.BedWarsConfig;
import de.cimeyclust.util.Functions;
import de.cimeyclust.util.RoundManager;

public class BedWars extends PluginBase
{
    private BedWarsAPI bedWarsAPI;
    private RoundManager roundManager;
    private BedWarsConfig bedWarsConfig;
    private Functions functions;

    @Override
    public void onEnable()
    {
        RegisteredServiceProvider<FakeInventories> provider = getServer().getServiceManager().getProvider(FakeInventories.class);

        if (provider == null || provider.getProvider() == null) {
            this.getServer().getPluginManager().disablePlugin(this);
        }
        this.bedWarsAPI = new BedWarsAPI(this);
        this.roundManager = new RoundManager(this);
        this.bedWarsConfig = new BedWarsConfig(this);
        this.functions = new Functions(this);


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

    public BedWarsAPI getBedWarsAPI()
    {
        return this.bedWarsAPI;
    }

    public BedWarsConfig getBedWarsConfig()
    {
        return this.bedWarsConfig;
    }

    public RoundManager getRoundManager()
    {
        return this.roundManager;
    }

    public Functions getFunctions()
    {
        return this.functions;
    }
}
