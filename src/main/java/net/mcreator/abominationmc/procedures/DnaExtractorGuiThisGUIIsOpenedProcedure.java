package net.mcreator.abominationmc.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.abominationmc.AbominationmcModVariables;
import net.mcreator.abominationmc.AbominationmcModElements;
import net.mcreator.abominationmc.AbominationmcMod;

import java.util.Map;

@AbominationmcModElements.ModElement.Tag
public class DnaExtractorGuiThisGUIIsOpenedProcedure extends AbominationmcModElements.ModElement {
	public DnaExtractorGuiThisGUIIsOpenedProcedure(AbominationmcModElements instance) {
		super(instance, 15);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				AbominationmcMod.LOGGER.warn("Failed to load dependency entity for procedure DnaExtractorGuiThisGUIIsOpened!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				AbominationmcMod.LOGGER.warn("Failed to load dependency x for procedure DnaExtractorGuiThisGUIIsOpened!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				AbominationmcMod.LOGGER.warn("Failed to load dependency y for procedure DnaExtractorGuiThisGUIIsOpened!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				AbominationmcMod.LOGGER.warn("Failed to load dependency z for procedure DnaExtractorGuiThisGUIIsOpened!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		{
			double _setval = (double) x;
			entity.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.dnax = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = (double) y;
			entity.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.dnay = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = (double) z;
			entity.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.dnaz = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
