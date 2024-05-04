package kasuga.lib.core.util;

import kasuga.lib.KasugaLib;
import kasuga.lib.core.annos.Inner;
import kasuga.lib.core.annos.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Util
public class Resources {

    /**
     * Get resources with their path.
     * @param location The location of file or folder.
     * @param isFile is the location pointing at a file or a folder?
     * @return the map of resource path and the resource.
     * @throws IOException if the file/folder don't exist, throw this.
     * 通过资源路径获取资源
     * @param location 文件/文件夹的路径
     * @param isFile 提供的路径是文件还是文件夹
     * @return 资源与资源的map
     * @throws IOException 如果资源不存在则抛出此错误
     */
    @Util
    public static Map<String, net.minecraft.server.packs.resources.Resource> getResources(ResourceLocation location, boolean isFile) throws IOException {
        ResourceManager rm = Minecraft.getInstance().getResourceManager();
        return innerGetResources(rm, location, isFile, false);
    }

    /**
     * Get resources with their full path. Full path means that the path contains the location path you gave.
     * @param location The location of file or folder.
     * @param isFile is the location pointing at a file or a folder?
     * @return the map of resource full path and the resource.
     * @throws IOException if the file/folder don't exist, throw this.
     * 通过绝对资源路径获取资源
     * @param location 文件/文件夹的路径
     * @param isFile 提供的路径是文件还是文件夹
     * @return 资源与资源的map
     * @throws IOException 如果资源不存在则抛出此错误
     */
    @Util
    public static Map<String, net.minecraft.server.packs.resources.Resource> getFullPathResources(ResourceLocation location, boolean isFile) throws IOException {
        ResourceManager rm = Minecraft.getInstance().getResourceManager();
        return innerGetResources(rm, location, isFile, true);
    }

    /**
     * Get single resource file from your asset or data.
     * @param location the file location.
     * @return the resource.
     * @throws IOException if the file don't exist, throw this.
     * 从asset或data获取资源文件
     * @param location 文件位置
     * @return 资源
     * @throws IOException 如果文件不存在则抛出此错误
     */
    @Util
    public static net.minecraft.server.packs.resources.Resource getResource(ResourceLocation location) throws IOException {
        ResourceManager rm = Minecraft.getInstance().getResourceManager();
        return rm.getResourceOrThrow(location);
    }

    /**
     * Get Block from its registration key.
     * @param location the block registration key (we usually use them in data-gen)
     * @return the block we got.
     * 通过注册key获取方块
     * @param location 方块注册的key(在数据生成中使用的)
     * @return 获取到的方块
     */
    @Util
    public static Block getBlock(ResourceLocation location) {
        return ForgeRegistries.BLOCKS.getValue(location);
    }

    /**
     * Get item from its registration key.
     * @param location the item registration key (we usually use them in data-gen)
     * @return the item we got.
     * 通过注册key获取物品
     * @param location 物品注册的key(在数据生成中使用的)
     * @return 获取到的物品
     */
    @Util
    public static Item getItem(ResourceLocation location) {
        return ForgeRegistries.ITEMS.getValue(location);
    }

    @Inner
    private static Map<String, net.minecraft.server.packs.resources.Resource> innerGetResources(ResourceManager rm, ResourceLocation location, boolean isFile, boolean fullyPath) throws IOException {
        if(isFile) {
            return Map.of(location.getPath(), rm.getResourceOrThrow(location));
        }
        Map<ResourceLocation, net.minecraft.server.packs.resources.Resource> resources = rm.listResources(location.getPath(),
                l -> l.getNamespace().equals(location.getNamespace()));
        HashMap<String, net.minecraft.server.packs.resources.Resource> result = new HashMap<>();
        for(ResourceLocation location1 : resources.keySet()) {
            if(fullyPath) {
                if (location1.getPath().contains("."))
                    result.put(location1.getPath(), resources.get(location1));
            } else {
                if (location1.getPath().contains("."))
                    result.put(location1.getPath().replaceAll(location.getPath() + "/", ""),
                            resources.get(location1));
            }
        }
        return result;
    }
}
