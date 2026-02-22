class iOSPlatform : Platform {
    override val name: String = "iOS"
}

actual fun getPlatform(): Platform {
    return iOSPlatform()
}

fun initKoinIos() {
    initKoin()
}