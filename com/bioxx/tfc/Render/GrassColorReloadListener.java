/*    */ package com.bioxx.tfc.Render;
/*    */ 
/*    */ import com.bioxx.tfc.Core.ColorizerGrassTFC;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.io.IOException;
/*    */ import net.minecraft.client.renderer.texture.TextureUtil;
/*    */ import net.minecraft.client.resources.IResourceManager;
/*    */ import net.minecraft.client.resources.IResourceManagerReloadListener;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class GrassColorReloadListener
/*    */   implements IResourceManagerReloadListener
/*    */ {
/* 18 */   private static final ResourceLocation TEXTURE = new ResourceLocation("textures/colormap/grass.png");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_110549_a(IResourceManager par1ResourceManager) {
/*    */     try {
/* 25 */       ColorizerGrassTFC.setGrassBiomeColorizer(TextureUtil.func_110986_a(par1ResourceManager, TEXTURE));
/*    */     }
/* 27 */     catch (IOException iOException) {}
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Render\GrassColorReloadListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */