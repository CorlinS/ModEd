package com.ModEd.CreeperBot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ModEd.ModEd;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCreeperBot extends Block implements ITileEntityProvider{

	public BlockCreeperBot (int id) {
		super(id, Material.wood);
		setHardness(1.0F);
		setResistance(1.0F);
		setCreativeTab(CreativeTabs.tabMisc);
		setTextureName("moded:creeper_bot");
	}

	public void breakBlock(World world, int x, int y, int z, int i, int j) {
		world.removeBlockTileEntity(x, y, z);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int metadata, float what, float these, float are) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity == null || player.isSneaking()) {
			return false;
		}
		//code to open gui explained later
		player.openGui(ModEd.instance, 0, world, x, y, z);

		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack itemStack)
	{
		super.onBlockPlacedBy(world, i, j, k, entityliving, itemStack);

		int l = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;

		// The direction for initial block facing
		byte byte0;

		switch (l)
		{
		case 1:
			byte0 = 4;
			break;

		case 2:
			byte0 = 2;
			break;

		case 3:
			byte0 = 5;
			break;

		default:
			byte0 = 3;
			break;
		}
		setDirection(world, i, j, k, byte0);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityCreeperBot();
	}


	private void setDirection(World world, int i, int j, int k, int l)
	{
		TileEntity tileentity = world.getBlockTileEntity(i, j, k);
		if (tileentity != null && (tileentity instanceof TileEntityCreeperBot))
		{
			TileEntityCreeperBot tileentitycreeperbot = (TileEntityCreeperBot)tileentity;
			tileentitycreeperbot.setDir(l);
			world.setBlockMetadataWithNotify(i, j, k, tileentitycreeperbot.getDirection(), 0);

		}
	}

	// This will tell minecraft not to render any side of our cube.
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l) {
		return false;
	}

	// And this tell it that you can see through this block, and neighbor blocks
	// should be rendered.
	public boolean isOpaqueCube() {
		return false;
	}

}
