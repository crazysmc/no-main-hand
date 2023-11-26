package io.github.crazysmc.nomainhand.mixin;

import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin
{
  @Shadow
  public abstract void setRenderHand(boolean renderHand);

  @Inject(at = @At("HEAD"), method = "renderWorld")
  public void renderWorld(CallbackInfo ci)
  {
    setRenderHand(false);
  }
}