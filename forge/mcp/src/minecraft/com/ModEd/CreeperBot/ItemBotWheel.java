package com.ModEd.CreeperBot;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBotWheel extends Item {

	public ItemBotWheel(int id) {
		super(id);
		
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName("Bot Wheel");
		setTextureName("moded:bot_wheel");
	}

}
