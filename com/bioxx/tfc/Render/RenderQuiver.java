/*    */ package com.bioxx.tfc.Render;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFC_Core;
/*    */ import com.bioxx.tfc.Items.ItemQuiver;
/*    */ import com.bioxx.tfc.Render.Models.ModelQuiver;
/*    */ import com.bioxx.tfc.api.Interfaces.IEquipable;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.client.event.RenderPlayerEvent;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderQuiver
/*    */ {
/* 24 */   private ModelQuiver quiver = new ModelQuiver();
/* 25 */   private static final ResourceLocation QUIVER_TEXTURE = new ResourceLocation("terrafirmacraft", "textures/models/armor/leatherquiver_1.png");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(EntityLivingBase entity, ItemStack item, RenderPlayerEvent.Specials.Pre e) {
/* 32 */     doRender(entity, item, e);
/*    */   }
/*    */   
/*    */   public void doRender(EntityLivingBase entity, ItemStack item, RenderPlayerEvent.Specials.Pre e) {
/* 36 */     if (e != null) {
/* 37 */       GL11.glPushMatrix();
/* 38 */       e.renderer.field_77109_a.field_78115_e.func_78794_c(0.0625F);
/* 39 */       GL11.glScalef(0.7F, 0.7F, 0.7F);
/* 40 */       GL11.glTranslatef(0.0F, 0.5F, 0.05F);
/* 41 */       TFC_Core.bindTexture(QUIVER_TEXTURE);
/* 42 */       this.quiver.render(entity, ((ItemQuiver)item.func_77973_b()).getQuiverArrows(item) / 8);
/* 43 */       GL11.glPopMatrix();
/*    */     } else {
/*    */       
/* 46 */       float entityTranslateY = (entity instanceof net.minecraft.entity.player.EntityPlayer) ? 0.0F : -1.5F;
/* 47 */       GL11.glPushMatrix();
/* 48 */       (Minecraft.func_71410_x()).field_71446_o.func_110577_a(QUIVER_TEXTURE);
/* 49 */       if (!entity.func_70093_af()) { GL11.glTranslatef(0.0F, entityTranslateY + 0.0F, 0.1F); }
/*    */       else
/* 51 */       { GL11.glTranslatef(0.0F, entityTranslateY + 0.1F, 0.1F);
/* 52 */         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F); }
/* 53 */        if (item != null) {
/* 54 */         if (item.func_77973_b() instanceof IEquipable) {
/* 55 */           ((IEquipable)item.func_77973_b()).onEquippedRender();
/*    */         }
/* 57 */         if (entity instanceof com.bioxx.tfc.Entities.Mobs.EntitySkeletonTFC) {
/* 58 */           this.quiver.render(entity, 16);
/*    */         } else {
/* 60 */           this.quiver.render(entity, ((ItemQuiver)item.func_77973_b()).getQuiverArrows(item) / 8);
/*    */         } 
/* 62 */       }  GL11.glPopMatrix();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Render\RenderQuiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */