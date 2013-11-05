package com.ModEd;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

        @Override
        public void onPacketData(INetworkManager manager,
                        Packet250CustomPayload packet, Player playerEntity) {
        	handleModEdDialog(packet);
        }
        
        private void handleModEdDialog(Packet packet) {
        	Packet250CustomPayload customPacket = (Packet250CustomPayload)packet;
        	 DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(customPacket.data));
             
        	 List<Integer> commands = new ArrayList<Integer>();
        	 
             try {
            	 int length = inputStream.readInt();
            	 for(int i = 0; i < length; i++) { 
                     commands.add(inputStream.readInt());
            	 }
             } catch (IOException e) {
                     e.printStackTrace();
                     return;
             }
             
             System.out.println("command: " + commands.toString());
             
        	return;
        }

}