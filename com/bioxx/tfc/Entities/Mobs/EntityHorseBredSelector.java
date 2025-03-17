/*    */ package com.bioxx.tfc.Entities.Mobs;
/*    */ 
/*    */ import net.minecraft.command.IEntitySelector;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class EntityHorseBredSelector
/*    */   implements IEntitySelector
/*    */ {
/*    */   public boolean func_82704_a(Entity par1Entity) {
/* 14 */     return (par1Entity instanceof EntityHorseTFC && ((EntityHorseTFC)par1Entity).func_110205_ce());
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Entities\Mobs\EntityHorseBredSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */