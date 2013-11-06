package com.ModEd.client;

import com.ModEd.CommonProxy;
import com.ModEd.CreeperBot.TileEntityCreeperBot;
import com.ModEd.CreeperBot.TileEntityCreeperBotRenderer;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderers() {
	// This is for rendering entities and so forth later on
	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCreeperBot.class, new TileEntityCreeperBotRenderer());
    }

}
