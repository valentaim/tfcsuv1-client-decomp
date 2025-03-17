/*    */ package com.bioxx.tfc.api.Events;
/*    */ 
/*    */ import cpw.mods.fml.common.eventhandler.Cancelable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraftforge.event.entity.EntityEvent;
/*    */ 
/*    */ 
/*    */ @Cancelable
/*    */ public class GetSkillMultiplierEvent
/*    */   extends EntityEvent
/*    */ {
/*    */   public final String skillname;
/*    */   public float skillResult;
/*    */   
/*    */   public GetSkillMultiplierEvent(EntityPlayer entity, String skillName, float result) {
/* 17 */     super((Entity)entity);
/* 18 */     this.skillname = skillName;
/* 19 */     this.skillResult = result;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\api\Events\GetSkillMultiplierEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */