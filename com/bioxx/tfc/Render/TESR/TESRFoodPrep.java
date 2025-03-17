/*     */ package com.bioxx.tfc.Render.TESR;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.TileEntities.TEFoodPrep;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TESRFoodPrep
/*     */   extends TESRBase
/*     */ {
/*     */   public void renderAt(TEFoodPrep te, double d, double d1, double d2, float f) {
/*  28 */     if (te.func_145831_w() != null) {
/*     */       
/*  30 */       EntityItem customitem = new EntityItem(this.field_147501_a.field_147550_f);
/*  31 */       customitem.field_70290_d = 0.0F;
/*  32 */       float blockScale = 0.6F;
/*  33 */       float timeD = (float)(360.0D * (System.currentTimeMillis() & 0x3FFFL) / 16383.0D);
/*  34 */       d1 += 0.2D;
/*     */       
/*  36 */       if (RenderManager.field_78727_a.field_78733_k.field_74347_j) {
/*     */         
/*  38 */         if (te.func_70301_a(0) != null) {
/*     */           
/*  40 */           GL11.glPushMatrix();
/*  41 */           GL11.glTranslatef((float)d + 0.25F, (float)d1 + 0.0F, (float)d2 + 0.25F);
/*  42 */           GL11.glRotatef(timeD, 0.0F, 1.0F, 0.0F);
/*  43 */           GL11.glScalef(blockScale, blockScale, blockScale);
/*  44 */           customitem.func_92058_a(te.func_70301_a(0));
/*  45 */           itemRenderer.func_76986_a(customitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/*  46 */           GL11.glPopMatrix();
/*     */         } 
/*  48 */         if (te.func_70301_a(1) != null) {
/*     */           
/*  50 */           GL11.glPushMatrix();
/*  51 */           GL11.glTranslatef((float)d + 0.75F, (float)d1 + 0.0F, (float)d2 + 0.25F);
/*  52 */           GL11.glRotatef(timeD, 0.0F, 1.0F, 0.0F);
/*  53 */           GL11.glScalef(blockScale, blockScale, blockScale);
/*  54 */           customitem.func_92058_a(te.func_70301_a(1));
/*  55 */           itemRenderer.func_76986_a(customitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/*  56 */           GL11.glPopMatrix();
/*     */         } 
/*  58 */         if (te.func_70301_a(2) != null) {
/*     */           
/*  60 */           GL11.glPushMatrix();
/*  61 */           GL11.glTranslatef((float)d + 0.25F, (float)d1 + 0.0F, (float)d2 + 0.5F);
/*  62 */           GL11.glRotatef(timeD, 0.0F, 1.0F, 0.0F);
/*  63 */           GL11.glScalef(blockScale, blockScale, blockScale);
/*  64 */           customitem.func_92058_a(te.func_70301_a(2));
/*  65 */           itemRenderer.func_76986_a(customitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/*  66 */           GL11.glPopMatrix();
/*     */         } 
/*  68 */         if (te.func_70301_a(3) != null) {
/*     */           
/*  70 */           GL11.glPushMatrix();
/*  71 */           GL11.glTranslatef((float)d + 0.75F, (float)d1 + 0.0F, (float)d2 + 0.5F);
/*  72 */           GL11.glRotatef(timeD, 0.0F, 1.0F, 0.0F);
/*  73 */           GL11.glScalef(blockScale, blockScale, blockScale);
/*  74 */           customitem.func_92058_a(te.func_70301_a(3));
/*  75 */           itemRenderer.func_76986_a(customitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/*  76 */           GL11.glPopMatrix();
/*     */         } 
/*  78 */         if (te.func_70301_a(4) != null) {
/*     */           
/*  80 */           GL11.glPushMatrix();
/*  81 */           GL11.glTranslatef((float)d + 0.25F, (float)d1 + 0.0F, (float)d2 + 0.75F);
/*  82 */           GL11.glRotatef(timeD, 0.0F, 1.0F, 0.0F);
/*  83 */           GL11.glScalef(blockScale, blockScale, blockScale);
/*  84 */           customitem.func_92058_a(te.func_70301_a(4));
/*  85 */           itemRenderer.func_76986_a(customitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/*  86 */           GL11.glPopMatrix();
/*     */         } 
/*  88 */         if (te.func_70301_a(6) != null)
/*     */         {
/*  90 */           GL11.glPushMatrix();
/*  91 */           GL11.glTranslatef((float)d + 0.75F, (float)d1 + 0.0F, (float)d2 + 0.75F);
/*  92 */           GL11.glRotatef(timeD, 0.0F, 1.0F, 0.0F);
/*  93 */           GL11.glScalef(blockScale, blockScale, blockScale);
/*  94 */           customitem.func_92058_a(te.func_70301_a(6));
/*  95 */           itemRenderer.func_76986_a(customitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/*  96 */           GL11.glPopMatrix();
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 101 */         GL11.glPushMatrix();
/* 102 */         GL11.glTranslated(d, d1 + 0.001D, d2);
/* 103 */         drawItem(te, 0, 0.05D, 0.35D, 0.05D, 0.35D);
/* 104 */         drawItem(te, 1, 0.65D, 0.95D, 0.05D, 0.35D);
/* 105 */         drawItem(te, 2, 0.05D, 0.35D, 0.35D, 0.65D);
/* 106 */         drawItem(te, 3, 0.65D, 0.95D, 0.35D, 0.65D);
/* 107 */         drawItem(te, 4, 0.05D, 0.35D, 0.65D, 0.95D);
/* 108 */         drawItem(te, 6, 0.65D, 0.95D, 0.65D, 0.95D);
/* 109 */         GL11.glPopMatrix();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawItem(TEFoodPrep te, int index, double minX, double maxX, double minZ, double maxZ) {
/* 116 */     if (te.storage[index] != null && !(te.storage[index].func_77973_b() instanceof net.minecraft.item.ItemBlock)) {
/*     */       
/* 118 */       TFC_Core.bindTexture(TextureMap.field_110576_c);
/* 119 */       float minU = te.storage[index].func_77954_c().func_94209_e();
/* 120 */       float maxU = te.storage[index].func_77954_c().func_94212_f();
/* 121 */       float minV = te.storage[index].func_77954_c().func_94206_g();
/* 122 */       float maxV = te.storage[index].func_77954_c().func_94210_h();
/* 123 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 124 */       tessellator.func_78382_b();
/* 125 */       tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
/* 126 */       tessellator.func_78374_a(minX, 0.0D, maxZ, minU, maxV);
/* 127 */       tessellator.func_78374_a(maxX, 0.0D, maxZ, maxU, maxV);
/* 128 */       tessellator.func_78374_a(maxX, 0.0D, minZ, maxU, minV);
/* 129 */       tessellator.func_78374_a(minX, 0.0D, minZ, minU, minV);
/* 130 */       tessellator.func_78381_a();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
/* 137 */     renderAt((TEFoodPrep)par1TileEntity, par2, par4, par6, par8);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Render\TESR\TESRFoodPrep.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */