package kasuga.lib.core.network;

import kasuga.lib.core.annos.Inner;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

/**
 * This class is designed for network packages. Network packages transmit our custom data between client and server.
 * For packages from client to server, use {@link C2SPacket}.
 * For packages from server to client, use {@link S2CPacket}
 * 这个类适用于网络数据包。网络数据包在客户端与服务器当中传输自定义数据
 * 有关从客户端传输数据包到服务器，使用 {@link C2SPacket}
 * 有关从服务器传输数据包到客户端，使用 {@link S2CPacket}
 */
public abstract class Packet {

    /**
     * This constructor is also used as decoder. While the program get data from network, it would
     * use this deserializer to create our packet.
     * @param buf the bytes we got from network.
     * 这个构造器也可以用作一个解码器。
     * 当程序从网络获取到数据时，将使用这个反序列化器来创建一个数据包
     * @param buf 从网络中获取到的数据
     */
    public Packet(FriendlyByteBuf buf) {}

    public Packet() {}

    @Inner
    abstract public boolean onReach(NetworkEvent.Context context);

    /**
     * The encoder of your packet, you must push all your data into this byte buffer.
     * @param buf the data container buffer, push your data into it.
     * 你的数据包的编码器，你需要将所有数据传输到这个缓冲区中以传输他们
     * @param buf 你的数据缓冲区，把所有的数据都传输至其中
     */
    abstract public void encode(FriendlyByteBuf buf);
}
