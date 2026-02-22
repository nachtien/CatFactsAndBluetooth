import api.Api
import app.cash.sqldelight.async.coroutines.awaitCreate
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.achtien.catfacts.CatFactsDatabase
import com.achtien.catfacts.Fact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import models.CatFact

class CatFactsRepository(
    private val api: Api,
    private val catFactsDatabase: CatFactsDatabaseWrapper
) {
    val coroutineScope: CoroutineScope = MainScope()

    val catFacts = catFactsDatabase.instance.factsQueries.selectAll()
        .asFlow()
        .mapToList(Dispatchers.IO)

    init {
        coroutineScope.launch {
            CatFactsDatabase.Schema.awaitCreate(catFactsDatabase.driver)
        }
    }

    suspend fun getCatFacts(lastId: Long, limit: Int = 20): List<CatFact> {
        val response = api.getCatFacts(lastId, limit)

        return response.data?.facts?.also {
            saveCatFacts(it)
        } ?: emptyList()
    }

    suspend fun saveCatFacts(catFacts: List<CatFact>?) {
        catFacts?.forEach { catFact ->
            val fact = Fact(
                id = catFact.id,
                message = catFact.fact
            )
            catFactsDatabase.instance.factsQueries.insertItem(fact)
        }
    }

    suspend fun clearCache() {
        catFactsDatabase.instance.factsQueries.deleteAll()
    }
}
