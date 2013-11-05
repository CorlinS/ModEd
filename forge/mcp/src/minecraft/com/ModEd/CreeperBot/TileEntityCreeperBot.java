package com.ModEd.CreeperBot;

import java.util.ArrayList;
import java.util.List;

import com.ModEd.ModEd;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.world.World;

public class TileEntityCreeperBot extends TileEntity {

	private boolean moved = false;
	private List<Integer> commands;
	private int direction;
	
	public TileEntityCreeperBot() {
		commands = new ArrayList<Integer>(10);
		direction = 3;
	}
	
	public TileEntityCreeperBot(int direction) {
		this.direction = direction;
	}

	public boolean move(int i)
	{
		int j = xCoord;
		int k = yCoord;
		int l = zCoord;
		int i1 = xCoord + i * Facing.offsetsXForSide[direction];
		int j1 = yCoord + i * Facing.offsetsYForSide[direction];
		int k1 = zCoord + i * Facing.offsetsZForSide[direction];
//		if (!canPlaceInBlock(j1))
//		{
//			return false;
//		}
		int l1 = worldObj.getBlockId(i1, j1, k1);
		if (l1 != 0 && l1 != Block.waterStill.blockID && l1 != Block.waterMoving.blockID && l1 != Block.lavaStill.blockID && l1 != Block.lavaMoving.blockID && l1 != Block.fire.blockID && l1 != Block.snow.blockID)
		{
			Block block = Block.blocksList[l1];
			if (!block.isBlockReplaceable(worldObj, i1, j1, k1))
			{
				return false;
			}
		}
//		AxisAlignedBB axisalignedbb = ModEd.blockCreeperBot.getCollisionBoundingBoxFromPool(worldObj, i1, j1, k1);
//		if (axisalignedbb != null && !worldObj.checkIfAABBIsClear(axisalignedbb))
//		{
//			return false;
//		}
		moved = true;
		World world = worldObj;
		world.setBlock(j, k, l, 0);
//		int i2 = ((clientState.dir - 2 & 3) << 2) + (clientState.subType & 3);
		world.setBlock(i1, j1, k1, ModEd.blockCreeperBot.blockID, 3, 3);
		TileEntity tileentity = world.getBlockTileEntity(i1, j1, k1);
		if (tileentity != null && (tileentity instanceof TileEntityCreeperBot))
		{
			TileEntityCreeperBot tileentitycreeperbot = (TileEntityCreeperBot)tileentity;
			tileentitycreeperbot.transferStateFrom(this);
			//tileentitycreeperbot.startAnimation(0);
			//tileentitycreeperbot.updateAnimation();
		}
		world.notifyBlockChange(j, k, l, 0);
		world.notifyBlockChange(i1, j1, k1, ModEd.blockCreeperBot.blockID);
		return true;
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);

		NBTTagList tagList = tagCompound.getTagList("Commands");
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);

		}
	}
	
	public void setDir(int facing) {
		direction = facing;
	}
	
	public void transferStateFrom(TileEntityCreeperBot tileentitycreeperbot)
    {
        commands = tileentitycreeperbot.commands;
        //clientState = tileentitycreeperbot.clientState;
        
        moved = false;
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

