package biggestxuan.wound.capability;

import biggestxuan.wound.config.Config;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

/**
 *  @Author Biggest_Xuan
 *  2023/01/14
 */

public class WoundCapability implements IWound{
    private float wound;
    private float woundResistance;

    public WoundCapability(){
        wound = 0;
        woundResistance = 0;
    }

    @Override
    public float getWound() {
        return wound;
    }

    @Override
    public void setWound(float value) {
        wound = value;
    }

    @Override
    public void healWound() {
        if (getWound() > 0){
            setWound((float) (getWound()- Config.woundNaturalHeal.get()));
        }else{
            setWound(0f);
        }
    }

    @Override
    public void addWound(float value) {
        wound += value;
    }

    @Override
    public float getWoundResistance() {
        return woundResistance;
    }

    @Override
    public void setWoundResistance(float value) {
        woundResistance = value;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putFloat("wound",wound);
        nbt.putFloat("woundResistance",woundResistance);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        wound = nbt.getFloat("wound");
        woundResistance = nbt.getFloat("woundResistance");
    }
}
