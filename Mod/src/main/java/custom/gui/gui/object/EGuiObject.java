package custom.gui.gui.object;

import custom.gui.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

public interface EGuiObject {
	public void draw(Gui gui);
	public void init(Gui gui);
}
