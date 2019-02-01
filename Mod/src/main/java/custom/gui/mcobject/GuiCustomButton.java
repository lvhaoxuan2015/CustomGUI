package custom.gui.mcobject;

import custom.gui.object.TextureManager;
import custom.gui.util.GuiUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiCustomButton extends GuiButton {

    String firstUrl, lastUrl;
    int textureX, textureY, firstTextureID, lastTextureID, firstColor, lastColor;
    boolean isuploadTextureImage = false;

    public GuiCustomButton(int buttonId, int x, int y, int widthIn, int heightIn, int firstColor, int lastColor, String buttonText, String imgUrl, String lastUrl) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
        this.firstUrl = imgUrl;
        this.lastUrl = lastUrl;
        this.firstColor = firstColor;
        this.lastColor = lastColor;
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            FontRenderer fontrenderer = mc.fontRendererObj;
            if (!isuploadTextureImage) {
                firstTextureID = GL11.glGenTextures();
                lastTextureID = GL11.glGenTextures();
                TextureUtil.uploadTextureImage(firstTextureID, TextureManager.getBufferedImage(firstUrl));
                TextureUtil.uploadTextureImage(lastTextureID, TextureManager.getBufferedImage(lastUrl));
                isuploadTextureImage = true;
            }
            if (this.hovered) {
                GlStateManager.bindTexture(lastTextureID);
            } else {
                GlStateManager.bindTexture(firstTextureID);
            }
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GuiUtil.drawModalRectWithCustomSizedTexture(xPosition, yPosition, textureX, textureY, width, height, width, height);
            if (this.hovered) {
                this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, lastColor);
            } else {
                this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, firstColor);
            }
            this.mouseDragged(mc, mouseX, mouseY);
        }
    }
}
