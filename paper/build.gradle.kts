plugins {
	id("io.papermc.paperweight.userdev") version "1.3.8"
}

dependencies {
    paperDevBundle("1.18.2-R0.1-SNAPSHOT")

	compileOnly("net.civmc.civmodcore:CivModCore:2.4.0:dev-all")
	compileOnly("net.civmc.namelayer:NameLayer:3.1.0:dev")
	compileOnly("net.civmc.citadel:Citadel:5.1.0:dev")
	compileOnly("net.civmc.bastion:Bastion:3.1.0:dev")
    compileOnly("net.civmc.civchat2:CivChat2:2.1.0:dev")
    compileOnly("net.civmc.combattagplus:CombatTagPlus:2.1.0:dev")
	compileOnly("net.civmc.banstick:BanStick:2.1.0:dev")
	compileOnly("net.civmc.randomspawn:RandomSpawn:3.1.0:dev")
	compileOnly("net.civmc.worldborder:WorldBorder:3.1.0:dev")

	compileOnly("com.github.DieReicheErethons:Brewery:3.1")
}
