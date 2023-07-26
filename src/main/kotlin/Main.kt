// Laboratorio # 2 de KOTLIN
// 25/07/2023
// Audrey Samantha Bhor López - 22545
// Programación de plataformas móviles

import java.util.*

class Hobby(val título: String, val descripción: String, val urlPhoto: String?) // se le coloca el signo de interrogación ya que puede aceptar nulos

class PerfilUsuario(
    val id: Int,
    val nombres: String,
    val apellidos: String,
    val urlPhoto: String?,
    val edad: Int,
    val correo: String,
    val biografía: String?,
    var estado: String,
    val hobbies: MutableList<Hobby> = mutableListOf()
) {
    fun agregarHobby(hobby: Hobby) {
        hobbies.add(hobby)
    }
}

fun main() {
    val perfiles: MutableList<PerfilUsuario> = mutableListOf()


    fun showError(message: String) {
        println("Error: $message")
        System.exit(1)
    }

    // Función para encontrar un perfil por ID.
    fun findProfileById(id: Int): PerfilUsuario? {
        return perfiles.find { it.id == id }
    }


    fun findProfilesByNames(lastName: String?, firstName: String?): List<PerfilUsuario> {
        return perfiles.filter {
            (lastName == null || it.apellidos.equals(lastName, ignoreCase = true)) &&
                    (firstName == null || it.nombres.equals(firstName, ignoreCase = true))
        }
    }


    fun deleteProfileById(id: Int): Boolean {
        val profile = findProfileById(id)
        return if (profile != null) {
            perfiles.remove(profile)
            true
        } else {
            false
        }
    }


    fun printProfile(profile: PerfilUsuario) {
        println("ID: ${profile.id}")
        println("Nombres: ${profile.nombres}")
        println("Apellidos: ${profile.apellidos}")
        println("URL de foto de perfil: ${profile.urlPhoto ?: "N/A"}")
        println("Edad: ${profile.edad}")
        println("Correo electrónico: ${profile.correo}")
        println("Biografía: ${profile.biografía ?: "N/A"}")
        println("Estado: ${profile.estado}")
        println("Hobbies:")
        if (profile.hobbies.isEmpty()) {
            println("\tN/A")
        } else {
            profile.hobbies.forEachIndexed { index, hobby ->
                println("\tHobby ${index + 1}: ${hobby.título}")
                println("\t   Descripción: ${hobby.descripción}")
                println("\t   URL de foto: ${hobby.urlPhoto ?: "N/A"}")
            }
        }
    }


    fun createProfile(): PerfilUsuario {
        val scanner = Scanner(System.`in`)

        println("Ingrese los siguientes datos:")
        print("ID: ")
        val id = scanner.nextInt()
        if (findProfileById(id) != null) {
            showError("Ya existe un perfil con el ID ingresado.")
        }

        scanner.nextLine() // Limpiar el buffer.

        print("Nombres: ")
        val nombres = scanner.nextLine()

        print("Apellidos: ")
        val apellidos = scanner.nextLine()

        print("URL de foto de perfil (opcional): ")
        val urlPhoto = scanner.nextLine().takeIf { it.isNotBlank() }

        print("Edad: ")
        val edad = scanner.nextInt()

        scanner.nextLine() // Limpiar el buffer.

        print("Correo electrónico: ")
        val correo = scanner.nextLine()

        print("Biografía (opcional): ")
        val biografía = scanner.nextLine().takeIf { it.isNotBlank() }

        print("Estado (Activo, Pendiente o Inactivo): ")
        val estado = scanner.nextLine()
        if (estado !in setOf("Activo", "Pendiente", "Inactivo")) {
            showError("El estado ingresado no es válido.")
        }

        return PerfilUsuario(id, nombres, apellidos, urlPhoto, edad, correo, biografía, estado)
    }

    // Función para agregar un hobby a un perfil.
    fun addHobbyToProfile(profile: PerfilUsuario) {
        val scanner = Scanner(System.`in`)
        println("Ingrese los datos del hobby:")
        print("Título: ")
        val título = scanner.nextLine()

        print("Descripción: ")
        val descripción = scanner.nextLine()

        print("URL de foto (opcional): ")
        val urlPhoto = scanner.nextLine().takeIf { it.isNotBlank() }

        profile.agregarHobby(Hobby(título, descripción, urlPhoto))
        println("Hobby agregado con éxito.")
    }

    // datos se introducen
    perfiles.add(
        PerfilUsuario(
            1,
            "Samantha",
            "Bhor",
            "https://gatitos.com/gato1.png",
            20,
            "sbho@gmail.com",
            "Me gusta cocinar",
            "Activo"
        )
    )
    perfiles.add(
        PerfilUsuario(
            2,
            "Susan",
            "Recinos",
            "https://fotography/pic.jpg",
            19,
            "susanita@gmail.com",
            null,
            "Pendiente"
        )
    )

    while (true) {
        println("Seleccione una opción:")
        println("1. Crear perfil")
        println("2. Buscar perfil de usuario(s)")
        println("3. Eliminar perfil")
        println("4. Agregar Hobby")
        println("0. Salir")
        print("Opción: ")

        val scanner = Scanner(System.`in`)
        when (scanner.nextInt()) {
            1 -> {
                val newProfile = createProfile()
                perfiles.add(newProfile)
                println("Perfil creado con éxito.")
            }

            2 -> {
                println("Ingrese el ID o los nombres y/o apellidos del perfil a buscar.")
                print("ID: ")
                val idToSearch = scanner.nextInt()
                val lastNameToSearch = scanner.nextLine().takeIf { it.isNotBlank() }
                val firstNameToSearch = scanner.nextLine().takeIf { it.isNotBlank() }

                val profilesFound =
                    if (idToSearch > 0) listOfNotNull(findProfileById(idToSearch))
                    else findProfilesByNames(lastNameToSearch, firstNameToSearch)

                if (profilesFound.isNotEmpty()) {
                    profilesFound.forEach { printProfile(it) }
                } else {
                    println("No se encontró ningún perfil con la información ingresada.")
                }
            }

            3 -> {
                print("Ingrese el ID del perfil a eliminar: ")
                val idToDelete = scanner.nextInt()

                if (deleteProfileById(idToDelete)) {
                    println("Perfil eliminado con éxito.")
                } else {
                    println("No se encontró ningún perfil con el ID ingresado.")
                }
            }

            4 -> {
                print("Ingrese el ID o los nombres y/o apellidos del perfil al que desea agregar un hobby: ")
                val idToSearch


                fun isLetters(string: String): Boolean {
                    for (c in string) {
                        if (c !in 'A'..'Z' && c !in 'a'..'z') {
                            return false
                        }
                    }
                    return true
                }

                fun main() {
                    val string = "Kotlin"

                    println("Is Alphabet: ${isLetters(string)}")        // true
                }
            }
