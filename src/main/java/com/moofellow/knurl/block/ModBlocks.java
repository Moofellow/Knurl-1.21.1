package com.moofellow.knurl.block;

import com.moofellow.knurl.Knurl;
import com.moofellow.knurl.item.ModItems;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Knurl.MODID);


    public static final DeferredBlock<Block> TEAK_LOG = registerBlock("teak_log", () -> new ModLogBlock(BlockBehaviour.Properties.of().mapColor((blockState) -> blockState.getValue(ModLogBlock.AXIS) == Direction.Axis.Y ? MapColor.COLOR_BROWN : MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASS).strength(2.0f).sound(SoundType.WOOD).ignitedByLava()));
    public static final DeferredBlock<Block> STRIPPED_TEAK_LOG = registerBlock("teak_log", () -> new ModLogBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASS).strength(2.0f).sound(SoundType.WOOD).ignitedByLava()));
    public static final DeferredBlock<Block> TEAK_PLANKS = registerBlock("teak_planks", () -> new ModWoodBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASS).strength(2.0f, 3.0f).sound(SoundType.WOOD).ignitedByLava()));

    private static BlockBehaviour.Properties registerLog(MapColor sides, MapColor top, SoundType sound) {
        return BlockBehaviour.Properties.of();
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
