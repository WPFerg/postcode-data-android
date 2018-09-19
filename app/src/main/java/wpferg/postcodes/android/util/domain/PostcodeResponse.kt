package wpferg.postcodes.android.util.domain

data class PostcodeResponse<T> constructor (val status: Int, val result: T)