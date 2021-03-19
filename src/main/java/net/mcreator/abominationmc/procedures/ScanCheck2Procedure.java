package net.mcreator.abominationmc.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;

import net.mcreator.abominationmc.AbominationmcModElements;
import net.mcreator.abominationmc.AbominationmcMod;

import java.util.Map;

@AbominationmcModElements.ModElement.Tag
public class ScanCheck2Procedure extends AbominationmcModElements.ModElement {
	public ScanCheck2Procedure(AbominationmcModElements instance) {
		super(instance, 7);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				AbominationmcMod.LOGGER.warn("Failed to load dependency x for procedure ScanCheck2!");
			return false;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				AbominationmcMod.LOGGER.warn("Failed to load dependency y for procedure ScanCheck2!");
			return false;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				AbominationmcMod.LOGGER.warn("Failed to load dependency z for procedure ScanCheck2!");
			return false;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				AbominationmcMod.LOGGER.warn("Failed to load dependency world for procedure ScanCheck2!");
			return false;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "ScanState")) == 2)) {
			return (true);
		}
		return (false);
	}
}
