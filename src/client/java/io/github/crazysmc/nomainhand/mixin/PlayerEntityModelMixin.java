package io.github.crazysmc.nomainhand.mixin;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityModel.class)
public abstract class PlayerEntityModelMixin extends BipedEntityModel<PlayerEntityRenderState>
{
  public PlayerEntityModelMixin(ModelPart modelPart)
  {
    super(modelPart);
  }

  @Inject(at = @At("TAIL"), method = "setAngles(Lnet/minecraft/client/render/entity/state/PlayerEntityRenderState;)V")
  private void setAngles(PlayerEntityRenderState playerEntityRenderState, CallbackInfo ci)
  {
    boolean leftHanded = playerEntityRenderState.mainArm == Arm.LEFT;
    if (leftHanded)
      leftArm.visible = false;
    else
      rightArm.visible = false;
  }
}
