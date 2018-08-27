package fr.wolfdev.fsr.client.mixin

import fr.wolfdev.fsr.client.FastSoundReload
import net.minecraft.client.Minecraft
import net.minecraftforge.client.resource.VanillaResourceType
import net.minecraftforge.fml.client.FMLClientHandler
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

@Mixin(Minecraft::class)
class MixinMinecraft {

    @Inject(method = ["processKeyF3"], at = [At("TAIL")])
    private fun onProcessKeyF3(ci: CallbackInfo): Boolean {
        if (FastSoundReload.SOUND_KEY.isPressed) {
            FMLClientHandler.instance().refreshResources(VanillaResourceType.SOUNDS)
            return true
        }
        return false
    }
}