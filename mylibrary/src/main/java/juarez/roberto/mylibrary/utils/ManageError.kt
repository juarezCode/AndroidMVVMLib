package juarez.roberto.mylibrary.utils

object SetError {
    fun code(code: Int): String {
        return when (code) {
            0 -> "No Hay conexión a internet"
            400 -> "No se puedo intepretar la solicitud"
            401 -> "Falta autenticación"
            404 -> "Recurso no encontrado"
            409 -> "Hubo un conflicto al realizar la solicitud"
            500 -> "Error en el servidor"
            else -> "Hubo un error, intentalo más tarde"
        }
    }
}