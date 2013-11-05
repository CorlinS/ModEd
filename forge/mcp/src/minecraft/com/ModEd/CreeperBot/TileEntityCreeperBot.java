package com.ModEd.CreeperBot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCreeperBot extends TileEntity {

	public TileEntityCreeperBot() {

	}
	
    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
            super.readFromNBT(tagCompound);
            
            NBTTagList tagList = tagCompound.getTagList("Commands");
            for (int i = 0; i < tagList.tagCount(); i++) {
                    NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
                    
            }
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
            super.writeToNBT(tagCompound);
                            
            NBTTagList commandList = new NBTTagList();
//            for (int i = 0; i < inv.length; i++) {
//                    ItemStack stack = inv[i];
//                    if (stack != null) {
//                            NBTTagCompound tag = new NBTTagCompound();
//                            tag.setByte("Slot", (byte) i);
//                            stack.writeToNBT(tag);
//                            itemList.appendTag(tag);
//                    }
//            }
            tagCompound.setTag("Commands", commandList);
    }

}

