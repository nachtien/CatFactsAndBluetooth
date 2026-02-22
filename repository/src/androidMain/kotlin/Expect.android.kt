import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.achtien.catfacts.CatFactsDatabase
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import org.koin.dsl.module

actual fun platformModule() = module {
    single {
        val driver =
            AndroidSqliteDriver(CatFactsDatabase.Schema.synchronous(), get(), "brightaidatabase.db")

        CatFactsDatabaseWrapper(driver, CatFactsDatabase(driver))
    }
    single<HttpClientEngine> { Android.create() }
}
