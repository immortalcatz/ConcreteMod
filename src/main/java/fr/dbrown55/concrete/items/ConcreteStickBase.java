package fr.dbrown55.concrete.items;

import fr.dbrown55.utilmod.objects.DbrownItem;
import net.minecraft.util.ResourceLocation;

public abstract class ConcreteStickBase extends DbrownItem {

	// Item used to craft fences/gates
	// It's not a sort a quartz pillar in concrete
	
	public ConcreteStickBase(ResourceLocation name) {
		super(name);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

}
