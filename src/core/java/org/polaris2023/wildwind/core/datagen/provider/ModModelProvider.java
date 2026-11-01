package org.polaris2023.wildwind.core.datagen.provider;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.data.PackOutput;
import org.polaris2023.wildwind.core.WildWindCore;
import org.polaris2023.wildwind.core.block.ModBlocks;
import org.polaris2023.wildwind.core.block.ModWoodFamilies;
import org.polaris2023.wildwind.core.block.WoodFamily;
import org.polaris2023.wildwind.core.registry.ModItems;

/**
 * 核心模块的模型生成器。
 */
public class ModModelProvider extends ModelProvider {

    public ModModelProvider(PackOutput output) {
        super(output, WildWindCore.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        for (WoodFamily family : ModWoodFamilies.ALL) {
            registerWoodModels(blockModels, family);
        }

        blockModels.createTrivialCube(ModBlocks.FLETCHING_TABLE.get());

        blockModels.createCrossBlockWithDefaultItem(ModBlocks.ROSE.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlockWithDefaultItem(ModBlocks.SOUL_ROSE.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createDoublePlantWithDefaultItem(ModBlocks.SOUL_ROSE_BUSH.get(), BlockModelGenerators.PlantType.NOT_TINTED);

        itemModels.generateFlatItem(ModItems.SPIDERLING_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
    }

    private void registerWoodModels(BlockModelGenerators blockModels, WoodFamily family) {
        blockModels.woodProvider(family.log().get()).logWithHorizontal(family.log().get()).wood(family.wood().get());
        blockModels.woodProvider(family.strippedLog().get())
                .logWithHorizontal(family.strippedLog().get())
                .wood(family.strippedWood().get());
        blockModels.family(family.planks().get())
                .stairs(family.stairs().get())
                .slab(family.slab().get())
                .fence(family.fence().get())
                .fenceGate(family.fenceGate().get())
                .pressurePlate(family.pressurePlate().get())
                .button(family.button().get());
        blockModels.createDoor(family.door().get());
        blockModels.createTrapdoor(family.trapdoor().get());
        if (family.hasLeaves()) {
            blockModels.createTintedLeaves(family.leaves().get(), TexturedModel.LEAVES, family.leavesTint());
        }
    }
}
