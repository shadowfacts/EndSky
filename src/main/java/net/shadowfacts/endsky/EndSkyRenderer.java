package net.shadowfacts.endsky;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IRenderHandler;

/**
 * @author shadowfacts
 */
public class EndSkyRenderer extends IRenderHandler {

	private static final ResourceLocation END_SKY_TEXTURES = new ResourceLocation("minecraft", "textures/environment/end_sky.png");
	private static final ResourceLocation MOON_PHASES_TEXTURES = new ResourceLocation("textures/environment/moon_phases.png");
	private static final ResourceLocation SUN_TEXTURES = new ResourceLocation("textures/environment/sun.png");

	@Override
	public void render(float partialTicks, WorldClient world, Minecraft mc) {
		renderEndSky(partialTicks, world, mc.renderEngine);
	}

	private void renderEndSky(float partialTicks, WorldClient world, TextureManager textureManager) {
		GlStateManager.disableFog();
		GlStateManager.disableAlpha();
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		RenderHelper.disableStandardItemLighting();
		GlStateManager.depthMask(false);
		textureManager.bindTexture(END_SKY_TEXTURES);
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vertexbuffer = tessellator.getBuffer();

		for (int i = 0; i < 6; ++i)
		{
			GlStateManager.pushMatrix();

			if (i == 1)
			{
				GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
			}

			if (i == 2)
			{
				GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
			}

			if (i == 3)
			{
				GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
			}

			if (i == 4)
			{
				GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
			}

			if (i == 5)
			{
				GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
			}

			vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
			vertexbuffer.pos(-100.0D, -100.0D, -100.0D).tex(0.0D, 0.0D).color(40, 40, 40, 255).endVertex();
			vertexbuffer.pos(-100.0D, -100.0D, 100.0D).tex(0.0D, 16.0D).color(40, 40, 40, 255).endVertex();
			vertexbuffer.pos(100.0D, -100.0D, 100.0D).tex(16.0D, 16.0D).color(40, 40, 40, 255).endVertex();
			vertexbuffer.pos(100.0D, -100.0D, -100.0D).tex(16.0D, 0.0D).color(40, 40, 40, 255).endVertex();
			tessellator.draw();
			GlStateManager.popMatrix();
		}

		GlStateManager.depthMask(true);
		GlStateManager.enableTexture2D();
		GlStateManager.enableAlpha();

		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		GlStateManager.pushMatrix();
		float f16 = 1.0F - world.getRainStrength(partialTicks);
		GlStateManager.color(1.0F, 1.0F, 1.0F, f16);
		GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
		float f17 = 30.0F;
		textureManager.bindTexture(SUN_TEXTURES);
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		vertexbuffer.pos((double)(-f17), 100.0D, (double)(-f17)).tex(0.0D, 0.0D).endVertex();
		vertexbuffer.pos((double)f17, 100.0D, (double)(-f17)).tex(1.0D, 0.0D).endVertex();
		vertexbuffer.pos((double)f17, 100.0D, (double)f17).tex(1.0D, 1.0D).endVertex();
		vertexbuffer.pos((double)(-f17), 100.0D, (double)f17).tex(0.0D, 1.0D).endVertex();
		tessellator.draw();
		f17 = 20.0F;
		textureManager.bindTexture(MOON_PHASES_TEXTURES);
		int i = world.getMoonPhase();
		int k = i % 4;
		int i1 = i / 4 % 2;
		float f22 = (float)(k + 0) / 4.0F;
		float f23 = (float)(i1 + 0) / 2.0F;
		float f24 = (float)(k + 1) / 4.0F;
		float f14 = (float)(i1 + 1) / 2.0F;
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		vertexbuffer.pos((double)(-f17), -100.0D, (double)f17).tex((double)f24, (double)f14).endVertex();
		vertexbuffer.pos((double)f17, -100.0D, (double)f17).tex((double)f22, (double)f14).endVertex();
		vertexbuffer.pos((double)f17, -100.0D, (double)(-f17)).tex((double)f22, (double)f23).endVertex();
		vertexbuffer.pos((double)(-f17), -100.0D, (double)(-f17)).tex((double)f24, (double)f23).endVertex();
		tessellator.draw();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.enableFog();
		GlStateManager.popMatrix();
		GlStateManager.enableTexture2D();
		GlStateManager.depthMask(true);
	}

}
