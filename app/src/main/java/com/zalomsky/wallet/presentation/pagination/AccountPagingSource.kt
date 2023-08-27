package com.zalomsky.wallet.presentation.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zalomsky.wallet.domain.model.Account

class AccountPagingSource(
    private val accounts: List<Account>
): PagingSource<Int, Account>() {

    companion object {
        private const val PAGE_SIZE = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Account> {

        val page = params.key ?: 0
        val startIndex = page * PAGE_SIZE
        val endIndex = startIndex + PAGE_SIZE

        return try {
            val data = accounts.subList(startIndex, endIndex)
            LoadResult.Page(
                data = data,
                prevKey = if (page > 0) page - 1 else null,
                nextKey = if (endIndex < accounts.size) page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Account>): Int? = null
}
