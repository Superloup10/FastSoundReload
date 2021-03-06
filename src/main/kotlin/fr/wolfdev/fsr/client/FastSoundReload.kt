package fr.wolfdev.fsr.client

import net.minecraft.client.settings.KeyBinding
import net.minecraftforge.client.settings.KeyModifier
import net.minecraftforge.fml.client.registry.ClientRegistry
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.Logger
import org.lwjgl.input.Keyboard

@Mod(modid = FastSoundReload.MODID, name = FastSoundReload.NAME, version = "@VERSION@", acceptedMinecraftVersions = "@MCVERSION@", modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter", dependencies = "required-after:forgelin;", clientSideOnly = true)
object FastSoundReload {
    const val MODID = "fsr"
    const val NAME = "FastSoundReload"
    lateinit var logger: Logger

    val SOUND_KEY = KeyBinding("key.sound", Keyboard.KEY_H, "key.categories.misc")

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        logger = event.modLog
        SOUND_KEY.setKeyModifierAndCode(KeyModifier.CONTROL, Keyboard.KEY_H)
        ClientRegistry.registerKeyBinding(SOUND_KEY)
    }
}