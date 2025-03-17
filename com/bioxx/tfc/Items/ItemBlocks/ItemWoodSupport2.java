/*    */ package com.bioxx.tfc.Items.ItemBlocks;
/*    */ 
/*    */ import com.bioxx.tfc.api.Constant.Global;
/*    */ import net.minecraft.block.Block;
/*    */ 
/*    */ 
/*    */ public class ItemWoodSupport2
/*    */   extends ItemWoodSupport
/*    */ {
/*    */   public ItemWoodSupport2(Block par1) {
/* 11 */     super(par1);
/* 12 */     this.field_77787_bX = true;
/* 13 */     func_77656_e(0);
/* 14 */     this.metaNames = new String[Global.WOOD_ALL.length - 16];
/* 15 */     System.arraycopy(Global.WOOD_ALL, 16, this.metaNames, 0, Global.WOOD_ALL.length - 16);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemWoodSupport2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */