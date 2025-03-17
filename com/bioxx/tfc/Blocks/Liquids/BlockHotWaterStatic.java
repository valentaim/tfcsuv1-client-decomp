/*    */ package com.bioxx.tfc.Blocks.Liquids;
/*    */ 
/*    */ import com.bioxx.tfc.Effects.GasFX;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.particle.EntityFX;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fluids.Fluid;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockHotWaterStatic
/*    */   extends BlockLiquidStatic
/*    */ {
/*    */   public BlockHotWaterStatic(Fluid fluid, Material m, Block f) {
/* 27 */     super(fluid, m, f);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_149720_d(IBlockAccess access, int x, int y, int z) {
/* 33 */     return this.fluidType.getColor();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149734_b(World world, int i, int j, int k, Random random) {
/* 40 */     if (world.func_147437_c(i - 1, j, k) || world.func_147437_c(i + 1, j, k) || world
/* 41 */       .func_147437_c(i, j, k - 1) || world.func_147437_c(i, j, k + 1) || world
/* 42 */       .func_147437_c(i, j + 1, k)) {
/*    */       
/* 44 */       double f = i + 0.5D;
/* 45 */       double f1 = j + 1.0D;
/* 46 */       double f2 = k + 0.5D;
/*    */       
/* 48 */       double f4 = (random.nextFloat() * -0.1F);
/* 49 */       double f5 = (random.nextFloat() * -0.1F);
/* 50 */       double f6 = (random.nextFloat() * -0.1F);
/*    */       
/* 52 */       (Minecraft.func_71410_x()).field_71452_i.func_78873_a((EntityFX)new GasFX(world, f, f1, f2, f4, f5, f6));
/* 53 */       f4 = (random.nextFloat() * -0.1F);
/* 54 */       f5 = (random.nextFloat() * -0.1F);
/* 55 */       f6 = (random.nextFloat() * -0.1F);
/* 56 */       (Minecraft.func_71410_x()).field_71452_i.func_78873_a((EntityFX)new GasFX(world, f, f1, f2, f4, f5, f6));
/* 57 */       f4 = (random.nextFloat() * -0.1F);
/* 58 */       f5 = (random.nextFloat() * -0.1F);
/* 59 */       f6 = (random.nextFloat() * -0.1F);
/* 60 */       (Minecraft.func_71410_x()).field_71452_i.func_78873_a((EntityFX)new GasFX(world, f, f1, f2, f4, f5, f6));
/* 61 */       f4 = (random.nextFloat() * -0.1F);
/* 62 */       f5 = (random.nextFloat() * -0.1F);
/* 63 */       f6 = (random.nextFloat() * -0.1F);
/* 64 */       (Minecraft.func_71410_x()).field_71452_i.func_78873_a((EntityFX)new GasFX(world, f, f1, f2, f4, f5, f6));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149651_a(IIconRegister registerer) {
/* 72 */     this.icons = new IIcon[] { registerer.func_94245_a("terrafirmacraft:water_still"), registerer.func_94245_a("terrafirmacraft:water_flow") };
/* 73 */     getFluid().setIcons(this.icons[0], this.icons[1]);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_149691_a(int side, int meta) {
/* 80 */     return (side != 0 && side != 1) ? this.icons[1] : this.icons[0];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_149670_a(World world, int x, int y, int z, Entity entity) {
/* 86 */     if (entity instanceof EntityLivingBase) {
/*    */       
/* 88 */       EntityLivingBase e = (EntityLivingBase)entity;
/* 89 */       if (world.field_73012_v.nextInt(25) == 0 && e.func_110143_aJ() < e.func_110138_aP()) {
/*    */         
/* 91 */         float diff = e.func_110138_aP() - e.func_110143_aJ();
/* 92 */         e.func_70691_i(Math.max(diff * 0.001F, 1.0E-4F));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Blocks\Liquids\BlockHotWaterStatic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */