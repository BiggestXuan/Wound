package biggestxuan.wound;

import biggestxuan.wound.capability.IWound;
import biggestxuan.wound.config.Config;
import biggestxuan.wound.effects.EffectRegistry;
import biggestxuan.wound.items.ItemRegistry;
import biggestxuan.wound.network.WoundNetwork;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

@Mod(Wound.MODID)
public class Wound {
    public static final String MODID = "wound";
    public static final String Name = "Wound";

    private static final Logger LOGGER = LogManager.getLogger();

    public Wound() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        EffectRegistry.EFFECT.register(bus);
        ItemRegistry.ITEM.register(bus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.CONFIG);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(WoundNetwork::registerMessage);
        event.enqueueWork(() -> CapabilityManager.INSTANCE.register(
                IWound.class,
                new Capability.IStorage<IWound>() {
                    @Nullable
                    @Override
                    public INBT writeNBT(Capability<IWound> capability, IWound instance, Direction side) {
                        return null;
                    }
                    @Override
                    public void readNBT(Capability<IWound> capability, IWound instance, Direction side, INBT nbt) {}
                },
                () -> null
        ));
    }

    public static ResourceLocation rl(String value){
        return new ResourceLocation(MODID,value);
    }
}
