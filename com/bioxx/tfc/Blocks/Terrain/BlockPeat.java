/*    */ package com.bioxx.tfc.Blocks.Terrain;
/*    */ 
/*    */ import com.bioxx.tfc.Blocks.BlockTerra;
/*    */ import com.bioxx.tfc.Core.TFCTabs;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockPeat
/*    */   extends BlockTerra
/*    */ {
/*    */   public BlockPeat() {
/* 23 */     super(Material.field_151578_c);
/* 24 */     func_149647_a(TFCTabs.TFC_BUILDING);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149666_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
/* 34 */     list.add(new ItemStack((Block)this, 1, 0));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_149651_a(IIconRegister registerer) {
/* 40 */     this.field_149761_L = registerer.func_94245_a("terrafirmacraft:soil/Peat");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_149742_c(World world, int x, int y, int z) {
/* 46 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public String func_149702_O() {
/* 56 */     return "terrafirmacraft:peat";
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Blocks\Terrain\BlockPeat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */