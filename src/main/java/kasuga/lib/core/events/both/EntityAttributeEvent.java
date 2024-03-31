package kasuga.lib.core.events.both;

import kasuga.lib.KasugaLib;
import kasuga.lib.core.annos.Inner;
import kasuga.lib.registrations.registry.SimpleRegistry;
import kasuga.lib.registrations.common.EntityReg;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashSet;

@Inner
public class EntityAttributeEvent {

    /**
     * This event deal with your entity attributes like HEALTH.
     * An entity which belongs to the LivingEntity class should have "MAX_HEALTH" attribute.
     * Or your game would crash while dealing with your entity's health.
     * @param event the given forge event.
     * 这是一个处理实体属性——类似HEALTH，血量——的事件
     * 一个属于LivingEntity类的实体应当有“MAX_HEALTH”属性
     * 不然你的游戏将在处理实体血量的时候崩溃
     * @param event 给定的forge event
     */
    @SubscribeEvent
    @Inner
    public static void entityAttributeCreation(EntityAttributeCreationEvent event) {
        for(SimpleRegistry registry : KasugaLib.STACKS.getRegistries().values()) {
            HashSet<EntityReg<? extends LivingEntity>> set = registry.getCachedLivingEntities();
            set.forEach(reg -> event.put(reg.getType(), reg.getAttributeSupplier().build()));
        }
    }
}
