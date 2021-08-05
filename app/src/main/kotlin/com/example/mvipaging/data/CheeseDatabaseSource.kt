package com.example.mvipaging.data

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import androidx.recyclerview.widget.ListUpdateCallback
import com.example.mvipaging.domain.CheeseSource
import com.example.mvipaging.presentation.CheeseAdapter
import com.example.mvipaging.presentation.CheeseListItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class CheeseDatabaseSource(
    private val cheeseDao: CheeseDao
) : CheeseSource {
    private val scope = CoroutineScope(Dispatchers.IO)
    private val pager = Pager(
        PagingConfig(
            pageSize = 20,
            enablePlaceholders = true,
            maxSize = 100
        )
    ) { cheeseDao.entities() }
        .flow
        .map { pagingData ->
            pagingData.map(CheeseListItem::Item)
                .insertSeparators { beforeSeparator, afterSeparator ->
                    afterSeparator?.name
                        ?.firstOrNull()
                        ?.takeUnless {
                            beforeSeparator?.name
                                ?.firstOrNull()
                                ?.equals(it, ignoreCase = true) == true
                        }
                        ?.let(CheeseListItem::Separator)
                }
        }
        .cachedIn(scope)

    private val noopListUpdateCallback = object : ListUpdateCallback {
        override fun onInserted(position: Int, count: Int) = Unit
        override fun onChanged(position: Int, count: Int, payload: Any?) = Unit
        override fun onMoved(fromPosition: Int, toPosition: Int) = Unit
        override fun onRemoved(position: Int, count: Int) = Unit
    }

    private val differ = AsyncPagingDataDiffer(
        diffCallback = CheeseAdapter.diffCallback,
        updateCallback = noopListUpdateCallback,
        mainDispatcher = Dispatchers.Default,
        workerDispatcher = Dispatchers.Default
    )

    init {
        scope.launch {
            pager.collectLatest {
                differ.submitData(it)
            }
        }
    }

    override suspend fun get(index: Int): List<CheeseListItem?> = with(differ) {
        if (index in 0 until itemCount) {
            getItem(index)
            loadStateFlow.first {
                it.refresh !is LoadState.Loading
            }
        } else {
            loadStateFlow.first {
                it.refresh.endOfPaginationReached || snapshot().isNotEmpty()
            }
        }
        snapshot()
    }
}
