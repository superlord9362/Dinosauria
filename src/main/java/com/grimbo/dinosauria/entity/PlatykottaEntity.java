package com.grimbo.dinosauria.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class PlatykottaEntity extends WaterMobEntity {


    protected PlatykottaEntity(EntityType<? extends WaterMobEntity> type, World p_i48565_2_) {
        super(type, p_i48565_2_);
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public int getMaxAir() {
        return 200000000;
    }

    @Override
    public void setAir(int air) {
        super.setAir(getMaxAir());
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes()
    {
        return MobEntity.func_233666_p_().
                createMutableAttribute(Attributes.MAX_HEALTH, 2)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D);
    }


//==SOUNDS==\\

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_OCELOT_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_OCELOT_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_OCELOT_AMBIENT;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_COW_STEP, 1, 1);
    }


    //==GOALS==\\

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 0.7D));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 0.5D));
        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 3.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
    }


    @Nullable
    public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return ModEntityTypes.BALAUR.get().create(this.world);
    }


}
