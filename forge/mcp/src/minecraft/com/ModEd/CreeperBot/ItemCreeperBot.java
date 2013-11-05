package com.ModEd.CreeperBot;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemCreeperBot extends Item {

	public ItemCreeperBot(int id) {
		super(id);
		
		// Constructor configuration
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName("creaperBot");

	}

}
