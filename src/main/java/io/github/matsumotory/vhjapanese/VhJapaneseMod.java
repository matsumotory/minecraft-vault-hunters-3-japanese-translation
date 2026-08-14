package io.github.matsumotory.vhjapanese;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(VhJapaneseMod.MOD_ID)
public class VhJapaneseMod {
    public static final String MOD_ID = "vhjapanese";
    private static final Logger LOGGER = LogUtils.getLogger();

    public VhJapaneseMod() {
        LOGGER.info("Vault Hunters 3 Japanese Translation ({}) loaded", MOD_ID);
    }
}
