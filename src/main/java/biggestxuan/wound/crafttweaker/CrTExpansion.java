package biggestxuan.wound.crafttweaker;

import biggestxuan.wound.capability.ModCapability;
import biggestxuan.wound.utils.MathUtils;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import net.minecraft.entity.player.PlayerEntity;
import org.openzen.zencode.java.ZenCodeType;

import java.util.concurrent.atomic.AtomicReference;

/**
 *  @Author Biggest_Xuan
 *  2023/01/16
 */

@ZenRegister
@ZenCodeType.Expansion("crafttweaker.api.player.MCPlayerEntity")
@SuppressWarnings("unused")
public class CrTExpansion {
    @ZenCodeType.Method
    public static float getWound(PlayerEntity player){
        AtomicReference<Float> wound = new AtomicReference<>(0f);
        player.getCapability(ModCapability.WOUND).ifPresent(c-> wound.set(c.getWound()));
        return wound.get();
    }

    @ZenCodeType.Method
    public static void setWound(PlayerEntity player,float value){
        player.getCapability(ModCapability.WOUND).ifPresent(c-> c.setWound(value));
    }

    @ZenCodeType.Method
    public static float getWoundResistance(PlayerEntity player){
        return MathUtils.getPlayerWoundResistance(player);
    }

    @ZenCodeType.Method
    public static void setWoundResistance(PlayerEntity player,float value){
        player.getCapability(ModCapability.WOUND).ifPresent(c-> c.setWoundResistance(value));
    }

    @ZenCodeType.Method
    public static float getCapWoundResistance(PlayerEntity player){
        AtomicReference<Float> wound = new AtomicReference<>(0f);
        player.getCapability(ModCapability.WOUND).ifPresent(c-> wound.set(c.getWoundResistance()));
        return wound.get();
    }
}
