package kasuga.lib.core.base;

import kasuga.lib.core.annos.Inner;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * This class is for SavedData. SavedData is a type of data that would be saved/load with the
 * game while the game paused, quit or load. The path of your file would be
 * ".minecraft/saves/save_name/data/resourceKey.dat".
 * All data should be serialized as nbt in order to be saved.
 * So you must prepare your SavedData class with valid serializer and deserializer method.
 * For more info, see {@link SavedData}
 * @param <T> the SavedData content Type
 * 这个类是用于SavedData的。SavedData是在游戏暂停，退出，加载时会被加载/保存的一类数据。
 * 你的文件的路径将是：
 * ".minecraft/saves/save_name/data/resourceKey.dat"
 * 所有数据应当被转化为nbt来存储
 * 所以你应当在使用SavedData类前准备好有效的序列化器和反序列化器
 * 更多信息请见{@link SavedData}
 * @param <T> SavedData内容类型
 */
public class Saved<T extends SavedData> {

    public final String resourceKey;
    @Nonnull
    Supplier<T> dataSupplier;
    @Nonnull
    LoadFunction<T> loadFunction;
    T data;

    /**
     * Use this to pack SavedData.
     * @param resourceKey The file name of your savedData.
     * @param data the data supplier, usually a constructor of your SavedData, we would use this to get your class instance.
     * @param loadFunction your constructor deserializer. it would deserialize nbt to your SavedData class.
     * 使用这个来包装SavedData
     * @param resourceKey 你的SavedData的文件名
     * @param data 你的数据提供器，通常是SavedData的构造函数，我们会使用它来获取类实例。
     * @param loadFunction 您的构造函数反序列化程序。它会将nbt反序列化为SavedData类。
     */
    public Saved(String resourceKey, @NotNull Supplier<T> data, @NotNull LoadFunction<T> loadFunction) {
        this.resourceKey = resourceKey;
        this.dataSupplier = data;
        this.loadFunction = loadFunction;
    }

    /**
     * This method could be used to load data from your disk. If there's no file in the folder. It will compute an empty
     * SavedData for you. Normally, we call this via
     * {@link net.minecraftforge.event.level.LevelEvent.Load} and
     * {@link net.minecraftforge.event.level.LevelEvent.Save}
     * @param level The ServerLevel we would use to load our file.
     * @return the loaded SavedData.
     * 这个方法用于从磁盘中加载数据。
     * 如果文件不存在，将计算并返回一个空SavedData
     * 我们通过{@link net.minecraftforge.event.level.LevelEvent.Load}，
     * {@link net.minecraftforge.event.level.LevelEvent.Save}调用
     * @param level 加载文件所使用的ServerLevel
     * @return 加载的SavedData
     */
    public T loadFromDisk(ServerLevel level) {
        data = level.getDataStorage().computeIfAbsent(this::load, dataSupplier, resourceKey);
        return data;
    }

    /**
     * This method could be used to save data to your disk. Tf there's already a file in the folder, we would overwrite it
     * with our new data. Normally, we call this via
     * {@link net.minecraftforge.event.level.LevelEvent.Save} and
     * {@link net.minecraftforge.event.level.LevelEvent.Unload}
     * @param level The ServerLevel we would use to save our file.
     * 这个方法用于从磁盘中保存数据。
     * 如果文件已经存在，将使用新的数据覆写它
     * 正常来讲，我们通过{@link net.minecraftforge.event.level.LevelEvent.Save}，
     * {@link net.minecraftforge.event.level.LevelEvent.Unload}调用
     * @param level 保存文件所使用的ServerLevel
     */
    public void saveToDisk(ServerLevel level) {
        if(data == null) return;
        data.setDirty(true);
        level.getDataStorage().set(resourceKey, data);
    }

    @Inner
    public T load(CompoundTag nbt) {
        return loadFunction.load(nbt);
    }

    /**
     * the SavedData getter
     * @return the SavedData
     * SavedData获取器
     * @return SavedData
     */
    public Optional<T> getData() {
        return Optional.of(data);
    }

    /**
     * This method is used to save additional data to the SavedData.
     * @param tag Data to be saved.
     * 这个方法是用于在存储SavedData时添加额外数据
     * @param tag 需要存储的Data
     */
    public void save(CompoundTag tag) {
        data.save(tag);
    }

    /**
     * save this SavedData to exact file.
     * @param file The data would be saved to this file.
     * 保存SavedData到外部文件
     * @param file 数据将存储到这个文件中
     */
    public void saveToFile(File file) {
        data.save(file);
    }

    /**
     * The deserializer function interface.
     * @param <T> Your SavedData contents.
     * 反序列化程序函数接口
     * @param <T> 你的SavedData内容
     */
    public interface LoadFunction<T extends SavedData> {
        T load(CompoundTag tag);
    }
}
