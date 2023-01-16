package biggestxuan.wound.event;

import biggestxuan.wound.Wound;
import biggestxuan.wound.capability.IWound;
import biggestxuan.wound.capability.ModCapability;
import biggestxuan.wound.capability.WoundCapabilityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 *  @Author Biggest_Xuan
 *  2023/01/14
 */

@Mod.EventBusSubscriber(modid = Wound.MODID)
public class CapabilityEvent {
    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof PlayerEntity){
            event.addCapability(Wound.rl("cap"),new WoundCapabilityProvider());
        }
    }

    @SubscribeEvent
    public static void playerCloned(PlayerEvent.Clone event){
        LazyOptional<IWound> oldSpeedCap = event.getOriginal().getCapability(ModCapability.WOUND);
        LazyOptional<IWound> newSpeedCap = event.getPlayer().getCapability(ModCapability.WOUND);
        if (oldSpeedCap.isPresent() && newSpeedCap.isPresent()) {
            newSpeedCap.ifPresent((newCap) -> oldSpeedCap.ifPresent((oldCap) -> newCap.deserializeNBT(oldCap.serializeNBT())));
        }
    }
}
