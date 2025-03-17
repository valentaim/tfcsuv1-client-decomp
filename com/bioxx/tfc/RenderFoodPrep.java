/*    */ package com.bioxx.tfc;
/*    */ 
/*    */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderFoodPrep
/*    */   implements ISimpleBlockRenderingHandler
/*    */ {
/*    */   public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {}
/*    */   
/*    */   public boolean renderWorldBlock(IBlockAccess world, int a, int b, int c, Block block, int modelId, RenderBlocks renderer) {
/* 22 */     Block ublock = world.func_147439_a(a, b - 1, c);
/* 23 */     IIcon icon = ublock.func_149673_e(world, a, b - 1, c, 0);
/* 24 */     if (icon == null) icon = Blocks.field_150348_b.func_149733_h(0); 
/* 25 */     renderer.func_147757_a(icon);
/* 26 */     renderer.func_147784_q(block, a, b, c);
/* 27 */     renderer.func_147771_a();
/*    */     
/* 29 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRender3DInInventory(int modelId) {
/* 35 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRenderId() {
/* 41 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\RenderFoodPrep.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */