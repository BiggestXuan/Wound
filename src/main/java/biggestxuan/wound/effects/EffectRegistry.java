package biggestxuan.wound.effects;

import biggestxuan.wound.Wound;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 *  @Author Biggest_Xuan
 *  2023/01/14
 */

@SuppressWarnings("unused")
public class EffectRegistry {
    public static final DeferredRegister<Effect> EFFECT = DeferredRegister.create(ForgeRegistries.POTIONS, Wound.MODID);

    public static final RegistryObject<Effect> WOUND_RESISTANCE = EFFECT.register("wound_resistance",WoundResistance::new);
}
