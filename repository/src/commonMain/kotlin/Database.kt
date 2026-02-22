import app.cash.sqldelight.db.SqlDriver
import com.achtien.catfacts.CatFactsDatabase

class CatFactsDatabaseWrapper(val driver: SqlDriver, val instance: CatFactsDatabase)
