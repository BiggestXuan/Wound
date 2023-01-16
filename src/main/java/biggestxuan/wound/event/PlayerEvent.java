package biggestxuan.wound.event;

import biggestxuan.wound.Wound;
import biggestxuan.wound.capability.ModCapability;
import biggestxuan.wound.config.Config;
import biggestxuan.wound.items.ItemRegistry;
import biggestxuan.wound.network.WoundNetwork;
import biggestxuan.wound.network.WoundPacket;
import biggestxuan.wound.utils.MathUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

/**
 *  @Author Biggest_Xuan
 *  2023/01/14
 */

@Mod.EventBusSubscriber(modid = Wound.MODID)
public class PlayerEvent {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void playerTick(TickEvent.PlayerTickEvent event){
        PlayerEntity player = event.player;
        if(player.level.isClientSide || event.phase == TickEvent.Phase.END) return;
        player.getCapability(ModCapability.WOUND).ifPresent((c)->{
            c.healWound();
            if(c.getWound() > Config.maxWound.get()){
                c.setWound(Config.maxWound.get().floatValue());
            }
        });
        if(player.getHealth() > MathUtils.getPlayerMaxHealth(player)) {
            player.setHealth(MathUtils.getPlayerMaxHealth(player));
        }
        player.getCapability(ModCapability.WOUND).ifPresent(cap-> WoundNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(()->
            (ServerPlayerEntity) player),new WoundPacket(cap.getWound(),cap.getWoundResistance())
        ));
        player.inventory.items.forEach((stack)->{
            if(stack.getItem().equals(ItemRegistry.SHIELD.get())){
                if(!stack.hasTag()){
                    stack.getOrCreateTag().putFloat("damage_limit",Config.defaultShield.get().floatValue());
                }
            }
        });
    }

    @SubscribeEvent
    public static void playerHurt(LivingHurtEvent event){
        LivingEntity entity = event.getEntityLiving();
        if(entity.level.isClientSide || !(entity instanceof PlayerEntity)) return;
        PlayerEntity player = (PlayerEntity) entity;
        float damage = event.getAmount();
        for(ItemStack stack:player.inventory.items){
            if(stack.getItem().equals(ItemRegistry.SHIELD.get())){
                float canProtect = stack.getOrCreateTag().getFloat("damage_limit");
                if(damage >= canProtect){
                    stack.shrink(1);
                    event.setCanceled(true);
                    break;
                }
            }
        }
    }

    @SubscribeEvent
    public static void playerHeal(LivingHealEvent event){
        LivingEntity entity = event.getEntityLiving();
        if(entity == null || entity.level.isClientSide) return;
        if(entity instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) entity;
            float canHeal = MathUtils.getPlayerMaxHealth(player) - player.getHealth();
            event.setAmount(Math.min(canHeal,event.getAmount()));
        }
    }

    @SubscribeEvent
    public static void playerHurt(LivingDamageEvent event){
        LivingEntity entity = event.getEntityLiving();
        if(entity == null || entity.level.isClientSide || !(entity instanceof PlayerEntity)) return;
        PlayerEntity player = (PlayerEntity) entity;
        if(canCauseWound(event.getSource()) && !player.hasEffect(Effects.ABSORPTION)){
            player.getCapability(ModCapability.WOUND).ifPresent(c->{
                float amt = event.getAmount();
                float wound = amt * (1 - MathUtils.getPlayerWoundResistance(player)) * Config.woundRate.get().floatValue();
                c.addWound(wound);
            });
        }
    }

    private static boolean canCauseWound(DamageSource source){
        boolean flag = true;
        if(source.getDirectEntity() != null){
            if(source.getDirectEntity() instanceof PlayerEntity && !Config.playerSource.get()){
                flag = false;
            }
            if(source.getDirectEntity() instanceof MobEntity && !Config.mobSource.get()){
                flag = false;
            }
        }else if(source.isFire() && !Config.fireSource.get()){
            flag = false;
        }else if(source.isExplosion() && !Config.explosionSource.get()){
            flag = false;
        }else if(source.equals(DamageSource.FALL) && !Config.fallSource.get()){
            flag = false;
        }else if((source.isMagic() || source.equals(DamageSource.WITHER)) && !Config.magicSource.get()){
            flag = false;
        }else if(source.equals(DamageSource.DROWN) && !Config.drownSource.get()){
            flag = false;
        }else if(source.equals(DamageSource.OUT_OF_WORLD)){
            flag = false;
        }else{
            if(!Config.otherSource.get()){
                flag = false;
            }
        }
        return flag;
    }
}
