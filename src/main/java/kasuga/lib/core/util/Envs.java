package kasuga.lib.core.util;

import kasuga.lib.core.annos.Util;
import net.minecraftforge.fml.loading.FMLLoader;

/**
 * This class is a utility for environment detecting.
 * 这是一个用于运行环境检测的工具类
 */
@Util
public class Envs {

    /**
     * Dev means the environment in IDEs like IDEA, Eclipse and so on.
     * @return is we are in dev environment?
     * Dev意味着处在一个IDE的开发环境，类似IDEA，Eclipse
     * @return 是否处在开发环境中
     */
    @Util
    public static boolean isDevEnvironment() {
        return !FMLLoader.isProduction();
    }

    /**
     * Client means we are in the client game side. The client controls rendering or client ticks.
     * @return is the game a client?
     * Client意味着处在客户端环境中。客户端负责渲染与tick
     * @return 是否处在一个客户端中
     */
    @Util
    public static boolean isClient() {
        return FMLLoader.getDist().isClient();
    }

    /**
     * DedicateServer is a kind of server that used for multiplayer gaming. These servers have no client,
     * they only runs the logical side of your world.
     * @return is the game a dedicate server?
     * DedicateServer是一类专门用于多人游戏的服务器，并不运行客户端
     * 这种用户仅运行世界的逻辑处理等功能
     * @retuen 是否处在一个服务器中
     */
    @Util
    public static boolean isDedicateServer() {
        return FMLLoader.getDist().isDedicatedServer();
    }
}
