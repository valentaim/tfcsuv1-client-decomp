/*      */ package com.bioxx.tfc;
/*      */ import com.bioxx.tfc.Core.Metal.Alloy;
/*      */ import com.bioxx.tfc.Core.Metal.AlloyManager;
/*      */ import com.bioxx.tfc.Core.Metal.MetalRegistry;
/*      */ import com.bioxx.tfc.Core.Recipes;
/*      */ import com.bioxx.tfc.Core.TFCTabs;
/*      */ import com.bioxx.tfc.Food.ItemFoodTFC;
/*      */ import com.bioxx.tfc.Handlers.TFCFuelHandler;
/*      */ import com.bioxx.tfc.Items.ItemBlocks.ItemWoodDoor;
/*      */ import com.bioxx.tfc.Items.ItemCustomSeeds;
/*      */ import com.bioxx.tfc.Items.ItemGem;
/*      */ import com.bioxx.tfc.Items.ItemIngot;
/*      */ import com.bioxx.tfc.Items.ItemMeltedMetal;
/*      */ import com.bioxx.tfc.Items.ItemMetalSheet;
/*      */ import com.bioxx.tfc.Items.ItemMetalSheet2x;
/*      */ import com.bioxx.tfc.Items.ItemTFCArmor;
/*      */ import com.bioxx.tfc.Items.ItemTerra;
/*      */ import com.bioxx.tfc.Items.ItemUnfinishedArmor;
/*      */ import com.bioxx.tfc.Items.Pottery.ItemPotteryMold;
/*      */ import com.bioxx.tfc.Items.Tools.ItemChisel;
/*      */ import com.bioxx.tfc.Items.Tools.ItemCustomAxe;
/*      */ import com.bioxx.tfc.Items.Tools.ItemCustomHoe;
/*      */ import com.bioxx.tfc.Items.Tools.ItemCustomPickaxe;
/*      */ import com.bioxx.tfc.Items.Tools.ItemCustomSaw;
/*      */ import com.bioxx.tfc.Items.Tools.ItemCustomScythe;
/*      */ import com.bioxx.tfc.Items.Tools.ItemCustomShovel;
/*      */ import com.bioxx.tfc.Items.Tools.ItemCustomSword;
/*      */ import com.bioxx.tfc.Items.Tools.ItemHammer;
/*      */ import com.bioxx.tfc.Items.Tools.ItemJavelin;
/*      */ import com.bioxx.tfc.Items.Tools.ItemKnife;
/*      */ import com.bioxx.tfc.Items.Tools.ItemMiscToolHead;
/*      */ import com.bioxx.tfc.Items.Tools.ItemProPick;
/*      */ import com.bioxx.tfc.api.Armor;
/*      */ import com.bioxx.tfc.api.Constant.Global;
/*      */ import com.bioxx.tfc.api.Enums.EnumDamageType;
/*      */ import com.bioxx.tfc.api.Enums.EnumFoodGroup;
/*      */ import com.bioxx.tfc.api.Enums.EnumSize;
/*      */ import com.bioxx.tfc.api.Metal;
/*      */ import com.bioxx.tfc.api.TFCBlocks;
/*      */ import com.bioxx.tfc.api.TFCItems;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraftforge.common.util.EnumHelper;
/*      */ 
/*      */ public class ItemSetup extends TFCItems {
/*      */   public static void setup() {
/*   47 */     igInToolMaterial = EnumHelper.addToolMaterial("IgIn", 1, igInStoneUses, igInStoneEff, 40.0F, 5);
/*   48 */     sedToolMaterial = EnumHelper.addToolMaterial("Sed", 1, sedStoneUses, sedStoneEff, 40.0F, 5);
/*   49 */     igExToolMaterial = EnumHelper.addToolMaterial("IgEx", 1, igExStoneUses, igExStoneEff, 40.0F, 5);
/*   50 */     mMToolMaterial = EnumHelper.addToolMaterial("MM", 1, mMStoneUses, mMStoneEff, 40.0F, 5);
/*      */     
/*   52 */     copperToolMaterial = EnumHelper.addToolMaterial("Copper", 2, copperUses, copperEff, 65.0F, 8);
/*      */     
/*   54 */     bronzeToolMaterial = EnumHelper.addToolMaterial("Bronze", 2, bronzeUses, bronzeEff, 100.0F, 13);
/*   55 */     bismuthBronzeToolMaterial = EnumHelper.addToolMaterial("BismuthBronze", 2, bismuthBronzeUses, bismuthBronzeEff, 90.0F, 10);
/*   56 */     blackBronzeToolMaterial = EnumHelper.addToolMaterial("BlackBronze", 2, blackBronzeUses, blackBronzeEff, 95.0F, 10);
/*      */     
/*   58 */     ironToolMaterial = EnumHelper.addToolMaterial("Iron", 2, wroughtIronUses, wroughtIronEff, 135.0F, 10);
/*      */     
/*   60 */     steelToolMaterial = EnumHelper.addToolMaterial("Steel", 2, steelUses, steelEff, 170.0F, 10);
/*      */     
/*   62 */     blackSteelToolMaterial = EnumHelper.addToolMaterial("BlackSteel", 2, blackSteelUses, blackSteelEff, 205.0F, 12);
/*      */     
/*   64 */     blueSteelToolMaterial = EnumHelper.addToolMaterial("BlueSteel", 3, blueSteelUses, blueSteelEff, 240.0F, 22);
/*   65 */     redSteelToolMaterial = EnumHelper.addToolMaterial("RedSteel", 3, redSteelUses, redSteelEff, 240.0F, 22);
/*      */     
/*   67 */     TerraFirmaCraft.LOG.info("Loading Items");
/*      */     
/*   69 */     fishingRod = (new ItemCustomFishingRod()).func_77655_b("fishingRod").func_111206_d("tools/fishing_rod");
/*   70 */     coal = (new ItemCoal()).func_77655_b("coal");
/*   71 */     stick = (new ItemStick()).func_77664_n().func_77655_b("stick");
/*   72 */     bow = (new ItemCustomBow()).func_77655_b("bow").func_111206_d("tools/bow");
/*   73 */     Items.field_151031_f = (ItemBow)bow;
/*   74 */     arrow = (new ItemArrow()).func_77655_b("arrow").func_77637_a(TFCTabs.TFC_WEAPONS);
/*   75 */     dye = (new ItemDyeCustom()).func_77655_b("dyePowder").func_111206_d("dye_powder").func_77637_a(TFCTabs.TFC_MATERIALS);
/*   76 */     glassBottle = (new ItemGlassBottle()).func_77655_b("Glass Bottle");
/*   77 */     potion = (new ItemCustomPotion()).func_77655_b("potion").func_111206_d("potion");
/*   78 */     rope = (new ItemCustomLeash()).func_77655_b("Rope").func_77637_a(TFCTabs.TFC_TOOLS);
/*   79 */     Items.field_151058_ca = rope;
/*      */     
/*   81 */     minecartCrate = (new ItemCustomMinecart(1)).func_77655_b("minecartChest").func_111206_d("minecart_chest");
/*   82 */     goldPan = (new ItemGoldPan()).func_77655_b("GoldPan");
/*   83 */     sluiceItem = (new ItemSluice()).setFolder("devices/").func_77655_b("SluiceItem").func_77637_a(TFCTabs.TFC_DEVICES);
/*      */     
/*   85 */     shears = (new ItemShears(0.0F, ironToolMaterial)).func_77655_b("shears").func_111206_d("shears");
/*      */     
/*   87 */     proPickBismuthBronze = (new ItemProPick()).func_77655_b("Bismuth Bronze ProPick").func_77656_e(bismuthBronzeUses / 3);
/*   88 */     proPickBlackBronze = (new ItemProPick()).func_77655_b("Black Bronze ProPick").func_77656_e(blackBronzeUses / 3);
/*   89 */     proPickBlackSteel = (new ItemProPick()).func_77655_b("Black Steel ProPick").func_77656_e(blackSteelUses / 3);
/*   90 */     proPickBlueSteel = (new ItemProPick()).func_77655_b("Blue Steel ProPick").func_77656_e(blueSteelUses / 3);
/*   91 */     proPickBronze = (new ItemProPick()).func_77655_b("Bronze ProPick").func_77656_e(bronzeUses / 3);
/*   92 */     proPickCopper = (new ItemProPick()).func_77655_b("Copper ProPick").func_77656_e(copperUses / 3);
/*   93 */     proPickIron = (new ItemProPick()).func_77655_b("Wrought Iron ProPick").func_77656_e(wroughtIronUses / 3);
/*   94 */     proPickRedSteel = (new ItemProPick()).func_77655_b("Red Steel ProPick").func_77656_e(redSteelUses / 3);
/*   95 */     proPickSteel = (new ItemProPick()).func_77655_b("Steel ProPick").func_77656_e(steelUses / 3);
/*      */     
/*   97 */     bismuthIngot = (new ItemIngot()).func_77655_b("Bismuth Ingot");
/*   98 */     bismuthBronzeIngot = (new ItemIngot()).func_77655_b("Bismuth Bronze Ingot");
/*   99 */     blackBronzeIngot = (new ItemIngot()).func_77655_b("Black Bronze Ingot");
/*  100 */     blackSteelIngot = (new ItemIngot()).func_77655_b("Black Steel Ingot");
/*  101 */     blueSteelIngot = (new ItemIngot()).func_77655_b("Blue Steel Ingot");
/*  102 */     brassIngot = (new ItemIngot()).func_77655_b("Brass Ingot");
/*  103 */     bronzeIngot = (new ItemIngot()).func_77655_b("Bronze Ingot");
/*  104 */     copperIngot = (new ItemIngot()).func_77655_b("Copper Ingot");
/*  105 */     goldIngot = (new ItemIngot()).func_77655_b("Gold Ingot");
/*  106 */     wroughtIronIngot = (new ItemIngot()).func_77655_b("Wrought Iron Ingot");
/*  107 */     leadIngot = (new ItemIngot()).func_77655_b("Lead Ingot");
/*  108 */     nickelIngot = (new ItemIngot()).func_77655_b("Nickel Ingot");
/*  109 */     pigIronIngot = (new ItemIngot()).func_77655_b("Pig Iron Ingot");
/*  110 */     platinumIngot = (new ItemIngot()).func_77655_b("Platinum Ingot");
/*  111 */     redSteelIngot = (new ItemIngot()).func_77655_b("Red Steel Ingot");
/*  112 */     roseGoldIngot = (new ItemIngot()).func_77655_b("Rose Gold Ingot");
/*  113 */     silverIngot = (new ItemIngot()).func_77655_b("Silver Ingot");
/*  114 */     steelIngot = (new ItemIngot()).func_77655_b("Steel Ingot");
/*  115 */     sterlingSilverIngot = (new ItemIngot()).func_77655_b("Sterling Silver Ingot");
/*  116 */     tinIngot = (new ItemIngot()).func_77655_b("Tin Ingot");
/*  117 */     zincIngot = (new ItemIngot()).func_77655_b("Zinc Ingot");
/*      */     
/*  119 */     bismuthIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Bismuth Double Ingot")).setSize(EnumSize.LARGE).setMetal("Bismuth", 200);
/*  120 */     bismuthBronzeIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Bismuth Bronze Double Ingot")).setSize(EnumSize.LARGE).setMetal("Bismuth Bronze", 200);
/*  121 */     blackBronzeIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Black Bronze Double Ingot")).setSize(EnumSize.LARGE).setMetal("Black Bronze", 200);
/*  122 */     blackSteelIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Black Steel Double Ingot")).setSize(EnumSize.LARGE).setMetal("Black Steel", 200);
/*  123 */     blueSteelIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Blue Steel Double Ingot")).setSize(EnumSize.LARGE).setMetal("Blue Steel", 200);
/*  124 */     brassIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Brass Double Ingot")).setSize(EnumSize.LARGE).setMetal("Brass", 200);
/*  125 */     bronzeIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Bronze Double Ingot")).setSize(EnumSize.LARGE).setMetal("Bronze", 200);
/*  126 */     copperIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Copper Double Ingot")).setSize(EnumSize.LARGE).setMetal("Copper", 200);
/*  127 */     goldIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Gold Double Ingot")).setSize(EnumSize.LARGE).setMetal("Gold", 200);
/*  128 */     wroughtIronIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Wrought Iron Double Ingot")).setSize(EnumSize.LARGE).setMetal("Wrought Iron", 200);
/*  129 */     leadIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Lead Double Ingot")).setSize(EnumSize.LARGE).setMetal("Lead", 200);
/*  130 */     nickelIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Nickel Double Ingot")).setSize(EnumSize.LARGE).setMetal("Nickel", 200);
/*  131 */     pigIronIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Pig Iron Double Ingot")).setSize(EnumSize.LARGE).setMetal("Pig Iron", 200);
/*  132 */     platinumIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Platinum Double Ingot")).setSize(EnumSize.LARGE).setMetal("Platinum", 200);
/*  133 */     redSteelIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Red Steel Double Ingot")).setSize(EnumSize.LARGE).setMetal("Red Steel", 200);
/*  134 */     roseGoldIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Rose Gold Double Ingot")).setSize(EnumSize.LARGE).setMetal("Rose Gold", 200);
/*  135 */     silverIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Silver Double Ingot")).setSize(EnumSize.LARGE).setMetal("Silver", 200);
/*  136 */     steelIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Steel Double Ingot")).setSize(EnumSize.LARGE).setMetal("Steel", 200);
/*  137 */     sterlingSilverIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Sterling Silver Double Ingot")).setSize(EnumSize.LARGE).setMetal("Sterling Silver", 200);
/*  138 */     tinIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Tin Double Ingot")).setSize(EnumSize.LARGE).setMetal("Tin", 200);
/*  139 */     zincIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Zinc Double Ingot")).setSize(EnumSize.LARGE).setMetal("Zinc", 200);
/*      */     
/*  141 */     gemRuby = (new ItemGem()).func_77655_b("Ruby");
/*  142 */     gemSapphire = (new ItemGem()).func_77655_b("Sapphire");
/*  143 */     gemEmerald = (new ItemGem()).func_77655_b("Emerald");
/*  144 */     gemTopaz = (new ItemGem()).func_77655_b("Topaz");
/*  145 */     gemTourmaline = (new ItemGem()).func_77655_b("Tourmaline");
/*  146 */     gemJade = (new ItemGem()).func_77655_b("Jade");
/*  147 */     gemBeryl = (new ItemGem()).func_77655_b("Beryl");
/*  148 */     gemAgate = (new ItemGem()).func_77655_b("Agate");
/*  149 */     gemOpal = (new ItemGem()).func_77655_b("Opal");
/*  150 */     gemGarnet = (new ItemGem()).func_77655_b("Garnet");
/*  151 */     gemJasper = (new ItemGem()).func_77655_b("Jasper");
/*  152 */     gemAmethyst = (new ItemGem()).func_77655_b("Amethyst");
/*  153 */     gemDiamond = (new ItemGem()).func_77655_b("Diamond");
/*      */ 
/*      */     
/*  156 */     igInShovel = (new ItemCustomShovel(igInToolMaterial)).func_77655_b("IgIn Stone Shovel").func_77656_e(igInStoneUses);
/*  157 */     igInAxe = (new ItemCustomAxe(igInToolMaterial, 60.0F)).func_77655_b("IgIn Stone Axe").func_77656_e(igInStoneUses);
/*  158 */     igInHoe = (new ItemCustomHoe(igInToolMaterial)).func_77655_b("IgIn Stone Hoe").func_77656_e(igInStoneUses);
/*      */     
/*  160 */     sedShovel = (new ItemCustomShovel(sedToolMaterial)).func_77655_b("Sed Stone Shovel").func_77656_e(sedStoneUses);
/*  161 */     sedAxe = (new ItemCustomAxe(sedToolMaterial, 60.0F)).func_77655_b("Sed Stone Axe").func_77656_e(sedStoneUses);
/*  162 */     sedHoe = (new ItemCustomHoe(sedToolMaterial)).func_77655_b("Sed Stone Hoe").func_77656_e(sedStoneUses);
/*      */     
/*  164 */     igExShovel = (new ItemCustomShovel(igExToolMaterial)).func_77655_b("IgEx Stone Shovel").func_77656_e(igExStoneUses);
/*  165 */     igExAxe = (new ItemCustomAxe(igExToolMaterial, 60.0F)).func_77655_b("IgEx Stone Axe").func_77656_e(igExStoneUses);
/*  166 */     igExHoe = (new ItemCustomHoe(igExToolMaterial)).func_77655_b("IgEx Stone Hoe").func_77656_e(igExStoneUses);
/*      */     
/*  168 */     mMShovel = (new ItemCustomShovel(mMToolMaterial)).func_77655_b("MM Stone Shovel").func_77656_e(mMStoneUses);
/*  169 */     mMAxe = (new ItemCustomAxe(mMToolMaterial, 60.0F)).func_77655_b("MM Stone Axe").func_77656_e(mMStoneUses);
/*  170 */     mMHoe = (new ItemCustomHoe(mMToolMaterial)).func_77655_b("MM Stone Hoe").func_77656_e(mMStoneUses);
/*      */     
/*  172 */     bismuthBronzePick = (new ItemCustomPickaxe(bismuthBronzeToolMaterial)).func_77655_b("Bismuth Bronze Pick").func_77656_e(bismuthBronzeUses);
/*  173 */     bismuthBronzeShovel = (new ItemCustomShovel(bismuthBronzeToolMaterial)).func_77655_b("Bismuth Bronze Shovel").func_77656_e(bismuthBronzeUses);
/*  174 */     bismuthBronzeAxe = (new ItemCustomAxe(bismuthBronzeToolMaterial, 150.0F)).func_77655_b("Bismuth Bronze Axe").func_77656_e(bismuthBronzeUses);
/*  175 */     bismuthBronzeHoe = (new ItemCustomHoe(bismuthBronzeToolMaterial)).func_77655_b("Bismuth Bronze Hoe").func_77656_e(bismuthBronzeUses);
/*      */     
/*  177 */     blackBronzePick = (new ItemCustomPickaxe(blackBronzeToolMaterial)).func_77655_b("Black Bronze Pick").func_77656_e(blackBronzeUses);
/*  178 */     blackBronzeShovel = (new ItemCustomShovel(blackBronzeToolMaterial)).func_77655_b("Black Bronze Shovel").func_77656_e(blackBronzeUses);
/*  179 */     blackBronzeAxe = (new ItemCustomAxe(blackBronzeToolMaterial, 170.0F)).func_77655_b("Black Bronze Axe").func_77656_e(blackBronzeUses);
/*  180 */     blackBronzeHoe = (new ItemCustomHoe(blackBronzeToolMaterial)).func_77655_b("Black Bronze Hoe").func_77656_e(blackBronzeUses);
/*      */     
/*  182 */     blackSteelPick = (new ItemCustomPickaxe(blackSteelToolMaterial)).func_77655_b("Black Steel Pick").func_77656_e(blackSteelUses);
/*  183 */     blackSteelShovel = (new ItemCustomShovel(blackSteelToolMaterial)).func_77655_b("Black Steel Shovel").func_77656_e(blackSteelUses);
/*  184 */     blackSteelAxe = (new ItemCustomAxe(blackSteelToolMaterial, 245.0F)).func_77655_b("Black Steel Axe").func_77656_e(blackSteelUses);
/*  185 */     blackSteelHoe = (new ItemCustomHoe(blackSteelToolMaterial)).func_77655_b("Black Steel Hoe").func_77656_e(blackSteelUses);
/*      */     
/*  187 */     blueSteelPick = (new ItemCustomPickaxe(blueSteelToolMaterial)).func_77655_b("Blue Steel Pick").func_77656_e(blueSteelUses);
/*  188 */     blueSteelShovel = (new ItemCustomShovel(blueSteelToolMaterial)).func_77655_b("Blue Steel Shovel").func_77656_e(blueSteelUses);
/*  189 */     blueSteelAxe = (new ItemCustomAxe(blueSteelToolMaterial, 270.0F)).func_77655_b("Blue Steel Axe").func_77656_e(blueSteelUses);
/*  190 */     blueSteelHoe = (new ItemCustomHoe(blueSteelToolMaterial)).func_77655_b("Blue Steel Hoe").func_77656_e(blueSteelUses);
/*      */     
/*  192 */     bronzePick = (new ItemCustomPickaxe(bronzeToolMaterial)).func_77655_b("Bronze Pick").func_77656_e(bronzeUses);
/*  193 */     bronzeShovel = (new ItemCustomShovel(bronzeToolMaterial)).func_77655_b("Bronze Shovel").func_77656_e(bronzeUses);
/*  194 */     bronzeAxe = (new ItemCustomAxe(bronzeToolMaterial, 160.0F)).func_77655_b("Bronze Axe").func_77656_e(bronzeUses);
/*  195 */     bronzeHoe = (new ItemCustomHoe(bronzeToolMaterial)).func_77655_b("Bronze Hoe").func_77656_e(bronzeUses);
/*      */     
/*  197 */     copperPick = (new ItemCustomPickaxe(copperToolMaterial)).func_77655_b("Copper Pick").func_77656_e(copperUses);
/*  198 */     copperShovel = (new ItemCustomShovel(copperToolMaterial)).func_77655_b("Copper Shovel").func_77656_e(copperUses);
/*  199 */     copperAxe = (new ItemCustomAxe(copperToolMaterial, 115.0F)).func_77655_b("Copper Axe").func_77656_e(copperUses);
/*  200 */     copperHoe = (new ItemCustomHoe(copperToolMaterial)).func_77655_b("Copper Hoe").func_77656_e(copperUses);
/*      */     
/*  202 */     wroughtIronPick = (new ItemCustomPickaxe(ironToolMaterial)).func_77655_b("Wrought Iron Pick").func_77656_e(wroughtIronUses);
/*  203 */     wroughtIronShovel = (new ItemCustomShovel(ironToolMaterial)).func_77655_b("Wrought Iron Shovel").func_77656_e(wroughtIronUses);
/*  204 */     wroughtIronAxe = (new ItemCustomAxe(ironToolMaterial, 185.0F)).func_77655_b("Wrought Iron Axe").func_77656_e(wroughtIronUses);
/*  205 */     wroughtIronHoe = (new ItemCustomHoe(ironToolMaterial)).func_77655_b("Wrought Iron Hoe").func_77656_e(wroughtIronUses);
/*      */     
/*  207 */     redSteelPick = (new ItemCustomPickaxe(redSteelToolMaterial)).func_77655_b("Red Steel Pick").func_77656_e(redSteelUses);
/*  208 */     redSteelShovel = (new ItemCustomShovel(redSteelToolMaterial)).func_77655_b("Red Steel Shovel").func_77656_e(redSteelUses);
/*  209 */     redSteelAxe = (new ItemCustomAxe(redSteelToolMaterial, 270.0F)).func_77655_b("Red Steel Axe").func_77656_e(redSteelUses);
/*  210 */     redSteelHoe = (new ItemCustomHoe(redSteelToolMaterial)).func_77655_b("Red Steel Hoe").func_77656_e(redSteelUses);
/*      */     
/*  212 */     steelPick = (new ItemCustomPickaxe(steelToolMaterial)).func_77655_b("Steel Pick").func_77656_e(steelUses);
/*  213 */     steelShovel = (new ItemCustomShovel(steelToolMaterial)).func_77655_b("Steel Shovel").func_77656_e(steelUses);
/*  214 */     steelAxe = (new ItemCustomAxe(steelToolMaterial, 210.0F)).func_77655_b("Steel Axe").func_77656_e(steelUses);
/*  215 */     steelHoe = (new ItemCustomHoe(steelToolMaterial)).func_77655_b("Steel Hoe").func_77656_e(steelUses);
/*      */ 
/*      */     
/*  218 */     bismuthBronzeChisel = (new ItemChisel(bismuthBronzeToolMaterial)).func_77655_b("Bismuth Bronze Chisel").func_77656_e(bismuthBronzeUses);
/*  219 */     blackBronzeChisel = (new ItemChisel(blackBronzeToolMaterial)).func_77655_b("Black Bronze Chisel").func_77656_e(blackBronzeUses);
/*  220 */     blackSteelChisel = (new ItemChisel(blackSteelToolMaterial)).func_77655_b("Black Steel Chisel").func_77656_e(blackSteelUses);
/*  221 */     blueSteelChisel = (new ItemChisel(blueSteelToolMaterial)).func_77655_b("Blue Steel Chisel").func_77656_e(blueSteelUses);
/*  222 */     bronzeChisel = (new ItemChisel(bronzeToolMaterial)).func_77655_b("Bronze Chisel").func_77656_e(bronzeUses);
/*  223 */     copperChisel = (new ItemChisel(copperToolMaterial)).func_77655_b("Copper Chisel").func_77656_e(copperUses);
/*  224 */     wroughtIronChisel = (new ItemChisel(ironToolMaterial)).func_77655_b("Wrought Iron Chisel").func_77656_e(wroughtIronUses);
/*  225 */     redSteelChisel = (new ItemChisel(redSteelToolMaterial)).func_77655_b("Red Steel Chisel").func_77656_e(redSteelUses);
/*  226 */     steelChisel = (new ItemChisel(steelToolMaterial)).func_77655_b("Steel Chisel").func_77656_e(steelUses);
/*      */     
/*  228 */     bismuthBronzeSword = (new ItemCustomSword(bismuthBronzeToolMaterial, 210.0F)).func_77655_b("Bismuth Bronze Sword").func_77656_e(bismuthBronzeUses);
/*  229 */     blackBronzeSword = (new ItemCustomSword(blackBronzeToolMaterial, 230.0F)).func_77655_b("Black Bronze Sword").func_77656_e(blackBronzeUses);
/*  230 */     blackSteelSword = (new ItemCustomSword(blackSteelToolMaterial, 270.0F)).func_77655_b("Black Steel Sword").func_77656_e(blackSteelUses);
/*  231 */     blueSteelSword = (new ItemCustomSword(blueSteelToolMaterial, 315.0F)).func_77655_b("Blue Steel Sword").func_77656_e(blueSteelUses);
/*  232 */     bronzeSword = (new ItemCustomSword(bronzeToolMaterial, 220.0F)).func_77655_b("Bronze Sword").func_77656_e(bronzeUses);
/*  233 */     copperSword = (new ItemCustomSword(copperToolMaterial, 165.0F)).func_77655_b("Copper Sword").func_77656_e(copperUses);
/*  234 */     wroughtIronSword = (new ItemCustomSword(ironToolMaterial, 240.0F)).func_77655_b("Wrought Iron Sword").func_77656_e(wroughtIronUses);
/*  235 */     redSteelSword = (new ItemCustomSword(redSteelToolMaterial, 315.0F)).func_77655_b("Red Steel Sword").func_77656_e(redSteelUses);
/*  236 */     steelSword = (new ItemCustomSword(steelToolMaterial, 265.0F)).func_77655_b("Steel Sword").func_77656_e(steelUses);
/*      */     
/*  238 */     bismuthBronzeMace = (new ItemCustomSword(bismuthBronzeToolMaterial, 210.0F, EnumDamageType.CRUSHING)).func_77655_b("Bismuth Bronze Mace").func_77656_e(bismuthBronzeUses);
/*  239 */     blackBronzeMace = (new ItemCustomSword(blackBronzeToolMaterial, 230.0F, EnumDamageType.CRUSHING)).func_77655_b("Black Bronze Mace").func_77656_e(blackBronzeUses);
/*  240 */     blackSteelMace = (new ItemCustomSword(blackSteelToolMaterial, 270.0F, EnumDamageType.CRUSHING)).func_77655_b("Black Steel Mace").func_77656_e(blackSteelUses);
/*  241 */     blueSteelMace = (new ItemCustomSword(blueSteelToolMaterial, 315.0F, EnumDamageType.CRUSHING)).func_77655_b("Blue Steel Mace").func_77656_e(blueSteelUses);
/*  242 */     bronzeMace = (new ItemCustomSword(bronzeToolMaterial, 220.0F, EnumDamageType.CRUSHING)).func_77655_b("Bronze Mace").func_77656_e(bronzeUses);
/*  243 */     copperMace = (new ItemCustomSword(copperToolMaterial, 165.0F, EnumDamageType.CRUSHING)).func_77655_b("Copper Mace").func_77656_e(copperUses);
/*  244 */     wroughtIronMace = (new ItemCustomSword(ironToolMaterial, 240.0F, EnumDamageType.CRUSHING)).func_77655_b("Wrought Iron Mace").func_77656_e(wroughtIronUses);
/*  245 */     redSteelMace = (new ItemCustomSword(redSteelToolMaterial, 315.0F, EnumDamageType.CRUSHING)).func_77655_b("Red Steel Mace").func_77656_e(redSteelUses);
/*  246 */     steelMace = (new ItemCustomSword(steelToolMaterial, 265.0F, EnumDamageType.CRUSHING)).func_77655_b("Steel Mace").func_77656_e(steelUses);
/*      */     
/*  248 */     bismuthBronzeSaw = (new ItemCustomSaw(bismuthBronzeToolMaterial)).func_77655_b("Bismuth Bronze Saw").func_77656_e(bismuthBronzeUses);
/*  249 */     blackBronzeSaw = (new ItemCustomSaw(blackBronzeToolMaterial)).func_77655_b("Black Bronze Saw").func_77656_e(blackBronzeUses);
/*  250 */     blackSteelSaw = (new ItemCustomSaw(blackSteelToolMaterial)).func_77655_b("Black Steel Saw").func_77656_e(blackSteelUses);
/*  251 */     blueSteelSaw = (new ItemCustomSaw(blueSteelToolMaterial)).func_77655_b("Blue Steel Saw").func_77656_e(blueSteelUses);
/*  252 */     bronzeSaw = (new ItemCustomSaw(bronzeToolMaterial)).func_77655_b("Bronze Saw").func_77656_e(bronzeUses);
/*  253 */     copperSaw = (new ItemCustomSaw(copperToolMaterial)).func_77655_b("Copper Saw").func_77656_e(copperUses);
/*  254 */     wroughtIronSaw = (new ItemCustomSaw(ironToolMaterial)).func_77655_b("Wrought Iron Saw").func_77656_e(wroughtIronUses);
/*  255 */     redSteelSaw = (new ItemCustomSaw(redSteelToolMaterial)).func_77655_b("Red Steel Saw").func_77656_e(redSteelUses);
/*  256 */     steelSaw = (new ItemCustomSaw(steelToolMaterial)).func_77655_b("Steel Saw").func_77656_e(steelUses);
/*      */     
/*  258 */     highCarbonBlackSteelIngot = (new ItemIngot(false)).func_77655_b("HC Black Steel Ingot");
/*  259 */     weakBlueSteelIngot = (new ItemIngot(false)).func_77655_b("Weak Blue Steel Ingot");
/*  260 */     weakRedSteelIngot = (new ItemIngot(false)).func_77655_b("Weak Red Steel Ingot");
/*  261 */     weakSteelIngot = (new ItemIngot(false)).func_77655_b("Weak Steel Ingot");
/*  262 */     highCarbonBlueSteelIngot = (new ItemIngot(false)).func_77655_b("HC Blue Steel Ingot");
/*  263 */     highCarbonRedSteelIngot = (new ItemIngot(false)).func_77655_b("HC Red Steel Ingot");
/*  264 */     highCarbonSteelIngot = (new ItemIngot(false)).func_77655_b("HC Steel Ingot");
/*      */     
/*  266 */     oreChunk = (new ItemOre()).setFolder("ore/").func_77655_b("Ore");
/*  267 */     smallOreChunk = (new ItemOreSmall()).func_77655_b("Small Ore");
/*  268 */     powder = (new ItemTerra()).setMetaNames(Global.POWDER).func_77655_b("Powder").func_77637_a(TFCTabs.TFC_MATERIALS);
/*  269 */     logs = (new ItemLogs()).func_77655_b("Log");
/*      */ 
/*      */ 
/*      */     
/*  273 */     igInStoneJavelin = (new ItemJavelin(igInToolMaterial, 60.0F)).func_77655_b("IgIn Stone Javelin");
/*  274 */     sedStoneJavelin = (new ItemJavelin(sedToolMaterial, 60.0F)).func_77655_b("Sed Stone Javelin");
/*  275 */     igExStoneJavelin = (new ItemJavelin(igExToolMaterial, 60.0F)).func_77655_b("IgEx Stone Javelin");
/*  276 */     mMStoneJavelin = (new ItemJavelin(mMToolMaterial, 60.0F)).func_77655_b("MM Stone Javelin");
/*  277 */     copperJavelin = (new ItemJavelin(copperToolMaterial, 80.0F)).func_77655_b("Copper Javelin");
/*  278 */     bismuthBronzeJavelin = (new ItemJavelin(bismuthBronzeToolMaterial, 90.0F)).func_77655_b("Bismuth Bronze Javelin");
/*  279 */     bronzeJavelin = (new ItemJavelin(bronzeToolMaterial, 100.0F)).func_77655_b("Bronze Javelin");
/*  280 */     blackBronzeJavelin = (new ItemJavelin(blackBronzeToolMaterial, 95.0F)).func_77655_b("Black Bronze Javelin");
/*  281 */     wroughtIronJavelin = (new ItemJavelin(ironToolMaterial, 135.0F)).func_77655_b("Wrought Iron Javelin");
/*  282 */     steelJavelin = (new ItemJavelin(steelToolMaterial, 170.0F)).func_77655_b("Steel Javelin");
/*  283 */     blackSteelJavelin = (new ItemJavelin(blackSteelToolMaterial, 205.0F)).func_77655_b("Black Steel Javelin");
/*  284 */     blueSteelJavelin = (new ItemJavelin(blueSteelToolMaterial, 240.0F)).func_77655_b("Blue Steel Javelin");
/*  285 */     redSteelJavelin = (new ItemJavelin(redSteelToolMaterial, 240.0F)).func_77655_b("Red Steel Javelin");
/*      */ 
/*      */     
/*  288 */     igInStoneJavelinHead = (new ItemMiscToolHead(igInToolMaterial)).func_77655_b("IgIn Stone Javelin Head");
/*  289 */     sedStoneJavelinHead = (new ItemMiscToolHead(sedToolMaterial)).func_77655_b("Sed Stone Javelin Head");
/*  290 */     igExStoneJavelinHead = (new ItemMiscToolHead(igExToolMaterial)).func_77655_b("IgEx Stone Javelin Head");
/*  291 */     mMStoneJavelinHead = (new ItemMiscToolHead(mMToolMaterial)).func_77655_b("MM Stone Javelin Head");
/*  292 */     copperJavelinHead = (new ItemMiscToolHead()).func_77655_b("Copper Javelin Head");
/*  293 */     bismuthBronzeJavelinHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Javelin Head");
/*  294 */     bronzeJavelinHead = (new ItemMiscToolHead()).func_77655_b("Bronze Javelin Head");
/*  295 */     blackBronzeJavelinHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Javelin Head");
/*  296 */     wroughtIronJavelinHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Javelin Head");
/*  297 */     steelJavelinHead = (new ItemMiscToolHead()).func_77655_b("Steel Javelin Head");
/*  298 */     blackSteelJavelinHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Javelin Head");
/*  299 */     blueSteelJavelinHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Javelin Head");
/*  300 */     redSteelJavelinHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Javelin Head");
/*      */     
/*  302 */     bismuthUnshaped = (new ItemMeltedMetal()).func_77655_b("Bismuth Unshaped");
/*  303 */     bismuthBronzeUnshaped = (new ItemMeltedMetal()).func_77655_b("Bismuth Bronze Unshaped");
/*  304 */     blackBronzeUnshaped = (new ItemMeltedMetal()).func_77655_b("Black Bronze Unshaped");
/*  305 */     blackSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("Black Steel Unshaped");
/*  306 */     blueSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("Blue Steel Unshaped");
/*  307 */     brassUnshaped = (new ItemMeltedMetal()).func_77655_b("Brass Unshaped");
/*  308 */     bronzeUnshaped = (new ItemMeltedMetal()).func_77655_b("Bronze Unshaped");
/*  309 */     copperUnshaped = (new ItemMeltedMetal()).func_77655_b("Copper Unshaped");
/*  310 */     goldUnshaped = (new ItemMeltedMetal()).func_77655_b("Gold Unshaped");
/*  311 */     wroughtIronUnshaped = (new ItemMeltedMetal()).func_77655_b("Wrought Iron Unshaped");
/*  312 */     leadUnshaped = (new ItemMeltedMetal()).func_77655_b("Lead Unshaped");
/*  313 */     nickelUnshaped = (new ItemMeltedMetal()).func_77655_b("Nickel Unshaped");
/*  314 */     pigIronUnshaped = (new ItemMeltedMetal()).func_77655_b("Pig Iron Unshaped");
/*  315 */     platinumUnshaped = (new ItemMeltedMetal()).func_77655_b("Platinum Unshaped");
/*  316 */     redSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("Red Steel Unshaped");
/*  317 */     roseGoldUnshaped = (new ItemMeltedMetal()).func_77655_b("Rose Gold Unshaped");
/*  318 */     silverUnshaped = (new ItemMeltedMetal()).func_77655_b("Silver Unshaped");
/*  319 */     steelUnshaped = (new ItemMeltedMetal()).func_77655_b("Steel Unshaped");
/*  320 */     sterlingSilverUnshaped = (new ItemMeltedMetal()).func_77655_b("Sterling Silver Unshaped");
/*  321 */     tinUnshaped = (new ItemMeltedMetal()).func_77655_b("Tin Unshaped");
/*  322 */     zincUnshaped = (new ItemMeltedMetal()).func_77655_b("Zinc Unshaped");
/*      */ 
/*      */     
/*  325 */     stoneHammer = (new ItemHammer(igInToolMaterial, 60.0F)).func_77655_b("Stone Hammer").func_77656_e(igInStoneUses);
/*  326 */     bismuthBronzeHammer = (new ItemHammer(bismuthBronzeToolMaterial, 90.0F)).func_77655_b("Bismuth Bronze Hammer").func_77656_e(bismuthBronzeUses);
/*  327 */     blackBronzeHammer = (new ItemHammer(blackBronzeToolMaterial, 95.0F)).func_77655_b("Black Bronze Hammer").func_77656_e(blackBronzeUses);
/*  328 */     blackSteelHammer = (new ItemHammer(blackSteelToolMaterial, 205.0F)).func_77655_b("Black Steel Hammer").func_77656_e(blackSteelUses);
/*  329 */     blueSteelHammer = (new ItemHammer(blueSteelToolMaterial, 240.0F)).func_77655_b("Blue Steel Hammer").func_77656_e(blueSteelUses);
/*  330 */     bronzeHammer = (new ItemHammer(bronzeToolMaterial, 100.0F)).func_77655_b("Bronze Hammer").func_77656_e(bronzeUses);
/*  331 */     copperHammer = (new ItemHammer(copperToolMaterial, 80.0F)).func_77655_b("Copper Hammer").func_77656_e(copperUses);
/*  332 */     wroughtIronHammer = (new ItemHammer(ironToolMaterial, 135.0F)).func_77655_b("Wrought Iron Hammer").func_77656_e(wroughtIronUses);
/*  333 */     redSteelHammer = (new ItemHammer(redSteelToolMaterial, 240.0F)).func_77655_b("Red Steel Hammer").func_77656_e(redSteelUses);
/*  334 */     steelHammer = (new ItemHammer(steelToolMaterial, 170.0F)).func_77655_b("Steel Hammer").func_77656_e(steelUses);
/*      */     
/*  336 */     ink = (new ItemTerra()).func_77655_b("Ink").func_77637_a(TFCTabs.TFC_MATERIALS);
/*  337 */     fireStarter = (new ItemFirestarter()).setFolder("tools/").func_77655_b("Firestarter");
/*      */ 
/*      */     
/*  340 */     bismuthBronzePickaxeHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Pick Head");
/*  341 */     blackBronzePickaxeHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Pick Head");
/*  342 */     blackSteelPickaxeHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Pick Head");
/*  343 */     blueSteelPickaxeHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Pick Head");
/*  344 */     bronzePickaxeHead = (new ItemMiscToolHead()).func_77655_b("Bronze Pick Head");
/*  345 */     copperPickaxeHead = (new ItemMiscToolHead()).func_77655_b("Copper Pick Head");
/*  346 */     wroughtIronPickaxeHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Pick Head");
/*  347 */     redSteelPickaxeHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Pick Head");
/*  348 */     steelPickaxeHead = (new ItemMiscToolHead()).func_77655_b("Steel Pick Head");
/*      */     
/*  350 */     bismuthBronzeShovelHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Shovel Head");
/*  351 */     blackBronzeShovelHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Shovel Head");
/*  352 */     blackSteelShovelHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Shovel Head");
/*  353 */     blueSteelShovelHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Shovel Head");
/*  354 */     bronzeShovelHead = (new ItemMiscToolHead()).func_77655_b("Bronze Shovel Head");
/*  355 */     copperShovelHead = (new ItemMiscToolHead()).func_77655_b("Copper Shovel Head");
/*  356 */     wroughtIronShovelHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Shovel Head");
/*  357 */     redSteelShovelHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Shovel Head");
/*  358 */     steelShovelHead = (new ItemMiscToolHead()).func_77655_b("Steel Shovel Head");
/*      */     
/*  360 */     bismuthBronzeHoeHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Hoe Head");
/*  361 */     blackBronzeHoeHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Hoe Head");
/*  362 */     blackSteelHoeHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Hoe Head");
/*  363 */     blueSteelHoeHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Hoe Head");
/*  364 */     bronzeHoeHead = (new ItemMiscToolHead()).func_77655_b("Bronze Hoe Head");
/*  365 */     copperHoeHead = (new ItemMiscToolHead()).func_77655_b("Copper Hoe Head");
/*  366 */     wroughtIronHoeHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Hoe Head");
/*  367 */     redSteelHoeHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Hoe Head");
/*  368 */     steelHoeHead = (new ItemMiscToolHead()).func_77655_b("Steel Hoe Head");
/*      */     
/*  370 */     bismuthBronzeAxeHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Axe Head");
/*  371 */     blackBronzeAxeHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Axe Head");
/*  372 */     blackSteelAxeHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Axe Head");
/*  373 */     blueSteelAxeHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Axe Head");
/*  374 */     bronzeAxeHead = (new ItemMiscToolHead()).func_77655_b("Bronze Axe Head");
/*  375 */     copperAxeHead = (new ItemMiscToolHead()).func_77655_b("Copper Axe Head");
/*  376 */     wroughtIronAxeHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Axe Head");
/*  377 */     redSteelAxeHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Axe Head");
/*  378 */     steelAxeHead = (new ItemMiscToolHead()).func_77655_b("Steel Axe Head");
/*      */     
/*  380 */     bismuthBronzeHammerHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Hammer Head");
/*  381 */     blackBronzeHammerHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Hammer Head");
/*  382 */     blackSteelHammerHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Hammer Head");
/*  383 */     blueSteelHammerHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Hammer Head");
/*  384 */     bronzeHammerHead = (new ItemMiscToolHead()).func_77655_b("Bronze Hammer Head");
/*  385 */     copperHammerHead = (new ItemMiscToolHead()).func_77655_b("Copper Hammer Head");
/*  386 */     wroughtIronHammerHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Hammer Head");
/*  387 */     redSteelHammerHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Hammer Head");
/*  388 */     steelHammerHead = (new ItemMiscToolHead()).func_77655_b("Steel Hammer Head");
/*      */ 
/*      */     
/*  391 */     bismuthBronzeChiselHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Chisel Head");
/*  392 */     blackBronzeChiselHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Chisel Head");
/*  393 */     blackSteelChiselHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Chisel Head");
/*  394 */     blueSteelChiselHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Chisel Head");
/*  395 */     bronzeChiselHead = (new ItemMiscToolHead()).func_77655_b("Bronze Chisel Head");
/*  396 */     copperChiselHead = (new ItemMiscToolHead()).func_77655_b("Copper Chisel Head");
/*  397 */     wroughtIronChiselHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Chisel Head");
/*  398 */     redSteelChiselHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Chisel Head");
/*  399 */     steelChiselHead = (new ItemMiscToolHead()).func_77655_b("Steel Chisel Head");
/*      */     
/*  401 */     bismuthBronzeSwordHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Sword Blade");
/*  402 */     blackBronzeSwordHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Sword Blade");
/*  403 */     blackSteelSwordHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Sword Blade");
/*  404 */     blueSteelSwordHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Sword Blade");
/*  405 */     bronzeSwordHead = (new ItemMiscToolHead()).func_77655_b("Bronze Sword Blade");
/*  406 */     copperSwordHead = (new ItemMiscToolHead()).func_77655_b("Copper Sword Blade");
/*  407 */     wroughtIronSwordHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Sword Blade");
/*  408 */     redSteelSwordHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Sword Blade");
/*  409 */     steelSwordHead = (new ItemMiscToolHead()).func_77655_b("Steel Sword Blade");
/*      */     
/*  411 */     bismuthBronzeMaceHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Mace Head");
/*  412 */     blackBronzeMaceHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Mace Head");
/*  413 */     blackSteelMaceHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Mace Head");
/*  414 */     blueSteelMaceHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Mace Head");
/*  415 */     bronzeMaceHead = (new ItemMiscToolHead()).func_77655_b("Bronze Mace Head");
/*  416 */     copperMaceHead = (new ItemMiscToolHead()).func_77655_b("Copper Mace Head");
/*  417 */     wroughtIronMaceHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Mace Head");
/*  418 */     redSteelMaceHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Mace Head");
/*  419 */     steelMaceHead = (new ItemMiscToolHead()).func_77655_b("Steel Mace Head");
/*      */     
/*  421 */     bismuthBronzeSawHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Saw Blade");
/*  422 */     blackBronzeSawHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Saw Blade");
/*  423 */     blackSteelSawHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Saw Blade");
/*  424 */     blueSteelSawHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Saw Blade");
/*  425 */     bronzeSawHead = (new ItemMiscToolHead()).func_77655_b("Bronze Saw Blade");
/*  426 */     copperSawHead = (new ItemMiscToolHead()).func_77655_b("Copper Saw Blade");
/*  427 */     wroughtIronSawHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Saw Blade");
/*  428 */     redSteelSawHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Saw Blade");
/*  429 */     steelSawHead = (new ItemMiscToolHead()).func_77655_b("Steel Saw Blade");
/*      */     
/*  431 */     highCarbonBlackSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("HC Black Steel Unshaped");
/*  432 */     weakBlueSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("Weak Blue Steel Unshaped");
/*  433 */     highCarbonBlueSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("HC Blue Steel Unshaped");
/*  434 */     weakRedSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("Weak Red Steel Unshaped");
/*  435 */     highCarbonRedSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("HC Red Steel Unshaped");
/*  436 */     weakSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("Weak Steel Unshaped");
/*  437 */     highCarbonSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("HC Steel Unshaped");
/*      */ 
/*      */     
/*  440 */     bismuthBronzeProPickHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze ProPick Head");
/*  441 */     blackBronzeProPickHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze ProPick Head");
/*  442 */     blackSteelProPickHead = (new ItemMiscToolHead()).func_77655_b("Black Steel ProPick Head");
/*  443 */     blueSteelProPickHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel ProPick Head");
/*  444 */     bronzeProPickHead = (new ItemMiscToolHead()).func_77655_b("Bronze ProPick Head");
/*  445 */     copperProPickHead = (new ItemMiscToolHead()).func_77655_b("Copper ProPick Head");
/*  446 */     wroughtIronProPickHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron ProPick Head");
/*  447 */     redSteelProPickHead = (new ItemMiscToolHead()).func_77655_b("Red Steel ProPick Head");
/*  448 */     steelProPickHead = (new ItemMiscToolHead()).func_77655_b("Steel ProPick Head");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  453 */     bismuthBronzeScythe = (new ItemCustomScythe(bismuthBronzeToolMaterial)).func_77655_b("Bismuth Bronze Scythe").func_77656_e(bismuthBronzeUses);
/*  454 */     blackBronzeScythe = (new ItemCustomScythe(blackBronzeToolMaterial)).func_77655_b("Black Bronze Scythe").func_77656_e(blackBronzeUses);
/*  455 */     blackSteelScythe = (new ItemCustomScythe(blackSteelToolMaterial)).func_77655_b("Black Steel Scythe").func_77656_e(blackSteelUses);
/*  456 */     blueSteelScythe = (new ItemCustomScythe(blueSteelToolMaterial)).func_77655_b("Blue Steel Scythe").func_77656_e(blueSteelUses);
/*  457 */     bronzeScythe = (new ItemCustomScythe(bronzeToolMaterial)).func_77655_b("Bronze Scythe").func_77656_e(bronzeUses);
/*  458 */     copperScythe = (new ItemCustomScythe(copperToolMaterial)).func_77655_b("Copper Scythe").func_77656_e(copperUses);
/*  459 */     wroughtIronScythe = (new ItemCustomScythe(ironToolMaterial)).func_77655_b("Wrought Iron Scythe").func_77656_e(wroughtIronUses);
/*  460 */     redSteelScythe = (new ItemCustomScythe(redSteelToolMaterial)).func_77655_b("Red Steel Scythe").func_77656_e(redSteelUses);
/*  461 */     steelScythe = (new ItemCustomScythe(steelToolMaterial)).func_77655_b("Steel Scythe").func_77656_e(steelUses);
/*      */     
/*  463 */     bismuthBronzeScytheHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Scythe Blade");
/*  464 */     blackBronzeScytheHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Scythe Blade");
/*  465 */     blackSteelScytheHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Scythe Blade");
/*  466 */     blueSteelScytheHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Scythe Blade");
/*  467 */     bronzeScytheHead = (new ItemMiscToolHead()).func_77655_b("Bronze Scythe Blade");
/*  468 */     copperScytheHead = (new ItemMiscToolHead()).func_77655_b("Copper Scythe Blade");
/*  469 */     wroughtIronScytheHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Scythe Blade");
/*  470 */     redSteelScytheHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Scythe Blade");
/*  471 */     steelScytheHead = (new ItemMiscToolHead()).func_77655_b("Steel Scythe Blade");
/*      */     
/*  473 */     woodenBucketEmpty = (new ItemCustomBucket(Blocks.field_150350_a)).func_77655_b("Wooden Bucket Empty");
/*  474 */     woodenBucketWater = (new ItemCustomBucket(TFCBlocks.freshWater, woodenBucketEmpty)).func_77655_b("Wooden Bucket Water");
/*  475 */     woodenBucketSaltWater = (new ItemCustomBucket(TFCBlocks.saltWater, woodenBucketEmpty)).func_77655_b("Wooden Bucket Salt Water");
/*  476 */     woodenBucketMilk = (new ItemCustomBucketMilk()).func_77655_b("Wooden Bucket Milk").func_77642_a(woodenBucketEmpty).func_77637_a(TFCTabs.TFC_FOODS);
/*      */     
/*  478 */     bismuthBronzeKnifeHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Knife Blade");
/*  479 */     blackBronzeKnifeHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Knife Blade");
/*  480 */     blackSteelKnifeHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Knife Blade");
/*  481 */     blueSteelKnifeHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Knife Blade");
/*  482 */     bronzeKnifeHead = (new ItemMiscToolHead()).func_77655_b("Bronze Knife Blade");
/*  483 */     copperKnifeHead = (new ItemMiscToolHead()).func_77655_b("Copper Knife Blade");
/*  484 */     wroughtIronKnifeHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Knife Blade");
/*  485 */     redSteelKnifeHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Knife Blade");
/*  486 */     steelKnifeHead = (new ItemMiscToolHead()).func_77655_b("Steel Knife Blade");
/*      */     
/*  488 */     bismuthBronzeKnife = (new ItemKnife(bismuthBronzeToolMaterial, 155.0F)).func_77655_b("Bismuth Bronze Knife").func_77656_e(bismuthBronzeUses);
/*  489 */     blackBronzeKnife = (new ItemKnife(blackBronzeToolMaterial, 165.0F)).func_77655_b("Black Bronze Knife").func_77656_e(blackBronzeUses);
/*  490 */     blackSteelKnife = (new ItemKnife(blackSteelToolMaterial, 205.0F)).func_77655_b("Black Steel Knife").func_77656_e(blackSteelUses);
/*  491 */     blueSteelKnife = (new ItemKnife(blueSteelToolMaterial, 250.0F)).func_77655_b("Blue Steel Knife").func_77656_e(blueSteelUses);
/*  492 */     bronzeKnife = (new ItemKnife(bronzeToolMaterial, 150.0F)).func_77655_b("Bronze Knife").func_77656_e(bronzeUses);
/*  493 */     copperKnife = (new ItemKnife(copperToolMaterial, 100.0F)).func_77655_b("Copper Knife").func_77656_e(copperUses);
/*  494 */     wroughtIronKnife = (new ItemKnife(ironToolMaterial, 175.0F)).func_77655_b("Wrought Iron Knife").func_77656_e(wroughtIronUses);
/*  495 */     redSteelKnife = (new ItemKnife(redSteelToolMaterial, 250.0F)).func_77655_b("Red Steel Knife").func_77656_e(redSteelUses);
/*  496 */     steelKnife = (new ItemKnife(steelToolMaterial, 200.0F)).func_77655_b("Steel Knife").func_77656_e(steelUses);
/*      */     
/*  498 */     flatRock = (new ItemFlatGeneric()).setFolder("rocks/flatrocks/").setMetaNames(Global.STONE_ALL).func_77655_b("FlatRock");
/*  499 */     looseRock = (new ItemLooseRock()).setSpecialCraftingType(flatRock).setFolder("rocks/").setMetaNames(Global.STONE_ALL).func_77655_b("LooseRock");
/*      */     
/*  501 */     igInStoneShovelHead = (new ItemMiscToolHead(igInToolMaterial)).func_77655_b("IgIn Stone Shovel Head");
/*  502 */     sedStoneShovelHead = (new ItemMiscToolHead(sedToolMaterial)).func_77655_b("Sed Stone Shovel Head");
/*  503 */     igExStoneShovelHead = (new ItemMiscToolHead(igExToolMaterial)).func_77655_b("IgEx Stone Shovel Head");
/*  504 */     mMStoneShovelHead = (new ItemMiscToolHead(mMToolMaterial)).func_77655_b("MM Stone Shovel Head");
/*      */     
/*  506 */     igInStoneAxeHead = (new ItemMiscToolHead(igInToolMaterial)).func_77655_b("IgIn Stone Axe Head");
/*  507 */     sedStoneAxeHead = (new ItemMiscToolHead(sedToolMaterial)).func_77655_b("Sed Stone Axe Head");
/*  508 */     igExStoneAxeHead = (new ItemMiscToolHead(igExToolMaterial)).func_77655_b("IgEx Stone Axe Head");
/*  509 */     mMStoneAxeHead = (new ItemMiscToolHead(mMToolMaterial)).func_77655_b("MM Stone Axe Head");
/*      */     
/*  511 */     igInStoneHoeHead = (new ItemMiscToolHead(igInToolMaterial)).func_77655_b("IgIn Stone Hoe Head");
/*  512 */     sedStoneHoeHead = (new ItemMiscToolHead(sedToolMaterial)).func_77655_b("Sed Stone Hoe Head");
/*  513 */     igExStoneHoeHead = (new ItemMiscToolHead(igExToolMaterial)).func_77655_b("IgEx Stone Hoe Head");
/*  514 */     mMStoneHoeHead = (new ItemMiscToolHead(mMToolMaterial)).func_77655_b("MM Stone Hoe Head");
/*      */     
/*  516 */     stoneKnifeHead = (new ItemMiscToolHead(igInToolMaterial)).func_77655_b("Stone Knife Blade");
/*  517 */     stoneHammerHead = (new ItemMiscToolHead(igInToolMaterial)).func_77655_b("Stone Hammer Head");
/*      */     
/*  519 */     stoneKnife = (new ItemKnife(igExToolMaterial, 40.0F)).func_77655_b("Stone Knife").func_77656_e(igExStoneUses);
/*  520 */     singlePlank = (new ItemPlank()).func_77655_b("SinglePlank");
/*      */     
/*  522 */     redSteelBucketEmpty = (new ItemSteelBucketRed(Blocks.field_150350_a)).func_77655_b("Red Steel Bucket Empty");
/*  523 */     redSteelBucketWater = (new ItemSteelBucketRed(TFCBlocks.freshWater)).func_77655_b("Red Steel Bucket Water").func_77642_a(redSteelBucketEmpty);
/*  524 */     redSteelBucketSaltWater = (new ItemSteelBucketRed(TFCBlocks.saltWater)).func_77655_b("Red Steel Bucket Salt Water").func_77642_a(redSteelBucketEmpty);
/*      */     
/*  526 */     blueSteelBucketEmpty = (new ItemSteelBucketBlue(Blocks.field_150350_a)).func_77655_b("Blue Steel Bucket Empty");
/*  527 */     blueSteelBucketLava = (new ItemSteelBucketBlue(TFCBlocks.lava)).func_77655_b("Blue Steel Bucket Lava").func_77642_a(blueSteelBucketEmpty);
/*      */     
/*  529 */     quern = (Item)((ItemTerra)(new ItemTerra()).func_77655_b("Quern").func_77656_e(250)).setSize(EnumSize.MEDIUM).setWeight(EnumWeight.HEAVY);
/*  530 */     flintSteel = (new ItemFlintSteel()).func_77655_b("flintAndSteel").func_77656_e(200).func_111206_d("flint_and_steel");
/*      */     
/*  532 */     doorOak = (new ItemWoodDoor(0)).func_77655_b("Oak Door");
/*  533 */     doorAspen = (new ItemWoodDoor(1)).func_77655_b("Aspen Door");
/*  534 */     doorBirch = (new ItemWoodDoor(2)).func_77655_b("Birch Door");
/*  535 */     doorChestnut = (new ItemWoodDoor(3)).func_77655_b("Chestnut Door");
/*  536 */     doorDouglasFir = (new ItemWoodDoor(4)).func_77655_b("Douglas Fir Door");
/*  537 */     doorHickory = (new ItemWoodDoor(5)).func_77655_b("Hickory Door");
/*  538 */     doorMaple = (new ItemWoodDoor(6)).func_77655_b("Maple Door");
/*  539 */     doorAsh = (new ItemWoodDoor(7)).func_77655_b("Ash Door");
/*  540 */     doorPine = (new ItemWoodDoor(8)).func_77655_b("Pine Door");
/*  541 */     doorSequoia = (new ItemWoodDoor(9)).func_77655_b("Sequoia Door");
/*  542 */     doorSpruce = (new ItemWoodDoor(10)).func_77655_b("Spruce Door");
/*  543 */     doorSycamore = (new ItemWoodDoor(11)).func_77655_b("Sycamore Door");
/*  544 */     doorWhiteCedar = (new ItemWoodDoor(12)).func_77655_b("White Cedar Door");
/*  545 */     doorWhiteElm = (new ItemWoodDoor(13)).func_77655_b("White Elm Door");
/*  546 */     doorWillow = (new ItemWoodDoor(14)).func_77655_b("Willow Door");
/*  547 */     doorKapok = (new ItemWoodDoor(15)).func_77655_b("Kapok Door");
/*  548 */     doorAcacia = (new ItemWoodDoor(16)).func_77655_b("Acacia Door");
/*      */     
/*  550 */     beer = (new ItemAlcohol()).func_77655_b("Beer").func_77637_a(TFCTabs.TFC_FOODS);
/*  551 */     cider = (new ItemAlcohol()).func_77655_b("Cider").func_77637_a(TFCTabs.TFC_FOODS);
/*  552 */     rum = (new ItemAlcohol()).func_77655_b("Rum").func_77637_a(TFCTabs.TFC_FOODS);
/*  553 */     ryeWhiskey = (new ItemAlcohol()).func_77655_b("RyeWhiskey").func_77637_a(TFCTabs.TFC_FOODS);
/*  554 */     sake = (new ItemAlcohol()).func_77655_b("Sake").func_77637_a(TFCTabs.TFC_FOODS);
/*  555 */     vodka = (new ItemAlcohol()).func_77655_b("Vodka").func_77637_a(TFCTabs.TFC_FOODS);
/*  556 */     whiskey = (new ItemAlcohol()).func_77655_b("Whiskey").func_77637_a(TFCTabs.TFC_FOODS);
/*  557 */     cornWhiskey = (new ItemAlcohol()).func_77655_b("CornWhiskey").func_77637_a(TFCTabs.TFC_FOODS);
/*      */     
/*  559 */     blueprint = (Item)new ItemBlueprint();
/*  560 */     nametag = (Item)new ItemCustomNameTag();
/*      */     
/*  562 */     woolYarn = (new ItemYarn()).func_77655_b("WoolYarn").func_77637_a(TFCTabs.TFC_MATERIALS);
/*  563 */     wool = (new ItemTerra()).func_77655_b("Wool").func_77637_a(TFCTabs.TFC_MATERIALS);
/*  564 */     woolCloth = (new ItemTerra()).func_77655_b("WoolCloth").func_77637_a(TFCTabs.TFC_MATERIALS);
/*  565 */     silkCloth = (new ItemTerra()).func_77655_b("SilkCloth").func_77637_a(TFCTabs.TFC_MATERIALS);
/*  566 */     burlapCloth = (new ItemTerra()).func_77655_b("BurlapCloth").func_77637_a(TFCTabs.TFC_MATERIALS);
/*  567 */     spindle = (new ItemSpindle()).func_77655_b("Spindle").func_77637_a(TFCTabs.TFC_POTTERY);
/*      */ 
/*      */     
/*  570 */     spindleHead = (new ItemPotteryBase()).setMetaNames(new String[] { "Clay Spindle", "Spindle Head" }).func_77655_b("Spindle Head").func_77637_a(TFCTabs.TFC_POTTERY);
/*  571 */     stoneBrick = (new ItemStoneBrick()).setFolder("tools/").func_77655_b("ItemStoneBrick");
/*  572 */     mortar = (new ItemTerra()).setFolder("tools/").func_77655_b("Mortar").func_77637_a(TFCTabs.TFC_MATERIALS);
/*  573 */     vinegar = (new ItemCustomBucket(Blocks.field_150350_a)).setFolder("food/").func_77655_b("Vinegar").func_77642_a(woodenBucketEmpty).func_77637_a(TFCTabs.TFC_FOODS);
/*  574 */     hide = (new ItemRawHide()).setFolder("tools/").func_77655_b("Hide").func_77637_a(TFCTabs.TFC_MATERIALS);
/*  575 */     soakedHide = (new ItemRawHide()).setFolder("tools/").func_77655_b("Soaked Hide").func_77637_a(TFCTabs.TFC_MATERIALS);
/*  576 */     scrapedHide = (new ItemRawHide()).setFolder("tools/").func_77655_b("Scraped Hide").func_77637_a(TFCTabs.TFC_MATERIALS);
/*  577 */     prepHide = (new ItemRawHide()).setFolder("tools/").setFolder("tools/").func_77655_b("Prep Hide").func_77637_a(TFCTabs.TFC_MATERIALS);
/*      */     
/*  579 */     sheepSkin = (new ItemRawHide()).setFolder("tools/").func_77655_b("Sheep Skin").func_77637_a(TFCTabs.TFC_MATERIALS);
/*  580 */     flatLeather = (new ItemFlatGeneric()).setFolder("tools/").func_77655_b("Flat Leather");
/*  581 */     leather = (new ItemLeather()).setSpecialCraftingType(flatLeather).setFolder("tools/").func_77655_b("TFC Leather");
/*      */     
/*  583 */     straw = (new ItemTerra()).setFolder("plants/").func_77655_b("Straw").func_77637_a(TFCTabs.TFC_MATERIALS);
/*      */     
/*  585 */     flatClay = (new ItemFlatGeneric()).setFolder("pottery/").setMetaNames(new String[] { "clay flat light", "clay flat dark", "clay flat fire", "clay flat dark fire" }).func_77655_b("Flat Clay");
/*      */     
/*  587 */     potteryJug = (new ItemPotteryJug()).func_77655_b("Jug");
/*  588 */     potterySmallVessel = (new ItemPotterySmallVessel()).func_77655_b("Small Vessel");
/*      */     
/*  590 */     ceramicMold = (new ItemPotteryBase()).setMetaNames(new String[] { "Clay Mold", "Ceramic Mold" }).func_77655_b("Mold");
/*  591 */     potteryBowl = (new ItemPotteryBase()).setMetaNames(new String[] { "Clay Bowl", "Ceramic Bowl" }).func_77655_b("ClayBowl");
/*  592 */     clayBall = (new ItemClay()).setSpecialCraftingType(flatClay, new ItemStack(flatClay, 1, 1)).setMetaNames(new String[] { "Clay", "Fire Clay" }).func_77655_b("Clay");
/*  593 */     fireBrick = (new ItemPotteryBase()).setMetaNames(new String[] { "Clay Fire Brick", "Fire Brick" }).func_77655_b("Fire Brick");
/*      */     
/*  595 */     paraffin = (Item)((ItemTerra)(new ItemTerra()).func_77655_b("Paraffin").func_77637_a(TFCTabs.TFC_MATERIALS)).setSize(EnumSize.SMALL);
/*      */ 
/*      */     
/*  598 */     clayMoldAxe = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Axe", "Ceramic Mold Axe", "Ceramic Mold Axe Copper", "Ceramic Mold Axe Bronze", "Ceramic Mold Axe Bismuth Bronze", "Ceramic Mold Axe Black Bronze" }).func_77655_b("Axe Mold");
/*      */     
/*  600 */     clayMoldChisel = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Chisel", "Ceramic Mold Chisel", "Ceramic Mold Chisel Copper", "Ceramic Mold Chisel Bronze", "Ceramic Mold Chisel Bismuth Bronze", "Ceramic Mold Chisel Black Bronze" }).func_77655_b("Chisel Mold");
/*      */     
/*  602 */     clayMoldHammer = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Hammer", "Ceramic Mold Hammer", "Ceramic Mold Hammer Copper", "Ceramic Mold Hammer Bronze", "Ceramic Mold Hammer Bismuth Bronze", "Ceramic Mold Hammer Black Bronze" }).func_77655_b("Hammer Mold");
/*      */     
/*  604 */     clayMoldHoe = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Hoe", "Ceramic Mold Hoe", "Ceramic Mold Hoe Copper", "Ceramic Mold Hoe Bronze", "Ceramic Mold Hoe Bismuth Bronze", "Ceramic Mold Hoe Black Bronze" }).func_77655_b("Hoe Mold");
/*      */     
/*  606 */     clayMoldKnife = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Knife", "Ceramic Mold Knife", "Ceramic Mold Knife Copper", "Ceramic Mold Knife Bronze", "Ceramic Mold Knife Bismuth Bronze", "Ceramic Mold Knife Black Bronze" }).func_77655_b("Knife Mold");
/*      */     
/*  608 */     clayMoldMace = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Mace", "Ceramic Mold Mace", "Ceramic Mold Mace Copper", "Ceramic Mold Mace Bronze", "Ceramic Mold Mace Bismuth Bronze", "Ceramic Mold Mace Black Bronze" }).func_77655_b("Mace Mold");
/*      */     
/*  610 */     clayMoldPick = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Pick", "Ceramic Mold Pick", "Ceramic Mold Pick Copper", "Ceramic Mold Pick Bronze", "Ceramic Mold Pick Bismuth Bronze", "Ceramic Mold Pick Black Bronze" }).func_77655_b("Pick Mold");
/*      */     
/*  612 */     clayMoldProPick = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold ProPick", "Ceramic Mold ProPick", "Ceramic Mold ProPick Copper", "Ceramic Mold ProPick Bronze", "Ceramic Mold ProPick Bismuth Bronze", "Ceramic Mold ProPick Black Bronze" }).func_77655_b("ProPick Mold");
/*      */     
/*  614 */     clayMoldSaw = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Saw", "Ceramic Mold Saw", "Ceramic Mold Saw Copper", "Ceramic Mold Saw Bronze", "Ceramic Mold Saw Bismuth Bronze", "Ceramic Mold Saw Black Bronze" }).func_77655_b("Saw Mold");
/*      */     
/*  616 */     clayMoldScythe = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Scythe", "Ceramic Mold Scythe", "Ceramic Mold Scythe Copper", "Ceramic Mold Scythe Bronze", "Ceramic Mold Scythe Bismuth Bronze", "Ceramic Mold Scythe Black Bronze" }).func_77655_b("Scythe Mold");
/*      */     
/*  618 */     clayMoldShovel = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Shovel", "Ceramic Mold Shovel", "Ceramic Mold Shovel Copper", "Ceramic Mold Shovel Bronze", "Ceramic Mold Shovel Bismuth Bronze", "Ceramic Mold Shovel Black Bronze" }).func_77655_b("Shovel Mold");
/*      */     
/*  620 */     clayMoldSword = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Sword", "Ceramic Mold Sword", "Ceramic Mold Sword Copper", "Ceramic Mold Sword Bronze", "Ceramic Mold Sword Bismuth Bronze", "Ceramic Mold Sword Black Bronze" }).func_77655_b("Sword Mold");
/*      */     
/*  622 */     clayMoldJavelin = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Javelin", "Ceramic Mold Javelin", "Ceramic Mold Javelin Copper", "Ceramic Mold Javelin Bronze", "Ceramic Mold Javelin Bismuth Bronze", "Ceramic Mold Javelin Black Bronze" }).func_77655_b("Javelin Mold");
/*      */     
/*  624 */     tuyereCopper = (new ItemTuyere(40, 0)).func_77655_b("Copper Tuyere");
/*  625 */     tuyereBronze = (new ItemTuyere(80, 1)).func_77655_b("Bronze Tuyere");
/*  626 */     tuyereBlackBronze = (new ItemTuyere(80, 1)).func_77655_b("Black Bronze Tuyere");
/*  627 */     tuyereBismuthBronze = (new ItemTuyere(80, 1)).func_77655_b("Bismuth Bronze Tuyere");
/*  628 */     tuyereWroughtIron = (new ItemTuyere(120, 2)).func_77655_b("Wrought Iron Tuyere");
/*  629 */     tuyereSteel = (new ItemTuyere(180, 3)).func_77655_b("Steel Tuyere");
/*  630 */     tuyereBlackSteel = (new ItemTuyere(260, 4)).func_77655_b("Black Steel Tuyere");
/*  631 */     tuyereRedSteel = (new ItemTuyere(400, 5)).func_77655_b("Red Steel Tuyere");
/*  632 */     tuyereBlueSteel = (new ItemTuyere(500, 6)).func_77655_b("Blue Steel Tuyere");
/*      */     
/*  634 */     bloom = (new ItemBloom()).setFolder("ingots/").func_77655_b("Iron Bloom");
/*  635 */     rawBloom = (new ItemBloom()).setFolder("ingots/").func_77655_b("Raw Iron Bloom");
/*      */     
/*  637 */     unknownIngot = (new ItemIngot()).func_77655_b("Unknown Ingot");
/*  638 */     unknownUnshaped = (new ItemMeltedMetal()).func_77655_b("Unknown Unshaped");
/*      */     
/*  640 */     jute = (new ItemTerra()).setFolder("plants/").func_77655_b("Jute").func_77637_a(TFCTabs.TFC_MATERIALS);
/*  641 */     juteFiber = (new ItemTerra()).setFolder("plants/").func_77655_b("Jute Fibre").func_77637_a(TFCTabs.TFC_MATERIALS);
/*      */     
/*  643 */     Items.field_151120_aE.func_77637_a(null);
/*  644 */     reeds = (new ItemReeds()).func_77655_b("Reeds").func_77637_a(TFCTabs.TFC_MATERIALS).func_111206_d("reeds");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  649 */     setupFood();
/*      */     
/*  651 */     fertilizer = (new ItemFertilizer()).func_77655_b("Fertilizer").func_77637_a(TFCTabs.TFC_MATERIALS);
/*      */ 
/*      */     
/*  654 */     setupArmor();
/*      */     
/*  656 */     Recipes.doors = new Item[] { doorOak, doorAspen, doorBirch, doorChestnut, doorDouglasFir, doorHickory, doorMaple, doorAsh, doorPine, doorSequoia, doorSpruce, doorSycamore, doorWhiteCedar, doorWhiteElm, doorWillow, doorKapok, doorAcacia };
/*      */ 
/*      */ 
/*      */     
/*  660 */     Recipes.axes = new Item[] { sedAxe, igInAxe, igExAxe, mMAxe, bismuthBronzeAxe, blackBronzeAxe, blackSteelAxe, blueSteelAxe, bronzeAxe, copperAxe, wroughtIronAxe, redSteelAxe, steelAxe };
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  665 */     Recipes.chisels = new Item[] { bismuthBronzeChisel, blackBronzeChisel, blackSteelChisel, blueSteelChisel, bronzeChisel, copperChisel, wroughtIronChisel, redSteelChisel, steelChisel };
/*      */ 
/*      */ 
/*      */     
/*  669 */     Recipes.saws = new Item[] { bismuthBronzeSaw, blackBronzeSaw, blackSteelSaw, blueSteelSaw, bronzeSaw, copperSaw, wroughtIronSaw, redSteelSaw, steelSaw };
/*      */ 
/*      */ 
/*      */     
/*  673 */     Recipes.knives = new Item[] { stoneKnife, bismuthBronzeKnife, blackBronzeKnife, blackSteelKnife, blueSteelKnife, bronzeKnife, copperKnife, wroughtIronKnife, redSteelKnife, steelKnife };
/*      */ 
/*      */ 
/*      */     
/*  677 */     Recipes.meltedMetal = new Item[] { bismuthUnshaped, bismuthBronzeUnshaped, blackBronzeUnshaped, blackSteelUnshaped, blueSteelUnshaped, brassUnshaped, bronzeUnshaped, copperUnshaped, goldUnshaped, wroughtIronUnshaped, leadUnshaped, nickelUnshaped, pigIronUnshaped, platinumUnshaped, redSteelUnshaped, roseGoldUnshaped, silverUnshaped, steelUnshaped, sterlingSilverUnshaped, tinUnshaped, zincUnshaped, highCarbonSteelUnshaped, weakSteelUnshaped, highCarbonBlackSteelUnshaped, highCarbonBlueSteelUnshaped, highCarbonRedSteelUnshaped, weakBlueSteelUnshaped, weakRedSteelUnshaped };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  685 */     Recipes.hammers = new Item[] { stoneHammer, bismuthBronzeHammer, blackBronzeHammer, blackSteelHammer, blueSteelHammer, bronzeHammer, copperHammer, wroughtIronHammer, redSteelHammer, steelHammer };
/*      */ 
/*      */ 
/*      */     
/*  689 */     Recipes.scythes = new Item[] { bismuthBronzeScythe, blackBronzeScythe, blackSteelScythe, blueSteelScythe, bronzeScythe, copperScythe, wroughtIronScythe, redSteelScythe, steelScythe };
/*      */ 
/*      */ 
/*      */     
/*  693 */     Recipes.picks = new Item[] { bismuthBronzePick, blackBronzePick, blackSteelPick, blueSteelPick, bronzePick, copperPick, wroughtIronPick, redSteelPick, steelPick };
/*      */ 
/*      */ 
/*      */     
/*  697 */     Recipes.proPicks = new Item[] { proPickBismuthBronze, proPickBlackBronze, proPickBlackSteel, proPickBlueSteel, proPickBronze, proPickCopper, proPickIron, proPickRedSteel, proPickSteel };
/*      */ 
/*      */ 
/*      */     
/*  701 */     Recipes.shovels = new Item[] { sedShovel, igInShovel, igExShovel, mMShovel, bismuthBronzeShovel, blackBronzeShovel, blackSteelShovel, blueSteelShovel, bronzeShovel, copperShovel, wroughtIronShovel, redSteelShovel, steelShovel };
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  706 */     Recipes.hoes = new Item[] { sedHoe, igInHoe, igExHoe, mMHoe, bismuthBronzeHoe, blackBronzeHoe, blackSteelHoe, blueSteelHoe, bronzeHoe, copperHoe, wroughtIronHoe, redSteelHoe, steelHoe };
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  711 */     Recipes.swords = new Item[] { bismuthBronzeSword, blackBronzeSword, blackSteelSword, blueSteelSword, bronzeSword, copperSword, wroughtIronSword, redSteelSword, steelSword };
/*      */ 
/*      */ 
/*      */     
/*  715 */     Recipes.maces = new Item[] { bismuthBronzeMace, blackBronzeMace, blackSteelMace, blueSteelMace, bronzeMace, copperMace, wroughtIronMace, redSteelMace, steelMace };
/*      */ 
/*      */ 
/*      */     
/*  719 */     Recipes.javelins = new Item[] { sedStoneJavelin, igInStoneJavelin, igExStoneJavelin, mMStoneJavelin, bismuthBronzeJavelin, blackBronzeJavelin, blackSteelJavelin, blueSteelJavelin, bronzeJavelin, copperJavelin, wroughtIronJavelin, redSteelJavelin, steelJavelin };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  725 */     Recipes.spindle = new Item[] { spindle };
/*      */     
/*  727 */     Recipes.gems = new Item[] { gemAgate, gemAmethyst, gemBeryl, gemDiamond, gemEmerald, gemGarnet, gemJade, gemJasper, gemOpal, gemRuby, gemSapphire, gemTopaz, gemTourmaline };
/*      */ 
/*      */     
/*  730 */     Recipes.seeds = new Item[] { seedsBarley, seedsCabbage, seedsCarrot, seedsGarlic, seedsGreenbean, seedsJute, seedsMaize, seedsOat, seedsOnion, seedsPotato, seedsRedBellPepper, seedsRice, seedsRye, seedsSoybean, seedsSquash, seedsSugarcane, seedsTomato, seedsWheat, seedsYellowBellPepper };
/*      */ 
/*      */ 
/*      */     
/*  734 */     ((TFCTabs)TFCTabs.TFC_BUILDING).setTabIconItemStack(new ItemStack(TFCBlocks.stoneSedBrick));
/*  735 */     ((TFCTabs)TFCTabs.TFC_DECORATION).setTabIconItemStack(new ItemStack(TFCBlocks.flora));
/*  736 */     ((TFCTabs)TFCTabs.TFC_DEVICES).setTabIconItem(sluiceItem);
/*  737 */     ((TFCTabs)TFCTabs.TFC_POTTERY).setTabIconItemStack(new ItemStack(potteryJug, 1, 1));
/*  738 */     ((TFCTabs)TFCTabs.TFC_MISC).setTabIconItem(blueSteelBucketLava);
/*  739 */     ((TFCTabs)TFCTabs.TFC_FOODS).setTabIconItem(redApple);
/*  740 */     ((TFCTabs)TFCTabs.TFC_TOOLS).setTabIconItem(redSteelAxe);
/*  741 */     ((TFCTabs)TFCTabs.TFC_WEAPONS).setTabIconItem(bismuthBronzeSword);
/*  742 */     ((TFCTabs)TFCTabs.TFC_ARMOR).setTabIconItem(bronzeHelmet);
/*  743 */     ((TFCTabs)TFCTabs.TFC_MATERIALS).setTabIconItem(blueSteelIngot);
/*      */     
/*  745 */     registerItems();
/*  746 */     registerMetals();
/*      */     
/*  748 */     TerraFirmaCraft.LOG.info("Finished Loading Items");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void setupFood() {
/*  756 */     foodList = new ArrayList();
/*      */     
/*  758 */     egg = (new ItemEgg()).setSize(EnumSize.SMALL).func_77655_b("egg").func_111206_d("egg").func_77637_a(TFCTabs.TFC_FOODS);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  763 */     porkchopRaw = (new ItemFoodMeat(EnumFoodGroup.Protein, 0, 0, 0, 0, 40, false, false)).setDecayRate(2.5F).setCanSmoke().setHasCookedIcon().setSmokeAbsorbMultiplier(1.0F).func_77655_b("Porkchop");
/*  764 */     fishRaw = (new ItemFoodMeat(EnumFoodGroup.Protein, 0, 0, 0, 0, 40, false, true)).setDecayRate(2.5F).setCanSmoke().setHasCookedIcon().setSmokeAbsorbMultiplier(1.0F).func_77655_b("Fish");
/*  765 */     beefRaw = (new ItemFoodMeat(EnumFoodGroup.Protein, 0, 0, 0, 0, 50, false, false)).setDecayRate(2.5F).setCanSmoke().setHasCookedIcon().setSmokeAbsorbMultiplier(1.0F).func_77655_b("Beef");
/*  766 */     chickenRaw = (new ItemFoodMeat(EnumFoodGroup.Protein, 0, 0, 0, 0, 40, false, false)).setDecayRate(2.5F).setCanSmoke().setHasCookedIcon().setSmokeAbsorbMultiplier(1.0F).func_77655_b("Chicken");
/*  767 */     soybean = (new ItemFoodTFC(EnumFoodGroup.Protein, 10, 0, 0, 0, 40, true)).func_77655_b("Soybeans");
/*  768 */     eggCooked = (new ItemFoodTFC(EnumFoodGroup.Protein, 0, 0, 0, 0, 25)).setDecayRate(2.5F).func_77655_b("Egg Cooked");
/*  769 */     calamariRaw = (new ItemFoodMeat(EnumFoodGroup.Protein, 0, 0, 20, 0, 35, false, false)).setCanSmoke().setHasCookedIcon().setSmokeAbsorbMultiplier(1.0F).setDecayRate(4.0F).func_77655_b("Calamari");
/*  770 */     muttonRaw = (new ItemFoodMeat(EnumFoodGroup.Protein, 0, 0, 0, 0, 40, false, false)).setDecayRate(2.5F).setCanSmoke().setHasCookedIcon().setSmokeAbsorbMultiplier(1.0F).func_77655_b("Mutton");
/*  771 */     venisonRaw = (new ItemFoodMeat(EnumFoodGroup.Protein, 5, 0, 0, 0, 50, false, false)).setDecayRate(2.5F).setCanSmoke().setHasCookedIcon().setSmokeAbsorbMultiplier(1.0F).func_77655_b("Venison");
/*  772 */     horseMeatRaw = (new ItemFoodMeat(EnumFoodGroup.Protein, 0, 0, 0, 0, 40, false, false)).setDecayRate(2.5F).setCanSmoke().setHasCookedIcon().setSmokeAbsorbMultiplier(1.0F).func_77655_b("HorseMeat");
/*      */ 
/*      */     
/*  775 */     cheese = (new ItemFoodTFC(EnumFoodGroup.Dairy, 0, 10, 20, 0, 35)).setDecayRate(0.5F).setCanSmoke().setSmokeAbsorbMultiplier(1.0F).func_77655_b("Cheese");
/*      */ 
/*      */     
/*  778 */     wheatGrain = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 5, 20)).setDecayRate(0.5F).func_77655_b("Wheat Grain");
/*  779 */     barleyGrain = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 10, 20)).setDecayRate(0.5F).func_77655_b("Barley Grain");
/*  780 */     oatGrain = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 5, 20)).setDecayRate(0.5F).func_77655_b("Oat Grain");
/*  781 */     ryeGrain = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 15, 0, 5, 20)).setDecayRate(0.5F).func_77655_b("Rye Grain");
/*  782 */     riceGrain = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 5, 20)).setDecayRate(0.5F).func_77655_b("Rice Grain");
/*  783 */     maizeEar = (new ItemFoodTFC(EnumFoodGroup.Grain, 25, 0, 0, 5, 20, true)).func_77655_b("Maize Ear");
/*      */     
/*  785 */     wheatWhole = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 5, 20, false, false)).setFolder("food/").func_77655_b("Wheat Whole");
/*  786 */     barleyWhole = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 10, 20, false, false)).setFolder("food/").func_77655_b("Barley Whole");
/*  787 */     oatWhole = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 5, 20, false, false)).setFolder("food/").func_77655_b("Oat Whole");
/*  788 */     ryeWhole = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 15, 0, 5, 20, false, false)).setFolder("food/").func_77655_b("Rye Whole");
/*  789 */     riceWhole = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 5, 20, false, false)).setFolder("food/").func_77655_b("Rice Whole");
/*      */     
/*  791 */     wheatGround = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 0, 20, false, false)).setFolder("food/").func_77655_b("Wheat Ground");
/*  792 */     barleyGround = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 5, 20, false, false)).setFolder("food/").func_77655_b("Barley Ground");
/*  793 */     oatGround = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 0, 20, false, false)).setFolder("food/").func_77655_b("Oat Ground");
/*  794 */     ryeGround = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 15, 0, 0, 20, false, false)).setFolder("food/").func_77655_b("Rye Ground");
/*  795 */     riceGround = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 0, 20, false, false)).setFolder("food/").func_77655_b("Rice Ground");
/*  796 */     cornmealGround = (new ItemFoodTFC(EnumFoodGroup.Grain, 25, 0, 0, 0, 20, false, false)).setFolder("food/").func_77655_b("Cornmeal Ground");
/*      */     
/*  798 */     wheatDough = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 0, 20, false, false)).func_77655_b("Wheat Dough");
/*  799 */     barleyDough = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 5, 20, false, false)).func_77655_b("Barley Dough");
/*  800 */     oatDough = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 0, 20, false, false)).func_77655_b("Oat Dough");
/*  801 */     ryeDough = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 15, 0, 0, 20, false, false)).func_77655_b("Rye Dough");
/*  802 */     riceDough = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 0, 20, false, false)).func_77655_b("Rice Dough");
/*  803 */     cornmealDough = (new ItemFoodTFC(EnumFoodGroup.Grain, 25, 0, 0, 0, 20, false, false)).func_77655_b("Cornmeal Dough");
/*      */     
/*  805 */     wheatBread = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 0, 20)).func_77655_b("Wheat Bread");
/*  806 */     barleyBread = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 5, 20)).func_77655_b("Barley Bread");
/*  807 */     oatBread = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 0, 20)).func_77655_b("Oat Bread");
/*  808 */     ryeBread = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 15, 0, 0, 20)).func_77655_b("Rye Bread");
/*  809 */     riceBread = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 0, 20)).func_77655_b("Rice Bread");
/*  810 */     cornBread = (new ItemFoodTFC(EnumFoodGroup.Grain, 25, 0, 0, 0, 20)).func_77655_b("Corn Bread");
/*      */ 
/*      */     
/*  813 */     tomato = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 30, 5, 0, 0, 50, true)).func_77655_b("Tomato");
/*  814 */     potato = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 0, 0, 10, 15, 20, true)).func_77655_b("Potato");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  834 */     onion = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 10, 25, 0, 0, 20, true) { public void func_94581_a(IIconRegister registerer) { super.func_94581_a(registerer); this.field_77787_bX = true; this.metaIcons = new IIcon[2]; this.metaIcons[0] = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + func_77658_a().replace("item.", "")); this.metaIcons[1] = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + "Rutabaga"); } public IIcon func_77617_a(int i) { if (i == 1) return this.metaIcons[1];  return super.func_77617_a(i); } }).func_77655_b(TFCOptions.onionsAreGross ? "Rutabaga" : "Onion");
/*  835 */     cabbage = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 10, 0, 0, 0, 30, true)).func_77655_b("Cabbage");
/*  836 */     garlic = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 0, 0, 0, 10, 20, true)).func_77655_b("Garlic");
/*  837 */     carrot = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 20, 0, 0, 0, 20, true)).func_77655_b("Carrot");
/*  838 */     greenbeans = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 20, 0, 0, 0, 20, true)).func_77655_b("Greenbeans");
/*  839 */     greenBellPepper = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 10, 0, 0, 0, 20, true)).func_77655_b("Green Bell Pepper");
/*  840 */     yellowBellPepper = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 15, 0, 0, 0, 20, true)).func_77655_b("Yellow Bell Pepper");
/*  841 */     redBellPepper = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 20, 0, 0, 0, 20, true)).func_77655_b("Red Bell Pepper");
/*  842 */     squash = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 20, 0, 0, 0, 20, true)).func_77655_b("Squash");
/*  843 */     seaWeed = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 0, 0, 10, 10, 10, true)).func_77655_b("Sea Weed");
/*  844 */     sugar = (new ItemFoodTFC(EnumFoodGroup.None, 30, 0, 0, 0, 0, true)).setDecayRate(0.01F).func_77655_b("Sugar");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  865 */     honeycomb = (new ItemFoodTFC(EnumFoodGroup.None, 50, 0, 0, 0, 0, true, true) { public ItemStack func_77654_b(ItemStack is, World world, EntityPlayer player) { super.func_77654_b(is, world, player); if (!world.field_72995_K && isEdible(is)) if (is.func_77942_o()) { float eatAmount = getFoodMaxWeight(is) / 3.0F; if (FoodStatsTFC.reduceFood(is, eatAmount)) is.field_77994_a = 0;  }   return is; } }).setDecayRate(0.01F).setSize(EnumSize.MEDIUM).func_77655_b("Honeycomb");
/*      */ 
/*      */     
/*  868 */     redApple = (new ItemFoodTFC(EnumFoodGroup.Fruit, 25, 5, 0, 10, 0, true)).setDecayRate(2.0F).func_77655_b(Global.FRUIT_META_NAMES[0]);
/*  869 */     banana = (new ItemFoodTFC(EnumFoodGroup.Fruit, 30, 5, 0, 0, 0, true)).setDecayRate(2.0F).func_77655_b(Global.FRUIT_META_NAMES[1]);
/*  870 */     orange = (new ItemFoodTFC(EnumFoodGroup.Fruit, 50, 30, 0, 10, 0, true)).setDecayRate(2.0F).func_77655_b(Global.FRUIT_META_NAMES[2]);
/*  871 */     greenApple = (new ItemFoodTFC(EnumFoodGroup.Fruit, 30, 15, 0, 10, 0, true)).setDecayRate(2.0F).func_77655_b(Global.FRUIT_META_NAMES[3]);
/*  872 */     lemon = (new ItemFoodTFC(EnumFoodGroup.Fruit, 30, 50, 0, 10, 0, true)).setDecayRate(2.0F).func_77655_b(Global.FRUIT_META_NAMES[4]);
/*  873 */     olive = (new ItemFoodTFC(EnumFoodGroup.Fruit, 10, 0, 3, 10, 0, true)).setDecayRate(2.0F).func_77655_b(Global.FRUIT_META_NAMES[5]);
/*  874 */     cherry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 30, 5, 0, 0, 0, true)).setDecayRate(2.0F).func_77655_b(Global.FRUIT_META_NAMES[6]);
/*  875 */     peach = (new ItemFoodTFC(EnumFoodGroup.Fruit, 25, 10, 0, 0, 0, true)).setDecayRate(2.0F).func_77655_b(Global.FRUIT_META_NAMES[7]);
/*  876 */     plum = (new ItemFoodTFC(EnumFoodGroup.Fruit, 20, 15, 0, 0, 0, true)).setDecayRate(2.0F).func_77655_b(Global.FRUIT_META_NAMES[8]);
/*      */     
/*  878 */     wintergreenBerry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 30, 0, 0, 20, 0)).setDecayRate(2.0F).func_77655_b("Wintergreen Berry");
/*  879 */     blueberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 30, 20, 0, 0, 0)).setDecayRate(2.0F).func_77655_b("Blueberry");
/*  880 */     raspberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 35, 15, 0, 5, 0)).setDecayRate(2.0F).func_77655_b("Raspberry");
/*  881 */     strawberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 30, 5, 0, 5, 0)).setDecayRate(2.0F).func_77655_b("Strawberry");
/*  882 */     blackberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 30, 30, 0, 0, 0)).setDecayRate(2.0F).func_77655_b("Blackberry");
/*  883 */     bunchberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 20, 5, 0, 0, 0)).setDecayRate(2.0F).func_77655_b("Bunchberry");
/*  884 */     cranberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 30, 5, 0, 45, 0)).setDecayRate(2.0F).func_77655_b("Cranberry");
/*  885 */     snowberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 10, 0, 0, 90, 0)).setDecayRate(2.0F).func_77655_b("Snowberry");
/*  886 */     elderberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 20, 40, 0, 10, 0)).setDecayRate(2.0F).func_77655_b("Elderberry");
/*  887 */     gooseberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 20, 40, 0, 0, 0)).setDecayRate(2.0F).func_77655_b("Gooseberry");
/*  888 */     cloudberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 40, 40, 0, 30, 0)).setDecayRate(2.0F).func_77655_b("Cloudberry");
/*      */     
/*  890 */     sandwich = (new ItemSandwich()).func_77655_b("Sandwich");
/*  891 */     salad = (new ItemSalad()).func_77655_b("Salad");
/*      */ 
/*      */     
/*  894 */     sugarcane = (new ItemFoodTFC(EnumFoodGroup.None, 30, 0, 0, 0, 0, false, false)).setDecayRate(0.75F).setFolder("plants/").func_77655_b("Sugarcane");
/*      */ 
/*      */     
/*  897 */     seedsWheat = (new ItemCustomSeeds(0)).func_77655_b("Seeds Wheat");
/*  898 */     seedsMaize = (new ItemCustomSeeds(1)).func_77655_b("Seeds Maize");
/*  899 */     seedsTomato = (new ItemCustomSeeds(2)).func_77655_b("Seeds Tomato");
/*  900 */     seedsBarley = (new ItemCustomSeeds(3)).func_77655_b("Seeds Barley");
/*  901 */     seedsRye = (new ItemCustomSeeds(4)).func_77655_b("Seeds Rye");
/*  902 */     seedsOat = (new ItemCustomSeeds(5)).func_77655_b("Seeds Oat");
/*  903 */     seedsRice = (new ItemCustomSeeds(6)).func_77655_b("Seeds Rice");
/*  904 */     seedsPotato = (new ItemCustomSeeds(7)).func_77655_b("Seeds Potato");
/*  905 */     seedsOnion = (new ItemCustomSeeds(8)).func_77655_b(TFCOptions.onionsAreGross ? "Seeds Rutabaga" : "Seeds Onion");
/*  906 */     seedsCabbage = (new ItemCustomSeeds(9)).func_77655_b("Seeds Cabbage");
/*  907 */     seedsGarlic = (new ItemCustomSeeds(10)).func_77655_b("Seeds Garlic");
/*  908 */     seedsCarrot = (new ItemCustomSeeds(11)).func_77655_b("Seeds Carrot");
/*  909 */     seedsYellowBellPepper = (new ItemCustomSeeds(12)).func_77655_b("Seeds Yellow Bell Pepper");
/*  910 */     seedsRedBellPepper = (new ItemCustomSeeds(13)).func_77655_b("Seeds Red Bell Pepper");
/*  911 */     seedsSoybean = (new ItemCustomSeeds(14)).func_77655_b("Seeds Soybean");
/*  912 */     seedsGreenbean = (new ItemCustomSeeds(15)).func_77655_b("Seeds Greenbean");
/*  913 */     seedsSquash = (new ItemCustomSeeds(16)).func_77655_b("Seeds Squash");
/*  914 */     seedsJute = (new ItemCustomSeeds(17)).func_77655_b("Seeds Jute");
/*  915 */     seedsSugarcane = (new ItemCustomSeeds(18)).func_77655_b("Seeds Sugarcane");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  920 */     fruitTreeSapling = (new ItemFruitTreeSapling()).func_77655_b("FruitSapling");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void registerMetals() {
/*  940 */     Global.BISMUTH = new Metal("Bismuth", TFCItems.bismuthUnshaped, TFCItems.bismuthIngot);
/*  941 */     Global.BISMUTHBRONZE = new Metal("Bismuth Bronze", TFCItems.bismuthBronzeUnshaped, TFCItems.bismuthBronzeIngot);
/*  942 */     Global.BLACKBRONZE = new Metal("Black Bronze", TFCItems.blackBronzeUnshaped, TFCItems.blackBronzeIngot);
/*  943 */     Global.BLACKSTEEL = new Metal("Black Steel", TFCItems.blackSteelUnshaped, TFCItems.blackSteelIngot);
/*  944 */     Global.BLUESTEEL = new Metal("Blue Steel", TFCItems.blueSteelUnshaped, TFCItems.blueSteelIngot);
/*  945 */     Global.BRASS = new Metal("Brass", TFCItems.brassUnshaped, TFCItems.brassIngot);
/*  946 */     Global.BRONZE = new Metal("Bronze", TFCItems.bronzeUnshaped, TFCItems.bronzeIngot);
/*  947 */     Global.COPPER = new Metal("Copper", TFCItems.copperUnshaped, TFCItems.copperIngot);
/*  948 */     Global.GOLD = new Metal("Gold", TFCItems.goldUnshaped, TFCItems.goldIngot);
/*  949 */     Global.WROUGHTIRON = new Metal("Wrought Iron", TFCItems.wroughtIronUnshaped, TFCItems.wroughtIronIngot);
/*  950 */     Global.LEAD = new Metal("Lead", TFCItems.leadUnshaped, TFCItems.leadIngot);
/*  951 */     Global.NICKEL = new Metal("Nickel", TFCItems.nickelUnshaped, TFCItems.nickelIngot);
/*  952 */     Global.PIGIRON = new Metal("Pig Iron", TFCItems.pigIronUnshaped, TFCItems.pigIronIngot);
/*  953 */     Global.PLATINUM = new Metal("Platinum", TFCItems.platinumUnshaped, TFCItems.platinumIngot);
/*  954 */     Global.REDSTEEL = new Metal("Red Steel", TFCItems.redSteelUnshaped, TFCItems.redSteelIngot);
/*  955 */     Global.ROSEGOLD = new Metal("Rose Gold", TFCItems.roseGoldUnshaped, TFCItems.roseGoldIngot);
/*  956 */     Global.SILVER = new Metal("Silver", TFCItems.silverUnshaped, TFCItems.silverIngot);
/*  957 */     Global.STEEL = new Metal("Steel", TFCItems.steelUnshaped, TFCItems.steelIngot);
/*  958 */     Global.STERLINGSILVER = new Metal("Sterling Silver", TFCItems.sterlingSilverUnshaped, TFCItems.sterlingSilverIngot);
/*  959 */     Global.TIN = new Metal("Tin", TFCItems.tinUnshaped, TFCItems.tinIngot);
/*  960 */     Global.ZINC = new Metal("Zinc", TFCItems.zincUnshaped, TFCItems.zincIngot);
/*  961 */     Global.WEAKSTEEL = new Metal("Weak Steel", TFCItems.weakSteelUnshaped, TFCItems.weakSteelIngot);
/*  962 */     Global.HCBLACKSTEEL = new Metal("HC Black Steel", TFCItems.highCarbonBlackSteelUnshaped, TFCItems.highCarbonBlackSteelIngot);
/*  963 */     Global.WEAKREDSTEEL = new Metal("Weak Red Steel", TFCItems.weakRedSteelUnshaped, TFCItems.weakRedSteelIngot);
/*  964 */     Global.HCREDSTEEL = new Metal("HC Red Steel", TFCItems.highCarbonRedSteelUnshaped, TFCItems.highCarbonRedSteelIngot);
/*  965 */     Global.WEAKBLUESTEEL = new Metal("Weak Blue Steel", TFCItems.weakBlueSteelUnshaped, TFCItems.weakBlueSteelIngot);
/*  966 */     Global.HCBLUESTEEL = new Metal("HC Blue Steel", TFCItems.highCarbonBlueSteelUnshaped, TFCItems.highCarbonBlueSteelIngot);
/*  967 */     Global.UNKNOWN = new Metal("Unknown", TFCItems.unknownUnshaped, TFCItems.unknownIngot, false);
/*      */     
/*  969 */     MetalRegistry.instance.addMetal(Global.BISMUTH, Alloy.EnumTier.TierI);
/*  970 */     MetalRegistry.instance.addMetal(Global.BISMUTHBRONZE, Alloy.EnumTier.TierI);
/*  971 */     MetalRegistry.instance.addMetal(Global.BLACKBRONZE, Alloy.EnumTier.TierI);
/*  972 */     MetalRegistry.instance.addMetal(Global.BLACKSTEEL, Alloy.EnumTier.TierV);
/*  973 */     MetalRegistry.instance.addMetal(Global.BLUESTEEL, Alloy.EnumTier.TierV);
/*  974 */     MetalRegistry.instance.addMetal(Global.BRASS, Alloy.EnumTier.TierI);
/*  975 */     MetalRegistry.instance.addMetal(Global.BRONZE, Alloy.EnumTier.TierI);
/*  976 */     MetalRegistry.instance.addMetal(Global.COPPER, Alloy.EnumTier.TierI);
/*  977 */     MetalRegistry.instance.addMetal(Global.GOLD, Alloy.EnumTier.TierI);
/*  978 */     MetalRegistry.instance.addMetal(Global.WROUGHTIRON, Alloy.EnumTier.TierIII);
/*  979 */     MetalRegistry.instance.addMetal(Global.LEAD, Alloy.EnumTier.TierI);
/*  980 */     MetalRegistry.instance.addMetal(Global.NICKEL, Alloy.EnumTier.TierI);
/*  981 */     MetalRegistry.instance.addMetal(Global.PIGIRON, Alloy.EnumTier.TierIV);
/*  982 */     MetalRegistry.instance.addMetal(Global.PLATINUM, Alloy.EnumTier.TierV);
/*  983 */     MetalRegistry.instance.addMetal(Global.REDSTEEL, Alloy.EnumTier.TierV);
/*  984 */     MetalRegistry.instance.addMetal(Global.ROSEGOLD, Alloy.EnumTier.TierI);
/*  985 */     MetalRegistry.instance.addMetal(Global.SILVER, Alloy.EnumTier.TierI);
/*  986 */     MetalRegistry.instance.addMetal(Global.STEEL, Alloy.EnumTier.TierIV);
/*  987 */     MetalRegistry.instance.addMetal(Global.STERLINGSILVER, Alloy.EnumTier.TierI);
/*  988 */     MetalRegistry.instance.addMetal(Global.TIN, Alloy.EnumTier.TierI);
/*  989 */     MetalRegistry.instance.addMetal(Global.ZINC, Alloy.EnumTier.TierI);
/*  990 */     MetalRegistry.instance.addMetal(Global.WEAKSTEEL, Alloy.EnumTier.TierV);
/*  991 */     MetalRegistry.instance.addMetal(Global.HCBLACKSTEEL, Alloy.EnumTier.TierV);
/*  992 */     MetalRegistry.instance.addMetal(Global.WEAKREDSTEEL, Alloy.EnumTier.TierV);
/*  993 */     MetalRegistry.instance.addMetal(Global.HCREDSTEEL, Alloy.EnumTier.TierV);
/*  994 */     MetalRegistry.instance.addMetal(Global.WEAKBLUESTEEL, Alloy.EnumTier.TierV);
/*  995 */     MetalRegistry.instance.addMetal(Global.HCBLUESTEEL, Alloy.EnumTier.TierV);
/*  996 */     MetalRegistry.instance.addMetal(Global.UNKNOWN, Alloy.EnumTier.TierI);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1001 */     Alloy bronze = new Alloy(Global.BRONZE, Alloy.EnumTier.TierI);
/* 1002 */     bronze.addIngred(Global.COPPER, 87.99F, 92.01F);
/* 1003 */     bronze.addIngred(Global.TIN, 7.99F, 12.01F);
/* 1004 */     AlloyManager.INSTANCE.addAlloy(bronze);
/*      */     
/* 1006 */     Alloy brass = new Alloy(Global.BRASS, Alloy.EnumTier.TierI);
/* 1007 */     brass.addIngred(Global.COPPER, 87.99F, 92.01F);
/* 1008 */     brass.addIngred(Global.ZINC, 7.99F, 12.01F);
/* 1009 */     AlloyManager.INSTANCE.addAlloy(brass);
/*      */     
/* 1011 */     Alloy roseGold = new Alloy(Global.ROSEGOLD, Alloy.EnumTier.TierI);
/* 1012 */     roseGold.addIngred(Global.GOLD, 69.99F, 85.01F);
/* 1013 */     roseGold.addIngred(Global.COPPER, 14.99F, 30.01F);
/* 1014 */     AlloyManager.INSTANCE.addAlloy(roseGold);
/*      */     
/* 1016 */     Alloy blackBronze = new Alloy(Global.BLACKBRONZE, Alloy.EnumTier.TierI);
/* 1017 */     blackBronze.addIngred(Global.GOLD, 9.99F, 25.01F);
/* 1018 */     blackBronze.addIngred(Global.COPPER, 49.99F, 70.01F);
/* 1019 */     blackBronze.addIngred(Global.SILVER, 9.99F, 25.01F);
/* 1020 */     AlloyManager.INSTANCE.addAlloy(blackBronze);
/*      */     
/* 1022 */     Alloy bismuthBronze = new Alloy(Global.BISMUTHBRONZE, Alloy.EnumTier.TierI);
/* 1023 */     bismuthBronze.addIngred(Global.ZINC, 19.99F, 30.01F);
/* 1024 */     bismuthBronze.addIngred(Global.COPPER, 49.99F, 65.01F);
/* 1025 */     bismuthBronze.addIngred(Global.BISMUTH, 9.99F, 20.01F);
/* 1026 */     AlloyManager.INSTANCE.addAlloy(bismuthBronze);
/*      */     
/* 1028 */     Alloy sterlingSilver = new Alloy(Global.STERLINGSILVER, Alloy.EnumTier.TierI);
/* 1029 */     sterlingSilver.addIngred(Global.SILVER, 59.99F, 80.01F);
/* 1030 */     sterlingSilver.addIngred(Global.COPPER, 19.99F, 40.01F);
/* 1031 */     AlloyManager.INSTANCE.addAlloy(sterlingSilver);
/*      */     
/* 1033 */     Alloy weakSteel = new Alloy(Global.WEAKSTEEL, Alloy.EnumTier.TierIII);
/* 1034 */     weakSteel.addIngred(Global.STEEL, 49.99F, 70.01F);
/* 1035 */     weakSteel.addIngred(Global.NICKEL, 14.99F, 25.01F);
/* 1036 */     weakSteel.addIngred(Global.BLACKBRONZE, 14.99F, 25.01F);
/* 1037 */     AlloyManager.INSTANCE.addAlloy(weakSteel);
/*      */     
/* 1039 */     Alloy weakRedSteel = new Alloy(Global.WEAKREDSTEEL, Alloy.EnumTier.TierIII);
/* 1040 */     weakRedSteel.addIngred(Global.BLACKSTEEL, 49.99F, 55.01F);
/* 1041 */     weakRedSteel.addIngred(Global.ROSEGOLD, 9.99F, 15.01F);
/* 1042 */     weakRedSteel.addIngred(Global.BRASS, 9.99F, 15.01F);
/* 1043 */     weakRedSteel.addIngred(Global.STEEL, 19.99F, 25.01F);
/* 1044 */     AlloyManager.INSTANCE.addAlloy(weakRedSteel);
/*      */     
/* 1046 */     Alloy weakBlueSteel = new Alloy(Global.WEAKBLUESTEEL, Alloy.EnumTier.TierIII);
/* 1047 */     weakBlueSteel.addIngred(Global.BLACKSTEEL, 49.99F, 55.01F);
/* 1048 */     weakBlueSteel.addIngred(Global.BISMUTHBRONZE, 9.99F, 15.01F);
/* 1049 */     weakBlueSteel.addIngred(Global.STERLINGSILVER, 9.99F, 15.01F);
/* 1050 */     weakBlueSteel.addIngred(Global.STEEL, 19.99F, 25.01F);
/* 1051 */     AlloyManager.INSTANCE.addAlloy(weakBlueSteel);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setupArmor() {
/* 1056 */     String[] names = { "Bismuth Bronze", "Black Bronze", "Black Steel", "Blue Steel", "Bronze", "Copper", "Wrought Iron", "Red Steel", "Steel" };
/* 1057 */     String[] namesNSO = { "Brass", "Gold", "Lead", "Nickel", "Pig Iron", "Platinum", "Silver", "Sterling Silver" };
/* 1058 */     CommonProxy proxy = TerraFirmaCraft.proxy;
/* 1059 */     int i = 0;
/*      */     
/* 1061 */     bismuthSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(0)).func_77655_b("Bismuth Sheet")).setMetal("Bismuth", 200);
/* 1062 */     bismuthBronzeSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(1)).func_77655_b("Bismuth Bronze Sheet")).setMetal("Bismuth Bronze", 200);
/* 1063 */     blackBronzeSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(2)).func_77655_b("Black Bronze Sheet")).setMetal("Black Bronze", 200);
/* 1064 */     blackSteelSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(3)).func_77655_b("Black Steel Sheet")).setMetal("Black Steel", 200);
/* 1065 */     blueSteelSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(4)).func_77655_b("Blue Steel Sheet")).setMetal("Blue Steel", 200);
/* 1066 */     bronzeSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(6)).func_77655_b("Bronze Sheet")).setMetal("Bronze", 200);
/* 1067 */     copperSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(7)).func_77655_b("Copper Sheet")).setMetal("Copper", 200);
/* 1068 */     wroughtIronSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(9)).func_77655_b("Wrought Iron Sheet")).setMetal("Wrought Iron", 200);
/* 1069 */     redSteelSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(14)).func_77655_b("Red Steel Sheet")).setMetal("Red Steel", 200);
/* 1070 */     roseGoldSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(15)).func_77655_b("Rose Gold Sheet")).setMetal("Rose Gold", 200);
/* 1071 */     steelSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(17)).func_77655_b("Steel Sheet")).setMetal("Steel", 200);
/* 1072 */     tinSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(19)).func_77655_b("Tin Sheet")).setMetal("Tin", 200);
/* 1073 */     zincSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(20)).func_77655_b("Zinc Sheet")).setMetal("Zinc", 200);
/*      */     
/* 1075 */     bismuthSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(0)).func_77655_b("Bismuth Double Sheet")).setMetal("Bismuth", 400);
/* 1076 */     bismuthBronzeSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(1)).func_77655_b("Bismuth Bronze Double Sheet")).setMetal("Bismuth Bronze", 400);
/* 1077 */     blackBronzeSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(2)).func_77655_b("Black Bronze Double Sheet")).setMetal("Black Bronze", 400);
/* 1078 */     blackSteelSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(3)).func_77655_b("Black Steel Double Sheet")).setMetal("Black Steel", 400);
/* 1079 */     blueSteelSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(4)).func_77655_b("Blue Steel Double Sheet")).setMetal("Blue Steel", 400);
/* 1080 */     bronzeSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(6)).func_77655_b("Bronze Double Sheet")).setMetal("Bronze", 400);
/* 1081 */     copperSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(7)).func_77655_b("Copper Double Sheet")).setMetal("Copper", 400);
/* 1082 */     wroughtIronSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(9)).func_77655_b("Wrought Iron Double Sheet")).setMetal("Wrought Iron", 400);
/* 1083 */     redSteelSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(14)).func_77655_b("Red Steel Double Sheet")).setMetal("Red Steel", 400);
/* 1084 */     roseGoldSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(15)).func_77655_b("Rose Gold Double Sheet")).setMetal("Rose Gold", 400);
/* 1085 */     steelSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(17)).func_77655_b("Steel Double Sheet")).setMetal("Steel", 400);
/* 1086 */     tinSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(19)).func_77655_b("Tin Double Sheet")).setMetal("Tin", 400);
/* 1087 */     zincSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(20)).func_77655_b("Zinc Double Sheet")).setMetal("Zinc", 400);
/*      */     
/* 1089 */     i = 0;
/* 1090 */     brassSheet = (new ItemMetalSheet(5)).setMetal("Brass", 200).func_77655_b(namesNSO[i++] + " Sheet");
/* 1091 */     goldSheet = (new ItemMetalSheet(8)).setMetal("Gold", 200).func_77655_b(namesNSO[i++] + " Sheet");
/* 1092 */     leadSheet = (new ItemMetalSheet(10)).setMetal("Lead", 200).func_77655_b(namesNSO[i++] + " Sheet");
/* 1093 */     nickelSheet = (new ItemMetalSheet(11)).setMetal("Nickel", 200).func_77655_b(namesNSO[i++] + " Sheet");
/* 1094 */     pigIronSheet = (new ItemMetalSheet(12)).setMetal("Pig Iron", 200).func_77655_b(namesNSO[i++] + " Sheet");
/* 1095 */     platinumSheet = (new ItemMetalSheet(13)).setMetal("Platinum", 200).func_77655_b(namesNSO[i++] + " Sheet");
/* 1096 */     silverSheet = (new ItemMetalSheet(16)).setMetal("Silver", 200).func_77655_b(namesNSO[i++] + " Sheet");
/* 1097 */     sterlingSilverSheet = (new ItemMetalSheet(18)).setMetal("Sterling Silver", 200).func_77655_b(namesNSO[i++] + " Sheet");
/*      */     
/* 1099 */     i = 0;
/* 1100 */     brassSheet2x = (new ItemMetalSheet2x(5)).setMetal("Brass", 400).func_77655_b(namesNSO[i++] + " Double Sheet");
/* 1101 */     goldSheet2x = (new ItemMetalSheet2x(8)).setMetal("Gold", 400).func_77655_b(namesNSO[i++] + " Double Sheet");
/* 1102 */     leadSheet2x = (new ItemMetalSheet2x(10)).setMetal("Lead", 400).func_77655_b(namesNSO[i++] + " Double Sheet");
/* 1103 */     nickelSheet2x = (new ItemMetalSheet2x(1)).setMetal("Nickel", 400).func_77655_b(namesNSO[i++] + " Double Sheet");
/* 1104 */     pigIronSheet2x = (new ItemMetalSheet2x(12)).setMetal("Pig Iron", 400).func_77655_b(namesNSO[i++] + " Double Sheet");
/* 1105 */     platinumSheet2x = (new ItemMetalSheet2x(13)).setMetal("Platinum", 400).func_77655_b(namesNSO[i++] + " Double Sheet");
/* 1106 */     silverSheet2x = (new ItemMetalSheet2x(16)).setMetal("Silver", 400).func_77655_b(namesNSO[i++] + " Double Sheet");
/* 1107 */     sterlingSilverSheet2x = (new ItemMetalSheet2x(18)).setMetal("Sterling Silver", 400).func_77655_b(namesNSO[i++] + " Double Sheet");
/*      */     
/* 1109 */     i = 0;
/* 1110 */     bismuthBronzeUnfinishedBoots = (new ItemUnfinishedArmor()).setMetal("Bismuth Bronze", 3).func_77655_b(names[i] + " Unfinished Boots"); i++;
/* 1111 */     blackBronzeUnfinishedBoots = (new ItemUnfinishedArmor()).setMetal("Black Bronze", 3).func_77655_b(names[i] + " Unfinished Boots"); i++;
/* 1112 */     blackSteelUnfinishedBoots = (new ItemUnfinishedArmor()).setMetal("Black Steel", 3).func_77655_b(names[i] + " Unfinished Boots"); i++;
/* 1113 */     blueSteelUnfinishedBoots = (new ItemUnfinishedArmor()).setMetal("Blue Steel", 3).func_77655_b(names[i] + " Unfinished Boots"); i++;
/* 1114 */     bronzeUnfinishedBoots = (new ItemUnfinishedArmor()).setMetal("Bronze", 3).func_77655_b(names[i] + " Unfinished Boots"); i++;
/* 1115 */     copperUnfinishedBoots = (new ItemUnfinishedArmor()).setMetal("Copper", 3).func_77655_b(names[i] + " Unfinished Boots"); i++;
/* 1116 */     wroughtIronUnfinishedBoots = (new ItemUnfinishedArmor()).setMetal("Wrought Iron", 3).func_77655_b(names[i] + " Unfinished Boots"); i++;
/* 1117 */     redSteelUnfinishedBoots = (new ItemUnfinishedArmor()).setMetal("Red Steel", 3).func_77655_b(names[i] + " Unfinished Boots"); i++;
/* 1118 */     steelUnfinishedBoots = (new ItemUnfinishedArmor()).setMetal("Steel", 3).func_77655_b(names[i] + " Unfinished Boots");
/*      */     
/* 1120 */     i = 0;
/* 1121 */     bismuthBronzeBoots = (new ItemTFCArmor(Armor.bismuthBronzePlate, proxy.getArmorRenderID("bismuthbronze"), 3, 50, 0)).func_77655_b(names[i] + " Boots"); i++;
/* 1122 */     blackBronzeBoots = (new ItemTFCArmor(Armor.blackBronzePlate, proxy.getArmorRenderID("blackbronze"), 3, 50, 0)).func_77655_b(names[i] + " Boots"); i++;
/* 1123 */     blackSteelBoots = (new ItemTFCArmor(Armor.blackSteelPlate, proxy.getArmorRenderID("blacksteel"), 3, 50, 0)).func_77655_b(names[i] + " Boots"); i++;
/* 1124 */     blueSteelBoots = (new ItemTFCArmor(Armor.blueSteelPlate, proxy.getArmorRenderID("bluesteel"), 3, 50, 0)).func_77655_b(names[i] + " Boots"); i++;
/* 1125 */     bronzeBoots = (new ItemTFCArmor(Armor.bronzePlate, proxy.getArmorRenderID("bronze"), 3, 50, 0)).func_77655_b(names[i] + " Boots"); i++;
/* 1126 */     copperBoots = (new ItemTFCArmor(Armor.copperPlate, proxy.getArmorRenderID("copper"), 3, 50, 0)).func_77655_b(names[i] + " Boots"); i++;
/* 1127 */     wroughtIronBoots = (new ItemTFCArmor(Armor.wroughtIronPlate, proxy.getArmorRenderID("wroughtiron"), 3, 50, 0)).func_77655_b(names[i] + " Boots"); i++;
/* 1128 */     redSteelBoots = (new ItemTFCArmor(Armor.redSteelPlate, proxy.getArmorRenderID("redsteel"), 3, 50, 0)).func_77655_b(names[i] + " Boots"); i++;
/* 1129 */     steelBoots = (new ItemTFCArmor(Armor.steelPlate, proxy.getArmorRenderID("steel"), 3, 50, 0)).func_77655_b(names[i] + " Boots");
/*      */     
/* 1131 */     i = 0;
/* 1132 */     bismuthBronzeUnfinishedGreaves = (new ItemUnfinishedArmor()).setMetal("Bismuth Bronze", 2).func_77655_b(names[i] + " Unfinished Greaves"); i++;
/* 1133 */     blackBronzeUnfinishedGreaves = (new ItemUnfinishedArmor()).setMetal("Black Bronze", 2).func_77655_b(names[i] + " Unfinished Greaves"); i++;
/* 1134 */     blackSteelUnfinishedGreaves = (new ItemUnfinishedArmor()).setMetal("Black Steel", 2).func_77655_b(names[i] + " Unfinished Greaves"); i++;
/* 1135 */     blueSteelUnfinishedGreaves = (new ItemUnfinishedArmor()).setMetal("Blue Steel", 2).func_77655_b(names[i] + " Unfinished Greaves"); i++;
/* 1136 */     bronzeUnfinishedGreaves = (new ItemUnfinishedArmor()).setMetal("Bronze", 2).func_77655_b(names[i] + " Unfinished Greaves"); i++;
/* 1137 */     copperUnfinishedGreaves = (new ItemUnfinishedArmor()).setMetal("Copper", 2).func_77655_b(names[i] + " Unfinished Greaves"); i++;
/* 1138 */     wroughtIronUnfinishedGreaves = (new ItemUnfinishedArmor()).setMetal("Wrought Iron", 2).func_77655_b(names[i] + " Unfinished Greaves"); i++;
/* 1139 */     redSteelUnfinishedGreaves = (new ItemUnfinishedArmor()).setMetal("Red Steel", 2).func_77655_b(names[i] + " Unfinished Greaves"); i++;
/* 1140 */     steelUnfinishedGreaves = (new ItemUnfinishedArmor()).setMetal("Steel", 2).func_77655_b(names[i] + " Unfinished Greaves");
/*      */     
/* 1142 */     i = 0;
/* 1143 */     bismuthBronzeGreaves = (new ItemTFCArmor(Armor.bismuthBronzePlate, proxy.getArmorRenderID("bismuthbronze"), 2, 50, 1)).func_77655_b(names[i] + " Greaves"); i++;
/* 1144 */     blackBronzeGreaves = (new ItemTFCArmor(Armor.blackBronzePlate, proxy.getArmorRenderID("blackbronze"), 2, 50, 1)).func_77655_b(names[i] + " Greaves"); i++;
/* 1145 */     blackSteelGreaves = (new ItemTFCArmor(Armor.blackSteelPlate, proxy.getArmorRenderID("blacksteel"), 2, 50, 1)).func_77655_b(names[i] + " Greaves"); i++;
/* 1146 */     blueSteelGreaves = (new ItemTFCArmor(Armor.blueSteelPlate, proxy.getArmorRenderID("bluesteel"), 2, 50, 1)).func_77655_b(names[i] + " Greaves"); i++;
/* 1147 */     bronzeGreaves = (new ItemTFCArmor(Armor.bronzePlate, proxy.getArmorRenderID("bronze"), 2, 50, 1)).func_77655_b(names[i] + " Greaves"); i++;
/* 1148 */     copperGreaves = (new ItemTFCArmor(Armor.copperPlate, proxy.getArmorRenderID("copper"), 2, 50, 1)).func_77655_b(names[i] + " Greaves"); i++;
/* 1149 */     wroughtIronGreaves = (new ItemTFCArmor(Armor.wroughtIronPlate, proxy.getArmorRenderID("wroughtiron"), 2, 50, 1)).func_77655_b(names[i] + " Greaves"); i++;
/* 1150 */     redSteelGreaves = (new ItemTFCArmor(Armor.redSteelPlate, proxy.getArmorRenderID("redsteel"), 2, 50, 1)).func_77655_b(names[i] + " Greaves"); i++;
/* 1151 */     steelGreaves = (new ItemTFCArmor(Armor.steelPlate, proxy.getArmorRenderID("steel"), 2, 50, 1)).func_77655_b(names[i] + " Greaves");
/*      */     
/* 1153 */     i = 0;
/* 1154 */     bismuthBronzeUnfinishedChestplate = (new ItemUnfinishedArmor()).setMetal("Bismuth Bronze", 1).func_77655_b(names[i] + " Unfinished Chestplate"); i++;
/* 1155 */     blackBronzeUnfinishedChestplate = (new ItemUnfinishedArmor()).setMetal("Black Bronze", 1).func_77655_b(names[i] + " Unfinished Chestplate"); i++;
/* 1156 */     blackSteelUnfinishedChestplate = (new ItemUnfinishedArmor()).setMetal("Black Steel", 1).func_77655_b(names[i] + " Unfinished Chestplate"); i++;
/* 1157 */     blueSteelUnfinishedChestplate = (new ItemUnfinishedArmor()).setMetal("Blue Steel", 1).func_77655_b(names[i] + " Unfinished Chestplate"); i++;
/* 1158 */     bronzeUnfinishedChestplate = (new ItemUnfinishedArmor()).setMetal("Bronze", 1).func_77655_b(names[i] + " Unfinished Chestplate"); i++;
/* 1159 */     copperUnfinishedChestplate = (new ItemUnfinishedArmor()).setMetal("Copper", 1).func_77655_b(names[i] + " Unfinished Chestplate"); i++;
/* 1160 */     wroughtIronUnfinishedChestplate = (new ItemUnfinishedArmor()).setMetal("Wrought Iron", 1).func_77655_b(names[i] + " Unfinished Chestplate"); i++;
/* 1161 */     redSteelUnfinishedChestplate = (new ItemUnfinishedArmor()).setMetal("Red Steel", 1).func_77655_b(names[i] + " Unfinished Chestplate"); i++;
/* 1162 */     steelUnfinishedChestplate = (new ItemUnfinishedArmor()).setMetal("Steel", 1).func_77655_b(names[i] + " Unfinished Chestplate");
/*      */     
/* 1164 */     i = 0;
/* 1165 */     bismuthBronzeChestplate = (new ItemTFCArmor(Armor.bismuthBronzePlate, proxy.getArmorRenderID("bismuthbronze"), 1, 50, 2)).func_77655_b(names[i] + " Chestplate"); i++;
/* 1166 */     blackBronzeChestplate = (new ItemTFCArmor(Armor.blackBronzePlate, proxy.getArmorRenderID("blackbronze"), 1, 50, 2)).func_77655_b(names[i] + " Chestplate"); i++;
/* 1167 */     blackSteelChestplate = (new ItemTFCArmor(Armor.blackSteelPlate, proxy.getArmorRenderID("blacksteel"), 1, 50, 2)).func_77655_b(names[i] + " Chestplate"); i++;
/* 1168 */     blueSteelChestplate = (new ItemTFCArmor(Armor.blueSteelPlate, proxy.getArmorRenderID("bluesteel"), 1, 50, 2)).func_77655_b(names[i] + " Chestplate"); i++;
/* 1169 */     bronzeChestplate = (new ItemTFCArmor(Armor.bronzePlate, proxy.getArmorRenderID("bronze"), 1, 50, 2)).func_77655_b(names[i] + " Chestplate"); i++;
/* 1170 */     copperChestplate = (new ItemTFCArmor(Armor.copperPlate, proxy.getArmorRenderID("copper"), 1, 50, 2)).func_77655_b(names[i] + " Chestplate"); i++;
/* 1171 */     wroughtIronChestplate = (new ItemTFCArmor(Armor.wroughtIronPlate, proxy.getArmorRenderID("wroughtiron"), 1, 50, 2)).func_77655_b(names[i] + " Chestplate"); i++;
/* 1172 */     redSteelChestplate = (new ItemTFCArmor(Armor.redSteelPlate, proxy.getArmorRenderID("redsteel"), 1, 50, 2)).func_77655_b(names[i] + " Chestplate"); i++;
/* 1173 */     steelChestplate = (new ItemTFCArmor(Armor.steelPlate, proxy.getArmorRenderID("steel"), 1, 50, 2)).func_77655_b(names[i] + " Chestplate");
/*      */     
/* 1175 */     i = 0;
/* 1176 */     bismuthBronzeUnfinishedHelmet = (new ItemUnfinishedArmor()).setMetal("Bismuth Bronze", 0).func_77655_b(names[i] + " Unfinished Helmet"); i++;
/* 1177 */     blackBronzeUnfinishedHelmet = (new ItemUnfinishedArmor()).setMetal("Black Bronze", 0).func_77655_b(names[i] + " Unfinished Helmet"); i++;
/* 1178 */     blackSteelUnfinishedHelmet = (new ItemUnfinishedArmor()).setMetal("Black Steel", 0).func_77655_b(names[i] + " Unfinished Helmet"); i++;
/* 1179 */     blueSteelUnfinishedHelmet = (new ItemUnfinishedArmor()).setMetal("Blue Steel", 0).func_77655_b(names[i] + " Unfinished Helmet"); i++;
/* 1180 */     bronzeUnfinishedHelmet = (new ItemUnfinishedArmor()).setMetal("Bronze", 0).func_77655_b(names[i] + " Unfinished Helmet"); i++;
/* 1181 */     copperUnfinishedHelmet = (new ItemUnfinishedArmor()).setMetal("Copper", 0).func_77655_b(names[i] + " Unfinished Helmet"); i++;
/* 1182 */     wroughtIronUnfinishedHelmet = (new ItemUnfinishedArmor()).setMetal("Wrought Iron", 0).func_77655_b(names[i] + " Unfinished Helmet"); i++;
/* 1183 */     redSteelUnfinishedHelmet = (new ItemUnfinishedArmor()).setMetal("Red Steel", 0).func_77655_b(names[i] + " Unfinished Helmet"); i++;
/* 1184 */     steelUnfinishedHelmet = (new ItemUnfinishedArmor()).setMetal("Steel", 0).func_77655_b(names[i] + " Unfinished Helmet");
/*      */     
/* 1186 */     i = 0;
/* 1187 */     bismuthBronzeHelmet = (new ItemTFCArmor(Armor.bismuthBronzePlate, proxy.getArmorRenderID("bismuthbronze"), 0, 50, 3)).func_77655_b(names[i] + " Helmet"); i++;
/* 1188 */     blackBronzeHelmet = (new ItemTFCArmor(Armor.blackBronzePlate, proxy.getArmorRenderID("blackbronze"), 0, 50, 3)).func_77655_b(names[i] + " Helmet"); i++;
/* 1189 */     blackSteelHelmet = (new ItemTFCArmor(Armor.blackSteelPlate, proxy.getArmorRenderID("blacksteel"), 0, 50, 3)).func_77655_b(names[i] + " Helmet"); i++;
/* 1190 */     blueSteelHelmet = (new ItemTFCArmor(Armor.blueSteelPlate, proxy.getArmorRenderID("bluesteel"), 0, 50, 3)).func_77655_b(names[i] + " Helmet"); i++;
/* 1191 */     bronzeHelmet = (new ItemTFCArmor(Armor.bronzePlate, proxy.getArmorRenderID("bronze"), 0, 50, 3)).func_77655_b(names[i] + " Helmet"); i++;
/* 1192 */     copperHelmet = (new ItemTFCArmor(Armor.copperPlate, proxy.getArmorRenderID("copper"), 0, 50, 3)).func_77655_b(names[i] + " Helmet"); i++;
/* 1193 */     wroughtIronHelmet = (new ItemTFCArmor(Armor.wroughtIronPlate, proxy.getArmorRenderID("wroughtiron"), 0, 50, 3)).func_77655_b(names[i] + " Helmet"); i++;
/* 1194 */     redSteelHelmet = (new ItemTFCArmor(Armor.redSteelPlate, proxy.getArmorRenderID("redsteel"), 0, 50, 3)).func_77655_b(names[i] + " Helmet"); i++;
/* 1195 */     steelHelmet = (new ItemTFCArmor(Armor.steelPlate, proxy.getArmorRenderID("steel"), 0, 50, 3)).func_77655_b(names[i] + " Helmet");
/*      */     
/* 1197 */     leatherHelmet = (new ItemTFCArmor(Armor.leather, proxy.getArmorRenderID("leather"), 0, ItemArmor.ArmorMaterial.CLOTH, 100, 3)).func_77655_b("helmetCloth").func_111206_d("leather_helmet");
/* 1198 */     leatherChestplate = (new ItemTFCArmor(Armor.leather, proxy.getArmorRenderID("leather"), 1, ItemArmor.ArmorMaterial.CLOTH, 100, 2)).func_77655_b("chestplateCloth").func_111206_d("leather_chestplate");
/* 1199 */     leatherLeggings = (new ItemTFCArmor(Armor.leather, proxy.getArmorRenderID("leather"), 2, ItemArmor.ArmorMaterial.CLOTH, 100, 1)).func_77655_b("leggingsCloth").func_111206_d("leather_leggings");
/* 1200 */     leatherBoots = (new ItemTFCArmor(Armor.leather, proxy.getArmorRenderID("leather"), 3, ItemArmor.ArmorMaterial.CLOTH, 100, 0)).func_77655_b("bootsCloth").func_111206_d("leather_boots");
/*      */     
/* 1202 */     quiver = (new ItemQuiver()).func_77655_b("Quiver");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void registerFurnaceFuel() {
/* 1208 */     TFCFuelHandler.registerFuel(blueSteelBucketLava, 20000);
/* 1209 */     TFCFuelHandler.registerFuel(singlePlank, 400);
/* 1210 */     TFCFuelHandler.registerFuel(woodenBucketEmpty, 300);
/* 1211 */     TFCFuelHandler.registerFuel(fireStarter, 100);
/* 1212 */     TFCFuelHandler.registerFuel(logs, 800);
/* 1213 */     TFCFuelHandler.registerFuel(sluiceItem, 300);
/* 1214 */     TFCFuelHandler.registerFuel(rope, 50);
/* 1215 */     TFCFuelHandler.registerFuel(arrow, 20);
/* 1216 */     TFCFuelHandler.registerFuel(bow, 100);
/* 1217 */     TFCFuelHandler.registerFuel(fishingRod, 100);
/* 1218 */     TFCFuelHandler.registerFuel(stick, 100);
/* 1219 */     TFCFuelHandler.registerFuel(coal, 1600);
/* 1220 */     TFCFuelHandler.registerFuel(woolCloth, 20);
/* 1221 */     TFCFuelHandler.registerFuel(silkCloth, 20);
/* 1222 */     TFCFuelHandler.registerFuel(burlapCloth, 20);
/* 1223 */     TFCFuelHandler.registerFuel(straw, 20);
/*      */     
/* 1225 */     for (int l = 0; l < Recipes.doors.length; l++) {
/* 1226 */       TFCFuelHandler.registerFuel(Recipes.doors[l], 300);
/*      */     }
/* 1228 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.woodSupportV), 300);
/* 1229 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.woodSupportV2), 300);
/* 1230 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.woodSupportH), 300);
/* 1231 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.woodSupportH2), 300);
/* 1232 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.fence), 300);
/* 1233 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.fence2), 300);
/* 1234 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.fenceGate), 300);
/* 1235 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.fenceGate2), 300);
/* 1236 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.chest), 300);
/* 1237 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.strawHideBed), 200);
/* 1238 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.thatch), 200);
/* 1239 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.planks), 300);
/* 1240 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.planks2), 300);
/* 1241 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.barrel), 300);
/* 1242 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.torch), 100);
/* 1243 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.sapling), 100);
/* 1244 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.sapling2), 100);
/*      */   }
/*      */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\ItemSetup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */