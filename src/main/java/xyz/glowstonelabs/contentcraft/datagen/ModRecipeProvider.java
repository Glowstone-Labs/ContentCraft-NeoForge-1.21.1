/*
 * Glowstone Labs - ContentCraft - RecipeProvider
 * 2025 Glowstone Labs
 * changelog:
 *  - initial implementation - CodeRandomMc
 */
package xyz.glowstonelabs.contentcraft.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;
import xyz.glowstonelabs.contentcraft.ContentCraft;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Provider class for generating ContentCraft mod recipes during data generation.
 * Extends Minecraft's RecipeProvider and implements IConditionBuilder for conditional recipes.
 */
public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    /**
     * Generates all recipes for the ContentCraft mod.
     * This method is called during data generation to create recipe JSON files.
     *
     * @param recipeOutput The recipe output consumer to register recipes
     */
    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        
    }


    /**
     * Registers smelting recipes for ore processing.
     *
     * @param recipeOutput The recipe output consumer to register recipes
     * @param ingredients  List of input ingredients that can be smelted
     * @param category     Recipe category the smelting belongs to
     * @param result       The resulting item from smelting
     * @param experience   Amount of experience granted when smelting completes
     * @param cookingTime  Time in ticks required to complete smelting
     * @param group        Recipe group name for recipe book organization
     */
    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, ingredients, category, result, experience, cookingTime, group, "_from_smelting");
    }

    /**
     * Registers blast furnace recipes for ore processing.
     *
     * @param recipeOutput The recipe output consumer to register recipes
     * @param ingredients  List of input ingredients that can be blasted
     * @param category     Recipe category the blasting belongs to
     * @param result       The resulting item from blasting
     * @param experience   Amount of experience granted when blasting completes
     * @param cookingTime  Time in ticks required to complete blasting
     * @param group        Recipe group name for recipe book organization
     */
    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, ingredients, category, result, experience, cookingTime, group, "_from_blasting");
    }

    /**
     * Generic method for registering cooking-type recipes (smelting, blasting, etc).
     * Creates individual recipes for each input ingredient resulting in the specified output.
     *
     * @param recipeOutput  The recipe output consumer to register recipes
     * @param serializer    The recipe serializer for the specific cooking type
     * @param recipeFactory Factory method for creating the specific recipe type
     * @param ingredients   List of input ingredients that can be cooked
     * @param category      Recipe category the cooking belongs to
     * @param result        The resulting item from cooking
     * @param experience    Amount of experience granted when cooking completes
     * @param cookingTime   Time in ticks required to complete cooking
     * @param group         Recipe group name for recipe book organization
     * @param suffix        Suffix to append to recipe names for this cooking type
     * @param <T>           The specific type of cooking recipe being created
     */
    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> serializer, AbstractCookingRecipe.Factory<T> recipeFactory, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group, String suffix) {
        for(ItemLike itemlike : ingredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), category, result, experience, cookingTime, serializer, recipeFactory).group(group).unlockedBy(getHasName(itemlike), has(itemlike)).save(recipeOutput, ContentCraft.MOD_ID + ":" + getItemName(result) + suffix + "_" + getItemName(itemlike));
        }

    }
}
