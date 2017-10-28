package Utilities;

import me.ES96.Survival.com.Survival;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.logging.Level;

public class UUIDConfig
{
    private FileConfiguration UUIDConfig = null;
    private File UUIDConfigFile = null;
    private static final String fileName = "uuids.yml";

    private Survival main;

    public UUIDConfig(Survival instance)
    {
        main = instance;
    }


    public void reloadUUIDConfig() {
        if (UUIDConfigFile == null) {
            UUIDConfigFile = new File(main.getDataFolder(),fileName);
        }
        UUIDConfig = YamlConfiguration.loadConfiguration(UUIDConfigFile);

        // Look for defaults in the jar
        Reader defConfigStream = null;
        try {
            defConfigStream = new InputStreamReader(main.getResource(fileName), "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            UUIDConfig.setDefaults(defConfig);
        }
    }

    public FileConfiguration getUUIDConfig() {
        if (UUIDConfig == null) {
            reloadUUIDConfig();
        }
        return UUIDConfig;
    }

    public void saveUUIDConfig() {
        if (UUIDConfig == null || UUIDConfigFile == null) {
            return;
        }
        try {
            getUUIDConfig().save(UUIDConfigFile);
        } catch (IOException ex) {
            main.getLogger().log(Level.SEVERE, "Could not save config to " + UUIDConfigFile, ex);
        }
    }

    public void saveDefaultUUIDConfig() {
        if (UUIDConfigFile == null) {
            UUIDConfigFile = new File(main.getDataFolder(), fileName);
        }
        if (!UUIDConfigFile.exists()) {
            main.saveResource(fileName, false);
        }
    }
}