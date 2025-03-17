package com.bioxx.tfc.Handlers.Network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

public abstract class AbstractPacket {
  public abstract void encodeInto(ChannelHandlerContext paramChannelHandlerContext, ByteBuf paramByteBuf);
  
  public abstract void decodeInto(ChannelHandlerContext paramChannelHandlerContext, ByteBuf paramByteBuf);
  
  public abstract void handleClientSide(EntityPlayer paramEntityPlayer);
  
  public abstract void handleServerSide(EntityPlayer paramEntityPlayer);
}


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Handlers\Network\AbstractPacket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */