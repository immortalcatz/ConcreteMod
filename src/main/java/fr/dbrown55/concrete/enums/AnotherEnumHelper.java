package fr.dbrown55.concrete.enums;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import fr.dbrown55.concrete.items.ItemBlockConcretePowder;
import fr.dbrown55.concrete.items.ItemHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class AnotherEnumHelper {
	
	private static Method addEnumFromForge = null;
	private static Class ISL_clazz = new ItemStack[]{}.getClass();
	
	public static EnumConcreteType addConcreteType(String enumName, String modid, String name, ItemStack[] toCraft, ItemBlockConcretePowder crafted){
		EnumConcreteType theEnum = addEnum(new Class[][] {{EnumConcreteType.class, String.class, String.class, ISL_clazz}}, EnumConcreteType.class, enumName, modid, name, toCraft);
		if(theEnum != null){
			ItemHandler.addResult(theEnum, crafted);
		}
		return theEnum;
	}
	
	private static < T extends Enum<? >> T addEnum(Class[][] classes, Class<?> class1, String enumName, String modid, String name, ItemStack[] toCraft){
		if(addEnumFromForge == null){
			try {
				Method addEnumMethod = EnumHelper.class.getDeclaredMethod("addEnum", new Class[] {Class[][].class, Class.class, String.class, Object[].class});
				addEnumMethod.setAccessible(true);
			} catch(SecurityException | NoSuchMethodException e) {
				System.err.println("Unable to access Forge's \"EnumHelper#addEnum\" method, sorry.");
				return null;
			}
		}
		try {
			return (T) addEnumFromForge.invoke(null, classes, class1, enumName, modid, name, toCraft);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.err.println("Unable to invoke Forge's \"EnumHelper#addEnum\" method, sorry.");
			return null;
		}
	}

}
