package com.ModEd.CreeperBot;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBotHead extends Item {

	public ItemBotHead(int id) {
		super(id);
		
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName("Bot Head");
		setTextureName("moded:skull_creeper");
	}

}
