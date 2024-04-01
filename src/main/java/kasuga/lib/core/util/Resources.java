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
     * 通过路径获取资源
     * @param location 资源的路径或文件夹
     * @param isFile 路径是文件还是文件夹
     * @return 资源的map路径活资源本身
     * @throws IOException 如果文件/文件夹不存在则抛出此异常
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
     * 通过绝对路径获取资源。绝对路径意味着包含资源所在的路径
     * @param location 资源的路径或文件夹
     * @param isFile 路径是文件还是文件夹
     * @return 资源的map路径活资源本身
     * @throws IOException 如果文件/文件夹不存在则抛出此异常
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
     * 从资产或数据获取资源
     * @param location 文件位置
     * @return 资源本身
     * @throws IOException 如果文件不存在则抛出此异常
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
     * 从注册键获取方块
     * @param location 方块的注册键(在生成数据时使用)
     * @return 获取到的方块本身
     */
    @Util
    public static Block getBlock(ResourceLocation location) {
        return ForgeRegistries.BLOCKS.getValue(location);
    }

    /**
     * Get item from its registration key.
     * @param location the item registration key (we usually use them in data-gen)
     * @return the item we got.
     * 从注册键获取物品
     * @param location 方块的注册键(在生成数据时使用)
     * @return 获取到的物品本身
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
