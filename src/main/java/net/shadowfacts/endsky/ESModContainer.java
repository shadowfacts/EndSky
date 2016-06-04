package net.shadowfacts.endsky;

import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.ModMetadata;

import java.util.Collections;

/**
 * @author shadowfacts
 */
public class ESModContainer extends DummyModContainer {

	private static final ModMetadata metadata = new ModMetadata();

	static {
		metadata.authorList = Collections.singletonList("");
		metadata.description = "End sky in the overworld.";
		metadata.modId = "EndSky";
		metadata.name = "EndSky";
		metadata.url = "https://github.com/shadowfacts/EndSky";
	}

	public ESModContainer() {
		super(metadata);
	}

}
