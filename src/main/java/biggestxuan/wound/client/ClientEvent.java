package biggestxuan.wound.client;

import biggestxuan.wound.Wound;
import biggestxuan.wound.capability.ModCapability;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 *  @Author Biggest_Xuan
 *  2023/01/16
 */

@Mod.EventBusSubscriber(modid = Wound.MODID)
@OnlyIn(Dist.CLIENT)
public class ClientEvent {
    //@SubscribeEvent
    public static void tick(TickEvent.ClientTickEvent event){
        if(Minecraft.getInstance().player == null) return;
        Minecraft.getInstance().player.getCapability(ModCapability.WOUND).ifPresent(c->{
            System.out.println(c.getWound());
        });
    }
}
