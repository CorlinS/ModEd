package com.ModEd.CreeperBot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.ModEd.ModEd;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.entity.monster.*;


public class TileEntityCreeperBot extends TileEntity {

	private boolean moved = false;
	public List<Integer> commands;
	private int direction;
	private static final ScheduledExecutorService worker = 
			  Executors.newSingleThreadScheduledExecutor();
	
	public TileEntityCreeperBot() {
		commands = new ArrayList<Integer>(10);
		direction = 3;
	}
	
	public TileEntityCreeperBot(int direction) {
		this.direction = direction;
	}
	
	public void runCommand() {
			
		//If we have a list, run the first command
		if(!this.commands.isEmpty()) {
			//Run the first command in the list, then delete it. 
			 Runnable task = new Runnable() {
				    private int command;
	
				    public Runnable init(int command) {
				    	this.command = command;
				    	return (this);
				    }
				    
					public void run() {
						System.out.println("running command: " + command);
						switch (command) {
							case 1:
								move(1);
								break;
							case 2:
								move(-1);
								break;
							case 3:
								turnRight();
								break;
							case 4:
								turnLeft();
								break;
						}
				    }
				  }.init(commands.get(0));
				  
	  
			  worker.schedule(task, 500, TimeUnit.MILLISECONDS);
			  commands.remove(0);
		} else {
	
//			EntityIronGolem asdf = new EntityIronGolem(this.worldObj);
//	        
//			Explosion boom = this.worldObj.createExplosion(asdf, (double)this.xCoord, 
//					(double)this.yCoord, (double)this.zCoord, (float)(1), true);
//			boom.doExplosionA();
		}
		  
	}
	
	

	public void turnRight()
	{
		System.out.println("turning right " + this.direction);
		 switch (direction) {
	         case 2 :
	        	 	direction = 4;
	                 break;
	         case 3 :
	        	 	direction = 5;
	                 break;
	         case 4 :
	        	 	direction = 3;
	                 break;
	         case 5 :
	        	 	direction = 2;
	                 break;
		 }
		 
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, getDirection(), 0);

		this.runCommand();
	}
	
	

	public void turnLeft()
	{
		System.out.println("turning left " + this.direction);
		switch (direction) {
		   case 2 :
			   direction = 5;
               break;

	       case 3 :
	    	   direction = 4;
	               break;
	
	       case 4 :
	    	   direction = 2;
	               break;
	
	       case 5 :
	    	   direction = 3;
	               break;
		 }

		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, getDirection(), 0);

		this.runCommand();
	}
	
	public boolean move(int i)
	{
		System.out.println("moving " + i);
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
				//We hit something! EXPLOOOODDDE!
				splode();
				
				if (block.blockID == ModEd.blockCreeperBot.blockID) {
					for (Object player : worldObj.playerEntities) {
						((EntityPlayer)player).addStat(ModEd.SELF_LOATHING, 1);
					}
				}
				
				return false;
			}
		}
		AxisAlignedBB axisalignedbb = ModEd.blockCreeperBot.getCollisionBoundingBoxFromPool(worldObj, i1, j1, k1);
		if (axisalignedbb != null && !worldObj.checkNoEntityCollision(axisalignedbb))
		{
			//We hit something! EXPLOOOODDDE!
			splode();
			return false;
		}
		moved = true;
		World world = worldObj;
		world.setBlock(j, k, l, 0);
//		int i2 = ((clientState.dir - 2 & 3) << 2) + (clientState.subType & 3);
		world.setBlock(i1, j1, k1, ModEd.blockCreeperBot.blockID, getDirection(), 3);
		TileEntity tileentity = world.getBlockTileEntity(i1, j1, k1);
		if (tileentity != null && (tileentity instanceof TileEntityCreeperBot))
		{
			TileEntityCreeperBot tileentitycreeperbot = (TileEntityCreeperBot)tileentity;
			tileentitycreeperbot.transferStateFrom(this);
			tileentitycreeperbot.runCommand();
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
	
	private void splode() {
		EntityIronGolem asdf = new EntityIronGolem(this.worldObj);
        
		Explosion boom = this.worldObj.createExplosion(asdf, (double)this.xCoord, 
				(double)this.yCoord, (double)this.zCoord, (float)(3), true);
		boom.doExplosionA();
		
		for (Object player : worldObj.playerEntities) {
			((EntityPlayer)player).addStat(ModEd.KABOOM, 1);
		}
	}
	
	public void transferStateFrom(TileEntityCreeperBot tileentitycreeperbot)
    {
        commands = tileentitycreeperbot.commands;
        //clientState = tileentitycreeperbot.clientState;
        direction  = tileentitycreeperbot.direction;
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
	
	public void programBlock(List<Integer> commands) {
		this.commands = commands;
		runCommand();
	}

	public int getDirection() {
	    return direction;
	}
    
}

