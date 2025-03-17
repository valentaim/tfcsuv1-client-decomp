/*    */ package com.bioxx.tfc.Render.Blocks;
/*    */ 
/*    */ import com.bioxx.tfc.Render.TESRChestHelper;
/*    */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderChest
/*    */   implements ISimpleBlockRenderingHandler
/*    */ {
/*    */   public boolean renderWorldBlock(IBlockAccess world, int i, int j, int k, Block block, int modelId, RenderBlocks renderer) {
/* 20 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
/* 26 */     GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 27 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 28 */     TESRChestHelper.instance.renderChest(block, metadata, modelID);
/* 29 */     GL11.glEnable(32826);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRender3DInInventory(int modelId) {
/* 35 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRenderId() {
/* 41 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Render\Blocks\RenderChest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */