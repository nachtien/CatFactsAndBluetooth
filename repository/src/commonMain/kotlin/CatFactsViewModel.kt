
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import models.CatFact

// I know this isn't a good spot for this file, but let's just keep it in here so it's simple
class CatFactsViewModel(private val catFactsRepository: CatFactsRepository) : ViewModel() {
    val catFacts = catFactsRepository.catFacts.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )

    init {
        viewModelScope.launch {
            catFacts.collect { facts ->
                if (facts.isEmpty()) {
                    loadMore()
                }
            }
        }
    }

    suspend fun getCatFacts(lastId: Long): List<CatFact> {
        return withContext(Dispatchers.IO) {
            Logger.e { "Getting cat facts: $lastId" }
            catFactsRepository.getCatFacts(lastId, 5)
        }
    }

    suspend fun loadMore() {
        getCatFacts(catFacts.value.lastOrNull()?.id ?: 0)
    }

    fun clearCache() {
        viewModelScope.launch(Dispatchers.IO) {
            catFactsRepository.clearCache()
        }
    }
}
