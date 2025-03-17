/*    */ package com.bioxx.tfc.Blocks.Vanilla;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockStairs;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockCustomStairs
/*    */   extends BlockStairs
/*    */ {
/*    */   public BlockCustomStairs(Block par2Block, int meta) {
/* 21 */     super(par2Block, meta);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
/* 31 */     for (int i = 0; i < 16; i++)
/*    */     {
/* 33 */       list.add(new ItemStack((Block)this, 1, i));
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockCustomStairs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */