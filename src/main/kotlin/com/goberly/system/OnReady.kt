package com.goberly.system

import com.goberly.system.messages.Welcome
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.session.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData

class OnReady : ListenerAdapter() {
    override fun onReady(event: ReadyEvent) {
        val guild = event.jda.getGuildById(1076995283466068120L)

        guild!!.updateCommands().addCommands(
            SlashCommmands().setWelcome()).queue()

        event.jda.addEventListener(
            Welcome())

        val guildSize = event.jda.guilds.size

        if(guildSize == 1) {
            event.jda.presence.activity = Activity.playing("on ${event.jda.guilds.size} guild")
        } else {
            event.jda.presence.activity = Activity.playing("on $guildSize guilds")
        }
    }

    class SlashCommmands {
        fun setWelcome(): SlashCommandData {
            return Commands.slash("setwelcome", "Set the welcome channel")
                .addOption(OptionType.CHANNEL, "channel", "The channel to set as the welcome channel", true)
        }
    }
}