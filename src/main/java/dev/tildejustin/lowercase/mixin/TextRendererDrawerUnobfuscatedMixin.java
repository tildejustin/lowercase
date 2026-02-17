package dev.tildejustin.lowercase.mixin;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(targets = "net/minecraft/client/gui/Font$PreparedTextBuilder", remap = false)
public class TextRendererDrawerUnobfuscatedMixin {
    @Dynamic
    @ModifyVariable(method = "accept(ILnet/minecraft/network/chat/Style;I)Z", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true)
    private int lowercaseChar(int character) {
        return Character.toLowerCase(character);
    }
}
