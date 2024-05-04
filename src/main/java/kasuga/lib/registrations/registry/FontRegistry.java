package kasuga.lib.registrations.registry;

import kasuga.lib.core.annos.Inner;
import kasuga.lib.core.client.render.component.Font;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;

/**
 * This is the registry of custom fonts. You could register TTF fonts (.ttf) or bitmap fonts (jsons and bins).
 * You must prepare your font under namespace:font folder. For example, if you have a
 * font file called example.ttf under namespace:font folder, then please create a ResourceLocation with path
 * "namespace:example.ttf". Pay attention that you shouldn't type "font/" in your location path as the Minecraft
 * pathFinder would fill it automatically. For more info, please see {@link kasuga.lib.core.client.render.component.Font}
 * and {@link net.minecraft.network.chat.Style}.
 * 这是个性化字体的注册类。你可以注册TTF字体(.ttf)或位图字体(jsons和bins)。
 * 你必须在你的namespace:font文件夹下准备你的字体
 * 例如，你有一个example.ttf的字体文件，放置在namespace:font文件夹下。
 * 请创建一个ResourceLocation，使用"namespace:example.ttf".
 * 注意：你不应当在路径中输入"font/"，Minecraft的pathFinder会自动找打它。
 * 更多信息请见{@link kasuga.lib.core.client.render.component.Font}，
 * {@link net.minecraft.network.chat.Style}。
 */
@Inner
public class FontRegistry {

    private final String namespace;
    @Inner
    private final HashMap<ResourceLocation, Font> listOfReg;

    @Inner
    public FontRegistry(String namespace) {
        this.namespace = namespace;
        this.listOfReg = new HashMap<>();
    }

    @Inner
    public void stackIn(Font reg) {
        listOfReg.put(reg.getLocation(), reg);
    }

    @Inner
    public void onRegister() {
        for(ResourceLocation location : listOfReg.keySet()) {
            listOfReg.get(location).loadFont();
        }
    }

    /**
     * returns the registered Font
     * @param location the font location
     * @return registered font.
     * 返回注册的字体
     * @param location 字体的位置
     * @return 注册的字体
     */
    public Font getFont(ResourceLocation location) {
        return listOfReg.getOrDefault(location, null);
    }
}
