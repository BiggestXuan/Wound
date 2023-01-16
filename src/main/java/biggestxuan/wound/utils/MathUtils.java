package biggestxuan.wound.utils;

import biggestxuan.wound.capability.IWound;
import biggestxuan.wound.capability.ModCapability;
import biggestxuan.wound.config.Config;
import biggestxuan.wound.effects.EffectRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.server.MinecraftServer;

import javax.annotation.Nonnull;

/**
 *  @Author Biggest_Xuan
 *  2023/01/14
 */

public class MathUtils {
    public static float getPlayerMaxHealth(@Nonnull PlayerEntity player){
        IWound cap = player.getCapability(ModCapability.WOUND).orElseThrow(NullPointerException::new);
        return Math.max(player.getMaxHealth() - cap.getWound(),0.5f);
    }

    public static float getPlayerWoundResistance(@Nonnull PlayerEntity player){
        double res = 0d;
        MinecraftServer server = player.getServer();
        if(server != null){
            if(server.isHardcore()){
                res = Config.woundResistanceHardCore.get();
            }else{
                switch (server.overworld().getDifficulty()){
                    case PEACEFUL:
                        res = Config.woundResistancePeaceful.get();
                        break;
                    case EASY:
                        res = Config.woundResistanceEasy.get();
                        break;
                    case NORMAL:
                        res = Config.woundResistanceNormal.get();
                        break;
                    case HARD:
                        res = Config.woundResistanceHard.get();
                        break;
                }
            }
        }
        EffectInstance instance = player.getEffect(EffectRegistry.WOUND_RESISTANCE.get());
        if(instance != null){
            res += (instance.getAmplifier()+1)*0.1;
        }
        res += player.getCapability(ModCapability.WOUND).orElseThrow(NullPointerException::new).getWoundResistance();
        return (float) Math.min(1,res);
    }
}
