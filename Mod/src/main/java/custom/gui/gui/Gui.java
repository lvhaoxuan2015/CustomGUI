package custom.gui.gui;

import net.java.games.input.Component.Identifier.Button;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnchantmentNameParts;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiSlot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import custom.gui.CustomGUI;
import custom.gui.gui.object.EGuiField;
import custom.gui.gui.object.EGuiObject;
import io.netty.buffer.Unpooled;

@SideOnly(Side.CLIENT)
public class Gui extends GuiScreen {
	public GuiScreen old;
	public List<EGuiObject> objList = new ArrayList<>();
	public List<GuiButton> EbuttonList = new ArrayList<>();
	public List<GuiTextField> GuiFieldList = new ArrayList<>();
	public int guiID;

	public Gui(GuiScreen old, List<EGuiObject> list, int guiID) {
		this.old = old;
		this.objList = list;
		this.EbuttonList = buttonList;
		this.guiID = guiID;
	}
	
	public void initGui() {
		for (EGuiObject obj : objList) {
			obj.init(this);
		}
	}

	public FontRenderer getFontRenderer() {
		return fontRendererObj;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		for (EGuiObject obj : objList) {
			obj.draw(this);
		}
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
		for (GuiTextField egf : GuiFieldList) {
			egf.textboxKeyTyped(typedChar, keyCode);
		}
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
    	for (GuiTextField egf : GuiFieldList) {
			egf.mouseClicked(mouseX, mouseY, mouseButton);
		}
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		CustomGUI.net.sendToServer(new FMLProxyPacket(
				new PacketBuffer(
						Unpooled.wrappedBuffer(("CLICKCUSTOMGUIBUTTON:" + this.guiID + ";" + button.id).getBytes())),
				CustomGUI.MODID));
	}
}