/*    */ package com.bioxx.tfc.Render;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFC_Core;
/*    */ import com.bioxx.tfc.api.Interfaces.IEquipable;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.ItemStack;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderLargeItem
/*    */ {
/*    */   public void render(EntityLivingBase entity, ItemStack item, RenderPlayerEvent.Specials.Pre e) {
/* 29 */     doRender(entity, item, e);
/*    */   }
/*    */   
/*    */   public void render(Entity entity, ItemStack item, RenderPlayerEvent.Specials.Pre e) {
/* 33 */     doRender(entity, item, e);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void doRender(EntityLivingBase entity, ItemStack item, RenderPlayerEvent.Specials.Pre e) {
/* 39 */     if (e != null) {
/* 40 */       GL11.glPushMatrix();
/* 41 */       e.renderer.field_77109_a.field_78115_e.func_78794_c(0.0625F);
/* 42 */       GL11.glScalef(0.7F, 0.7F, 0.7F);
/* 43 */       GL11.glTranslatef(0.0F, 0.5F, 0.6F);
/* 44 */       TFC_Core.bindTexture(TextureMap.field_110575_b);
/* 45 */       RenderBlocks.getInstance().func_147800_a(Block.func_149634_a(item.func_77973_b()), item.func_77960_j(), 1.0F);
/* 46 */       GL11.glPopMatrix();
/*    */     } else {
/*    */       
/* 49 */       float entityTranslateY = (entity instanceof net.minecraft.entity.player.EntityPlayer) ? 0.0F : -1.5F;
/* 50 */       GL11.glPushMatrix();
/*    */       
/* 52 */       if (!entity.func_70093_af()) { GL11.glTranslatef(0.0F, 0.2F + entityTranslateY + 0.0F, 0.5F); }
/*    */       else
/* 54 */       { GL11.glTranslatef(0.0F, 0.2F + entityTranslateY - 0.1F, 0.6F);
/* 55 */         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F); }
/* 56 */        GL11.glScalef(0.8F, 0.8F, 0.8F);
/* 57 */       GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*    */       
/* 59 */       if (item != null) {
/* 60 */         if (item.func_77973_b() instanceof IEquipable) {
/* 61 */           ((IEquipable)item.func_77973_b()).onEquippedRender();
/*    */         }
/* 63 */         else if (Block.func_149634_a(item.func_77973_b()) instanceof IEquipable) {
/* 64 */           ((IEquipable)Block.func_149634_a(item.func_77973_b())).onEquippedRender();
/*    */         } 
/* 66 */         Block block = Block.func_149634_a(item.func_77973_b());
/* 67 */         TFC_Core.bindTexture(TextureMap.field_110575_b);
/* 68 */         RenderBlocks.getInstance().func_147800_a(block, item.func_77960_j(), 1.0F);
/*    */       } 
/* 70 */       GL11.glPopMatrix();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void doRender(Entity entity, ItemStack item, RenderPlayerEvent.Specials.Pre e) {
/* 75 */     float entityTranslateY = (entity instanceof net.minecraft.entity.player.EntityPlayer) ? 0.0F : -1.5F;
/* 76 */     GL11.glPushMatrix();
/*    */     
/* 78 */     if (!entity.func_70093_af()) { GL11.glTranslatef(0.0F, 0.2F + entityTranslateY + 0.0F, 0.5F); }
/*    */     else
/* 80 */     { GL11.glTranslatef(0.0F, 0.2F + entityTranslateY - 0.1F, 0.6F);
/* 81 */       GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F); }
/* 82 */      GL11.glScalef(0.8F, 0.8F, 0.8F);
/* 83 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*    */     
/* 85 */     if (item != null) {
/* 86 */       if (item.func_77973_b() instanceof IEquipable) {
/* 87 */         ((IEquipable)item.func_77973_b()).onEquippedRender();
/*    */       }
/* 89 */       else if (Block.func_149634_a(item.func_77973_b()) instanceof IEquipable) {
/* 90 */         ((IEquipable)Block.func_149634_a(item.func_77973_b())).onEquippedRender();
/*    */       } 
/* 92 */       Block block = Block.func_149634_a(item.func_77973_b());
/* 93 */       TFC_Core.bindTexture(TextureMap.field_110575_b);
/* 94 */       RenderBlocks.getInstance().func_147800_a(block, item.func_77960_j(), 1.0F);
/*    */     } 
/* 96 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Render\RenderLargeItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */