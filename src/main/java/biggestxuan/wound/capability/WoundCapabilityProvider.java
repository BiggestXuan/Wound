package biggestxuan.wound.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 *  @Author Biggest_Xuan
 *  2023/01/14
 */

public class WoundCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundNBT> {
    private IWound wound;

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == ModCapability.WOUND ? LazyOptional.of(this::get).cast() : LazyOptional.empty();
    }

    public IWound get(){
        if(wound == null){
            wound = new WoundCapability();
        }
        return wound;
    }

    @Override
    public CompoundNBT serializeNBT() {
        return get().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        get().deserializeNBT(nbt);
    }
}
