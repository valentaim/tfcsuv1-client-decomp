/*    */ package com.bioxx.tfc.Containers.Slots;
/*    */ 
/*    */ import com.bioxx.tfc.api.TFCItems;
/*    */ import cpw.mods.fml.common.registry.GameData;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SlotLogPile
/*    */   extends Slot
/*    */ {
/*    */   public SlotLogPile(EntityPlayer entityplayer, IInventory iinventory, int i, int j, int k) {
/* 17 */     super(iinventory, i, j, k);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75214_a(ItemStack itemstack) {
/* 24 */     Item fromcook = (Item)GameData.getItemRegistry().func_82594_a("cookingwithtfc:item.Log");
/* 25 */     if (fromcook != null) return (itemstack.func_77973_b() == TFCItems.logs || itemstack.func_77973_b() == fromcook); 
/* 26 */     return (itemstack.func_77973_b() == TFCItems.logs);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_75219_a() {
/* 32 */     return 4;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Containers\Slots\SlotLogPile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */