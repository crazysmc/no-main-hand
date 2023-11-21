package io.github.crazysmc.mixin.client;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin
    extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>>
{
  public PlayerEntityRendererMixin(EntityRendererFactory.Context ctx, PlayerEntityModel<AbstractClientPlayerEntity> model, float shadowRadius)
  {
    super(ctx, model, shadowRadius);
  }

  @Inject(at = @At("TAIL"), method = "setModelPose")
  private void setModelPose(AbstractClientPlayerEntity player, CallbackInfo ci)
  {
    if (player.isSpectator())
      return;
    boolean leftHanded = player.getMainArm() == Arm.LEFT;
    model.leftArm.visible = !leftHanded;
    model.rightArm.visible = leftHanded;
  }
}
