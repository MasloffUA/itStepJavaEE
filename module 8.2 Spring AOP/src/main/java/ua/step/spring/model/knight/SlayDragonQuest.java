package ua.step.spring.model.knight;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(value = "slaydragon")
public class SlayDragonQuest implements Quest {

	public int embark(String name) {
		System.out.println(name + " Нашел дракона");
		System.out.println(name + " Убил дракона");
		System.out.println(name + " Съел дракона");
		return 3;
	}
}