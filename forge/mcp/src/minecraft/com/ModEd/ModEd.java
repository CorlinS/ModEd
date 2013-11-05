package com.ModEd;

import com.ModEd.CreeperBot.BlockCreeperBot;
import com.ModEd.CreeperBot.ItemBotHead;
import com.ModEd.CreeperBot.ItemBotWheel;
import com.ModEd.CreeperBot.TileEntityCreeperBot;
import com.ModEd.ui.GuiHandler;

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
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="modedmodid", name="ModEd", version="0.0.0")

@NetworkMod(clientSideRequired=true, serverSideRequired=false, 
channels={"GenericRandom"}, packetHandler = PacketHandler.class)

public class ModEd {

        // The instance of your mod that Forge uses.
        @Instance(value = "modedmodid")
        public static ModEd instance;
        
        // Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="com.ModEd.client.ClientProxy", serverSide="com.ModEd.CommonProxy")
        public static CommonProxy proxy;
        
        public static Block blockCreeperBot;
        public static Item itemBotWheel;
        public static Item itemBotHead;
        
        @EventHandler // used in 1.6.2
        //@PreInit    // used in 1.5.2
        public void preInit(FMLPreInitializationEvent event) {

        	blockCreeperBot = new BlockCreeperBot(500);
        	itemBotWheel = new ItemBotWheel(5000);
        	itemBotHead = new ItemBotHead(5001);
        	
        }
        
        @EventHandler // used in 1.6.2
        //@Init       // used in 1.5.2
        public void load(FMLInitializationEvent event) {
           proxy.registerRenderers();
                
           GameRegistry.addRecipe(new ItemStack(itemBotWheel), " x", "xyx", " x",
        		   'x', new ItemStack(Block.cobblestone), 'y', new ItemStack(Item.ingotIron));
           
           GameRegistry.addRecipe(new ItemStack(itemBotHead), "xyx", "yzy", "xyx",
        		   'x', new ItemStack(Block.leaves), 'y', new ItemStack(Item.ingotIron), 'z', new ItemStack(Item.gunpowder));
           
		   GameRegistry.addRecipe(new ItemStack(blockCreeperBot), "x x", " y", "x x",
			        'x', new ItemStack(itemBotWheel), 'y', new ItemStack(itemBotHead));
		   
		   LanguageRegistry.addName(blockCreeperBot, "Creeper Bot");
		   LanguageRegistry.addName(itemBotWheel, "Bot Wheel");
		   LanguageRegistry.addName(itemBotHead, "Bot Head");
		   
		   GameRegistry.registerBlock(blockCreeperBot, "blockCreeperBot");
           GameRegistry.registerTileEntity(TileEntityCreeperBot.class, "tileEntityCreeperBot");
           
           //UI setup. 
           NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
                
                
        }
        
        @EventHandler // used in 1.6.2
        //@PostInit   // used in 1.5.2
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        }

}
