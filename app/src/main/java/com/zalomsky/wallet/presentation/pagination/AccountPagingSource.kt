package com.zalomsky.wallet.presentation.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zalomsky.wallet.domain.model.AccountEntity

class AccountPagingSource(
    private val accountEntities: List<AccountEntity>
): PagingSource<Int, AccountEntity>() {

    companion object {
        private const val PAGE_SIZE = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AccountEntity> {

        val page = params.key ?: 0
        val startIndex = page * PAGE_SIZE
        val endIndex = startIndex + PAGE_SIZE

        return try {
            val data = accountEntities.subList(startIndex, endIndex)
            LoadResult.Page(
                data = data,
                prevKey = if (page > 0) page - 1 else null,
                nextKey = if (endIndex < accountEntities.size) page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AccountEntity>): Int? = null
}
