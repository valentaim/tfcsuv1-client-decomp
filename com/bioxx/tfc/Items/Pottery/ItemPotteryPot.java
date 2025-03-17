/*    */ package com.bioxx.tfc.Items.Pottery;
/*    */ 
/*    */ import com.bioxx.tfc.api.Enums.EnumSize;
/*    */ import com.bioxx.tfc.api.Enums.EnumWeight;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemPotteryPot
/*    */   extends ItemPotteryBase
/*    */ {
/*    */   public ItemPotteryPot() {
/* 16 */     this.metaNames = new String[] { "Clay Pot", "Ceramic Pot" };
/* 17 */     setWeight(EnumWeight.LIGHT);
/* 18 */     setSize(EnumSize.SMALL);
/* 19 */     func_77637_a(null);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
/* 25 */     super.func_77624_a(is, player, arraylist, flag);
/* 26 */     if (is.func_77942_o() && is.field_77990_d.func_74764_b("LiquidType"))
/*    */     {
/* 28 */       arraylist.add(is.field_77990_d.func_74779_i("LiquidType"));
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Items\Pottery\ItemPotteryPot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */