package biggestxuan.wound.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

/**
 *  @Author Biggest_Xuan
 *  2023/01/14
 */

public interface IWound extends INBTSerializable<CompoundNBT> {
    float getWound();

    void setWound(float value);

    void healWound();

    void addWound(float value);

    float getWoundResistance();

    void setWoundResistance(float value);
}
