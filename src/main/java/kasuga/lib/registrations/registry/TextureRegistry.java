package kasuga.lib.registrations.registry;

import kasuga.lib.core.annos.Inner;
import kasuga.lib.core.client.render.texture.SimpleTexture;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.HashSet;

/**
 * TextureRegistry is registry for KasugaLib style textures. We provide {@link SimpleTexture}
 * and {@link kasuga.lib.core.client.render.texture.WorldTexture} for quick Texture usage.
 * TextuerRegistry是Kasugalib类型注册贴图使用的类。
 * 我们提供{@link SimpleTexture}与{@link kasuga.lib.core.client.render.texture.WorldTexture}以快速使用贴图。
 */
@Inner
public class TextureRegistry {
    private final HashSet<SimpleTexture> UNREGED;
    private final HashMap<ResourceLocation, SimpleTexture> PICTURES;

    public TextureRegistry(String namespace) {
        UNREGED = new HashSet<>();
        PICTURES = new HashMap<>();
    }

    @Inner
    public void stackIn(SimpleTexture pic) {
        this.UNREGED.add(pic);
    }

    @Inner
    public HashSet<SimpleTexture> getUnregistered() {
        return UNREGED;
    }

    @Inner
    public void clearUnregistered() {
        UNREGED.clear();
    }

    /**
     * get texture from this registry.
     * @param location the location of the texture.
     * @return texture.
     * 通过注册名获取贴图
     * @param location 贴图的位置
     * @return 贴图
     */
    public SimpleTexture getTexture(ResourceLocation location) {
        return PICTURES.getOrDefault(location, null);
    }

    /**
     * get all registered pics.
     * @return registered pics.
     * 获取所有注册的图片
     * @return 注册的图片
     */
    public HashMap<ResourceLocation, SimpleTexture> getPictures() {
        return PICTURES;
    }

    /**
     * this is a event fired in {@link kasuga.lib.core.events.client.TextureRegistryEvent}
     * 这是在{@link kasuga.lib.core.events.client.TextureRegistryEvent}中被清除的事件。
     */
    @Inner
    public void onRegister() {
        for(SimpleTexture picture : getUnregistered()) {
            picture.uploadPicture(picture.getLocation());
            PICTURES.put(picture.getLocation(), picture);
        }
        UNREGED.clear();
    }
}
