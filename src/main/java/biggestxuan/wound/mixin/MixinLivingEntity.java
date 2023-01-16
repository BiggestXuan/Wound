package biggestxuan.wound.mixin;

import biggestxuan.wound.effects.EffectRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 *  @Author Biggest_Xuan
 *  2023/01/16
 */

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    @Inject(method = "eat",at = @At(value = "INVOKE",target = "Lnet/minecraft/entity/LivingEntity;addEatEffect(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;)V"))
    public void eat(World p_213357_1_, ItemStack p_213357_2_, CallbackInfoReturnable<ItemStack> cir){
        LivingEntity living = (LivingEntity) (Object)this;
        if(p_213357_2_.getItem().equals(Items.GOLDEN_APPLE)){
            living.addEffect(new EffectInstance(EffectRegistry.WOUND_RESISTANCE.get(),600,0));
        }
        if(p_213357_2_.getItem().equals(Items.ENCHANTED_GOLDEN_APPLE)){
            living.addEffect(new EffectInstance(EffectRegistry.WOUND_RESISTANCE.get(),2400,2));
        }
    }
}
