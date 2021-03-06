package fluxedtrinkets.api;

import java.util.List;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.config.Property;

public abstract class BaseEffect implements IEffect {

	private String name;
	private Property usage;

	public BaseEffect(String name, Property usage) {
		this.name = name;
		this.usage = usage;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getUsage() {
		return usage.getInt();
	}

	@Override
	public abstract int onWornTick(ItemStack stack, EntityLivingBase entity, ITrinket item);

	@Override
	public void onEquipped(ItemStack stack, EntityLivingBase entity, ITrinket item) {
		;
	}

	@Override
	public void onUnequipped(ItemStack stack, EntityLivingBase entity, ITrinket item) {
		;
	}

	@Override
	public boolean canEquip(ItemStack stack, EntityLivingBase entity, ITrinket item) {
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack stack, EntityLivingBase entity, ITrinket item) {
		return true;
	}

	public boolean hasEnergy(ITrinket item, ItemStack stack, int amount) {
		return item.getEnergyStored(stack) > amount;
	}

	public Vec3 getPos(EntityLivingBase entity) {
		return Vec3.createVectorHelper(entity.posX, entity.posY, entity.posZ);
	}

	public AxisAlignedBB getBBSurrounding(EntityLivingBase entity, double range) {
		Vec3 pos = getPos(entity);
		return AxisAlignedBB.getBoundingBox(pos.xCoord - range, pos.yCoord - range, pos.zCoord - range, pos.xCoord + range, pos.yCoord + range, pos.zCoord + range);
	}

	@SuppressWarnings("unchecked")
	public <T extends Entity> List<T> getEntitiesAround(EntityLivingBase entity, double range, final Class<? extends T> clazz) {
		AxisAlignedBB bb = getBBSurrounding(entity, range);
		return entity.worldObj.getEntitiesWithinAABBExcludingEntity(entity, bb, new IEntitySelector() {

			@Override
			public boolean isEntityApplicable(Entity entity) {
				return clazz.isAssignableFrom(entity.getClass());
			}
		});
	}

	public List<Entity> getEntitiesAround(EntityLivingBase entity, double range) {
		return getEntitiesAround(entity, range, Entity.class);
	}

	@Override
	public void onPowerEmpty(ItemStack stack, EntityLivingBase entity, ITrinket item) {
		;
	}

}
