package com.example.and_p_test.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Photo content(Post) data
 */
@Parcelize
data class PhotoContentDTO(
    var explain: String? = null,    // 설명
    var imgUrl: String? = null, // 이미지 url
    var uid: String? = null,    // 사용자 uid
    var userId: String? = null, // 사용자 id
    var timestamp: Long? = null,    // 게시시간
    var favoriteCount: Int = 0, // 좋아요수
    var favorites: MutableMap<String, Boolean> = mutableMapOf() // 좋아요한 사람들
): Parcelable {
    data class Comment(
        var uid: String? = null,
        var userId: String? = null,
        var comment: String? = null,
        var timestamp: Long? = null
    )
}