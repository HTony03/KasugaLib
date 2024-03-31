package kasuga.lib.core.network;

import kasuga.lib.core.annos.Inner;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

/**
 * This packet should be sent by logical server and received by logical client.
 * It is used for your custom packets that transmit custom data from server to client.
 * To register one of this, see {@link kasuga.lib.registrations.common.ChannelReg}
 * 这个数据包应当从服务器发送，由客户端接收
 * 这个部分适用于在服务器与客户端中传输你的自定义数据包中的自定义数据
 * 如需注册一个，请见 {@link kasuga.lib.registrations.common.ChannelReg}
 */
public abstract class S2CPacket extends Packet {

    /**
     * The decoder constructor of your packet. Take all your data out from the byte buffer here.
     * @param buf the received byte buffer.
     * 你的数据包的解码与构造器，从这里的缓冲区中取出所有的数据
     * @param buf 收到的数据缓冲区
     */
    public S2CPacket(FriendlyByteBuf buf) {super(buf);}

    public S2CPacket() {super();}
    @Override
    @Inner
    public boolean onReach(NetworkEvent.Context context) {
        context.enqueueWork(() -> handle(Minecraft.getInstance()));
        return true;
    }

    /**
     * The handler of your packet, the packet would be handled here.
     * @param minecraft Your minecraft client.
     * 你的数据包的处理器，数据包将在这里被处理
     * @param minecraft 你的minecraft客户端
     */
    public abstract void handle(Minecraft minecraft);

    /**
     * Push your data into the byte buffer here.
     * @param buf the data container buffer, push your data into it.
     * 将你的数据传输到这个缓冲区中
     * @param buf 你的数据缓冲区，把所有的数据都传输至其中
     */
    public abstract void encode(FriendlyByteBuf buf);
}
