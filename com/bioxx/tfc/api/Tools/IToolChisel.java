package com.bioxx.tfc.api.Tools;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface IToolChisel {
  boolean onUsed(World paramWorld, EntityPlayer paramEntityPlayer, int paramInt1, int paramInt2, int paramInt3, Block paramBlock, int paramInt4, int paramInt5, float paramFloat1, float paramFloat2, float paramFloat3);
  
  boolean canChisel(EntityPlayer paramEntityPlayer, int paramInt1, int paramInt2, int paramInt3);
}


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\api\Tools\IToolChisel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */