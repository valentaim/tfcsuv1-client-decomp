/*    */ package com.bioxx.tfc.Blocks;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFCTabs;
/*    */ import com.bioxx.tfc.TileEntities.TileEntitySpawnMeter;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class BlockSpawnMeter
/*    */   extends BlockTerraContainer
/*    */ {
/*    */   IIcon iconTop;
/* 17 */   IIcon[] icons = new IIcon[9];
/*    */ 
/*    */   
/*    */   public BlockSpawnMeter() {
/* 21 */     super(Material.field_151576_e);
/* 22 */     func_149647_a(TFCTabs.TFCDevices);
/* 23 */     func_149715_a(1.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLightValue(IBlockAccess world, int x, int y, int z) {
/* 29 */     int meta = world.func_72805_g(x, y, z);
/* 30 */     return Math.min(meta, 8);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_149662_c() {
/* 36 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_149692_a(int j) {
/* 42 */     return j;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IIcon func_149691_a(int i, int j) {
/* 48 */     if (i < 2) {
/* 49 */       return this.iconTop;
/*    */     }
/* 51 */     return this.icons[j];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_149651_a(IIconRegister iconRegisterer) {
/* 57 */     this.iconTop = iconRegisterer.func_94245_a("terrafirmacraft:devices/MeterTop");
/* 58 */     for (int i = 0; i < 9; i++)
/*    */     {
/* 60 */       this.icons[i] = iconRegisterer.func_94245_a("terrafirmacraft:devices/Meter" + i);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public TileEntity func_149915_a(World var1, int var2) {
/* 67 */     return (TileEntity)new TileEntitySpawnMeter();
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Blocks\BlockSpawnMeter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */