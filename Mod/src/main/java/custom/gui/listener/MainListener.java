package custom.gui.listener;

import custom.gui.CustomGUI;
import custom.gui.gui.Gui;
import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;

public class MainListener {
	@SubscribeEvent
	public void GuiOpenEvent(GuiOpenEvent e) {
		if (e.getGui() instanceof Gui) {
			Gui gui = (Gui) e.getGui();
			CustomGUI.net.sendToServer(new FMLProxyPacket(
					new PacketBuffer(Unpooled.wrappedBuffer(("OPENCUSTOMGUI:" + gui.guiID).getBytes())),
					CustomGUI.MODID));
		}
	}
}
