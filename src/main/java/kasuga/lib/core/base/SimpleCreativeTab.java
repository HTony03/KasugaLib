package kasuga.lib.core.base;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

/**
 * This class is for CreativeModeTab functioning. Without this class we have to override the {@link CreativeModeTab#makeIcon()}
 * method over and over again only for apply our icons, which makes no scene.
 * 这个类是用于CreatvieModeTab(创造物品栏)工作的
 * 如果没有这一个类，我们将要在添加图标时重复覆写 {@link CreativeModeTab#makeIcon()} ，但这并不必要
 */
public class SimpleCreativeTab extends CreativeModeTab {
    public final Supplier<ItemStack> icon;

    /**
     * Use this to get a SimpleCreativeTab
     * @param label the name of your tab, usually a translation key.
     * @param icon the icon supplier. We would use this to get the icon automatically.
     * 使用这个方法(Method)来得到一个简易的创造物品栏
     * @param label 你的创造物品栏的名称，通常是一个翻译key
     * @param icon 你的创造物品栏的图标获取器，将使用这个来自动获取图标
     */
    public SimpleCreativeTab(String label, @Nonnull Supplier<ItemStack> icon) {
        super(label);
        this.icon = icon;
    }

    @Override
    public ItemStack makeIcon() {
        return icon.get();
    }
}
