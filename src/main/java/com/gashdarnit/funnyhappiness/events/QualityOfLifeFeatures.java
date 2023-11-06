package com.gashdarnit.funnyhappiness.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class QualityOfLifeFeatures implements Listener {
    @EventHandler
    public void craftNetherWartFromCrate(PrepareItemCraftEvent event) {
        ItemStack item = new ItemStack(Material.SADDLE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("Crate of Nether Warts");
        meta.setCustomModelData(637782970);
        meta.setLore(null);
        item.setItemMeta(meta);

        craftCheck(new ItemStack(Material.NETHER_WART, 9), event.getInventory(), item);
    }

    @EventHandler
    public void craftNetherWartCrateBundle(PrepareItemCraftEvent event) {
        ItemStack material = new ItemStack(Material.SADDLE);
        ItemMeta materialMeta = material.getItemMeta();

        materialMeta.setDisplayName("Crate of Nether Warts");
        materialMeta.setCustomModelData(637782970);
        materialMeta.setLore(null);
        material.setItemMeta(materialMeta);

        ItemStack result = new ItemStack(Material.SADDLE);
        ItemMeta resultMeta = result.getItemMeta();

        resultMeta.setDisplayName("Bundle of Nether Warts Crate");
        resultMeta.setCustomModelData(197872505);
        resultMeta.setLore(null);
        result.setItemMeta(resultMeta);

        Map<Integer, ItemStack> materials = new HashMap<>();
        for(int i = 0; i < event.getInventory().getMatrix().length; i++)
            materials.put(i, material);

        craftCheck(result, event.getInventory(), materials);
    }

    @EventHandler
    public void craftNetherWartCrateFromChest(PrepareItemCraftEvent event) {
        ItemStack item = new ItemStack(Material.SADDLE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("Bundle of Nether Warts Crate");
        meta.setCustomModelData(197872505);
        meta.setLore(null);
        item.setItemMeta(meta);

        ItemStack result = new ItemStack(Material.SADDLE, 9);
        ItemMeta resultMeta = result.getItemMeta();

        resultMeta.setCustomModelData(637782970);
        resultMeta.setDisplayName("Crate of Nether Warts");
        resultMeta.setLore(null);
        result.setItemMeta(resultMeta);

        craftCheck(result, event.getInventory(), item);
    }

    private void craftCheck(ItemStack expectedResult, CraftingInventory inventory, Map<Integer, ItemStack> materials) {
        ItemStack[] items = inventory.getMatrix();
        int validItems = 0;
        int airCount = 0;

        for(int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                airCount++;

            } else if(items[i] != null && validateMaterial(items[i], materials.get(i))) {
                validItems++;
            } else return;
        }

        if(validItems == materials.size() && airCount == items.length - materials.size()) {
            inventory.setResult(expectedResult);
        }
    }

    private void craftCheck(ItemStack expectedResult, CraftingInventory inventory, ItemStack material) {
        ItemStack[] items = inventory.getMatrix();
        int validItems = 0;
        int airCount = 0;

        for(int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                airCount++;

            } else if(items[i] != null && validateMaterial(items[i], material)) {
                validItems++;
            } else return;
        }

        if(validItems == 1 && airCount == items.length - validItems) {
            inventory.setResult(expectedResult);
        }
    }


    private boolean validateMaterial(ItemStack itemOne, ItemStack itemTwo) {
        if(itemOne.hasItemMeta() && itemTwo.hasItemMeta()) {
            int passCount = 0;

            if(itemOne.getType() == itemTwo.getType()) passCount++;
            if(itemOne.getItemMeta().getDisplayName().equals(itemTwo.getItemMeta().getDisplayName())) passCount++;
            if(itemOne.getItemMeta().getCustomModelData() == itemTwo.getItemMeta().getCustomModelData()) passCount++;

            if(passCount == 3) return true;
        }

        return false;
    }

}
