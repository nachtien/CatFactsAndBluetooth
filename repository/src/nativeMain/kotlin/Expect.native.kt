
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.achtien.catfacts.CatFactsDatabase
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

actual fun platformModule() = module {
    single {
        val driver = NativeSqliteDriver(CatFactsDatabase.Schema.synchronous(), "brightaidatabase.db")

        CatFactsDatabaseWrapper(driver, CatFactsDatabase(driver))
    }
    single<HttpClientEngine> { Darwin.create() }
}
