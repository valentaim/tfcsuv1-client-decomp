/*    */ package com.bioxx.tfc.api.Interfaces;
/*    */ 
/*    */ public interface IEquipable {
/*    */   EquipType getEquipType(ItemStack paramItemStack);
/*    */   
/*    */   void onEquippedRender();
/*    */   
/*    */   boolean getTooHeavyToCarry(ItemStack paramItemStack);
/*    */   
/*    */   public enum EquipType {
/* 11 */     BACK, NULL;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\api\Interfaces\IEquipable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */