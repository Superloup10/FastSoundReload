package fr.wolfdev.fsr.asm.mixin.client;

import fr.wolfdev.fsr.client.FastSoundReload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.client.resource.VanillaResourceType;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = Minecraft.class)
public abstract class MixinMinecraft
{
    @Shadow
    protected abstract void debugFeedbackTranslated(String untranslatedTemplate, Object... objs);

    @Inject(method = "processKeyF3", at = @At("HEAD"), cancellable = true)
    private void onProcessKeyF3(int auxKey, CallbackInfoReturnable<Boolean> cir)
    {
        if(auxKey == FastSoundReload.INSTANCE.getSOUND_KEY().getKeyCode())
        {
            FMLClientHandler.instance().refreshResources(VanillaResourceType.SOUNDS);
            debugFeedbackTranslated("debug.reload_sound.message");
            cir.setReturnValue(true);
            cir.cancel();
        }
    }

    @Inject(method = "processKeyF3", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiNewChat;printChatMessage(Lnet/minecraft/util/text/ITextComponent;)V", ordinal = 9), locals = LocalCapture.CAPTURE_FAILHARD)
    private void onProcessKeyChat(int auxKey, CallbackInfoReturnable<Boolean> cir, GuiNewChat guiNewChat)
    {
        guiNewChat.printChatMessage(new TextComponentTranslation("debug.reload_sound.help", FastSoundReload.INSTANCE.getSOUND_KEY().getDisplayName()));
    }
}