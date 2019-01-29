package custom.gui.gui;

import java.util.List;

import custom.gui.gui.object.EGuiObject;
import net.minecraft.client.Minecraft;

public class CustomGUIAPI {
	public static void openGUI(List<EGuiObject> list, int guiID) {
		Minecraft.getMinecraft().displayGuiScreen(new Gui(Minecraft.getMinecraft().currentScreen, list, guiID));
	}
}
