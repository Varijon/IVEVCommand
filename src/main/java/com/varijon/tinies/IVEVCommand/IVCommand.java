package com.varijon.tinies.IVEVCommand;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.battles.attacks.Attack;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class IVCommand implements ICommand {

	private List aliases;
	public IVCommand()
	{
	   this.aliases = new ArrayList();
	   this.aliases.add("iv");
	   this.aliases.add("ivs");
	}
	
	@Override
	public int compareTo(ICommand arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "/ivs";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "/ivs slot";
	}

	@Override
	public List<String> getAliases() {
		// TODO Auto-generated method stub
		return this.aliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException 
	{
		if(sender.canUseCommand(4, "IVEVCommand.IVs"))
		{
			if(args.length == 0)
			{
				sender.sendMessage(new TextComponentString(TextFormatting.RED + "Usage: /ivs slot"));						
				return;
			}
			if(sender instanceof EntityPlayerMP)
			{
				EntityPlayerMP player = (EntityPlayerMP) sender;
				
				try
				{
					PlayerPartyStorage playerStorage =  Pixelmon.storageManager.getParty(player);
					
					if(!NumberUtils.isNumber(args[0]))
					{
						sender.sendMessage(new TextComponentString(TextFormatting.RED + "Invalid slot!"));			
						return;
					}

					int partyNumber = Integer.parseInt(args[0]) - 1;
					
					if(partyNumber < 0 || partyNumber > 5)
					{
						sender.sendMessage(new TextComponentString(TextFormatting.RED + "Invalid slot!"));	
						return;
					}
					
					Pokemon pixelmon = playerStorage.get(partyNumber);;
					if (pixelmon != null)
					{
						int pixelmonIVHP = pixelmon.getStats().ivs.hp;
						int pixelmonIVAttack = pixelmon.getStats().ivs.attack;
						int pixelmonIVDefense = pixelmon.getStats().ivs.defence;
						int pixelmonIVSpAtt = pixelmon.getStats().ivs.specialAttack;
						int pixelmonIVSpDef = pixelmon.getStats().ivs.specialDefence;
						int pixelmonIVSpeed = pixelmon.getStats().ivs.speed;
						String pixelmonName = pixelmon.getBaseStats().pixelmonName;
						
						String testString = TextFormatting.GREEN + "HP:" + "`" + colorIV(pixelmonIVHP) + (pixelmonIVHP == 31 ? TextFormatting.BOLD : "") + pixelmonIVHP + (pixelmon.getIVs().isHyperTrained(StatsType.HP) ? TextFormatting.WHITE + " (" + TextFormatting.AQUA + "HT" + TextFormatting.WHITE + ")" : "")
								+ "\n" + TextFormatting.RED + "Attack:"   + "`"  + colorIV(pixelmonIVAttack) + (pixelmonIVAttack == 31 ? TextFormatting.BOLD : "") + pixelmonIVAttack + (pixelmon.getIVs().isHyperTrained(StatsType.Attack) ? TextFormatting.WHITE + " (" + TextFormatting.AQUA + "HT" + TextFormatting.WHITE + ")" : "")
								+ "\n" + TextFormatting.GOLD + "Defence:" + "`" + colorIV(pixelmonIVDefense) + (pixelmonIVDefense == 31 ? TextFormatting.BOLD : "")  + pixelmonIVDefense + (pixelmon.getIVs().isHyperTrained(StatsType.Defence) ? TextFormatting.WHITE + " (" + TextFormatting.AQUA + "HT" + TextFormatting.WHITE + ")" : "")
								+ "\n" + TextFormatting.LIGHT_PURPLE + "Sp.Attack:" + "`" + colorIV(pixelmonIVSpAtt) + (pixelmonIVSpAtt == 31 ? TextFormatting.BOLD : "") + pixelmonIVSpAtt + (pixelmon.getIVs().isHyperTrained(StatsType.SpecialAttack) ? TextFormatting.WHITE + " (" + TextFormatting.AQUA + "HT" + TextFormatting.WHITE + ")" : "")
								+ "\n" + TextFormatting.YELLOW + "Sp.Defence:" + "`" + colorIV(pixelmonIVSpDef) + (pixelmonIVSpDef == 31 ? TextFormatting.BOLD : "") + pixelmonIVSpDef + (pixelmon.getIVs().isHyperTrained(StatsType.SpecialDefence) ? TextFormatting.WHITE + " (" + TextFormatting.AQUA + "HT" + TextFormatting.WHITE + ")" : "")
								+ "\n" + TextFormatting.AQUA + "Speed:" + "`" + colorIV(pixelmonIVSpeed) + (pixelmonIVSpeed == 31 ? TextFormatting.BOLD : "") + pixelmonIVSpeed + (pixelmon.getIVs().isHyperTrained(StatsType.Speed) ? TextFormatting.WHITE + " (" + TextFormatting.AQUA + "HT" + TextFormatting.WHITE + ")" : ""); 
						
//						sender.sendMessage(new TextComponentString(TextFormatting.AQUA + pixelmonName + (pixelmonName.charAt(pixelmonName.length()-1) == 's' ? "'" : "'s") + " " + TextFormatting.WHITE + "IVs:"));
//						sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "HP:            " + colorIV(pixelmonIVHP) + (pixelmonIVHP == 31 ? TextFormatting.BOLD : "") + pixelmonIVHP));
//						sender.sendMessage(new TextComponentString(TextFormatting.RED + "Attack:       " + colorIV(pixelmonIVAttack) + (pixelmonIVAttack == 31 ? TextFormatting.BOLD : "")  + pixelmonIVAttack));
//						sender.sendMessage(new TextComponentString(TextFormatting.GOLD + "Defence:     " + colorIV(pixelmonIVDefense) + (pixelmonIVDefense == 31 ? TextFormatting.BOLD : "") + pixelmonIVDefense));
//						sender.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "Sp.Attack:    " + colorIV(pixelmonIVSpAtt) + (pixelmonIVSpAtt == 31 ? TextFormatting.BOLD : "") + pixelmonIVSpAtt));
//						sender.sendMessage(new TextComponentString(TextFormatting.YELLOW + "Sp.Defence:  " + colorIV(pixelmonIVSpDef) + (pixelmonIVSpDef == 31 ? TextFormatting.BOLD : "")  + pixelmonIVSpDef));
//						sender.sendMessage(new TextComponentString(TextFormatting.AQUA + "Speed:        " + colorIV(pixelmonIVSpeed) + (pixelmonIVSpeed == 31 ? TextFormatting.BOLD : "")  + pixelmonIVSpeed));
						TabText tt = new TabText(testString);
						tt.setTabs(12);
						sender.sendMessage(new TextComponentString(TextFormatting.AQUA + pixelmonName + (pixelmonName.charAt(pixelmonName.length()-1) == 's' ? "'" : "'s") + " " + TextFormatting.GRAY + TextFormatting.WHITE + "IVs:"));

						sender.sendMessage(new TextComponentString(tt.getPage(0, false)));
						int combinedIV = pixelmonIVHP + pixelmonIVAttack + pixelmonIVDefense + pixelmonIVSpAtt + pixelmonIVSpDef + pixelmonIVSpeed;
											
						sender.sendMessage(new TextComponentString(TextFormatting.GOLD + "Total: " + TextFormatting.YELLOW + combinedIV + "/186 or " + Math.round(((float)combinedIV / 186.0 * 100.0)) + "%"));
					}
					else
					{
						sender.sendMessage(new TextComponentString(TextFormatting.RED + "Party slot is empty!"));						
					}
				}
				catch(Exception ex)
				{
					//System.out.println(ex.getStackTrace());
				}
			}
			return;
		}
		else
		{
			sender.sendMessage(new TextComponentString(TextFormatting.RED + "You don't have permission to use this command"));
			return;
		}

	}
	
	public TextFormatting colorIV(int pIV)
	{
		if(pIV < 6)
		{
			return TextFormatting.DARK_RED;
		}
		if(pIV < 11)
		{
			return TextFormatting.RED;
		}
		if(pIV < 16)
		{
			return TextFormatting.GOLD;
		}
		if(pIV < 23)
		{
			return TextFormatting.DARK_GREEN;
		}
		if(pIV < 31)
		{
			return TextFormatting.GREEN;
		}
		if(pIV == 31)
		{
			return TextFormatting.BLUE;
		}
		return TextFormatting.GRAY;
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) 
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		// TODO Auto-generated method stub
		return false;
	}

}
