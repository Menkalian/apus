package de.menkalian.apus.server

import de.menkalian.apus.server.data.ChangeEntity
import de.menkalian.apus.server.data.IActionTypeRepository
import de.menkalian.apus.server.data.IChangeRepository
import de.menkalian.apus.server.data.IPredefinedRepository
import de.menkalian.apus.server.data.PredefinedEntity
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.sql.Timestamp
import java.time.Instant
import java.util.Scanner
import kotlin.system.exitProcess

@SpringBootApplication
class AdminToolApplication {
    @Bean
    fun cliRunner(changeRepo: IChangeRepository, predefRepo: IPredefinedRepository, atRepo: IActionTypeRepository): CommandLineRunner =
        CommandLineRunner {
            val scanner = Scanner(System.`in`)

            println("Apus CLI Admin Tool (use `help` for a List of commands)")
            print("> ")

            while (scanner.hasNextLine()) {
                val line = scanner.nextLine().split(" ")

                when (line[0]) {
                    "list"         -> predefRepo.findAll().forEach { println("#${it.id} | ${it.nameDe} | ${it.nameEn}") }
                    "create"       -> {
                        val toCreate = requestItemInfo()
                        predefRepo.save(toCreate)

                        val change = ChangeEntity()
                        change.id = -1
                        change.affected = toCreate
                        change.timestamp = Timestamp.from(Instant.now())
                        change.type = atRepo.findActionTypeEntityByValue("CREATED").id
                        changeRepo.save(change)
                    }
                    "edit"         -> {
                        val id = line[1].toInt()

                        val toEdit = predefRepo.findById(id).orElseGet { null }
                        val edited = requestItemInfo(toEdit)
                        edited.id = toEdit?.id ?: -1
                        predefRepo.save(edited)

                        val deleteChange = ChangeEntity()
                        deleteChange.affected = edited
                        deleteChange.timestamp = Timestamp.from(Instant.now())
                        deleteChange.type = atRepo.findActionTypeEntityByValue("DELETED").id
                        changeRepo.save(deleteChange)

                        val createChange = ChangeEntity()
                        createChange.affected = edited
                        createChange.timestamp = Timestamp.from(Instant.now())
                        createChange.type = atRepo.findActionTypeEntityByValue("CREATED").id
                        changeRepo.save(createChange)
                    }
                    "quit", "exit" -> exitProcess(0)
                    "help"         -> println(
                        """
                    ***** Apus CLI Admin Tool *****
                    
                    Commands:
                     - list: Shows a list of all predefined items
                     - create: Create a new item
                     - edit [id]: Edits the item with the given ID
                     - quit/exit
                """.trimIndent()
                    )
                    else           -> println("No valid input. Consider consulting help")
                }
                print("> ")
            }
        }

    fun requestItemInfo(item: PredefinedEntity? = null): PredefinedEntity {
        val scanner = Scanner(System.`in`)

        print("Name (DE):${if (item != null) "[${item.nameDe}]" else ""} ")
        if (scanner.hasNextLine().not()) {
            error("Standard-Input ended unexpectedly")
        }
        var nameDe = scanner.nextLine()
        if (nameDe.isBlank() && item != null)
            nameDe = item.nameDe

        print("Name (EN):${if (item != null) "[${item.nameEn}]" else ""} ")
        if (scanner.hasNextLine().not()) {
            error("Standard-Input ended unexpectedly")
        }
        var nameEn = scanner.nextLine()
        if (nameEn.isBlank() && item != null)
            nameEn = item.nameEn

        val predefinedEntity = PredefinedEntity()
        predefinedEntity.nameDe = nameDe
        predefinedEntity.nameEn = nameEn
        return predefinedEntity
    }

}

fun main(args: Array<String>) {
    runApplication<AdminToolApplication>(*args)
}