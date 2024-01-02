package dev.tildejustin.lowercase.mixin;

import net.minecraft.client.font.TextRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(TextRenderer.class)
public class TextRendererMixin {
    @ModifyVariable(
            method = {
                    /* 1.19.4+ */ "drawInternal(Ljava/lang/String;FFIZLorg/joml/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/client/font/TextRenderer$TextLayerType;IIZ)I",
                    /* 1.19.3 */ "method_27529(Ljava/lang/String;FFIZLorg/joml/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider;ZIIZ)I",
                    /* 1.16.2-1.19.2 */ "method_27529(Ljava/lang/String;FFIZLnet/minecraft/class_1159;Lnet/minecraft/class_4597;ZIIZ)I"
            },
            at = @At(value = "HEAD", ordinal = 0), argsOnly = true, allow = 1)
    private String lowercase(String original) {
        return original.toLowerCase();
    }

    @Mixin(TextRenderer.Drawer.class)
    public static class DrawerMixin {
        @ModifyVariable(method = "accept", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true)
        private int lowercaseChar(int character) {
            return Character.toLowerCase(character);
        }
    }
}
