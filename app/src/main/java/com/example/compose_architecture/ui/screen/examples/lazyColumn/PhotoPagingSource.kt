package com.example.compose_architecture.ui.screen.examples.lazyColumn

import androidx.paging.PagingSource
import androidx.paging.PagingState

class PhotoPagingSource(private val photoRepository: PhotoRepository) : PagingSource<Int, Photo>() {

    // 데이터를 로드하는 부분
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            // key 값이 없을 때는 1로 초기화
            val page = params.key ?: 1
            val photoResponse = photoRepository.getPhotos(page)
            // LoadResult.Page는 데이터 로드에 성공했을 때 반환되는 값
            LoadResult.Page(
                data = photoResponse,
                // null로 설정하면 반드시 다음 데이터만 로드한다
                prevKey = if (page == 1) null else page - 1,
                nextKey = page.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    /**
     * refresh 시 다시 시작할 key를 반환해주면 된다
     *
     * @param state 로드된 페이지 및 마지막으로 엑세스한 위치 등의 상태를 갖고 있음
     * @return 여기선 key가 Int이기 때문에 Int를 반환한다
     * */
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        // state.anchorPosition은 현재 사용자가 보고 있는 아이템 중 가장 가까운 아이템의 포지션을 의미
        // 즉, 사용자가 현재 보고 있는 아이템이 있는지 유무를 state.anchorPosition?를 통해 확인
        return state.anchorPosition?.let {
            // 사용자가 보고 있는 아이템 중 가장 가까운 아이템에서 앞 뒤 값의 포지션을 반환한다.
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}