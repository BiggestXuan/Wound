package biggestxuan.wound.items;

import biggestxuan.wound.Wound;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;

/**
 *  @Author Biggest_Xuan
 *  2023/01/16
 */

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, Wound.MODID);

    public static final RegistryObject<Item> SHIELD = ITEM.register("heavy_shield",() -> new Item(new Item.Properties().tab(ItemGroup.TAB_COMBAT).stacksTo(4)){
        @Override
        @OnlyIn(Dist.CLIENT)
        public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
            if(p_77624_1_.hasTag()){
                float limit = p_77624_1_.getOrCreateTag().getFloat("damage_limit");
                p_77624_3_.add(new TranslationTextComponent("tooltip.heavy_shield.desc",limit));
            }
        }
    });
}
