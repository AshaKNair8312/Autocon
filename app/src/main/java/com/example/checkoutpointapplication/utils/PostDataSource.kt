package com.example.checkoutpointapplication.utils

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.checkoutpointapplication.dataprovider.HistoryResponse
import com.example.checkoutpointapplication.dataprovider.PrintData
import com.example.checkoutpointapplication.dataprovider.PrintResponse
import com.example.checkoutpointapplication.dataprovider.PrintTypes
import timber.log.Timber

class PostDataSource(
    private val appContext: Context,
    private val list: ArrayList<HistoryResponse>
) :
    PagingSource<Int, HistoryResponse>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HistoryResponse> {
        try {

            val currentLoadingPageKey = params.key ?: 10
            Timber.d("loadeingresponse $list")

            val responseData = mutableListOf<HistoryResponse>()
            list.forEach {
                responseData.add(
                    HistoryResponse(
                        it.INVH_DT,
                        it.INVH_TM,
                        it.INVH_SERV_MODE,
                        it.INVH_NET_TOTAL, it.INVH_POYNT_STATUS, it.INVH_CUR_CODE
                    )
                )

            }

            val prevKey = if (currentLoadingPageKey == 10) null else currentLoadingPageKey - 10

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(10)
            )

        } catch (e: Exception) {
            Timber.d("loadeingresponseError ${e.message}")
            return LoadResult.Error(e)
        }

    }


    override fun getRefreshKey(state: PagingState<Int, HistoryResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


}