/*    */ package com.bioxx.tfc.Blocks.Flora;
/*    */ 
/*    */ import com.bioxx.tfc.api.Constant.Global;
/*    */ import com.bioxx.tfc.api.TFCBlocks;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.util.IIcon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockLogVert2
/*    */   extends BlockLogVert
/*    */ {
/*    */   public BlockLogVert2() {
/* 16 */     this.woodNames = new String[Global.WOOD_ALL.length - 16];
/* 17 */     System.arraycopy(Global.WOOD_ALL, 16, this.woodNames, 0, Global.WOOD_ALL.length - 16);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_149692_a(int dmg) {
/* 23 */     dmg += 16; return dmg;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_149691_a(int side, int meta) {
/* 30 */     if (meta > 15) meta -= 16; 
/* 31 */     return TFCBlocks.logNatural2.func_149691_a(side, meta);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Blocks\Flora\BlockLogVert2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */