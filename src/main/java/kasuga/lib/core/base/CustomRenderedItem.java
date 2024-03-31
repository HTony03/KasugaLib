package kasuga.lib.core.base;

import kasuga.lib.core.annos.Inner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

/**
 * This class is created for any item which needs to customize rendering.
 * Customize rendering means that we would not use the vanilla renderer to finish our render process.
 * Needs more Examples? You could google "create" for their amazing custom rendered items.
 * 这一个类是用于创建一个需要自定义渲染的物品
 * 自定义渲染意味着不适用vanilla渲染来完成渲染进程
 * 更多的“自定义渲染物品”范例可参照机械动力mod
 */
public abstract class CustomRenderedItem extends Item {

    /**
     * Use this to create your customRenderedItem.
     * @param pProperties The given itemProperties.
     * 使用这一个方法(Method)来创建的的自定义渲染物品
     * @param pProperties 给与的itemProperties
     */
    public CustomRenderedItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    @Inner
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return getCustomItemRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
            }
        });
    }

    /**
     * Override this to link to your custom renderer,
     * the renderer should be a subClass of {@link BlockEntityWithoutLevelRenderer}.
     * @param dispatcher the given render dispatcher.
     * @param modelSet the given model set.
     * @return your custom render renderer
     * 请覆写这个地方来连接你的自定义渲染
     * 渲染器应当是一个{@link BlockEntityWithoutLevelRenderer}的子类
     * @param dispatcher 给与的渲染线程调度器
     * @param modelSet 给与的model set
     * @return 你的自定义渲染器
     */
    public abstract BlockEntityWithoutLevelRenderer getCustomItemRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet modelSet);
}
