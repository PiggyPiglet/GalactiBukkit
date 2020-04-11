package me.piggypiglet.galacticraft.commands.registerables;

import com.google.inject.TypeLiteral;
import me.piggypiglet.galacticraft.boot.registerables.Registerable;
import me.piggypiglet.galacticraft.commands.framework.Command;
import me.piggypiglet.galacticraft.scanning.framework.Scanner;

import javax.inject.Inject;
import java.util.Set;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class CommandsRegisterable extends Registerable {
    @Inject private Scanner scanner;

    @Override
    protected void execute() {
        addBinding(new TypeLiteral<Set<Command>>(){}, scanner.getSubTypesOf(Command.class).stream()
                .map(injector::getInstance)
                .collect(Collectors.toSet()));
    }
}
