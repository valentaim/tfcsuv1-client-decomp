/*    */ package com.bioxx.tfc.api.Events;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ 
/*    */ public class EntityArmorCalcEvent extends EntityEvent {
/*    */   public float incomingDamage;
/*    */   public final EventType eventType;
/*    */   
/*    */   public enum EventType {
/*  9 */     PRE, POST;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityArmorCalcEvent(EntityLivingBase p, float damage, EventType eType) {
/* 19 */     super((Entity)p);
/* 20 */     this.incomingDamage = damage;
/* 21 */     this.eventType = eType;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\api\Events\EntityArmorCalcEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */