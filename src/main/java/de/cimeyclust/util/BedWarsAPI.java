package de.cimeyclust.util;

import cn.nukkit.utils.Config;
import de.cimeyclust.BedWars;

import java.io.File;

public class BedWarsAPI
{
    private BedWars plugin;
    private File file;
    private Config config;

    public BedWarsAPI(BedWars plugin) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), "config.yml");
        this.config = new Config(this.file, Config.YAML);
    }

    // Getter Methods


    // Setter Methods


}
