package kasuga.lib.core.network;

import kasuga.lib.core.annos.Inner;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

/**
 * This packet should be sent by logical client and received by logical server.
 * It is used for your custom packets that transmit custom data from client to server.
 * To register one of this, see {@link kasuga.lib.registrations.common.ChannelReg}
 * 这个数据包应当由客户端发送，由服务器接收
 * 这个部分适用于在服务器与客户端中传输你的自定义数据包中的自定义数据
 * 如需注册一个，请见 {@link kasuga.lib.registrations.common.ChannelReg}
 */
public abstract class C2SPacket extends Packet {

    /**
     * This function is the deserializer of your packet.
     * See {@link Packet} for more constructor info.
     * @param buf data bytes you got from the network.
     * 这个函数是你数据包的反序列化器
     * 见 {@link Packet} 以获得更多有关构造器的信息
     * @param buf 从网络获取到的字节数据
     */
    public C2SPacket(FriendlyByteBuf buf) {super(buf);}

    public C2SPacket() {super();}

    @Inner
    public boolean onReach(NetworkEvent.Context context) {
        context.enqueueWork(() -> handle(context));
        return true;
    }

    /**
     * The handler of your packet. After this packet has been received by
     * logical server, we would handle this packet in this method.
     * @param context some server info, such level, player and so on.
     * 你的数据包的处理器，当这个数据包被服务器接收是，将在这个方法(Method)中处理这个包
     * @param context 一些服务器的信息，像世界(level)，玩家等
     */
    public abstract void handle(NetworkEvent.Context context);

    /**
     * The encoder of your packet. You must put all your data into this
     * byte buffer in order to transmit them.
     * @param buf the data container buffer, push your data into it.
     * 你的数据包的编码器，你需要将所有数据传输到这个缓冲区中以传输他们
     * @param buf 你的数据缓冲区，把所有的数据都传输至其中
     */
    abstract public void encode(FriendlyByteBuf buf);
}
