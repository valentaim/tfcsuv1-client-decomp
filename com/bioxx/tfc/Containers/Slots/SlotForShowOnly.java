/*    */ package com.bioxx.tfc.Containers.Slots;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ public class SlotForShowOnly
/*    */   extends Slot
/*    */ {
/*    */   public SlotForShowOnly(IInventory iinventory, int i, int j, int k) {
/* 15 */     super(iinventory, i, j, k);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_82869_a(EntityPlayer par1EntityPlayer) {
/* 21 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75214_a(ItemStack par1ItemStack) {
/* 27 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public boolean func_111238_b() {
/* 34 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Containers\Slots\SlotForShowOnly.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */