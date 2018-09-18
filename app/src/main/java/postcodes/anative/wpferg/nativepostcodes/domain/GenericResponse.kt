package postcodes.anative.wpferg.nativepostcodes.domain

data class GenericResponse<T> constructor (val status: Int, val result: T)