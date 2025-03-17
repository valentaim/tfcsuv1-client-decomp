/*    */ package com.bioxx.tfc.Items.ItemBlocks;
/*    */ 
/*    */ import com.bioxx.tfc.api.Constant.Global;
/*    */ import net.minecraft.block.Block;
/*    */ 
/*    */ 
/*    */ public class ItemCustomWood
/*    */   extends ItemTerraBlock
/*    */ {
/*    */   public ItemCustomWood(Block b) {
/* 11 */     super(b);
/* 12 */     this.metaNames = new String[16];
/* 13 */     System.arraycopy(Global.WOOD_ALL, 0, this.metaNames, 0, 16);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemCustomWood.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */