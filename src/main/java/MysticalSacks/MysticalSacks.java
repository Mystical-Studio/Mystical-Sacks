package MysticalSacks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MysticalSacks extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Hello there!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Goodbye!");
    }
}
