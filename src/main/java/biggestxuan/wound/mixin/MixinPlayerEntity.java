package biggestxuan.wound.mixin;

import biggestxuan.wound.utils.MathUtils;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 *  @Author Biggest_Xuan
 *  2023/01/14
 */

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity {
    @Inject(method = "isHurt",at = @At("HEAD"),cancellable = true)
    public void isHurt(CallbackInfoReturnable<Boolean> cir){
        PlayerEntity player = (PlayerEntity) (Object) this;
        cir.setReturnValue(player.getHealth() > 0 && player.getHealth() < MathUtils.getPlayerMaxHealth(player)*0.99f);
        cir.cancel();
    }
}
