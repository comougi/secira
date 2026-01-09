package com.ougi.secira.authentication.presentation.mapper

import com.ougi.secira.authentication.domain.model.TokenPair
import com.ougi.secira.authentication.presentation.model.TokenPairResponse

internal fun TokenPair.toPresentation() =
    TokenPairResponse(
        accessToken = accessToken,
        refreshToken = refreshToken
    )