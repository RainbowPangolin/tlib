package com.t2pellet.tlib;

import com.mojang.logging.LogUtils;
import com.t2pellet.tlib.client.TLibModClient;
import com.t2pellet.tlib.client.TenzinLibClient;
import com.t2pellet.tlib.services.ForgeSidedExecutor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

@Mod(TenzinLib.MODID)
@TLibMod.IMod(TenzinLib.MODID)
public class TenzinLibForge extends TLibForgeMod {
    private static final Logger LOGGER = LogUtils.getLogger();

    private static TenzinLibForge instance = null;
    public static TenzinLibForge getInstance() {
        return instance;
    }

    private Map<String, TLibForgeMod> modMap;

    @Override
    protected void initialSetup() {
        LOGGER.info("TLib initialSetup");
        instance = this;
        modMap = new HashMap<>();
    }

    @Override
    protected TLibMod getCommonMod() {
        LOGGER.info("TLib getCommonMod");
        return TenzinLib.INSTANCE;
    }

    @Override
    protected TLibModClient getClientMod() {
        LOGGER.info("TLib getClientMod");
        return TenzinLibClient.INSTANCE;
    }

    @Override
    protected void registerEvents() {
        LOGGER.info("TLib registerEvents");

        MinecraftForge.EVENT_BUS.addListener(((ForgeSidedExecutor) Services.SIDE)::onServerTick);
    }

    public void register(String id, TLibForgeMod mod) {
        modMap.put(id, mod);
    }

    public TLibForgeMod get(String modid) {
        return modMap.get(modid);
    }
}
