package net.mcreator.abominationmc.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.container.Slot;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.BlockState;

import net.mcreator.abominationmc.item.DnaVileItem;
import net.mcreator.abominationmc.item.DnaVileFilledItem;
import net.mcreator.abominationmc.AbominationmcModVariables;
import net.mcreator.abominationmc.AbominationmcModElements;
import net.mcreator.abominationmc.AbominationmcMod;

import java.util.function.Supplier;
import java.util.Map;

@AbominationmcModElements.ModElement.Tag
public class DnaExtractorGuiWhileThisGUIIsOpenTickProcedure extends AbominationmcModElements.ModElement {
	public DnaExtractorGuiWhileThisGUIIsOpenTickProcedure(AbominationmcModElements instance) {
		super(instance, 16);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				AbominationmcMod.LOGGER.warn("Failed to load dependency entity for procedure DnaExtractorGuiWhileThisGUIIsOpenTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				AbominationmcMod.LOGGER.warn("Failed to load dependency world for procedure DnaExtractorGuiWhileThisGUIIsOpenTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if ((!((new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world,
				new BlockPos(
						(int) ((entity.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new AbominationmcModVariables.PlayerVariables())).dnax),
						(int) ((entity.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new AbominationmcModVariables.PlayerVariables())).dnay),
						(int) ((entity.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new AbominationmcModVariables.PlayerVariables())).dnaz)),
				"ScanState")) > 0))) {
			if (((new Object() {
				public ItemStack getItemStack(int sltid) {
					Entity _ent = entity;
					if (_ent instanceof ServerPlayerEntity) {
						Container _current = ((ServerPlayerEntity) _ent).openContainer;
						if (_current instanceof Supplier) {
							Object invobj = ((Supplier) _current).get();
							if (invobj instanceof Map) {
								return ((Slot) ((Map) invobj).get(sltid)).getStack();
							}
						}
					}
					return ItemStack.EMPTY;
				}
			}.getItemStack((int) (1))).getItem() == new ItemStack(DnaVileItem.block, (int) (1)).getItem())) {
				if ((!((new Object() {
					public ItemStack getItemStack(int sltid) {
						Entity _ent = entity;
						if (_ent instanceof ServerPlayerEntity) {
							Container _current = ((ServerPlayerEntity) _ent).openContainer;
							if (_current instanceof Supplier) {
								Object invobj = ((Supplier) _current).get();
								if (invobj instanceof Map) {
									return ((Slot) ((Map) invobj).get(sltid)).getStack();
								}
							}
						}
						return ItemStack.EMPTY;
					}
				}.getItemStack((int) (0))).getItem() == (ItemStack.EMPTY).getItem()))) {
					if (entity instanceof PlayerEntity) {
						Container _current = ((PlayerEntity) entity).openContainer;
						if (_current instanceof Supplier) {
							Object invobj = ((Supplier) _current).get();
							if (invobj instanceof Map) {
								ItemStack _setstack = (new Object() {
									public ItemStack getItemStack(int sltid) {
										Entity _ent = entity;
										if (_ent instanceof ServerPlayerEntity) {
											Container _current = ((ServerPlayerEntity) _ent).openContainer;
											if (_current instanceof Supplier) {
												Object invobj = ((Supplier) _current).get();
												if (invobj instanceof Map) {
													return ((Slot) ((Map) invobj).get(sltid)).getStack();
												}
											}
										}
										return ItemStack.EMPTY;
									}
								}.getItemStack((int) (2)));
								_setstack.setCount((int) 1);
								((Slot) ((Map) invobj).get((int) (2))).putStack(_setstack);
								_current.detectAndSendChanges();
							}
						}
					}
					if (!world.isRemote()) {
						BlockPos _bp = new BlockPos(
								(int) ((entity.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new AbominationmcModVariables.PlayerVariables())).dnax),
								(int) ((entity.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new AbominationmcModVariables.PlayerVariables())).dnay),
								(int) ((entity.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new AbominationmcModVariables.PlayerVariables())).dnaz));
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putDouble("ScanState", 1);
						if (world instanceof World)
							((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
					}
					new Object() {
						private int ticks = 0;
						private float waitTicks;
						private IWorld world;
						public void start(IWorld world, int waitTicks) {
							this.waitTicks = waitTicks;
							MinecraftForge.EVENT_BUS.register(this);
							this.world = world;
						}

						@SubscribeEvent
						public void tick(TickEvent.ServerTickEvent event) {
							if (event.phase == TickEvent.Phase.END) {
								this.ticks += 1;
								if (this.ticks >= this.waitTicks)
									run();
							}
						}

						private void run() {
							if (!world.isRemote()) {
								BlockPos _bp = new BlockPos(
										(int) ((entity.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY, null)
												.orElse(new AbominationmcModVariables.PlayerVariables())).dnax),
										(int) ((entity.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY, null)
												.orElse(new AbominationmcModVariables.PlayerVariables())).dnay),
										(int) ((entity.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY, null)
												.orElse(new AbominationmcModVariables.PlayerVariables())).dnaz));
								TileEntity _tileEntity = world.getTileEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_tileEntity != null)
									_tileEntity.getTileData().putDouble("ScanState", 2);
								if (world instanceof World)
									((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
							}
							if (entity instanceof PlayerEntity) {
								Container _current = ((PlayerEntity) entity).openContainer;
								if (_current instanceof Supplier) {
									Object invobj = ((Supplier) _current).get();
									if (invobj instanceof Map) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid) {
												Entity _ent = entity;
												if (_ent instanceof ServerPlayerEntity) {
													Container _current = ((ServerPlayerEntity) _ent).openContainer;
													if (_current instanceof Supplier) {
														Object invobj = ((Supplier) _current).get();
														if (invobj instanceof Map) {
															return ((Slot) ((Map) invobj).get(sltid)).getStack();
														}
													}
												}
												return ItemStack.EMPTY;
											}
										}.getItemStack((int) (2)));
										_setstack.setCount((int) 1);
										((Slot) ((Map) invobj).get((int) (2))).putStack(_setstack);
										_current.detectAndSendChanges();
									}
								}
							}
							new Object() {
								private int ticks = 0;
								private float waitTicks;
								private IWorld world;
								public void start(IWorld world, int waitTicks) {
									this.waitTicks = waitTicks;
									MinecraftForge.EVENT_BUS.register(this);
									this.world = world;
								}

								@SubscribeEvent
								public void tick(TickEvent.ServerTickEvent event) {
									if (event.phase == TickEvent.Phase.END) {
										this.ticks += 1;
										if (this.ticks >= this.waitTicks)
											run();
									}
								}

								private void run() {
									if (!world.isRemote()) {
										BlockPos _bp = new BlockPos(
												(int) ((entity.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY, null)
														.orElse(new AbominationmcModVariables.PlayerVariables())).dnax),
												(int) ((entity.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY, null)
														.orElse(new AbominationmcModVariables.PlayerVariables())).dnay),
												(int) ((entity.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY, null)
														.orElse(new AbominationmcModVariables.PlayerVariables())).dnaz));
										TileEntity _tileEntity = world.getTileEntity(_bp);
										BlockState _bs = world.getBlockState(_bp);
										if (_tileEntity != null)
											_tileEntity.getTileData().putDouble("ScanState", 3);
										if (world instanceof World)
											((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
									}
									if (entity instanceof PlayerEntity) {
										Container _current = ((PlayerEntity) entity).openContainer;
										if (_current instanceof Supplier) {
											Object invobj = ((Supplier) _current).get();
											if (invobj instanceof Map) {
												ItemStack _setstack = (new Object() {
													public ItemStack getItemStack(int sltid) {
														Entity _ent = entity;
														if (_ent instanceof ServerPlayerEntity) {
															Container _current = ((ServerPlayerEntity) _ent).openContainer;
															if (_current instanceof Supplier) {
																Object invobj = ((Supplier) _current).get();
																if (invobj instanceof Map) {
																	return ((Slot) ((Map) invobj).get(sltid)).getStack();
																}
															}
														}
														return ItemStack.EMPTY;
													}
												}.getItemStack((int) (2)));
												_setstack.setCount((int) 1);
												((Slot) ((Map) invobj).get((int) (2))).putStack(_setstack);
												_current.detectAndSendChanges();
											}
										}
									}
									new Object() {
										private int ticks = 0;
										private float waitTicks;
										private IWorld world;
										public void start(IWorld world, int waitTicks) {
											this.waitTicks = waitTicks;
											MinecraftForge.EVENT_BUS.register(this);
											this.world = world;
										}

										@SubscribeEvent
										public void tick(TickEvent.ServerTickEvent event) {
											if (event.phase == TickEvent.Phase.END) {
												this.ticks += 1;
												if (this.ticks >= this.waitTicks)
													run();
											}
										}

										private void run() {
											if (!world.isRemote()) {
												BlockPos _bp = new BlockPos(
														(int) ((entity.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY, null)
																.orElse(new AbominationmcModVariables.PlayerVariables())).dnax),
														(int) ((entity.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY, null)
																.orElse(new AbominationmcModVariables.PlayerVariables())).dnay),
														(int) ((entity.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY, null)
																.orElse(new AbominationmcModVariables.PlayerVariables())).dnaz));
												TileEntity _tileEntity = world.getTileEntity(_bp);
												BlockState _bs = world.getBlockState(_bp);
												if (_tileEntity != null)
													_tileEntity.getTileData().putDouble("ScanState", 4);
												if (world instanceof World)
													((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
											}
											if (entity instanceof PlayerEntity) {
												Container _current = ((PlayerEntity) entity).openContainer;
												if (_current instanceof Supplier) {
													Object invobj = ((Supplier) _current).get();
													if (invobj instanceof Map) {
														ItemStack _setstack = (new Object() {
															public ItemStack getItemStack(int sltid) {
																Entity _ent = entity;
																if (_ent instanceof ServerPlayerEntity) {
																	Container _current = ((ServerPlayerEntity) _ent).openContainer;
																	if (_current instanceof Supplier) {
																		Object invobj = ((Supplier) _current).get();
																		if (invobj instanceof Map) {
																			return ((Slot) ((Map) invobj).get(sltid)).getStack();
																		}
																	}
																}
																return ItemStack.EMPTY;
															}
														}.getItemStack((int) (2)));
														_setstack.setCount((int) 1);
														((Slot) ((Map) invobj).get((int) (2))).putStack(_setstack);
														_current.detectAndSendChanges();
													}
												}
											}
											new Object() {
												private int ticks = 0;
												private float waitTicks;
												private IWorld world;
												public void start(IWorld world, int waitTicks) {
													this.waitTicks = waitTicks;
													MinecraftForge.EVENT_BUS.register(this);
													this.world = world;
												}

												@SubscribeEvent
												public void tick(TickEvent.ServerTickEvent event) {
													if (event.phase == TickEvent.Phase.END) {
														this.ticks += 1;
														if (this.ticks >= this.waitTicks)
															run();
													}
												}

												private void run() {
													if (!world.isRemote()) {
														BlockPos _bp = new BlockPos(
																(int) ((entity
																		.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY, null)
																		.orElse(new AbominationmcModVariables.PlayerVariables())).dnax),
																(int) ((entity
																		.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY, null)
																		.orElse(new AbominationmcModVariables.PlayerVariables())).dnay),
																(int) ((entity
																		.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY, null)
																		.orElse(new AbominationmcModVariables.PlayerVariables())).dnaz));
														TileEntity _tileEntity = world.getTileEntity(_bp);
														BlockState _bs = world.getBlockState(_bp);
														if (_tileEntity != null)
															_tileEntity.getTileData().putDouble("ScanState", 5);
														if (world instanceof World)
															((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
													}
													if (entity instanceof PlayerEntity) {
														Container _current = ((PlayerEntity) entity).openContainer;
														if (_current instanceof Supplier) {
															Object invobj = ((Supplier) _current).get();
															if (invobj instanceof Map) {
																ItemStack _setstack = (new Object() {
																	public ItemStack getItemStack(int sltid) {
																		Entity _ent = entity;
																		if (_ent instanceof ServerPlayerEntity) {
																			Container _current = ((ServerPlayerEntity) _ent).openContainer;
																			if (_current instanceof Supplier) {
																				Object invobj = ((Supplier) _current).get();
																				if (invobj instanceof Map) {
																					return ((Slot) ((Map) invobj).get(sltid)).getStack();
																				}
																			}
																		}
																		return ItemStack.EMPTY;
																	}
																}.getItemStack((int) (2)));
																_setstack.setCount((int) 1);
																((Slot) ((Map) invobj).get((int) (2))).putStack(_setstack);
																_current.detectAndSendChanges();
															}
														}
													}
													new Object() {
														private int ticks = 0;
														private float waitTicks;
														private IWorld world;
														public void start(IWorld world, int waitTicks) {
															this.waitTicks = waitTicks;
															MinecraftForge.EVENT_BUS.register(this);
															this.world = world;
														}

														@SubscribeEvent
														public void tick(TickEvent.ServerTickEvent event) {
															if (event.phase == TickEvent.Phase.END) {
																this.ticks += 1;
																if (this.ticks >= this.waitTicks)
																	run();
															}
														}

														private void run() {
															if (!world.isRemote()) {
																BlockPos _bp = new BlockPos(
																		(int) ((entity
																				.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY,
																						null)
																				.orElse(new AbominationmcModVariables.PlayerVariables())).dnax),
																		(int) ((entity
																				.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY,
																						null)
																				.orElse(new AbominationmcModVariables.PlayerVariables())).dnay),
																		(int) ((entity
																				.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY,
																						null)
																				.orElse(new AbominationmcModVariables.PlayerVariables())).dnaz));
																TileEntity _tileEntity = world.getTileEntity(_bp);
																BlockState _bs = world.getBlockState(_bp);
																if (_tileEntity != null)
																	_tileEntity.getTileData().putDouble("ScanState", 6);
																if (world instanceof World)
																	((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
															}
															if (entity instanceof PlayerEntity) {
																Container _current = ((PlayerEntity) entity).openContainer;
																if (_current instanceof Supplier) {
																	Object invobj = ((Supplier) _current).get();
																	if (invobj instanceof Map) {
																		ItemStack _setstack = (new Object() {
																			public ItemStack getItemStack(int sltid) {
																				Entity _ent = entity;
																				if (_ent instanceof ServerPlayerEntity) {
																					Container _current = ((ServerPlayerEntity) _ent).openContainer;
																					if (_current instanceof Supplier) {
																						Object invobj = ((Supplier) _current).get();
																						if (invobj instanceof Map) {
																							return ((Slot) ((Map) invobj).get(sltid)).getStack();
																						}
																					}
																				}
																				return ItemStack.EMPTY;
																			}
																		}.getItemStack((int) (2)));
																		_setstack.setCount((int) 1);
																		((Slot) ((Map) invobj).get((int) (2))).putStack(_setstack);
																		_current.detectAndSendChanges();
																	}
																}
															}
															new Object() {
																private int ticks = 0;
																private float waitTicks;
																private IWorld world;
																public void start(IWorld world, int waitTicks) {
																	this.waitTicks = waitTicks;
																	MinecraftForge.EVENT_BUS.register(this);
																	this.world = world;
																}

																@SubscribeEvent
																public void tick(TickEvent.ServerTickEvent event) {
																	if (event.phase == TickEvent.Phase.END) {
																		this.ticks += 1;
																		if (this.ticks >= this.waitTicks)
																			run();
																	}
																}

																private void run() {
																	if (!world.isRemote()) {
																		BlockPos _bp = new BlockPos((int) ((entity
																				.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY,
																						null)
																				.orElse(new AbominationmcModVariables.PlayerVariables())).dnax),
																				(int) ((entity.getCapability(
																						AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY, null)
																						.orElse(new AbominationmcModVariables.PlayerVariables())).dnay),
																				(int) ((entity.getCapability(
																						AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY, null)
																						.orElse(new AbominationmcModVariables.PlayerVariables())).dnaz));
																		TileEntity _tileEntity = world.getTileEntity(_bp);
																		BlockState _bs = world.getBlockState(_bp);
																		if (_tileEntity != null)
																			_tileEntity.getTileData().putDouble("ScanState", 7);
																		if (world instanceof World)
																			((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
																	}
																	if (entity instanceof PlayerEntity) {
																		Container _current = ((PlayerEntity) entity).openContainer;
																		if (_current instanceof Supplier) {
																			Object invobj = ((Supplier) _current).get();
																			if (invobj instanceof Map) {
																				ItemStack _setstack = (new Object() {
																					public ItemStack getItemStack(int sltid) {
																						Entity _ent = entity;
																						if (_ent instanceof ServerPlayerEntity) {
																							Container _current = ((ServerPlayerEntity) _ent).openContainer;
																							if (_current instanceof Supplier) {
																								Object invobj = ((Supplier) _current).get();
																								if (invobj instanceof Map) {
																									return ((Slot) ((Map) invobj).get(sltid))
																											.getStack();
																								}
																							}
																						}
																						return ItemStack.EMPTY;
																					}
																				}.getItemStack((int) (2)));
																				_setstack.setCount((int) 1);
																				((Slot) ((Map) invobj).get((int) (2))).putStack(_setstack);
																				_current.detectAndSendChanges();
																			}
																		}
																	}
																	new Object() {
																		private int ticks = 0;
																		private float waitTicks;
																		private IWorld world;
																		public void start(IWorld world, int waitTicks) {
																			this.waitTicks = waitTicks;
																			MinecraftForge.EVENT_BUS.register(this);
																			this.world = world;
																		}

																		@SubscribeEvent
																		public void tick(TickEvent.ServerTickEvent event) {
																			if (event.phase == TickEvent.Phase.END) {
																				this.ticks += 1;
																				if (this.ticks >= this.waitTicks)
																					run();
																			}
																		}

																		private void run() {
																			if (!world.isRemote()) {
																				BlockPos _bp = new BlockPos((int) ((entity.getCapability(
																						AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY, null)
																						.orElse(new AbominationmcModVariables.PlayerVariables())).dnax),
																						(int) ((entity.getCapability(
																								AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY,
																								null)
																								.orElse(new AbominationmcModVariables.PlayerVariables())).dnay),
																						(int) ((entity.getCapability(
																								AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY,
																								null)
																								.orElse(new AbominationmcModVariables.PlayerVariables())).dnaz));
																				TileEntity _tileEntity = world.getTileEntity(_bp);
																				BlockState _bs = world.getBlockState(_bp);
																				if (_tileEntity != null)
																					_tileEntity.getTileData().putDouble("ScanState", 8);
																				if (world instanceof World)
																					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
																			}
																			if (entity instanceof PlayerEntity) {
																				Container _current = ((PlayerEntity) entity).openContainer;
																				if (_current instanceof Supplier) {
																					Object invobj = ((Supplier) _current).get();
																					if (invobj instanceof Map) {
																						ItemStack _setstack = (new Object() {
																							public ItemStack getItemStack(int sltid) {
																								Entity _ent = entity;
																								if (_ent instanceof ServerPlayerEntity) {
																									Container _current = ((ServerPlayerEntity) _ent).openContainer;
																									if (_current instanceof Supplier) {
																										Object invobj = ((Supplier) _current).get();
																										if (invobj instanceof Map) {
																											return ((Slot) ((Map) invobj).get(sltid))
																													.getStack();
																										}
																									}
																								}
																								return ItemStack.EMPTY;
																							}
																						}.getItemStack((int) (2)));
																						_setstack.setCount((int) 1);
																						((Slot) ((Map) invobj).get((int) (2))).putStack(_setstack);
																						_current.detectAndSendChanges();
																					}
																				}
																			}
																			new Object() {
																				private int ticks = 0;
																				private float waitTicks;
																				private IWorld world;
																				public void start(IWorld world, int waitTicks) {
																					this.waitTicks = waitTicks;
																					MinecraftForge.EVENT_BUS.register(this);
																					this.world = world;
																				}

																				@SubscribeEvent
																				public void tick(TickEvent.ServerTickEvent event) {
																					if (event.phase == TickEvent.Phase.END) {
																						this.ticks += 1;
																						if (this.ticks >= this.waitTicks)
																							run();
																					}
																				}

																				private void run() {
																					if (!world.isRemote()) {
																						BlockPos _bp = new BlockPos((int) ((entity.getCapability(
																								AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY,
																								null)
																								.orElse(new AbominationmcModVariables.PlayerVariables())).dnax),
																								(int) ((entity.getCapability(
																										AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY,
																										null)
																										.orElse(new AbominationmcModVariables.PlayerVariables())).dnay),
																								(int) ((entity.getCapability(
																										AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY,
																										null)
																										.orElse(new AbominationmcModVariables.PlayerVariables())).dnaz));
																						TileEntity _tileEntity = world.getTileEntity(_bp);
																						BlockState _bs = world.getBlockState(_bp);
																						if (_tileEntity != null)
																							_tileEntity.getTileData().putDouble("ScanState", 0);
																						if (world instanceof World)
																							((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
																					}
																					if (entity instanceof PlayerEntity) {
																						Container _current = ((PlayerEntity) entity).openContainer;
																						if (_current instanceof Supplier) {
																							Object invobj = ((Supplier) _current).get();
																							if (invobj instanceof Map) {
																								ItemStack _setstack = (new Object() {
																									public ItemStack getItemStack(int sltid) {
																										Entity _ent = entity;
																										if (_ent instanceof ServerPlayerEntity) {
																											Container _current = ((ServerPlayerEntity) _ent).openContainer;
																											if (_current instanceof Supplier) {
																												Object invobj = ((Supplier) _current)
																														.get();
																												if (invobj instanceof Map) {
																													return ((Slot) ((Map) invobj)
																															.get(sltid)).getStack();
																												}
																											}
																										}
																										return ItemStack.EMPTY;
																									}
																								}.getItemStack((int) (2)));
																								_setstack.setCount((int) 1);
																								((Slot) ((Map) invobj).get((int) (2)))
																										.putStack(_setstack);
																								_current.detectAndSendChanges();
																							}
																						}
																					}
																					if (((new Object() {
																						public ItemStack getItemStack(int sltid) {
																							Entity _ent = entity;
																							if (_ent instanceof ServerPlayerEntity) {
																								Container _current = ((ServerPlayerEntity) _ent).openContainer;
																								if (_current instanceof Supplier) {
																									Object invobj = ((Supplier) _current).get();
																									if (invobj instanceof Map) {
																										return ((Slot) ((Map) invobj).get(sltid))
																												.getStack();
																									}
																								}
																							}
																							return ItemStack.EMPTY;
																						}
																					}.getItemStack((int) (0)))
																							.getItem() == new ItemStack(Items.ROTTEN_FLESH, (int) (1))
																									.getItem())) {
																						if (entity instanceof PlayerEntity) {
																							Container _current = ((PlayerEntity) entity).openContainer;
																							if (_current instanceof Supplier) {
																								Object invobj = ((Supplier) _current).get();
																								if (invobj instanceof Map) {
																									ItemStack _setstack = new ItemStack(
																											DnaVileFilledItem.block, (int) (1));
																									_setstack.setCount((int) 1);
																									((Slot) ((Map) invobj).get((int) (2)))
																											.putStack(_setstack);
																									_current.detectAndSendChanges();
																								}
																							}
																						}
																						(new Object() {
																							public ItemStack getItemStack(int sltid) {
																								Entity _ent = entity;
																								if (_ent instanceof ServerPlayerEntity) {
																									Container _current = ((ServerPlayerEntity) _ent).openContainer;
																									if (_current instanceof Supplier) {
																										Object invobj = ((Supplier) _current).get();
																										if (invobj instanceof Map) {
																											return ((Slot) ((Map) invobj).get(sltid))
																													.getStack();
																										}
																									}
																								}
																								return ItemStack.EMPTY;
																							}
																						}.getItemStack((int) (2))).getOrCreateTag()
																								.putDouble("dnaVal", 1);
																					}
																					new Object() {
																						private int ticks = 0;
																						private float waitTicks;
																						private IWorld world;
																						public void start(IWorld world, int waitTicks) {
																							this.waitTicks = waitTicks;
																							MinecraftForge.EVENT_BUS.register(this);
																							this.world = world;
																						}

																						@SubscribeEvent
																						public void tick(TickEvent.ServerTickEvent event) {
																							if (event.phase == TickEvent.Phase.END) {
																								this.ticks += 1;
																								if (this.ticks >= this.waitTicks)
																									run();
																							}
																						}

																						private void run() {
																							{
																								Entity _ent = entity;
																								if (_ent instanceof ServerPlayerEntity) {
																									Container _current = ((ServerPlayerEntity) _ent).openContainer;
																									if (_current instanceof Supplier) {
																										Object invobj = ((Supplier) _current).get();
																										if (invobj instanceof Map) {
																											((Slot) ((Map) invobj).get((int) (0)))
																													.decrStackSize((int) (1));
																											_current.detectAndSendChanges();
																										}
																									}
																								}
																							}
																							{
																								Entity _ent = entity;
																								if (_ent instanceof ServerPlayerEntity) {
																									Container _current = ((ServerPlayerEntity) _ent).openContainer;
																									if (_current instanceof Supplier) {
																										Object invobj = ((Supplier) _current).get();
																										if (invobj instanceof Map) {
																											((Slot) ((Map) invobj).get((int) (1)))
																													.decrStackSize((int) (1));
																											_current.detectAndSendChanges();
																										}
																									}
																								}
																							}
																							if (((new Object() {
																								public ItemStack getItemStack(int sltid) {
																									Entity _ent = entity;
																									if (_ent instanceof ServerPlayerEntity) {
																										Container _current = ((ServerPlayerEntity) _ent).openContainer;
																										if (_current instanceof Supplier) {
																											Object invobj = ((Supplier) _current)
																													.get();
																											if (invobj instanceof Map) {
																												return ((Slot) ((Map) invobj)
																														.get(sltid)).getStack();
																											}
																										}
																									}
																									return ItemStack.EMPTY;
																								}
																							}.getItemStack((int) (2))).getItem() == (ItemStack.EMPTY)
																									.getItem())) {
																								if (entity instanceof PlayerEntity) {
																									Container _current = ((PlayerEntity) entity).openContainer;
																									if (_current instanceof Supplier) {
																										Object invobj = ((Supplier) _current).get();
																										if (invobj instanceof Map) {
																											ItemStack _setstack = new ItemStack(
																													DnaVileItem.block, (int) (1));
																											_setstack.setCount((int) 1);
																											((Slot) ((Map) invobj).get((int) (2)))
																													.putStack(_setstack);
																											_current.detectAndSendChanges();
																										}
																									}
																								}
																							}
																							MinecraftForge.EVENT_BUS.unregister(this);
																						}
																					}.start(world, (int) 9);
																					MinecraftForge.EVENT_BUS.unregister(this);
																				}
																			}.start(world, (int) 8);
																			MinecraftForge.EVENT_BUS.unregister(this);
																		}
																	}.start(world, (int) 7);
																	MinecraftForge.EVENT_BUS.unregister(this);
																}
															}.start(world, (int) 6);
															MinecraftForge.EVENT_BUS.unregister(this);
														}
													}.start(world, (int) 5);
													MinecraftForge.EVENT_BUS.unregister(this);
												}
											}.start(world, (int) 4);
											MinecraftForge.EVENT_BUS.unregister(this);
										}
									}.start(world, (int) 3);
									MinecraftForge.EVENT_BUS.unregister(this);
								}
							}.start(world, (int) 2);
							MinecraftForge.EVENT_BUS.unregister(this);
						}
					}.start(world, (int) 1);
				}
			} else {
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos(
							(int) ((entity.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new AbominationmcModVariables.PlayerVariables())).dnax),
							(int) ((entity.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new AbominationmcModVariables.PlayerVariables())).dnay),
							(int) ((entity.getCapability(AbominationmcModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new AbominationmcModVariables.PlayerVariables())).dnaz));
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble("ScanState", 0);
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			}
		}
	}
}
