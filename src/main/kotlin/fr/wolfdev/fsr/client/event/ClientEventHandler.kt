package fr.wolfdev.fsr.client.event

import fr.wolfdev.fsr.client.FastSoundReload
import net.minecraftforge.client.resource.VanillaResourceType
import net.minecraftforge.fml.client.FMLClientHandler
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.InputEvent

@Mod.EventBusSubscriber(modid = FastSoundReload.MODID)
object ClientEventHandler {

    @JvmStatic
    @SubscribeEvent
    fun keyInput(event: InputEvent.KeyInputEvent) {
        if (FastSoundReload.SOUND_KEY.isPressed)
            FMLClientHandler.instance().refreshResources(VanillaResourceType.SOUNDS)
    }
}