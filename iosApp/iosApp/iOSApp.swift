import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        ComposeApp.KoinInitIosKt.doInitKoinIos()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
