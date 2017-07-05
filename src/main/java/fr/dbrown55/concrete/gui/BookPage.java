package fr.dbrown55.concrete.gui;

import java.util.HashMap;
import java.util.Random;

import fr.dbrown55.concrete.gui.BookStringClickable.EnumClickAction;
import fr.dbrown55.concrete.gui.DrawnCraftingGrid.AnimatedDrawnCraftingGrid;
import fr.dbrown55.concrete.gui.DrawnCraftingGrid.DrawnRecipe;
import fr.dbrown55.concrete.items.ItemHandler;
import fr.dbrown55.concrete.recipes.RecipeHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class BookPage {

	private static HashMap<String, BookPage> PAGES = new HashMap<String, BookPage>();
	private static HashMap<Integer, String> INT_TO_NAME = new HashMap<Integer, String>(); 
	
	private static int LAST_USED_ID = 0;
	private int ID;
	private BookString[] textLeft;
	private BookString[] textRight;
	private DrawnCraftingGrid grid;
	
	private BookPage(BookString[] textLeftIn, BookString[] textRightIn, DrawnCraftingGrid grid) {
		this.ID = LAST_USED_ID;
		LAST_USED_ID++;
		
		if(textLeftIn != null){
			this.textLeft = textLeftIn;
		} else {
			this.textLeft = new BookString[] {};
		}
		
		if(textRightIn != null){
			this.textRight = textRightIn;
		} else {
			this.textRight = new BookString[] {};
		}
		
		this.grid = grid;
	}
	
	public static void initPages() {
		PAGES.clear();
		INT_TO_NAME.clear();
		LAST_USED_ID = 0;
		
		// Stuff that pages will need (like recipes)
		DrawnRecipe[] VANILLA_CONCRETE_RECIPES = new DrawnRecipe[16];
		for(int i = 0; i < 16; i++) {
			VANILLA_CONCRETE_RECIPES[i] = new DrawnRecipe(new ItemStack(ItemHandler.VANILLA_POWDER, 8, i), RecipeHandler.SAND, RecipeHandler.GRAVEL, RecipeHandler.SAND, RecipeHandler.GRAVEL, new ItemStack(Items.DYE, 1, 15-i), RecipeHandler.GRAVEL, RecipeHandler.SAND, RecipeHandler.GRAVEL, RecipeHandler.SAND);
		}
		
		int randColor = new Random().nextInt(16);
		
		ItemStack solidVanilla = new ItemStack(ItemHandler.VANILLA_SOLID, 1, randColor);
		ItemStack stickVanilla = new ItemStack(ItemHandler.VANILLA_STICK, 1, randColor);
		DrawnRecipe[] VANILLA_PLUS_RECIPES = new DrawnRecipe[] {
				new DrawnRecipe(new ItemStack(ItemHandler.VANILLA_STAIRS, 8, randColor), solidVanilla, null, null, solidVanilla, solidVanilla, null, solidVanilla, solidVanilla, solidVanilla),
				new DrawnRecipe(new ItemStack(ItemHandler.VANILLA_SLAB, 6, randColor), null, null, null, null, null, null, solidVanilla, solidVanilla, solidVanilla),
				new DrawnRecipe(new ItemStack(ItemHandler.VANILLA_STICK, 8, randColor), null, null, null, null, solidVanilla, null, null, solidVanilla, null),
				new DrawnRecipe(new ItemStack(ItemHandler.VANILLA_FENCE, 3, randColor), null, null, null, solidVanilla, stickVanilla, solidVanilla, solidVanilla, stickVanilla, solidVanilla),
				new DrawnRecipe(new ItemStack(ItemHandler.VANILLA_GATE, 1, randColor), null, null, null, stickVanilla, solidVanilla, stickVanilla, stickVanilla, solidVanilla, stickVanilla)
		};
		
		ItemStack powderWhite = new ItemStack(ItemHandler.VANILLA_POWDER, 1, 0);
		DrawnRecipe[] DYE_RECIPES = new DrawnRecipe[15];
		for(int i = 0; i < 15; i++) {
			DYE_RECIPES[i] = new DrawnRecipe(new ItemStack(ItemHandler.VANILLA_POWDER, 8, i+1), powderWhite, powderWhite, powderWhite, powderWhite, new ItemStack(Items.DYE, 1, 14-i), powderWhite, powderWhite, powderWhite, powderWhite);
		}
		
		ItemStack powder = new ItemStack(ItemHandler.VANILLA_POWDER, 1, randColor);
		ItemStack magma = new ItemStack(Items.MAGMA_CREAM);
		ItemStack glowdust = new ItemStack(Items.GLOWSTONE_DUST);
		ItemStack reddust = new ItemStack(Items.REDSTONE);
		DrawnRecipe[] CUSTOM_RECIPES = new DrawnRecipe[] {
			new DrawnRecipe(new ItemStack(ItemHandler.MAGMA_POWDER, 1, randColor), null, magma, null, magma, powder, magma, null, magma, null),
			new DrawnRecipe(new ItemStack(ItemHandler.GLOWSTONE_POWDER, 1, randColor), null, glowdust, null, glowdust, powder, glowdust, null, glowdust, null),
			new DrawnRecipe(new ItemStack(ItemHandler.REDSTONE_POWDER, 1, randColor), reddust, reddust, reddust, reddust, powder, reddust, reddust, reddust, reddust)
		};
		
		addPage("INTRO", null, new BookString[] {new BookString("helpBook.intro.title"), new BookString("helpBook.intro.by"), new BookString(""), new BookString("helpBook.intro.1"), new BookString("helpBook.intro.2"), new BookString("helpBook.intro.3")});
		addPage("TABLE_OF_CONTENT", new BookString[] {new BookString("helpBook.tableOfContent"), new BookString(""), new BookStringClickable("helpBook.vanilla.title", EnumClickAction.SET_BOOK_PAGE, "VANILLA"), new BookStringClickable("helpBook.vanilla+.title", EnumClickAction.SET_BOOK_PAGE, "VANILLA+"), new BookStringClickable("helpBook.dying.title", EnumClickAction.SET_BOOK_PAGE, "DYING"), new BookStringClickable("helpBook.custom.title", EnumClickAction.SET_BOOK_PAGE, "CUSTOM"), new BookStringClickable("helpBook.links.title", EnumClickAction.SET_BOOK_PAGE, "LINKS")}, null);
		addPage("VANILLA", new BookString[] {new BookString("helpBook.vanilla.title")}, new BookString[] {new BookString("helpBook.vanilla.text1"), new BookString("helpBook.vanilla.text2"), new BookString("helpBook.vanilla.text3")}, new AnimatedDrawnCraftingGrid(VANILLA_CONCRETE_RECIPES, 15));
		addPage("VANILLA+", new BookString[] {new BookString("helpBook.vanilla+.title")}, new BookString[] {new BookString("helpBook.vanilla+.text1"), new BookString("helpBook.vanilla+.text2"), new BookString("helpBook.vanilla+.text3")}, new AnimatedDrawnCraftingGrid(VANILLA_PLUS_RECIPES, 30));
		addPage("DYING", new BookString[] {new BookString("helpBook.dying.title")}, new BookString[] {new BookString("helpBook.dying.text1"), new BookString("helpBook.dying.text2"), new BookString("helpBook.dying.text3")}, new AnimatedDrawnCraftingGrid(DYE_RECIPES, 15));
		addPage("CUSTOM", new BookString[] {new BookString("helpBook.custom.title")}, new BookString[] {new BookString("helpBook.custom.text1"), new BookString("helpBook.custom.text2"), new BookString("helpBook.custom.text3"), new BookString(""), new BookString("helpBook.custom.text4"), new BookString("helpBook.custom.text5"), new BookString("helpBook.custom.text6"), new BookString("helpBook.custom.text7")}, new AnimatedDrawnCraftingGrid(CUSTOM_RECIPES, 30));
		addPage("LINKS", new BookString[] {new BookString("helpBook.links.title"), new BookString(""), new BookStringClickable("helpBook.links.youtube", EnumClickAction.OPEN_URL, "https://www.youtube.com/channel/UCUVk3BQ1l2ncfIZaSMLUNVA"), new BookStringClickable("helpBook.links.twitter", EnumClickAction.OPEN_URL, "https://twitter.com/Dbrown55_YT"), new BookStringClickable("helpBook.links.site", EnumClickAction.OPEN_URL, "http://dbrown55.ddns.net:8000")}, null);
	}
	
	private static void addPage(String name, BookString[] textLeftIn, BookString[] textRightIn) {
		addPage(name, textLeftIn, textRightIn, null);
	}
	
	private static void addPage(String name, BookString[] textLeftIn, BookString[] textRightIn, DrawnCraftingGrid grid) {
		name = name.toUpperCase();
		BookPage page = new BookPage(textLeftIn, textRightIn, grid);
		PAGES.put(name.toUpperCase(), page);
		INT_TO_NAME.put(page.ID, name);
	}
	
	public static BookPage getPageFromName(String name) {
		if(PAGES.containsKey(name)) {
			return PAGES.get(name);
		}
		return null;
	}
	
	public static BookPage getPageFromNumber(int number) {
		if(INT_TO_NAME.containsKey(number)) {
			return getPageFromName(INT_TO_NAME.get(number));
		}
		return null;
	}
	
	public static int getPageNumberFromName(String name) {
		BookPage page = getPageFromName(name);
		if(page != null) {
			return page.ID;
		}
		return -1;
	}
	
	public static int getPageCount() {
		return PAGES.size();
	}
	
	public boolean hasDrawnGrid() {
		return this.grid != null;
	}
	
	public DrawnCraftingGrid getDrawnGrid() {
		return this.grid;
	}
	
	public BookString[] getLeftPageContents() {
		return this.textLeft;
	}
	
	public BookString[] getRightPageContents() {
		return this.textRight;
	}
	
}
