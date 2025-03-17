/*    */ package com.bioxx.tfc.Items.ItemBlocks;
/*    */ 
/*    */ import com.bioxx.tfc.api.Constant.Global;
/*    */ import com.bioxx.tfc.api.Enums.EnumSize;
/*    */ import com.bioxx.tfc.api.Enums.EnumWeight;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ public class ItemToolRack
/*    */   extends ItemTerraBlock
/*    */ {
/*    */   public ItemToolRack(Block par1) {
/* 14 */     super(par1);
/* 15 */     this.metaNames = Global.WOOD_ALL;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumSize getSize(ItemStack is) {
/* 21 */     return EnumSize.HUGE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumWeight getWeight(ItemStack is) {
/* 27 */     return EnumWeight.LIGHT;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemToolRack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */