package ro.alexmamo.firestorepagination.Oldresults

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await
import ro.alexmamo.firestorepagination.utils.Oldresult

class FirestorePagingSource (
    private val queryOldresultsByName: Query
) : PagingSource<QuerySnapshot, Oldresult>() {
    override fun getRefreshKey(state: PagingState<QuerySnapshot, Oldresult>): QuerySnapshot? {
        return null
    }

    override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, Oldresult> {
        return try {
            val currentPage = params.key ?: queryOldresultsByName.get().await()
            val lastVisibleOldresult = currentPage.documents[currentPage.size() - 1]
            val nextPage = queryOldresultsByName.startAfter(lastVisibleOldresult).get().await()
            LoadResult.Page(
                data = currentPage.toObjects(Oldresult::class.java),
                prevKey = null,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}