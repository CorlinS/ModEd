package com.ModEd.ui;


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureObject;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.ModEd.ModEd;
import com.ModEd.CreeperBot.TileEntityCreeperBot;

import cpw.mods.fml.common.network.PacketDispatcher;

public class GuiProgramBot extends GuiScreen {
	
    /**
     * Starting X position for the Gui. Inconsistent use for Gui backgrounds.
     */
    protected int guiLeft;

    /**
     * Starting Y position for the Gui. Inconsistent use for Gui backgrounds.
     */
    protected int guiTop;
    
    /** The X size of the inventory window in pixels. */
    protected int xSize = 176;

    /** The Y size of the inventory window in pixels. */
    protected int ySize = 166;

	private List<Integer> commands = new ArrayList<Integer>();
	
	private TileEntityCreeperBot tileEntity;
	
    private static final char up = '\u25B2';
    private static final char dn = '\u25BC';
    private static final char lf = '\u25C0';
    private static final char rg = '\u25B6';
    
    int x = 0;
    int y = 0;
	
    public GuiProgramBot (EntityPlayer player,
    		TileEntityCreeperBot tileEntity) {
    	 super.initGui();
       
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;

        
        this.tileEntity = tileEntity;
            //the container is instanciated and passed to the superclass for handling
           //super(new ContainerTiny(inventoryPlayer, tileEntity));
        }

  
        protected void drawGuiContainerBackgroundLayer(int param1, int param2) {
                //draw text and stuff here
            //the parameters for drawString are: string, x, y, color
            
            	ResourceLocation rel = new ResourceLocation("moded","textures/gui/demo_background.png");
            	
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                this.mc.getTextureManager().bindTexture(rel);
     
                int x = 10; //(width - xSize) / 2;
                int y = 10; //(height - ySize) / 2;
                this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
                
                fontRenderer.drawString("Commands to give the bot:", x+8, y+6, 4210752);
                
                String displayCommands = "Commands: ";
                
                for(Integer i : commands) {
                	switch(i) {
	                	case 1: 
	                		displayCommands += up + " ";
	                		break;
	                	case 2: 
	                		displayCommands += dn + " ";
	                		break;
	                	case 3: 
	                		displayCommands += lf + " ";
	                		break;
	                	case 4: 
	                		displayCommands += rg + " ";
	                		break;
	                	case 5: 
	                		displayCommands += "x2 ";
	                		break;
	                	case 6: 
	                		displayCommands += "x4 ";
	                		break;
	                	case 7: 
	                		displayCommands += "x8 ";
	                		break;
	                	case 8: 
	                		displayCommands += "x16 ";
	                		break;
                	}
                }
                
                displayCommands = displayCommands.substring(0, displayCommands.length() - 1);
                
                int ycoords = y+70;
                
                while(displayCommands.length() > 30) {
                	fontRenderer.drawString(displayCommands.substring(0,30), x+10, ycoords, 4210752);
                	displayCommands = displayCommands.substring(30, displayCommands.length());
                	ycoords += 15;
                }
                fontRenderer.drawString(displayCommands, x+10, ycoords, 4210752);
             
            
            //TODO: LOCALIZE our mod. 
            // fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
    	
    	// this.drawDefaultBackground();
         int k = this.guiLeft;
         int l = this.guiTop;
         
         this.drawGuiContainerBackgroundLayer(par1, par2);
//             GL11.glDisable(GL12.GL_RESCALE_NORMAL);
//             RenderHelper.disableStandardItemLighting();
//             GL11.glDisable(GL11.GL_LIGHTING);
//             GL11.glDisable(GL11.GL_DEPTH_TEST);
         super.drawScreen(par1, par2, par3);
//             RenderHelper.enableGUIStandardItemLighting();
//             GL11.glPushMatrix();
//             GL11.glTranslatef((float)k, (float)l, 0.0F);
//             GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//             GL11.glEnable(GL12.GL_RESCALE_NORMAL);
         

//             GL11.glEnable(GL11.GL_LIGHTING);
//             GL11.glEnable(GL11.GL_DEPTH_TEST);
//             RenderHelper.enableStandardItemLighting();
//             
        
         
//             GL11.glDisable(GL11.GL_LIGHTING);
//             this.drawGuiContainerForegroundLayer(par1, par2);
//             GL11.glEnable(GL11.GL_LIGHTING);
        
    }
    
    @Override
    public void initGui() {
           
	        int x = 10; //(width - xSize) / 2;
	        int y = 10; //(height - ySize) / 2;
	        
        
            //id, x, y, width, height, text
            buttonList.add(new GuiButton(1, x+30, y+20, 20, 20, Character.toString(up)));
            buttonList.add(new GuiButton(2, x+30, y+40, 20, 20, Character.toString(dn)));
            buttonList.add(new GuiButton(3, x+10, y+30, 20, 20, Character.toString(lf)));
            buttonList.add(new GuiButton(4, x+50, y+30, 20, 20, Character.toString(rg)));
            
            
            buttonList.add(new GuiButton(5, x+70, y+50, 20, 20, "x2"));
            buttonList.add(new GuiButton(6, x+90, y+50, 20, 20, "x4"));
            buttonList.add(new GuiButton(7, x+110, y+50, 20, 20, "x8"));
            buttonList.add(new GuiButton(8, x+130, y+50, 20, 20, "x16"));
            
            
            buttonList.add(new GuiButton(100, x+120, y+20, 30, 20, "GO!"));
    }

    protected void actionPerformed(GuiButton guibutton) {
            //id is the id you give your button
            switch(guibutton.id) {
            
            case 100:          
                	ByteArrayOutputStream bos = new ByteArrayOutputStream(8 * commands.size());
                    DataOutputStream outputStream = new DataOutputStream(bos);
                    try {
                    	//Write our size
                    	outputStream.writeInt(commands.size());
                    	outputStream.writeInt(tileEntity.xCoord);
                    	outputStream.writeInt(tileEntity.yCoord);
                    	outputStream.writeInt(tileEntity.zCoord);
                    	//Write our data
                    	for(Integer i : commands) {
                            outputStream.writeInt(i.intValue());
                    	}
                    } catch (Exception ex) {
                            ex.printStackTrace();
                    }

                    Packet250CustomPayload packet = new Packet250CustomPayload();
                    packet.channel = "GenericRandom";
                    packet.data = bos.toByteArray();
                    packet.length = bos.size();
                    
                    //Packet code here
                    PacketDispatcher.sendPacketToServer(packet); //send packet               
                   
           	    //close the window. 
                this.mc.thePlayer.closeScreen();   
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            	if(!commands.isEmpty()) {
            		int command = commands.get(commands.size()-1);
            		if(command < 5) {
            			commands.add(guibutton.id);
            		}
            	}
            	break;
            default:
            	commands.add(guibutton.id);
            	break;
            	
                
            }
            
    }

}