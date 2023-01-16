package biggestxuan.wound.network;

import biggestxuan.wound.Wound;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

/**
 *  @Author Biggest_Xuan
 *  2023/01/14
 */

public class WoundNetwork {
    public static SimpleChannel INSTANCE;
    public static final String name = Wound.MODID;
    private static int id = 0;

    public static void registerMessage(){
        INSTANCE = NetworkRegistry.newSimpleChannel(
                Wound.rl("wound"),
                () -> name,
                (i) -> i.equals(name),
                (i) -> i.equals(name)
        );

        INSTANCE.messageBuilder(WoundPacket.class,id++)
                .encoder(WoundPacket::encode)
                .decoder(WoundPacket::new)
                .consumer(WoundPacket::handle)
                .add();
    }
}
