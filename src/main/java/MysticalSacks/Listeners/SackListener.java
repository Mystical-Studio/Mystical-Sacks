package MysticalSacks.Listeners;

import MysticalSacks.MysticalSacks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SackListener implements Listener {

    private final MysticalSacks plugin;

    public SackListener(MysticalSacks plugin) {
        this.plugin = plugin;
    }

    // Detect right-click with a Mystical Sack
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Action action = event.getAction();
        ItemStack item = event.getItem();
        Player player = event.getPlayer();

        if (item == null || item.getType() != Material.BUNDLE) return;

        if (!isMysticalSack(item)) return;

        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            event.setCancelled(true);
            openMysticalSackGUI(player);
        }
    }

    // Optional: Detect clicking it inside inventory
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack currentItem = event.getCurrentItem();
        if (currentItem == null || currentItem.getType() != Material.BUNDLE) return;

        if (!isMysticalSack(currentItem)) return;

        if (event.isRightClick()) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            openMysticalSackGUI(player);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().getInventory().addItem(createMysticalSack());
    }

    private boolean isMysticalSack(ItemStack item) {
        if (item == null || item.getType() != Material.BUNDLE) return false;
        ItemMeta meta = item.getItemMeta();
        return meta != null && meta.getDisplayName() != null &&
                ChatColor.stripColor(meta.getDisplayName()).equalsIgnoreCase("Mystical Sack");
    }

    private void openMysticalSackGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 36, ChatColor.DARK_PURPLE + "Mystical Sack");
        player.openInventory(gui);
        player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1f, 1f);
    }

    private ItemStack createMysticalSack() {
        ItemStack sack = new ItemStack(Material.BUNDLE);
        ItemMeta meta = sack.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Mystical Sack");
        sack.setItemMeta(meta);
        return sack;
    }
}
