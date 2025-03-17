/*    */ package com.bioxx.tfc.Handlers;
/*    */ 
/*    */ import com.bioxx.tfc.WorldGen.BiomeDecoratorTFC;
/*    */ import com.bioxx.tfc.WorldGen.TFCBiome;
/*    */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraft.world.biome.BiomeDecorator;
/*    */ import net.minecraftforge.event.terraingen.BiomeEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BiomeEventHandler
/*    */ {
/*    */   @SubscribeEvent
/*    */   public void onCreateDecorator(BiomeEvent.CreateDecorator event) {
/* 15 */     event.newBiomeDecorator = (BiomeDecorator)new BiomeDecoratorTFC((TFCBiome)event.biome);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Handlers\BiomeEventHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */