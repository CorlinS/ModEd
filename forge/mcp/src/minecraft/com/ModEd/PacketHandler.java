package com.ModEd;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ModEd.CreeperBot.TileEntityCreeperBot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player playerEntity) {
		handleModEdDialog(packet, playerEntity);
	}

	private void handleModEdDialog(Packet packet, Player playerEntity) {
		Packet250CustomPayload customPacket = (Packet250CustomPayload)packet;
		DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(customPacket.data));

		List<Integer> commands = new ArrayList<Integer>();

		try {
			int length = inputStream.readInt();
			int xCoord = inputStream.readInt();
			int yCoord = inputStream.readInt();
			int zCoord = inputStream.readInt();

			for(int i = 0; i < length; i++) { 
				int command = inputStream.readInt();
				if(command < 5) {
						commands.add(command);
				} else {
					//These are the x commands, so add many of a previous command. 
					int multi = (int) Math.pow(2, (command-4));
					int lastcommand = commands.get(commands.size()-1);
					for(int j = 0; j < multi-1; j++) {
						commands.add(lastcommand);
					}
				}
			}
			
			TileEntity tileEntityCreeperBot = ((EntityPlayer)playerEntity).worldObj.getBlockTileEntity(xCoord, yCoord, zCoord);
			
			((TileEntityCreeperBot)tileEntityCreeperBot).programBlock(commands);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		System.out.println("command: " + commands.toString());

	}

}