package com.ModEd;

import com.ModEd.CreeperBot.BlockCreeperBot;
import com.ModEd.CreeperBot.TileEntityCreeperBot;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
//import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;  //1.6.X
//import cpw.mods.fml.common.Mod.PostInit;
//import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="ModEdModID", name="ModEd", version="0.0.0")
@NetworkMod(clientSideRequired=true)

public class ModEd {

        // The instance of your mod that Forge uses.
        @Instance(value = "ModEdModID")
        public static ModEd instance;
        
        // Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="com.ModEd.client.ClientProxy", serverSide="com.ModEd.CommonProxy")
        public static CommonProxy proxy;
        
        public static Block blockCreeperBot;
        
        @EventHandler // used in 1.6.2
        //@PreInit    // used in 1.5.2
        public void preInit(FMLPreInitializationEvent event) {

        	blockCreeperBot = new BlockCreeperBot(500);
        	
        }
        
        @EventHandler // used in 1.6.2
        //@Init       // used in 1.5.2
        public void load(FMLInitializationEvent event) {
           proxy.registerRenderers();
                
		   ItemStack dirtStack = new ItemStack(Block.dirt);
		   ItemStack diamondsStack = new ItemStack(Item.diamond, 64);
		
		   GameRegistry.addShapelessRecipe(diamondsStack, dirtStack, dirtStack);
		   
		   LanguageRegistry.addName(blockCreeperBot, "Creeper Bot");
		   GameRegistry.registerBlock(blockCreeperBot, "blockCreeperBot");
           GameRegistry.registerTileEntity(TileEntityCreeperBot.class, "tileEntityCreeperBot");
                
        }
        
        @EventHandler // used in 1.6.2
        //@PostInit   // used in 1.5.2
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        }

}
