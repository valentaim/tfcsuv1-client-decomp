/*     */ package com.bioxx.tfc.Handlers.Client;
/*     */ import com.bioxx.tfc.Core.Player.PlayerInfo;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityHorseTFC;
/*     */ import com.bioxx.tfc.GUI.GuiAnvil;
/*     */ import com.bioxx.tfc.GUI.GuiBarrel;
/*     */ import com.bioxx.tfc.GUI.GuiFoodPrep;
/*     */ import com.bioxx.tfc.GUI.GuiInventoryTFC;
/*     */ import com.bioxx.tfc.GUI.GuiLargeVessel;
/*     */ import com.bioxx.tfc.GUI.GuiWorkbench;
/*     */ import com.bioxx.tfc.TileEntities.TEAnvil;
/*     */ import com.bioxx.tfc.TileEntities.TEBarrel;
/*     */ import com.bioxx.tfc.TileEntities.TEFirepit;
/*     */ import com.bioxx.tfc.TileEntities.TEFoodPrep;
/*     */ import com.bioxx.tfc.TileEntities.TEVessel;
/*     */ import cuchaz.ships.EntityShip;
/*     */ import cuchaz.ships.ShipWorld;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.event.GuiOpenEvent;
/*     */ 
/*     */ public class GuiHandler extends GuiHandler {
/*     */   public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
/*     */     ShipWorld shipWorld;
/*     */     TileEntity te;
/*     */     PlayerInfo pi;
/*     */     try {
/*  29 */       EntityShip ship = ShipLocator.getFromPlayerLook(player);
/*  30 */       if (ship != null)
/*  31 */         shipWorld = ship.getShipWorld(); 
/*  32 */       te = shipWorld.func_147438_o(x, y, z);
/*     */     }
/*  34 */     catch (Exception e) {
/*     */       
/*  36 */       te = null;
/*     */     } 
/*     */     
/*  39 */     switch (id) {
/*     */       
/*     */       case 0:
/*  42 */         return new GuiLogPile(player.field_71071_by, (TELogPile)te, (World)shipWorld, x, y, z);
/*     */       case 1:
/*  44 */         return new GuiWorkbench(player.field_71071_by, (TEWorkbench)te, (World)shipWorld, x, y, z);
/*     */       case 19:
/*  46 */         return new GuiVesselLiquid(player.field_71071_by, (World)shipWorld, x, y, z);
/*     */       case 20:
/*  48 */         return new GuiFirepit(player.field_71071_by, (TEFirepit)te, (World)shipWorld, x, y, z);
/*     */       case 21:
/*  50 */         return new GuiAnvil(player.field_71071_by, (TEAnvil)te, (World)shipWorld, x, y, z);
/*     */       case 22:
/*  52 */         return null;
/*     */       case 23:
/*  54 */         return new GuiForge(player.field_71071_by, (TEForge)te, (World)shipWorld, x, y, z);
/*     */       case 24:
/*  56 */         return new GuiPlanSelection(player, (TEAnvil)te, (World)shipWorld, x, y, z);
/*     */       case 25:
/*  58 */         return new GuiSluice(player.field_71071_by, (TESluice)te, (World)shipWorld, x, y, z);
/*     */       case 26:
/*  60 */         return new GuiBlastFurnace(player.field_71071_by, (TEBlastFurnace)te, (World)shipWorld, x, y, z);
/*     */       case 27:
/*  62 */         return new GuiCalendar(player);
/*     */       case 28:
/*  64 */         pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(player);
/*  65 */         return new GuiKnapping(player.field_71071_by, (pi.specialCraftingTypeAlternate == null) ? pi.specialCraftingType : null, (World)shipWorld, x, y, z);
/*     */       case 29:
/*  67 */         return new GuiChestTFC((IInventory)player.field_71071_by, (IInventory)te, (World)shipWorld, x, y, z);
/*     */       case 31:
/*  69 */         return new GuiInventoryTFC(player);
/*     */       
/*     */       case 32:
/*     */       case 33:
/*  73 */         return new GuiQuern(player.field_71071_by, (TEQuern)te, (World)shipWorld, x, y, z);
/*     */       case 34:
/*  75 */         return new GuiBlueprint(player, (World)shipWorld, x, y, z);
/*     */       case 35:
/*  77 */         return new GuiBarrel(player.field_71071_by, (TEBarrel)te, (World)shipWorld, x, y, z, 0);
/*     */       case 36:
/*  79 */         return new GuiBarrel(player.field_71071_by, (TEBarrel)te, (World)shipWorld, x, y, z, 1);
/*     */       case 37:
/*  81 */         return new GuiCrucible(player.field_71071_by, (TECrucible)te, (World)shipWorld, x, y, z);
/*     */       case 38:
/*  83 */         return new GuiMold(player.field_71071_by, (World)shipWorld, x, y, z);
/*     */       case 39:
/*  85 */         return new GuiVessel(player.field_71071_by, (World)shipWorld, x, y, z);
/*     */       case 40:
/*  87 */         return new GuiQuiver(player.field_71071_by, (World)shipWorld, x, y, z);
/*     */       case 41:
/*  89 */         return new GuiNestBox(player.field_71071_by, (TENestBox)te, (World)shipWorld, x, y, z);
/*     */       
/*     */       case 42:
/*  92 */         if (player.field_70154_o instanceof EntityHorseTFC) {
/*     */           
/*  94 */           EntityHorseTFC horse = (EntityHorseTFC)player.field_70154_o;
/*  95 */           horse.updateChestSaddle();
/*  96 */           return new GuiScreenHorseInventoryTFC(player.field_71071_by, (IInventory)horse.getHorseChest(), horse);
/*     */         } 
/*     */         
/*  99 */         return null;
/*     */       
/*     */       case 43:
/* 102 */         return new GuiGrill(player.field_71071_by, (TEGrill)te, (World)shipWorld, x, y, z);
/*     */       case 44:
/* 104 */         return new GuiFoodPrep(player.field_71071_by, (TEFoodPrep)te, (World)shipWorld, x, y, z, 0);
/*     */       case 45:
/* 106 */         return new GuiFoodPrep(player.field_71071_by, (TEFoodPrep)te, (World)shipWorld, x, y, z, 1);
/*     */       case 46:
/* 108 */         return new GuiLargeVessel(player.field_71071_by, (TEVessel)te, (World)shipWorld, x, y, z, 0);
/*     */       case 47:
/* 110 */         return new GuiLargeVessel(player.field_71071_by, (TEVessel)te, (World)shipWorld, x, y, z, 1);
/*     */       case 48:
/* 112 */         return new GuiCustomNametag(player, (World)shipWorld, x, y, z);
/*     */       
/*     */       case 49:
/* 115 */         return new GuiHopper(player.field_71071_by, (TEHopper)te, (World)shipWorld, x, y, z);
/*     */     } 
/*     */     
/* 118 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void openGuiHandler(GuiOpenEvent event) {
/* 125 */     if (event.gui instanceof net.minecraft.client.gui.inventory.GuiInventory && !(event.gui instanceof GuiInventoryTFC))
/* 126 */       event.gui = (GuiScreen)new GuiInventoryTFC((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g); 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Handlers\Client\GuiHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */