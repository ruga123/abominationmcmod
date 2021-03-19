package net.mcreator.abominationmc.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.abominationmc.AbominationmcModElements;
import net.mcreator.abominationmc.AbominationmcMod;

import java.util.Map;

@AbominationmcModElements.ModElement.Tag
public class DetectionMovementProcedure extends AbominationmcModElements.ModElement {
	public DetectionMovementProcedure(AbominationmcModElements instance) {
		super(instance, 2);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				AbominationmcMod.LOGGER.warn("Failed to load dependency entity for procedure DetectionMovement!");
			return false;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				AbominationmcMod.LOGGER.warn("Failed to load dependency x for procedure DetectionMovement!");
			return false;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				AbominationmcMod.LOGGER.warn("Failed to load dependency y for procedure DetectionMovement!");
			return false;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				AbominationmcMod.LOGGER.warn("Failed to load dependency z for procedure DetectionMovement!");
			return false;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				AbominationmcMod.LOGGER.warn("Failed to load dependency world for procedure DetectionMovement!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((!(entity.getPersistentData().getBoolean("Blind")))) {
			return (true);
		}
		if ((!(entity.isSneaking()))) {
			if ((!(((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.AIR.getDefaultState().getBlock())
					|| (BlockTags.getCollection().getTagByID(new ResourceLocation(("abominationmc:no_sound").toLowerCase(java.util.Locale.ENGLISH)))
							.contains((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock()))))) {
				if ((((entity.getMotion().getZ()) > 0) || (((entity.getMotion().getY()) > 0) || ((entity.getMotion().getX()) > 0)))) {
					return (true);
				}
			}
		}
		return (false);
	}
}
