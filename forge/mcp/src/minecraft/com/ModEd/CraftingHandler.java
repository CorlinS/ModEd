package com.ModEd;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.ICraftingHandler;

public class CraftingHandler implements ICraftingHandler
{

@Override
public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix)
{
	if (item.itemID == ModEd.blockCreeperBot.blockID) {
		player.addStat(ModEd.CREEPER_BOT_CRAFTED, 1);
	}
}

@Override
public void onSmelting(EntityPlayer player, ItemStack item)
{

}

}

