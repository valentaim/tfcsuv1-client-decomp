/*    */ package com.bioxx.tfc.api.Events;
/*    */ 
/*    */ import cpw.mods.fml.common.eventhandler.Cancelable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraftforge.event.entity.EntityEvent;
/*    */ 
/*    */ 
/*    */ @Cancelable
/*    */ public class PlayerSkillEvent
/*    */   extends EntityEvent
/*    */ {
/*    */   protected PlayerSkillEvent(EntityPlayer entity) {
/* 14 */     super((Entity)entity);
/*    */   }
/*    */   
/*    */   @Cancelable
/*    */   public static class Increase
/*    */     extends PlayerSkillEvent
/*    */   {
/*    */     public final int skillGain;
/*    */     public final String skillName;
/*    */     
/*    */     public Increase(EntityPlayer entity, String name, int skill) {
/* 25 */       super(entity);
/* 26 */       this.skillGain = skill;
/* 27 */       this.skillName = name;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\api\Events\PlayerSkillEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */