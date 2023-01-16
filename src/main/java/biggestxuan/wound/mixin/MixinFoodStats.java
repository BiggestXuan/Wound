package biggestxuan.wound.mixin;

import biggestxuan.wound.utils.MathUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.FoodStats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 *  @Author Biggest_Xuan
 *  2023/01/16
 */

@Mixin(FoodStats.class)
public abstract class MixinFoodStats {
    @Redirect(method = "tick",at = @At(value = "INVOKE",target = "Lnet/minecraft/entity/player/PlayerEntity;isHurt()Z",ordinal = 0))
    private boolean isHurt(PlayerEntity player){
        float f = player.getFoodData().getSaturationLevel() / 6.0F;
        return MathUtils.getPlayerMaxHealth(player) - player.getHealth() >= f;
    }

    @Redirect(method = "tick",at = @At(value = "INVOKE",target = "Lnet/minecraft/entity/player/PlayerEntity;isHurt()Z",ordinal = 1))
    private boolean isHurt1(PlayerEntity player){
        return MathUtils.getPlayerMaxHealth(player) - player.getHealth() >= 1;
    }
}
