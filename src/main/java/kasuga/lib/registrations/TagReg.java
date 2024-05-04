package kasuga.lib.registrations;

import kasuga.lib.registrations.Reg;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

/**
 * This is the base class of KasugaLib style Tag Registration.
 * Tag is a kind of data-gen element. It marks some static attribute of blocks or items.
 * For example, all blocks which could be harvested by pickaxe should have a tag "minecraft:mineable/pickaxe"
 * @param <T> Tag type you would like to register.
 * 这是Kasugalib类型注册的基类。
 * 标签是一类数据生成元素。它标记着方块或物品的静态属性
 * 举个例子，所有可被稿子挖掘的方块都含有"minecraft:mineable/pixkaxe"这一标签
 * @param <T> 你希望注册的标签类型
 */
public abstract class TagReg<T> extends Reg {

    public ResourceLocation location = null;
    public TagReg(String registrationKey) {
        super(registrationKey);
    }
    public abstract TagKey<T> tag();

    /**
     * get resource location of the tag.
     * @return the location.
     * 获取标签的位置
     * @return 位置
     */
    public ResourceLocation location() {return location;}
}
