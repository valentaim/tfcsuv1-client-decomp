/*    */ package com.bioxx.tfc.Items.ItemBlocks;
/*    */ 
/*    */ import com.bioxx.tfc.api.Constant.Global;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ 
/*    */ 
/*    */ public class ItemFence
/*    */   extends ItemTerraBlock
/*    */ {
/*    */   public ItemFence(Block par1) {
/* 12 */     super(par1);
/* 13 */     this.metaNames = new String[16];
/* 14 */     System.arraycopy(Global.WOOD_ALL, 0, this.metaNames, 0, 16);
/*    */   }
/*    */   
/*    */   public void func_94581_a(IIconRegister registerer) {}
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemFence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */