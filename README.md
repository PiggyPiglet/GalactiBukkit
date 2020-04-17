# Galacticraft
[![build shield](https://img.shields.io/jenkins/build?jobUrl=https%3A%2F%2Fci.piggypiglet.me%2Fjob%2FGalacticraft%2F)](https://ci.piggypiglet.me/job/Galacticraft/)

This is my entry for Global Developers' spigot plugin contest. In it's current stage, it's a POC of the galacticraft forge mod, in vanilla minecraft (paper).

## Requirements
- Paper 1.15.2
- Java 8

## Bugs
- Placed rockets can't be picked up after a server restart, make sure to pickup your rocket before the server stops. If you accidentally leave a rocket placed, right click on the rocket till you find the head to retrieve the item back, then kill the armor stands via command (e.g. /kill @e[type=armor_stand]).
- Gravity algorithm is severely affected by latency
- Gravity algorithm doesn't apply to strafing while in air (resulting in the illusion of not being able to strafe; you can strafe, it's just slow)
- Velocity is preserved after jumping, leading to exponential speed increase (gravity algorithm)

## Quick Tutorial
This plugin isn't gameplay ready yet, so here's how to test out it's features:

### Launch Pads
- Create a 3x3 flat area of purpur slabs. The slabs can be different height, so long they're in the same block y coordinate.

### Gravity
Gravity is disabled by default due to all the bugs listed above, if you wish to experiment with it, uncomment the `GravityRegisterable.class` in `GalacticraftBootstrap#REGISTERABLES`.

### Rockets
#### Recipe
- 5 * 64 iron ingots
- 1 * dragon head
- 1 * shulker box

![recipe picture](https://p1g.pw/gc/rocketrecipe.png)

#### Usage
- Place the rocket in the middle of the launch pad
- Right click on it to mount, hitbox is small as of now, so you may need to get close, or move around till you find a clickable area.
- Left click the rocket to destroy it. An item will drop, and the plugin will kill the armor stands.
- When mounted, double left click to open the gui. The fuel system isn't implemented yet, so you can launch straight away. Simply click the launch button.

#### Demo
[![demo](https://p1g.pw/gc/trickvideo.PNG)](https://p1g.pw/gc/rockets3.mp4)

### Commands
Only debug commands are currently implemented. The rocket system doesn't have world changing yet, so use `/gc switch-world <world>` to switch world for now. The moon world is simply called `world`. 