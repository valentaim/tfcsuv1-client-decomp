/*    */ package com.bioxx.tfc.Blocks.Vanilla;
/*    */ 
/*    */ import com.bioxx.tfc.api.Constant.Global;
/*    */ import com.bioxx.tfc.api.Interfaces.IMultipleBlock;
/*    */ import com.bioxx.tfc.api.TFCBlocks;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.ITileEntityProvider;
/*    */ import net.minecraft.util.IIcon;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockCustomFenceGate2
/*    */   extends BlockCustomFenceGate
/*    */   implements ITileEntityProvider, IMultipleBlock
/*    */ {
/*    */   public BlockCustomFenceGate2() {
/* 17 */     this.woodNames = new String[Global.WOOD_ALL.length - 16];
/* 18 */     System.arraycopy(Global.WOOD_ALL, 16, this.woodNames, 0, Global.WOOD_ALL.length - 16);
/* 19 */     this.icons = new IIcon[this.woodNames.length];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IIcon func_149691_a(int par1, int par2) {
/* 25 */     return this.icons[Math.min(par2, this.icons.length - 1)];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Block getBlockTypeForRender() {
/* 31 */     return TFCBlocks.fenceGate2;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockCustomFenceGate2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */