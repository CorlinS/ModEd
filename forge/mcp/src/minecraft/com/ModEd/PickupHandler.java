package com.ModEd;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import cpw.mods.fml.common.IPickupNotifier;

public class PickupHandler implements IPickupNotifier {

	@Override
	public void notifyPickup(EntityItem item, EntityPlayer player)
	{
		if(item.getEntityItem().itemID == Item.gunpowder.itemID) {
			player.addStat(ModEd.GUNPOWDER, 1);
		}
	}

}
