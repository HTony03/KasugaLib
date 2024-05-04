package kasuga.lib.registrations;

import kasuga.lib.core.annos.Mandatory;
import kasuga.lib.registrations.common.ItemReg;
import kasuga.lib.registrations.exception.RegistryElementNotPresentException;
import kasuga.lib.registrations.registry.SimpleRegistry;
import net.minecraft.CrashReport;
import net.minecraft.client.Minecraft;

/**
 * Reg is the core class for KasugaLib style registration. We use this to complete most kinds of registrations.
 * For more info, see {@link SimpleRegistry}
 * Reg是KasugaLib样式注册的核心类。我们使用这个类完成大部分类型的注册工作
 * 更多信息请见 {@link SimpleRegistry}
 */
public abstract class Reg {

    public final String registrationKey;

    public Reg(String registrationKey) {
        this.registrationKey = registrationKey;
    }

    /**
     * This method must be called after all config has been given. That means this method should be the last part of
     * any reg element. Call this, we would hand all elements in to forge and minecraft.
     * @param registry the mod SimpleRegistry.
     * @return self.
     * 这个方法应当在所有config被给予后被调用，意味着这个方法应当在所有register以后最后使用
     * 当这个方法被调用后，将把所有的元素提交到Forge与Minecraft中
     * @param registry SimpleRegistry类
     * @return 自身
     */
    @Mandatory
    public abstract Reg submit(SimpleRegistry registry);
    public abstract String getIdentifier();
    public String toString() {
        return getIdentifier() + "." + registrationKey;
    }

    public void crashOnNotPresent(Class<?> clazz, String regType, String method) {
        RegistryElementNotPresentException exception =
                RegistryElementNotPresentException.of(clazz, regType, method);
        Minecraft.crash(CrashReport.forThrowable(exception, "Oops!"));
    }
}
