/*    */ package com.bioxx.tfc.Items.Tools;
/*    */ 
/*    */ import com.bioxx.tfc.api.Enums.EnumItemReach;
/*    */ import com.bioxx.tfc.api.TFCBlocks;
/*    */ import com.google.common.collect.Sets;
/*    */ import java.util.Set;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemCustomShovel
/*    */   extends ItemTerraTool
/*    */ {
/* 20 */   public static Set<Block> BLOCKS_EFFECTIVE_AGAINST = Sets.newHashSet((Object[])new Block[] { (Block)Blocks.field_150349_c, Blocks.field_150346_d, (Block)Blocks.field_150354_m, Blocks.field_150351_n, Blocks.field_150433_aE, Blocks.field_150431_aC, Blocks.field_150435_aG, Blocks.field_150458_ak, Blocks.field_150425_aM, (Block)Blocks.field_150391_bh, TFCBlocks.dirt, TFCBlocks.dirt2, TFCBlocks.grass, TFCBlocks.grass2, TFCBlocks.clayGrass, TFCBlocks.clayGrass2, TFCBlocks.peatGrass, TFCBlocks.peat, TFCBlocks.clay, TFCBlocks.clay2 });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemCustomShovel(Item.ToolMaterial par2EnumToolMaterial) {
/* 30 */     super(1.0F, par2EnumToolMaterial, BLOCKS_EFFECTIVE_AGAINST);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_150897_b(Block par1Block) {
/* 39 */     return (par1Block == Blocks.field_150431_aC) ? true : ((par1Block == Blocks.field_150433_aE));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_94581_a(IIconRegister registerer) {
/* 45 */     String name = func_77658_a().replace("item.", "");
/* 46 */     name = name.replace("IgIn ", "");
/* 47 */     name = name.replace("IgEx ", "");
/* 48 */     name = name.replace("Sed ", "");
/* 49 */     name = name.replace("MM ", "");
/* 50 */     this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:tools/" + name);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumItemReach getReach(ItemStack is) {
/* 56 */     return EnumItemReach.FAR;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Items\Tools\ItemCustomShovel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */