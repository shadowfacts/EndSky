package net.shadowfacts.endsky;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

/**
 * @author shadowfacts
 */
@IFMLLoadingPlugin.MCVersion("1.12")
@IFMLLoadingPlugin.Name("EndSky")
@IFMLLoadingPlugin.SortingIndex(2000)
public class ESPlugin implements IFMLLoadingPlugin {

	@Override
	public String[] getASMTransformerClass() {
		return new String[]{"net.shadowfacts.endsky.DimensionTypeTransformer"};
	}

	@Override
	public String getModContainerClass() {
		return "net.shadowfacts.endsky.ESModContainer";
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {

	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}

}
