package com.ougi.secira.authentication.domain.usecase

import com.ougi.secira.authentication.domain.model.JwksVariables

private const val ACCESS_PUBLIC_KEY_X_PLACEHOLDER = "{{ACCESS_PUBLIC_KEY_X}}"
private const val ACCESS_PUBLIC_KEY_Y_PLACEHOLDER = "{{ACCESS_PUBLIC_KEY_Y}}"
private const val REFRESH_PUBLIC_KEY_X_PLACEHOLDER = "{{REFRESH_PUBLIC_KEY_X}}"
private const val REFRESH_PUBLIC_KEY_Y_PLACEHOLDER = "{{REFRESH_PUBLIC_KEY_Y}}"

internal class FillJwksFileEnvVariablesUseCase(
    private val jwksVariables: JwksVariables,
) {

    fun fill(fileJson: String) =
        with(jwksVariables) {
            fileJson
                .replace(ACCESS_PUBLIC_KEY_X_PLACEHOLDER, accessPublicKeyX)
                .replace(ACCESS_PUBLIC_KEY_Y_PLACEHOLDER, accessPublicKeyY)
                .replace(REFRESH_PUBLIC_KEY_X_PLACEHOLDER, refreshPublicKeyX)
                .replace(REFRESH_PUBLIC_KEY_Y_PLACEHOLDER, refreshPublicKeyY)
        }

}