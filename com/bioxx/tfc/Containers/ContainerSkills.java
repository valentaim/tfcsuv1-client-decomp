/*    */ package com.bioxx.tfc.Containers;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class ContainerSkills
/*    */   extends ContainerTFC
/*    */ {
/*    */   public ContainerSkills(EntityPlayer player) {
/* 10 */     this.player = player;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_75141_a(int par1, ItemStack par2ItemStack) {
/* 16 */     this.player.field_71069_bz.func_75139_a(par1).func_75215_d(par2ItemStack);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Containers\ContainerSkills.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */