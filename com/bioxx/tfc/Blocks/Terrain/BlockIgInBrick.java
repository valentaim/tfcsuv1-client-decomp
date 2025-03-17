/*    */ package com.bioxx.tfc.Blocks.Terrain;
/*    */ 
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockIgInBrick
/*    */   extends BlockIgInSmooth
/*    */ {
/*    */   public void func_149651_a(IIconRegister iconRegisterer) {
/* 17 */     for (int i = 0; i < this.names.length; i++)
/* 18 */       this.icons[i] = iconRegisterer.func_94245_a("terrafirmacraft:rocks/" + this.names[i] + " Brick"); 
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Blocks\Terrain\BlockIgInBrick.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */