package wpferg.postcodes.android.domain

data class PostcodeResponse<T> constructor (val status: Int, val result: T)