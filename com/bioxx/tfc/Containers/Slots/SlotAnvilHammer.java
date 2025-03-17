/*    */ package com.bioxx.tfc.Containers.Slots;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SlotAnvilHammer
/*    */   extends Slot
/*    */ {
/*    */   public SlotAnvilHammer(EntityPlayer entityplayer, IInventory iinventory, int i, int j, int k) {
/* 14 */     super(iinventory, i, j, k);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75214_a(ItemStack itemstack) {
/* 20 */     return itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemHammer;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Containers\Slots\SlotAnvilHammer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */