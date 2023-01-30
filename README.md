# BukkitPlugin-PokeBonus
一个需要依赖PixelmonMod模组的插件，可以实现玩家的宝可梦的能力面板中的数值进行加成修改，更好的控制。控制方式是由判断存储的关键字类进行判断的，所以你也可以依赖本插件进行存入自定义的值
## 基本用法
插件主类
~~~java
package me.myplugin.myplugin.Example;

import me.gsqlin.pokebonus.utils.PokeData;  
import org.bukkit.Bukkit;  
import org.bukkit.plugin.java.JavaPlugin;
import me.myplugin.myplugin.Commands;

public class Example extends JavaPlugin {  
	static PokeBonusData pokeBonusData;  
    @Override  
	public void onEnable() {  
		pokeBonusData = PokeBonusData.getInstance(); //获取数据，之后可以用
		getCommand("examplecmd").setExecutor(new Commands());
		getLogger().info("§aPlugin enabled!");
	}
	public static PokeBonus getInstance(){  
	    return ((Example) Bukkit.getPluginManager().getPlugin("Example"));  
	}
}
~~~
指令/功能 类
~~~java
package me.myplugin.myplugin.Commands;
  
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;  
import com.pixelmonmod.pixelmon.api.storage.PlayerPartyStorage;  
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;  
import me.gsqlin.pokebonus.utils.Bonus;  
import me.gsqlin.pokebonus.utils.BonusData;  
import me.gsqlin.pokebonus.utils.PokeData;  
import org.bukkit.command.Command;  
import org.bukkit.command.CommandExecutor;  
import org.bukkit.command.CommandSender;  
import org.bukkit.entity.Player;  
  
import java.util.ArrayList;  
import java.util.List;
  
public class Commands implements CommandExecutor {  
	static PokeBonus plugin = PokeBonus.getInstance();  
	@Override  
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {  
		PlayerPartyStorage pps = StorageProxy.getParty(((Player) sender).getUniqueId());  
		Pokemon pokemon = pps.get(0); //获取背包第一个精灵  
		//获取宝可梦数据  
		BonusData data = plugin.pokeBonusData.getBonusData(pokemon);  
		//建立主加成和子加成  
		Bonus SubBonus = new Bonus("type=Stat-Attack;value=0.1");  
		Bonus SubBonus2 = new Bonus("type=Stat-SAttack;value=0.05");  
		List<Bonus> bonusList = new ArrayList<>();  
		bonusList.add(SubBonus);  
		bonusList.add(SubBonus2);  
		//设置到宝可梦的数据里面  
		data.set(bonusList);  
		//在总数据内更新它  
		PokeBonusData.updatePokemon(pokemon);
		return false;  
	 }
}
~~~
注意: 记得更新，其他自定义的数据可以自己写更新内容。
## 其他用法
获取所有标签值
~~~java
//这里写的是获取所有MyCustom的值他的值是valueType也就是String的
String valueType = "";
String type = "MyCustom";
List<String> list = PokeBonusData.getTotalValue(type,valueType);
~~~
//获取可以进行计算的值进行总和
~~~java
double valueType = 0.0;
String type = "Stat-Speed";
List<double> list = pokeBonusData.getTotalValue(type);
double grossValue = 0.0;
for(double value:list){
	grossValue+=value;
}
sender.sendMeesage(String.valueOf(grossValue));
~~~
基本类型都可以，自己写的一般不太行
## 其他
本插件自己有的一些标签
Stat-Speed —— 面板中的速度
Stat-Attack —— 面板中的攻击
Stat-SAttack —— 面板中的特攻
Stat-Defense —— 面板中的防御
Stat-SDefense —— 面板中的特防
Stat-HP —— 面板中的生命

[**[球的盗窃团伙|Q群:793373365]**](https://jq.qq.com/?_wv=1027&k=6Z6EVsBJ)
