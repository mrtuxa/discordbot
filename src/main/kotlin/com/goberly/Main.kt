package com.goberly

import com.goberly.system.OnReady
import io.github.cdimascio.dotenv.dotenv
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.ktorm.database.Database

class Main {
    companion object {
        val dotenv = dotenv()
        val database = Database.connect(Main.dotenv["DB_CONNECTION_STRING"], user = "")
    }
}


class Test : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        println(event.message.contentRaw)
    }
}

fun main() {

    JDABuilder.createDefault(Main.dotenv["TOKEN"])
        .addEventListeners(OnReady())
        .addEventListeners(Test())
        .build()
}