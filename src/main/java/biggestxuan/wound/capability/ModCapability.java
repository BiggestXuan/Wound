package biggestxuan.wound.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

/**
 *  @Author Biggest_Xuan
 *  2023/01/14
 */

public class ModCapability {
    @CapabilityInject(IWound.class)
    public static Capability<IWound> WOUND;
}
