package fr.wolfdev.fsr.client.event

import fr.wolfdev.fsr.client.FastSoundReload
import net.minecraft.client.Minecraft
import net.minecraft.util.text.Style
import net.minecraft.util.text.TextComponentString
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.util.text.TextFormatting
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
        if (FastSoundReload.SOUND_KEY.isPressed) {
            FMLClientHandler.instance().refreshResources(VanillaResourceType.SOUNDS)
            FMLClientHandler.instance().client.debugFeedbackTranslated("debug.reload_sound.message")
        }
    }

    @JvmStatic
    fun Minecraft.debugFeedbackTranslated(untranslatedTemplate: String) {
        this.ingameGUI.chatGUI.printChatMessage(TextComponentString("").appendSibling(TextComponentTranslation("debug.prefix").setStyle(Style().setColor(TextFormatting.YELLOW).setBold(true))).appendText(" ").appendSibling(TextComponentTranslation(untranslatedTemplate)))
    }
}