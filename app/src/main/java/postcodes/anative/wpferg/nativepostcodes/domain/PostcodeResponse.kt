package postcodes.anative.wpferg.nativepostcodes.domain

data class PostcodeResponse<T> constructor (val status: Int, val result: T)