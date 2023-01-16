package biggestxuan.wound.network;

import biggestxuan.wound.client.ClientHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 *  @Author Biggest_Xuan
 *  2023/01/14
 */

public class WoundPacket {
    private final float wound;
    private final float woundResistance;

    public WoundPacket(float Wound,float WoundResistance){
        this.wound = Wound;
        this.woundResistance = WoundResistance;
    }

    public WoundPacket(PacketBuffer buf){
        wound = buf.readFloat();
        woundResistance = buf.readFloat();
    }

    public void encode(PacketBuffer buf){
        buf.writeFloat(wound);
        buf.writeFloat(woundResistance);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx){
        NetworkEvent.Context c = ctx.get();
        c.enqueueWork(()-> DistExecutor.unsafeRunWhenOn(Dist.CLIENT,()->()-> ClientHandler.handlePacket(this,ctx)));
        c.setPacketHandled(true);
    }

    public float getWound() {
        return wound;
    }

    public float getWoundResistance() {
        return woundResistance;
    }
}
