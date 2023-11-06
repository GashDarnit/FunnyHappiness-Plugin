package com.gashdarnit.funnyhappiness;

import com.gashdarnit.funnyhappiness.commands.MiscellaneousCommands;
import com.gashdarnit.funnyhappiness.events.BetterShield;
import com.gashdarnit.funnyhappiness.events.Taboo;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class FunnyHappiness extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BetterShield(), this);
        getServer().getPluginManager().registerEvents(new Taboo(), this);

        MiscellaneousCommands miscellaneousCommands = new MiscellaneousCommands();
        getCommand("religion").setExecutor(miscellaneousCommands);
        getCommand("religion").setTabCompleter(miscellaneousCommands);

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[FunnyHappiness] is enabled!");

    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[FunnyHappiness] is disabled!");
    }
}
