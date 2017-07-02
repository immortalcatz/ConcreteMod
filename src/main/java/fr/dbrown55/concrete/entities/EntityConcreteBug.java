package fr.dbrown55.concrete.entities;

import java.awt.Color;

import fr.dbrown55.concrete.items.ItemHandler;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class EntityConcreteBug extends EntityMob {
	
	private static final DataParameter<Integer> COLOR = EntityDataManager.<Integer>createKey(EntityConcreteBug.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> SOLID = EntityDataManager.<Boolean>createKey(EntityConcreteBug.class, DataSerializers.BOOLEAN);
	
	public EntityConcreteBug(World worldIn) {
		super(worldIn);
		this.setSize(0.4F, 0.3F);
	}
	
	public EntityConcreteBug(World worldIn, double x, double y, double z) {
		this(worldIn);
		this.setPosition(x, y, z);
	}
	
	public EntityConcreteBug(World worldIn, double x, double y, double z, float yaw) {
		this(worldIn);
		this.setPositionAndRotation(x, y, z, yaw, 0.0F);
	}
	
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(COLOR, MapColor.SNOW.colorValue);
		this.dataManager.register(SOLID, false);
	}
	
	// Not sure if its called, but I keep it here 'cause I dunno
	public static void registerFixesConcreteBug(DataFixer fixer) {
		EntityLiving.registerFixesMob(fixer, "ConcreteBug");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		NBTTagCompound nbt2 = super.writeToNBT(compound);
		nbt2.setInteger("ColorInt", this.getColor());
		nbt2.setBoolean("Solid", this.isSolid());
		return nbt2;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.setColor(compound.getInteger("ColorInt"));
		this.setSolid(compound.getBoolean("Solid"));
	}
	
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, false));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if(!this.worldObj.isRemote) {
			if(this.getEntityBoundingBox() != null && this.worldObj.isAABBInMaterial(this.getEntityBoundingBox(), Material.WATER) && !this.isSolid()) {
				this.setSolid(true);
			}
		}
	}
	
	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand, ItemStack stack) {
		if(hand == EnumHand.MAIN_HAND && stack == null && this.isSolid() && !this.worldObj.isRemote) {
			ItemStack is = new ItemStack(ItemHandler.CONCRETE_BUG);
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setInteger("color", this.getColor());
			is.setTagCompound(nbt);
			EntityItem item = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, is);
			this.worldObj.spawnEntityInWorld(item);
			this.setDead();
		}
		return super.processInteract(player, hand, stack);
	}
	
	@Override
	public void onKillCommand() {
		if(this.isSolid()) {
			this.setDead();
		} else {
			super.onKillCommand();
		}
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if(this.isEntityInvulnerable(source)) {
			return false;
		} else {
			if(this.isSolid()) {
				return false;
			} else {
				return super.attackEntityFrom(source, amount);
			}
		}
	}
	
	@Override
	protected ResourceLocation getLootTable() {
		return LootTableList.ENTITIES_SILVERFISH;
	}
	
	public void setColor(int color) {
		this.dataManager.set(COLOR, color);
	}
	
	public int getColor() {
		return this.dataManager.get(COLOR);
	}
	
	public void setSolid(boolean solid) {
		this.dataManager.set(SOLID, solid);
		this.setSilent(solid);
	}
	
	public boolean isSolid() {
		return this.dataManager.get(SOLID);
	}

	public void addColor(int colorInt) {
		if(this.getColor() == 0xFFFFFF){
			this.setColor(colorInt);
		} else {
			Color col1 = new Color(this.getColor());
			Color col2 = new Color(colorInt);
			
			int r = (col1.getRed() + col2.getRed())/2, g = (col1.getGreen() + col2.getGreen())/2, b = (col1.getBlue() + col2.getBlue())/2;
			
			Color col3 = new Color(r, g, b);
			this.setColor(col3.getRGB());
		}
	}
	

}
