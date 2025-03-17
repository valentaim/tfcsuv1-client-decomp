/*    */ package com.bioxx.tfc.Core.Config;
/*    */ 
/*    */ import cpw.mods.fml.client.config.DummyConfigElement;
/*    */ import cpw.mods.fml.client.config.GuiConfig;
/*    */ import cpw.mods.fml.client.config.IConfigElement;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraftforge.common.config.ConfigElement;
/*    */ import net.minecraftforge.common.config.Configuration;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TFC_ConfigGUI
/*    */   extends GuiConfig
/*    */ {
/*    */   public TFC_ConfigGUI(GuiScreen parent) {
/* 21 */     super(parent, getConfigElements(), "terrafirmacraft", false, false, "TerraFirmaCraft");
/*    */   }
/*    */ 
/*    */   
/*    */   private static List<IConfigElement> getConfigElements() {
/* 26 */     List<IConfigElement> root = new ArrayList<IConfigElement>();
/* 27 */     root.add(new DummyConfigElement.DummyCategoryElement("TFCOptions.cfg", "config.gui.TFCConfig", getAllFrom(TFC_ConfigFiles.getGeneralConfig())));
/* 28 */     root.add(new DummyConfigElement.DummyCategoryElement("TFCCrafting.cfg", "config.gui.TFCCrafting", getAllFrom(TFC_ConfigFiles.getCraftingConfig())));
/* 29 */     root.add(new DummyConfigElement.DummyCategoryElement("TFCOre.cfg", "config.gui.TFCOre", getAllFrom(TFC_ConfigFiles.getOresConfig())));
/* 30 */     return root;
/*    */   }
/*    */ 
/*    */   
/*    */   private static List<IConfigElement> getAllFrom(Configuration generalConfig) {
/* 35 */     List<IConfigElement> root = new ArrayList<IConfigElement>();
/* 36 */     for (String catName : generalConfig.getCategoryNames()) {
/*    */       
/* 38 */       if (catName.contains("."))
/* 39 */         continue;  root.add(new ConfigElement(generalConfig.getCategory(catName)));
/*    */     } 
/* 41 */     return root;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Core\Config\TFC_ConfigGUI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */