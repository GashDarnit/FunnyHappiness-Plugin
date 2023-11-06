package com.gashdarnit.funnyhappiness;

import com.gashdarnit.funnyhappiness.commands.MiscellaneousCommands;
import com.gashdarnit.funnyhappiness.events.QualityOfLifeFeatures;
import com.gashdarnit.funnyhappiness.events.Taboo;
import org.bukkit.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class FunnyHappiness extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new QualityOfLifeFeatures(), this);
        getServer().getPluginManager().registerEvents(new Taboo(), this);

        MiscellaneousCommands miscellaneousCommands = new MiscellaneousCommands();
        getCommand("religion").setExecutor(miscellaneousCommands);
        getCommand("religion").setTabCompleter(miscellaneousCommands);

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[FunnyHappiness] is enabled!");

        Bukkit.addRecipe(netherWartCrateRecipe());

    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[FunnyHappiness] is disabled!");
    }

    public ShapedRecipe netherWartCrateRecipe() {
        String name = "Crate of Nether Warts";

        ItemStack item = new ItemStack(Material.SADDLE);
        ItemMeta meta = item.getItemMeta();


        meta.setCustomModelData(637782970);
        meta.setDisplayName(name);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "nether_warts_crate");
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("AAA", "AAA", "ABA");
        recipe.setIngredient('A', Material.NETHER_WART);
        recipe.setIngredient('B', new RecipeChoice.MaterialChoice(Tag.LOGS));

        return recipe;
    }

}
