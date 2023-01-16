package biggestxuan.wound.client;

import biggestxuan.wound.capability.ModCapability;
import biggestxuan.wound.network.WoundPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 *  @Author Biggest_Xuan
 *  2023/01/14
 */

@OnlyIn(Dist.CLIENT)
public class ClientHandler {
    public static void handlePacket(WoundPacket packet, Supplier<NetworkEvent.Context> ctx){
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if(player != null){
            player.getCapability(ModCapability.WOUND).ifPresent((c) -> {
                c.setWound(packet.getWound());
                c.setWoundResistance(packet.getWoundResistance());
            });
        }
    }
}
