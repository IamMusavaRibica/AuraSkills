package dev.aurelium.auraskills.bukkit.loot.parser;

import dev.aurelium.auraskills.api.loot.Loot;
import dev.aurelium.auraskills.api.loot.LootParser;
import dev.aurelium.auraskills.api.loot.LootParsingContext;
import dev.aurelium.auraskills.bukkit.loot.type.CommandLoot;
import dev.aurelium.auraskills.common.commands.CommandExecutor;
import dev.aurelium.auraskills.common.util.data.Validate;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.Locale;

public class CommandLootParser implements LootParser {

    @Override
    public Loot parse(LootParsingContext context, ConfigurationNode config) throws SerializationException {
        String executorName = config.node("executor").getString("console");
        CommandExecutor executor = CommandExecutor.valueOf(executorName.toUpperCase(Locale.ROOT));

        String command = config.node("command").getString("");
        Validate.notNull(command, "Command loot must specify key command");

        return new CommandLoot(context.parseValues(config), executor, command);
    }
}
