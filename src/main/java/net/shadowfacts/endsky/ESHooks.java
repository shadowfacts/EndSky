package net.shadowfacts.endsky;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;

/**
 * @author shadowfacts
 */
public class ESHooks {

	public static WorldProvider createWorldProvider(WorldProvider worldProvider) {
		if (worldProvider.getDimensionType() == DimensionType.OVERWORLD) {
			worldProvider.setSkyRenderer(new EndSkyRenderer());
		}
		return worldProvider;
	}

}
